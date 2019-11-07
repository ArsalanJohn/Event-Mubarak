package com.example.fyp.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.fyp.Fragments.CustomerProfile;
import com.example.fyp.Fragments.My_Bookings;
import com.example.fyp.Fragments.ProfileUpdate;


public class CustomerPagerAdapter extends FragmentStatePagerAdapter {


    public CustomerPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                My_Bookings bookings = new My_Bookings();
                return bookings;
            case 1:
                CustomerProfile cus_profile = new CustomerProfile();
                return cus_profile;

            case 2:
                ProfileUpdate update = new ProfileUpdate();
                return update;
                default:
                    return null;



        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:

                return "Bookings";
            case 1:

                return "Profile";

            case 2:

                return "Edit";



            default:
                return null;



        }




    }

    @Override
    public int getCount() {
        return 3;
    }





}
