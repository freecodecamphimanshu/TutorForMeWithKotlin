package com.ekoebtech.tutorforme.Activity

import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.Toolbar
import com.ekoebtech.tutorforme.Adapter.TutorProfilePagerAdapter

import com.ekoebtech.tutorforme.R
import kotlinx.android.synthetic.main.activity_tutor_list.*
import kotlinx.android.synthetic.main.activity_tutor_profile.*
import kotlinx.android.synthetic.main.fragment_tutor_profile.view.*

class TutorProfileActivity : AppCompatActivity() {

    /**
     * The [androidx.viewpager.widget.PagerAdapter] that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * androidx.fragment.app.FragmentStatePagerAdapter.
     */
   /* private var mSectionsPagerAdapter: SectionsPagerAdapter? = null*/
    lateinit var adapter : TutorProfilePagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutor_profile)

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.blue_color_800)
        }


        setSupportActionBar(toolbar as Toolbar?)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }
        (toolbar as Toolbar?)?.setNavigationOnClickListener(View.OnClickListener { onBackPressed() })

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        adapter = TutorProfilePagerAdapter(supportFragmentManager)
        container.adapter = adapter
        tbl_pages.setupWithViewPager(container);


    }


  /*  override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_tutor_profile, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId

        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }
*/

   /* *//**
     * A [FragmentPagerAdapter] that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     *//*
    inner class SectionsPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return 3
        }
    }
*/
    /**
     * A placeholder fragment containing a simple view.
     */
   /* class PlaceholderFragment : Fragment() {

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val rootView = inflater.inflate(R.layout.fragment_tutor_profile, container, false)
            rootView.section_label.text = getString(R.string.section_format, arguments?.getInt(ARG_SECTION_NUMBER))
            return rootView
        }

        companion object {
            *//**
             * The fragment argument representing the section number for this
             * fragment.
             *//*
            private val ARG_SECTION_NUMBER = "section_number"

            *//**
             * Returns a new instance of this fragment for the given section
             * number.
             *//*
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }*/
}
