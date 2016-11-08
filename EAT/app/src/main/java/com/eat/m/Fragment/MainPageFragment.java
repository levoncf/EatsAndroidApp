package com.eat.m.Fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.eat.R;
import com.eat.m.Adapter.MenuAdapter;
import com.eat.m.modle.Menu;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class MainPageFragment extends Fragment implements View.OnClickListener {
    private PullToRefreshListView pullToRefreshListView;
    private List<Menu> Items=new ArrayList<>();
    private MenuAdapter topicAdapter;
    private TextView tc;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // 在这里初始化fragment的页面
        return inflater.inflate(R.layout.fragment_mainpage, container, false);

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // 由于fragment不是activity，不是oncreated，而是onActivityCreated
        intview();
    }
    private void intview() {
        tc= (TextView) getView().findViewById(R.id.shownotic);

        pullToRefreshListView=(PullToRefreshListView) getView().findViewById(R.id.PullToRefreshListView);
      //  pullToRefreshListView.setMode(PullToRefreshBase.Mode.);

       // pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        //qurydata();
        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {//刷新
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {//下拉刷新
                tc.setVisibility(View.GONE);
             qurydata();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });


    }

    private void qurydata() {


      BmobQuery<Menu> query=new BmobQuery<Menu>();



        query.order("-updatedAt");

        query.findObjects(new FindListener<Menu>() {
            @Override
            public void done(List<Menu> list, BmobException e) {
                if(e==null){
                    Toast.makeText(getActivity(), "loding success", Toast.LENGTH_SHORT).show();
                    //Toast.makeText(getActivity(), "查询成功"+list.size(), Toast.LENGTH_SHORT).show();
                    Items.clear();

                    Items = list;

                    topicAdapter=new MenuAdapter(getActivity(),Items);
                    pullToRefreshListView.setAdapter(topicAdapter);
                    pullToRefreshListView.onRefreshComplete();
                }
                else {
                    Toast.makeText(getActivity(), "loding error", Toast.LENGTH_SHORT).show();
                    pullToRefreshListView.onRefreshComplete();
                }







            }
        });


    }

    @Override
    public void onClick(View v) {

    }
}
