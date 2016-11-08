package com.eat.m.modle;

import cn.bmob.v3.BmobObject;


public class Menu extends BmobObject {
    private String menuname;
    private String username;
    private  String foodnam;
    private    String chetname;
    private   String rest;
    private double jindu;
    private double weidu;

    public double getWeidu() {
        return weidu;
    }

    public void setWeidu(double weidu) {
        this.weidu = weidu;
    }

    public double getJindu() {
        return jindu;
    }

    public void setJindu(double jindu) {
        this.jindu = jindu;
    }

    public String getFoodnam() {
        return foodnam;
    }

    public void setFoodnam(String foodnam) {
        this.foodnam = foodnam;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }

    public String getChetname() {
        return chetname;
    }

    public void setChetname(String chetname) {
        this.chetname = chetname;
    }

    public String getDescd() {
        return descd;
    }

    public void setDescd(String descd) {
        this.descd = descd;
    }

    private  String descd;
    public String getMenuname() {
        return menuname;
    }

    public void setMenuname(String menuname) {
        this.menuname = menuname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMenuurl() {
        return menuurl;
    }

    public void setMenuurl(String menuurl) {
        this.menuurl = menuurl;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserheadurl() {
        return userheadurl;
    }

    public void setUserheadurl(String userheadurl) {
        this.userheadurl = userheadurl;
    }

    private String address;
    private String menuurl;
    private String userheadurl;
}
