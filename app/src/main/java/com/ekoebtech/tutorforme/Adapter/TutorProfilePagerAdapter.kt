package com.ekoebtech.tutorforme.Adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.ekoebtech.tutorforme.Fragment.TutorProfileFragment
import com.ekoebtech.tutorforme.Fragment.TutorProfileReviewFragment

class TutorProfilePagerAdapter(fragmentManager: FragmentManager) : FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment? {
        when (position) {
            0 -> return TutorProfileFragment()
            1 -> return TutorProfileReviewFragment()
        }
        return null
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {

            0 -> return "Profile"
            1 -> return "Review"
            else -> return null
        }
    }
}