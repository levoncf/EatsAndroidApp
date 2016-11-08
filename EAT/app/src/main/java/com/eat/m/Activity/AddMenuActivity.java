package com.eat.m.Activity;

import android.Manifest;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eat.R;
import com.eat.m.modle.Menu;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;


public class AddMenuActivity extends AppCompatActivity {
    private EditText userName;
    private EditText passWord;
    private Button login;
    private EditText name;
    private EditText address;
    private EditText desc;
    private EditText chef;
    ImageView back;//
    ImageView sure;
    ImageView head;
    TextView addtitle;
    String uirl;
    ProgressDialog dialog2;
    double latitude=0.0;
    double longitude=0.0;
    private static int RESULT_LOAD_IMAGE = 1;
    RelativeLayout headerbar_two;

    LocationManager lm;


    private Dialog dialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addmenu);
        intview();
        intdata();

    }

    private void intdata() {
        lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //从GPS获取最近的定位信息
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for Activity#requestPermissions for more details.
                return;
            }
        }
        Location lc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);


        updateView(lc);


        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 8, new LocationListener() {

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProviderEnabled(String provider) {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    public void requestPermissions(@NonNull String[] permissions, int requestCode)
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for Activity#requestPermissions for more details.
                        return;
                    }
                }
                updateView(lm.getLastKnownLocation(provider));
            }

            @Override
            public void onProviderDisabled(String provider) {
                // TODO Auto-generated method stub
                updateView(null);
            }

            @Override
            public void onLocationChanged(Location location) {

                updateView(location);
            }
        });

    }
    public void updateView(Location newLocation){
        if (newLocation != null) {
            StringBuilder sb = new StringBuilder();


            latitude=newLocation.getLatitude();
            System.out.println(latitude + "--------------");
        longitude=newLocation.getLongitude();


        } else {

        }
    }
    private void intview() {
        back = (ImageView) findViewById(R.id.headbar_left_imagebutton_two);
        addtitle = (TextView) findViewById(R.id.headbar_title_two);
        headerbar_two = (RelativeLayout) findViewById(R.id.default_headbar_two);
        sure = (ImageView) findViewById(R.id.headbar_right_imagebutton_two);
        head = (ImageView) findViewById(R.id.addmenupic);
        headerbar_two.setBackgroundColor(getResources().getColor(R.color.yellow));
        sure.setVisibility(View.VISIBLE);
        sure.setImageResource(R.mipmap.sure);
        sure.setBackground(getResources().getDrawable(R.drawable.button_default_bg));
        back.setVisibility(View.VISIBLE);
        back.setImageResource(R.mipmap.back);
        back.setBackground(getResources().getDrawable(R.drawable.button_default_bg));
        addtitle.setTextColor(getResources().getColor(R.color.white));


        name = (EditText) findViewById(R.id.foodname);
        address = (EditText) findViewById(R.id.resturantname);
        desc = (EditText) findViewById(R.id.description);
        chef = (EditText) findViewById(R.id.chefname);
        chef.setText(MyApplication.MYUSER.getUsername());
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");//
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
            }
        });




        sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                momit();


            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void momit() {
        if (MyApplication.MYUSER.getHeadurl() == "") {
            Toast.makeText(getApplicationContext(), "you should complete your information", Toast.LENGTH_SHORT).show();

        } else {



            String foodnam = name.getText().toString();
            String chetname = chef.getText().toString();
            String rest = address.getText().toString();
            String descd = desc.getText().toString();
            String headivurl = MyApplication.MYUSER.getHeadurl();
            Menu menu = new Menu();
            menu.setAddress(rest);
            menu.setMenuname(foodnam);
            menu.setChetname(chetname);
            menu.setDescd(descd);
            menu.setJindu(longitude);
            menu.setWeidu(latitude);
            menu.setUserheadurl(headivurl);
            menu.setMenuurl(headpicurl);

            menu.save(new SaveListener<String>() {
                @Override
                public void done(String s, BmobException e) {
                    if (e == null) {
                        Toast.makeText(AddMenuActivity.this, "CREAT SUCCESS", Toast.LENGTH_SHORT).show();
                        finish();

                    } else {

                        Toast.makeText(AddMenuActivity.this, "CREAT ERROR", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE) {
            Uri uri = data.getData();
            uirl = getRealPathFromUri(this, uri);
            dialog = creatDialog(this, "uploading picture...");
            dialog.show();
            updata(uirl);
            //to do fiheadnd the path of pi  
            head.setImageURI(uri);
        }

    }

    public static Dialog creatDialog(final Context context, final String text) {
        final Dialog dialog = new Dialog(context, R.style.new_circle_progress);
        dialog.setContentView(R.layout.layout_progressbar);
        dialog.getWindow().getAttributes().gravity = Gravity.CENTER;
        TextView textView = (TextView) dialog.findViewById(R.id.progress_msg);
        textView.setText(text);
        return dialog;
    }

    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private String headpicurl;

    private void updata(String uri) {

        final BmobFile bmobFile = new BmobFile(new File(uri));
        System.out.println(uri);
        bmobFile.uploadblock(new UploadFileListener() {

            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(getApplicationContext(), "success", Toast.LENGTH_SHORT).show();
                    headpicurl = bmobFile.getFileUrl();
                    dialog.dismiss();
                } else {
                    dialog.dismiss();
                    Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onProgress(Integer value) {

            }
        });
    }






    }






