package com.eat.m.modle;

import cn.bmob.v3.BmobUser;


public class User extends BmobUser {
    private String headurl="";
    private String phone;
    private String Resturant;

    public String getResturant() {
        return Resturant;
    }

    public void setResturant(String resturant) {
        Resturant = resturant;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHeadurl() {
        return headurl;
    }

    public void setHeadurl(String headurl) {
        this.headurl = headurl;
    }
}
