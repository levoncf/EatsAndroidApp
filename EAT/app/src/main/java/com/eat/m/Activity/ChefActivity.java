package com.eat.m.Activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eat.R;
import com.eat.m.modle.User;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;


public class ChefActivity extends AppCompatActivity {
    ImageView back;//B

    private List<User> listitem=new ArrayList<User>();
    TextView addtitle;

    RelativeLayout headerbar_two;
    private ImageView infoheadiv;
    private TextView infoheadname;
    private TextView infoheadphone;
    private TextView infores;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        setContentView(R.layout.activity_chef);
        
        intView();

    }

    private void intView() {
        init();
        back= (ImageView) findViewById(R.id.headbar_left_imagebutton_two);
        addtitle= (TextView) findViewById(R.id.headbar_title_two);

        headerbar_two= (RelativeLayout) findViewById(R.id.default_headbar_two);
        headerbar_two.setBackgroundColor(getResources().getColor(R.color.light_blue));
        back.setVisibility(View.VISIBLE);
        back.setImageResource(R.mipmap.back);
        back.setBackground(getResources().getDrawable(R.drawable.button_default_bg));
        addtitle.setTextColor(getResources().getColor(R.color.white));
        addtitle.setText("Chef Information");



        infoheadiv= (ImageView) findViewById(R.id.chefimageinfo);
        infoheadname= (TextView) findViewById(R.id.chefnameinfo);
        infoheadphone= (TextView) findViewById(R.id.chefphoneinfo);
        infores=(TextView) findViewById(R.id.chefaddressinfo);

        qure();


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    private void init() {

            imageLoader.init(ImageLoaderConfiguration.createDefault(this));
            //ImageLoaderConfiguration imageLoaderConfiguration=new ;
            options = new DisplayImageOptions.Builder()
                    .showStubImage(R.mipmap.loadingpic)
                    .showImageForEmptyUri(R.mipmap.lodingere)
                    .showImageOnFail(R.mipmap.lodingere).cacheInMemory()
                    .cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY)
                    .bitmapConfig(Bitmap.Config.RGB_565)
                    .displayer(new FadeInBitmapDisplayer(300)).build();

    }

    private void qure() {
        String username=getIntent().getStringExtra("username");
        System.out.println("----------+"+username);
        BmobQuery<User> query = new BmobQuery<User>();
//查询playerName叫“比目”的数据
        query.addWhereEqualTo("username", username);
//返回50条数据，如果不加上这条语句，默认返回10条数据

//执行查询方法
       query.findObjects(new FindListener<User>() {
           @Override
           public void done(List<User> list, BmobException e) {
               if (e == null) {
                   Toast.makeText(getApplicationContext(), "Success", Toast.LENGTH_SHORT).show();
                   listitem = list;
                    System.out.println("----------+"+listitem.size());
                   freshview();
               } else {
                   Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();System.out.println("----dddd------+" );
               }
           }
       });

    }

    private void freshview() {
        infoheadname.setText(listitem.get(0).getUsername());
        infoheadphone.setText(listitem.get(0).getPhone());
        infores.setText(listitem.get(0).getResturant());
        imageLoader.displayImage(listitem.get(0).getHeadurl(), infoheadiv, options);
    }
}
