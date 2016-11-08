package com.eat.m.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.eat.R;
import com.eat.m.modle.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class LoginActivity extends AppCompatActivity {
    private EditText userName;
    private EditText passWord;
    private Button  login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        intview();

    }

    private void intview() {
        userName= (EditText) findViewById(R.id.login_username);
        passWord= (EditText) findViewById(R.id.login_password);
        login= (Button) findViewById(R.id.Login_bt);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent s = new Intent(getApplicationContext(), D.class);
              //  startActivity(s);
                 commit();
            }
        });
    }

    private void commit() {
        String user=userName.getText().toString().trim();
        String passnumber=passWord.getText().toString().trim();



            User bu = new User();
            bu.setUsername(user);
            bu.setPassword(passnumber);
            bu.login(new SaveListener<User>() {
                @Override
                public void done(User s, BmobException e) {

                    if(e==null){

                        MyApplication.MYUSER=s;
                        System.out.println(MyApplication.MYUSER.getUsername());
                        Intent intent=new Intent(getApplicationContext(),MainPageActivity.class);
                        startActivity(intent);finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"login error",Toast.LENGTH_SHORT).show();
                    }
                }
            });




        }



}
