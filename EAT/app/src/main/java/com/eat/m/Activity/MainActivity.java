package com.eat.m.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.eat.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;

import cn.bmob.v3.Bmob;

public class MainActivity extends AppCompatActivity {
    private Button login;
    private Button regist;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bmob.initialize(this, "1349d487b41d7756413bfba537232f39");
    //    Bmob.initialize(this, "1349d487b41d7756413bfba537232f39");
        setContentView(R.layout.activity_main);
        initdata();
        intView();

    }

    private void initdata() {
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPriority(Thread.NORM_PRIORITY - 2)
                .denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator())
                .tasksProcessingOrder(QueueProcessingType.LIFO)

                .build();
        ImageLoader.getInstance().init(config);
    }

    private void intView() {
        login= (Button) findViewById(R.id.login);
        regist= (Button) findViewById(R.id.Rg);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplication(),LoginActivity.class);
                startActivity(i);finish();

            }
        });

        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplication(),RegistActivity.class);
                startActivity(i);

            }
        });
    }


}
