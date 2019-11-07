package com.example.fyp.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.fyp.Fragments.DealsFragment;
import com.example.fyp.Fragments.DetailsFragment;
import com.example.fyp.Fragments.My_Bookings;
import com.example.fyp.Fragments.My_Orders;
import com.example.fyp.Fragments.PackagesFragment;
import com.example.fyp.Fragments.VendorDealFragment;
import com.example.fyp.Fragments.VendorPackageFragment;


public class VendorViewPagerAdapter extends FragmentStatePagerAdapter {


    public VendorViewPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                VendorDealFragment deals = new VendorDealFragment();
                return deals;
            case 1:
                My_Orders orders = new My_Orders();
                return orders;

            case 2:
                VendorPackageFragment packages = new VendorPackageFragment();
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

                return "Orders";

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
