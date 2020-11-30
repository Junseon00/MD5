package com.example.myapplication;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class VPAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> items;
    private ArrayList<String> itext = new ArrayList<String>();

    @Override
    public Fragment getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getCount() {
        return items.size();
    }
//생성자?
    public  VPAdapter(FragmentManager fm){
        super(fm);
        items=new ArrayList<Fragment>();
        items.add(new Frag_follower());
        //items.add(new Frag_following());
        items.add(new Frag_friendRequest());
            // (주현) 돌보미 페이지로 가서 첫번째 탭은 돌보미 목록, 두번째 탭은 돌보미 신청 목록으로 띄우면 좋을 것 같아서 수정했습니다..
            // (주현) 새로 만든 돌보미 창은 뭔가 건드리면 안될거 같아서 일단 안 넣어둠..

        itext.add("돌보미 목록");
        itext.add("돌보미 신청");

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return itext.get(position);
    }
}
