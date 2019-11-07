package com.example.fyp.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.fyp.Fragments.AddCompany;
import com.example.fyp.Fragments.AddOrders;
import com.example.fyp.Fragments.AddPackage;
import com.example.fyp.Fragments.DealsFragment;
import com.example.fyp.Fragments.My_Bookings;
import com.example.fyp.Fragments.PackagesFragment;


public class VendorEditPagerAdapter extends FragmentStatePagerAdapter {


    public VendorEditPagerAdapter(FragmentManager fm) {
        super(fm);

    }

    @Override
    public Fragment getItem(int i) {

        switch (i){
            case 0:
                AddPackage pkg = new AddPackage();
                return pkg;
            case 1:
                AddOrders orders = new AddOrders();
                return orders;

            case 2:
                AddCompany addCompany = new AddCompany();
                return addCompany;
                default:
                    return null;



        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        switch (position){
            case 0:

                return "Packages/Deals";
            case 1:

                return "Add Orders";

            case 2:

                return "Add Company";



            default:
                return null;



        }




    }

    @Override
    public int getCount() {
        return 3;
    }





}
