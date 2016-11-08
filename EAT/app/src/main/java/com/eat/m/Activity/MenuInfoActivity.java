package com.eat.m.Activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.eat.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;


public class MenuInfoActivity extends AppCompatActivity {
    private ImageView infomenuiv;
    private ImageView infoheadiv;
    private TextView infoheadname;
    private TextView infoheadphone;
    private TextView infoadd;
private     TextView infodesc;
    ImageView back;//B


    TextView addtitle;
    TextView far;
    RelativeLayout headerbar_two;

    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;
    LocationManager lm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menuinfo);

        intview();

    }
    Intent intent;
    private void intview() {    intent=getIntent();

        back= (ImageView) findViewById(R.id.headbar_left_imagebutton_two);
        addtitle= (TextView) findViewById(R.id.headbar_title_two);
        far=(TextView)findViewById(R.id.km);
        headerbar_two= (RelativeLayout) findViewById(R.id.default_headbar_two);
        headerbar_two.setBackgroundColor(getResources().getColor(R.color.light_blue));
        back.setVisibility(View.VISIBLE);
        back.setImageResource(R.mipmap.back);
        back.setBackground(getResources().getDrawable(R.drawable.button_default_bg));
        addtitle.setTextColor(getResources().getColor(R.color.white));
        addtitle.setText("Food Information");

        infodesc= (TextView) findViewById(R.id.descinfo);
        infomenuiv= (ImageView) findViewById(R.id.info_menupic);
        infoheadiv= (ImageView) findViewById(R.id.info_headpic);
        infoheadname= (TextView) findViewById(R.id.info_headname);
        infoheadphone= (TextView) findViewById(R.id.info_headphone);
        infoadd=(TextView) findViewById(R.id.info_address);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });

        imageLoader.init(ImageLoaderConfiguration.createDefault(this));
        //ImageLoaderConfiguration imageLoaderConfiguration=new ;
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.loadingpic)
                .showImageForEmptyUri(R.mipmap.lodingere)
                .showImageOnFail(R.mipmap.lodingere).cacheInMemory()
                .cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(300)).build();
        init();
        intdata();

    }

    private void init() {
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

        //更新显示定位信息
        updateView(lc);

        //设置每3秒 获取一次GPS定位信息
        lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 3000, 8, new LocationListener() {

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onProviderEnabled(String provider) {
                // 当GPS LocationProvider可用时，更新定位
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


    private void intdata() {
      //  intent = getIntent();
        infodesc.setText(intent.getStringExtra("desc"));
        infoheadname.setText(intent.getStringExtra("username"));
      infoheadphone.setText(intent.getStringExtra(""));
    //    System.out.println(jindu + "--------------" + intent.getDoubleExtra("jingdu", 0.0) + "--");
      //  System.out.println(weidu + "-------------" + intent.getDoubleExtra("weidu", 0.0) + "--");

        infoadd.setText(intent.getStringExtra("address"));
        imageLoader.displayImage(intent.getStringExtra("headurl"), infoheadiv, options);
        imageLoader.displayImage(intent.getStringExtra("menuurl"), infomenuiv, options);

    }
    private double jindu;
    private double weidu;
    public void updateView(Location newLocation){
        if (newLocation != null) {



            System.out.println(jindu + "--------------" + intent.getDoubleExtra("jingdu", 0.0) + "--");
            System.out.println(weidu + "-------------" + intent.getDoubleExtra("weidu", 0.0) + "--");
            weidu=newLocation.getLatitude();  jindu=newLocation.getLongitude();
            far.setText("" + getDistance(jindu, weidu, intent.getDoubleExtra("jingdu", 0.0), intent.getDoubleExtra("weidu", 0.0)) + "m");

        } else {

        }
    }
    public static double getDistance(double longitude1, double latitude1,
                                     double longitude2, double latitude2) {
        double Lat1 = rad(latitude1);
        double Lat2 = rad(latitude2);
        double a = Lat1 - Lat2;
        double b = rad(longitude1) - rad(longitude2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2)
                + Math.cos(Lat1) * Math.cos(Lat2)
                * Math.pow(Math.sin(b / 2), 2)));
        s = s * 6378137.0;
        s = Math.round(s * 10000) / 10000;
        return s;
    }
    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }
}
