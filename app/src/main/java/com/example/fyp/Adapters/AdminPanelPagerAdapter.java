package com.example.fyp.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.fyp.Fragments.BookingRequest;
import com.example.fyp.Fragments.DealsFragment;
import com.example.fyp.Fragments.My_Bookings;
import com.example.fyp.Fragments.PackageRequest;
import com.example.fyp.Fragments.PackagesFragment;
import com.example.fyp.Fragments.UserList;


public class AdminPanelPagerAdapter extends FragmentStatePagerAdapter {


    public AdminPanelPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                UserList userList = new UserList();
                return userList;
            case 1:
                BookingRequest bookingRequest = new BookingRequest();
                return bookingRequest;

            case 2:
                PackageRequest packages = new PackageRequest();
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

                return "Users";
            case 1:

                return "Bookings";

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
