package com.eat.m.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.eat.R;
import com.eat.m.modle.User;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;


public class RegistActivity extends AppCompatActivity {
    private EditText userName;
    private EditText passWord;
    private EditText againpassWord;
    private Button regist;
    private Button back;
    private  Dialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        intview();

    }

    private void intview() {
        back= (Button) findViewById(R.id.returnbt);
        userName= (EditText) findViewById(R.id.rg_username);
        passWord= (EditText) findViewById(R.id.rg_password);
        againpassWord= (EditText) findViewById(R.id.rg_password_again);
        regist= (Button) findViewById(R.id.regist_bt);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                commit();
            }
        });
    }

    private void commit() {
        String user=userName.getText().toString().trim();
        String passnumber=passWord.getText().toString().trim();
        String againpassnumber=againpassWord.getText().toString().trim();
        if(!passnumber.equals(againpassnumber)){System.out.println("-------------------1");
            Toast.makeText(getApplicationContext(),"the password is not correct",Toast.LENGTH_SHORT).show();
        }
        else if(user.equals("")||passnumber.equals("")||againpassnumber.equals("")){
            Toast.makeText(getApplicationContext(),"You should input the name or password",Toast.LENGTH_SHORT).show();
        }
        else{System.out.println("-------------------3");
            User bu = new User();
            bu.setUsername(user);
            bu.setPassword(passnumber);
            bu.signUp(new SaveListener<User>() {
                @Override
                public void done(User s, BmobException e) {

                    if(e==null){
                        MyApplication.MYUSER=s;
                        System.out.println( MyApplication.MYUSER.getObjectId()+"----------------");
                        Intent intent=new Intent(getApplicationContext(),MainPageActivity.class);
                        startActivity(intent);finish();
                    }else{
                        Toast.makeText(getApplicationContext(),"regist error",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }


    public static Dialog creatDialog(final Context context,final String text){
        final Dialog dialog = new Dialog(context, R.style.new_circle_progress);
        dialog.setContentView(R.layout.layout_progressbar);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        TextView textView = (TextView)dialog.findViewById(R.id.progress_msg);
        textView.setText(text);
        return dialog;
    }
}
