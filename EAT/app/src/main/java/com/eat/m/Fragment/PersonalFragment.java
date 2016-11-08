package com.eat.m.Fragment;

import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.eat.R;
import com.eat.m.Activity.MyApplication;
import com.eat.m.modle.User;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.io.File;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;
import cn.bmob.v3.listener.UploadFileListener;


public class PersonalFragment extends Fragment implements View.OnClickListener{
    private TextView name;
    private EditText address;
    private EditText desc;
    private EditText phone;
    String uirl;
    Dialog dialog;  ImageView head;
    private static int RESULT_LOAD_IMAGE = 1;
    RelativeLayout headerbar_two;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_personnal, container, false);

    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        intview();
    }
    TextView sure;
    private void intview() {
        sure= (TextView) getView().findViewById(R.id.suree);
                name= (TextView) getView().findViewById(R.id.username);
        address= (EditText)  getView().findViewById(R.id.resturantnamett);
        head=(ImageView) getView().findViewById(R.id.userheadpic);

        phone= (EditText)  getView().findViewById(R.id.telephone);

        name.setText(MyApplication.MYUSER.getUsername());
        sure.setOnClickListener(this);
        head.setOnClickListener(this);
        qurydata();
    }
    String phonenumber;
    String addressC;
    private void commit() {
        System.out.println(MyApplication.MYUSER.getObjectId()+"-----------------");

        addressC=address.getText().toString();
        phonenumber =phone.getText().toString();
        User user = new User();
        System.out.println("------------"+headpicurl);
        user.setHeadurl(headpicurl);
        user.setPhone(phonenumber);
        user.setResturant(address.getText().toString());



        user.update(MyApplication.MYUSER.getObjectId(), new UpdateListener() {

            @Override
            public void done(BmobException e) {
                if (e == null) {
                    Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
                   // finish();
                } else {
                    Toast.makeText(getActivity(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==RESULT_LOAD_IMAGE){
            Uri uri =data.getData();
            uirl =getRealPathFromUri(getActivity(),uri);
            //uirl=uri.toString();

            //to do fiheadnd the path of pi  
            head.setImageURI(uri);
            dialog=creatDialog(getActivity(),"uploading picture...");
            dialog.show();
            updata(uirl);
        }

    }
    private  String headpicurl;
    private void updata(String uri) {

        final BmobFile bmobFile = new BmobFile(new File(uri));
        System.out.println(uri);
        bmobFile.uploadblock(new UploadFileListener() {

            @Override
            public void done(BmobException e) {
                if(e==null){
                    MyApplication.MYUSER.setPhone(phonenumber);

                    Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();
                    headpicurl=bmobFile.getFileUrl();
                    MyApplication.MYUSER.setHeadurl(headpicurl);
                    dialog.dismiss();
                }else{ dialog.dismiss();
                    Toast.makeText(getActivity(), e.toString(), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onProgress(Integer value) {

            }
        });
    }
    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
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
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.suree:
                System.out.println(MyApplication.MYUSER.getObjectId()+"-----------------");
                commit();
                break;
            case R.id.userheadpic:
                System.out.println("csdfadsf-----------------");
                Intent intent =new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");//相片类型  
                startActivityForResult(intent, RESULT_LOAD_IMAGE);
                break;
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

    private void qurydata() {
        if(MyApplication.MYUSER.getHeadurl()!=null){
            freshview();
        }
        if(MyApplication.MYUSER.getPhone()!=null)
        {
            phone.setText(MyApplication.MYUSER.getPhone());
        }
        if(MyApplication.MYUSER.getResturant()!=null){
            address.setText(MyApplication.MYUSER.getResturant());
        }

    }




    DisplayImageOptions options;
    private void freshview() {

        ImageLoader imageLoader = ImageLoader.getInstance();

        imageLoader.init(ImageLoaderConfiguration.createDefault(getActivity()));
        //ImageLoaderConfiguration imageLoaderConfiguration=new ;
        options = new DisplayImageOptions.Builder()
                .showStubImage(R.mipmap.loadingpic)
                .showImageForEmptyUri(R.mipmap.lodingere)
                .showImageOnFail(R.mipmap.lodingere).cacheInMemory()
                .cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .displayer(new FadeInBitmapDisplayer(300)).build();
        imageLoader.displayImage(MyApplication.MYUSER.getHeadurl(), head, options);
    }
}
