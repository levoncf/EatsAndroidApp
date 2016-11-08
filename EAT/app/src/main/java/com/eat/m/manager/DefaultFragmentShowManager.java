package com.eat.m.manager;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.annotation.NonNull;

import java.util.Collection;
import java.util.Iterator;


public class DefaultFragmentShowManager extends FragmentShowManager {

    public DefaultFragmentShowManager(Activity activity, int contentId) {
        super(activity, contentId);
    }

    @Override
    public void addFragment(@NonNull Fragment fragment) {
        fragmentList.add(fragment);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(contentId, fragment);
        fragmentTransaction.hide(fragment);
        fragmentTransaction.show(fragmentList.get(showIndex));
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void addAllFragment(@NonNull Collection<Fragment> c) {
        fragmentList.addAll(c);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Iterator<Fragment> iter = c.iterator();
        for (;iter.hasNext();) {
            Fragment f = iter.next();
            fragmentTransaction.add(contentId, f);
            fragmentTransaction.hide(f);
        }
        fragmentTransaction.show(fragmentList.get(showIndex));
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    public void setCurrentItem(int index) {
        if (index >= fragmentList.size()) {
            throw new IllegalArgumentException("index.");
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.hide(fragmentList.get(showIndex));
        fragmentTransaction.show(fragmentList.get(index));
        fragmentTransaction.commitAllowingStateLoss();
        showIndex = index;
    }

    @Override
    public int getCurrentItem() {
        return showIndex;
    }
}
