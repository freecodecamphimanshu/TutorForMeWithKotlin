package com.ekoebtech.tutorforme.Activity

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar
import com.ekoebtech.tutorforme.Adapter.RegisterStepIndicatorAdapter
import com.ekoebtech.tutorforme.Fragment.*
import com.ekoebtech.tutorforme.R

import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.content_register.*

class RegisterActivity : AppCompatActivity(),RegisterFragmentFirst.OnFragmentInteractionListener,
    RegisterSecondFragment.OnFragmentInteractionListener, RegisterThirdFragment.OnFragmentInteractionListener,
    RegisterFourthFragment.OnFragmentInteractionListener,RegisterFivthFragment.OnFragmentInteractionListener {

    override fun onFragmentInteraction(pos: Int) {

        Log.e("Checking The Postion", pos.toString())
        step_indicator.setCurrentStepPosition(pos)
        viewPager.setCurrentItem(pos)
    }

    lateinit var adapter : RegisterStepIndicatorAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.blue_color_800)
        }

        setContentView(R.layout.activity_register)
        setSupportActionBar(toolbar as Toolbar?)

        supportActionBar?.setDisplayShowTitleEnabled(false)

        adapter = RegisterStepIndicatorAdapter(supportFragmentManager)
        viewPager.setAdapter(adapter)
        step_indicator.setupWithViewPager(viewPager)
        // Enable | Disable click on step number
        step_indicator.setClickable(false);

    }

}
