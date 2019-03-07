package com.ekoebtech.tutorforme.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ekoebtech.tutorforme.Fragment.*

class RegisterStepIndicatorAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment? {

        var fragment : Fragment = RegisterFragmentFirst.newInstance()
        if (position == 1)
        {
            fragment = RegisterSecondFragment.newInstance()
        }
        else if (position == 2)
        {
            fragment = RegisterThirdFragment.newInstance()
        }else if (position == 3)
        {
            fragment = RegisterFourthFragment.newInstance()
        }else if (position == 4)
        {
            fragment = RegisterFivthFragment.newInstance()
        }
        return  fragment
    }

    override fun getCount(): Int {
        return 5
    }
}