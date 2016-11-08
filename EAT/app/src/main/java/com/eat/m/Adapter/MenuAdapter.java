package com.eat.m.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eat.R;
import com.eat.m.Activity.ChefActivity;
import com.eat.m.Activity.MenuInfoActivity;
import com.eat.m.modle.Menu;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.List;


public class MenuAdapter extends BaseAdapter {
    Context context;
    List<Menu> topicList;
    public static ImageLoader imageLoader = ImageLoader.getInstance();
    public DisplayImageOptions options;
    private LayoutInflater mInflater = null;
    public MenuAdapter(Context context, List<Menu> list){
        this.context=context;
        topicList=list;
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        //ImageLoaderConfiguration imageLoaderConfiguration=new ;
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.loadingpic)
                .showImageForEmptyUri(R.mipmap.lodingere)
                .showImageOnFail(R.mipmap.lodingere).cacheInMemory()
                .cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(300)).build();
        mInflater = LayoutInflater.from(context);
    }
    public void setList(List<Menu> list){
        topicList=list;
    }
    @Override
    public int getCount() {
        return topicList.size();
    }

    @Override
    public Object getItem(int position) {
        return topicList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
      final Menu topic=topicList.get(position);

        if (convertView == null) {
            convertView = mInflater.inflate(
                    R.layout.menuitem_listview, null);
            viewHolder=new ViewHolder();
            viewHolder.name= (TextView) convertView.findViewById(R.id.item_name);
            viewHolder.address= (TextView) convertView.findViewById(R.id.item_adress);
            viewHolder.menuiv= (ImageView) convertView.findViewById(R.id.item_menu_jv);
            viewHolder.headiv=(ImageView) convertView.findViewById(R.id.item_head);

            convertView.setTag(viewHolder);
        }
        else{
            viewHolder = (ViewHolder)convertView.getTag();
        }
        viewHolder.headiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii=new Intent(context, ChefActivity.class);
                ii.putExtra("username",topic.getChetname());
                context.startActivity(ii);
            }
        });
        viewHolder.menuiv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, MenuInfoActivity.class);
                intent.putExtra("objid", topic.getObjectId());
                    System.out.println("ccccccccccccc" + topic.getJindu());
                System.out.println("ccccccccccccc"+topic.getWeidu());
                intent.putExtra("jingdu", topic.getJindu());
                intent.putExtra("weidu", topic.getWeidu());
                intent.putExtra("menuurl", topic.getMenuurl());
                intent.putExtra("headurl", topic.getUserheadurl());
                intent.putExtra("username", topic.getChetname());
                intent.putExtra("address", topic.getAddress());
                intent.putExtra("desc", topic.getDescd());

                intent.putExtra("foodname", topic.getFoodnam());
                context.startActivity(intent);
            }
        });
        viewHolder.name.setText("Food:"+topic.getMenuname());
        viewHolder.address.setText("Address:"+topic.getAddress()+" "+"ChefName:"+topic.getChetname());

        //viewHolder.headiv.setImageResource(topic.getUser.getimsge);//设置头像
        imageLoader.displayImage(topic.getMenuurl(), viewHolder.menuiv, options);
        imageLoader.displayImage(topic.getUserheadurl(),viewHolder.headiv, options);
        return convertView;
    }



    public class ViewHolder{
        TextView name;

        TextView address;
        ImageView menuiv;
        ImageView headiv;

    }
}
