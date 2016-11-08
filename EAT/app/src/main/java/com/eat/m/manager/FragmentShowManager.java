package com.eat.m.manager;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public abstract class FragmentShowManager {
    protected List<Fragment> fragmentList;
    protected Activity activity;
    protected int contentId;
    protected FragmentManager fragmentManager;
    protected int showIndex;

    public FragmentShowManager(Activity activity, int contentId) {
        fragmentList = new ArrayList<>();
        this.activity = activity;
        this.contentId = contentId;
        fragmentManager = activity.getFragmentManager();
        showIndex = 0;
    }

    public abstract void addFragment(Fragment fragment);
    public abstract void addAllFragment(Collection<Fragment> c);
    public abstract void setCurrentItem(int index);
    public abstract int getCurrentItem();
}
