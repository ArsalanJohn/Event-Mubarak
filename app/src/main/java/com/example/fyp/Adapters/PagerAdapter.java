package com.example.fyp.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.fyp.Fragments.DealsFragment;
import com.example.fyp.Fragments.DetailsFragment;
import com.example.fyp.Fragments.PackagesFragment;


public class PagerAdapter extends FragmentStatePagerAdapter {


    public PagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                DealsFragment deals = new DealsFragment();
                return deals;
            case 1:
                DetailsFragment details = new DetailsFragment();
                return details;

            case 2:
                PackagesFragment packages = new PackagesFragment();
                return packages;
                default:
                    return null;



        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:

                return "Deals";
            case 1:

                return "Details";

            case 2:

                return "Packages";



            default:
                return null;



        }




    }

    @Override
    public int getCount() {
        return 3;
    }





}
