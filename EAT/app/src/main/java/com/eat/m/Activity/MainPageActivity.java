package com.eat.m.Activity;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.eat.R;
import com.eat.m.Fragment.MainPageFragment;
import com.eat.m.Fragment.PersonalFragment;
import com.eat.m.manager.DefaultFragmentShowManager;
import com.eat.m.manager.FragmentShowManager;

import java.util.ArrayList;
import java.util.List;

public class MainPageActivity extends AppCompatActivity{
    private Button login;
    private Button regist;
    private FragmentShowManager fragmentShowManager;
    private LinearLayout mianpege_ll;//
    private ImageView add;
    private LinearLayout shequ_ll;//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);

        intView();

    }

    private void intView() {
        mianpege_ll= (LinearLayout) findViewById(R.id.mianpagell);
        shequ_ll= (LinearLayout) findViewById(R.id.shequll);
        add= (ImageView) findViewById(R.id.add);
        fragmentShowManager = new DefaultFragmentShowManager(this, R.id.fragment_content);
        List<Fragment> list = new ArrayList<>();
        list.add(new MainPageFragment());
        list.add(new PersonalFragment());

        fragmentShowManager.addAllFragment(list);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(),AddMenuActivity.class);
                startActivity(intent);
            }
        });
        mianpege_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("ddddd");
                setSelection(fragmentShowManager.getCurrentItem(), false);
                fragmentShowManager.setCurrentItem(0);
                setSelection(fragmentShowManager.getCurrentItem(), true);
            }
        });
        shequ_ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("cccc");
                setSelection(fragmentShowManager.getCurrentItem(), false);
                fragmentShowManager.setCurrentItem(1);
                setSelection(fragmentShowManager.getCurrentItem(), true);
            }
        });
    }

    private void setSelection(int index, boolean isSelected) {
        switch (index) {
            case 0:
                mianpege_ll.setSelected(isSelected);

                break;
            case 1:
                shequ_ll.setSelected(isSelected);

                break;

        }
    }

}
