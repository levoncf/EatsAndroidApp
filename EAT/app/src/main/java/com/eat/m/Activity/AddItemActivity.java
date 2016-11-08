package com.eat.m.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eat.R;


public class AddItemActivity extends AppCompatActivity {

    ImageView back;//


    TextView addtitle;

    RelativeLayout headerbar_two;
    TextView addone;
    TextView addtwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      
        setContentView(R.layout.activity_additemactivity);
        
        intView();

    }

    private void intView() {

        back= (ImageView) findViewById(R.id.headbar_left_imagebutton_two);
        addtitle= (TextView) findViewById(R.id.headbar_title_two);
        addone=(TextView) findViewById(R.id.addmenu);
        addtwo=(TextView) findViewById(R.id.addchef);
        headerbar_two= (RelativeLayout) findViewById(R.id.default_headbar_two);
        headerbar_two.setBackgroundColor(getResources().getColor(R.color.yellow));
        back.setVisibility(View.VISIBLE);
        back.setImageResource(R.mipmap.back);
        back.setBackground(getResources().getDrawable(R.drawable.button_default_bg));
        addtitle.setTextColor(getResources().getColor(R.color.white));
        addone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddMenuActivity.class);
                startActivity(intent);
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


}
