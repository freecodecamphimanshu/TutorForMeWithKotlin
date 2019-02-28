package com.ekoebtech.tutorforme.Activity

import android.app.ProgressDialog
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.StringRequest
import com.ekoebtech.tutorforme.Adapter.TutorListActivityAdapter
import com.ekoebtech.tutorforme.Model.ClassListModel
import com.ekoebtech.tutorforme.Model.TutorListActivityModel
import com.ekoebtech.tutorforme.R
import com.ekoebtech.tutorforme.Source.AppController
import com.ekoebtech.tutorforme.Source.SettingConstant
import kotlinx.android.synthetic.main.activity_tutor_list.*
import kotlinx.android.synthetic.main.content_tutor_list.*
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList




class TutorListActivity : AppCompatActivity() {

    lateinit var cityAdapter: ArrayAdapter<String>
    lateinit var classAdapter: ArrayAdapter<ClassListModel>
    lateinit var subjectAdapter: ArrayAdapter<String>
    lateinit var adapter : TutorListActivityAdapter
    var tutorList  = ArrayList<TutorListActivityModel>()
    var cityList = ArrayList<String>()
    var classList = ArrayList<ClassListModel>()
    var subjectList = ArrayList<String>()
    var searchListUrl = SettingConstant.BASEURL_SECOND + "homeapi/SearchList"
    lateinit var subjectName : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutor_list)

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            val window = this.window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.statusBarColor = this.resources.getColor(R.color.blue_color_800)
        }

        setSupportActionBar(_tutor_toolbar as Toolbar?)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        if (supportActionBar != null) {
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
            supportActionBar!!.setDisplayShowHomeEnabled(true)
        }
        (_tutor_toolbar as Toolbar?)?.setNavigationOnClickListener(View.OnClickListener { onBackPressed() })


        if (intent != null) {

            cityList = intent.getStringArrayListExtra("CityList")
            classList = intent.getParcelableArrayListExtra("ClassList")
            subjectList = intent.getStringArrayListExtra("SubjectList")
            subjectName = intent.getStringExtra("SubjectName")
        }

        main_txt.setText(subjectName + " Tutors Near You")


        //city Spinner
        city_spinner.background.setColorFilter(resources.getColor(R.color.dark_blue_color), PorterDuff.Mode.SRC_ATOP)
        cityAdapter = ArrayAdapter(applicationContext, R.layout.customize_spinner,cityList)
        cityAdapter.setDropDownViewResource(R.layout.customize_spinner)
        city_spinner.setAdapter(cityAdapter)

        //class Spinner
        class_spinner.background.setColorFilter(resources.getColor(R.color.dark_blue_color),PorterDuff.Mode.SRC_ATOP)
        classAdapter = ArrayAdapter(applicationContext, R.layout.customize_spinner,classList)
        classAdapter.setDropDownViewResource(R.layout.customize_spinner)
        class_spinner.setAdapter(classAdapter)

        //subject Spinner
        subject_spinner.background.setColorFilter(resources.getColor(R.color.dark_blue_color),PorterDuff.Mode.SRC_ATOP)
        subjectAdapter = ArrayAdapter(applicationContext,
            R.layout.customize_spinner,subjectList)
        subjectAdapter.setDropDownViewResource(R.layout.customize_spinner)
        subject_spinner.setAdapter(subjectAdapter)

        adapter = TutorListActivityAdapter(this,tutorList)
        var mLayoutInflater: RecyclerView.LayoutManager = LinearLayoutManager(this)
        tutor_list_recy.setLayoutManager(mLayoutInflater)
        tutor_list_recy.setItemAnimator(DefaultItemAnimator())
        tutor_list_recy.setAdapter(adapter)

       /* fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/

        //LOAD DATA
        callSearchAPI(SettingConstant.APIMOBILENUMBER,SettingConstant.APIPASSWORD,"","","",
            "","")
    }


  /*  fun getLoadData()
    {
        var model  = TutorListActivityModel("")
        tutorList.add(model)
        model  = TutorListActivityModel("")
        tutorList.add(model)
        model  = TutorListActivityModel("")
        tutorList.add(model)
        model  = TutorListActivityModel("")
        tutorList.add(model)
        model  = TutorListActivityModel("")
        tutorList.add(model)
        model  = TutorListActivityModel("")
        tutorList.add(model)
        model  = TutorListActivityModel("")
        tutorList.add(model)


     *//*  *//**//*--------city spinner--------*//**//*
        cityList.add("Select City")
        cityList.add("Ahmadabad")
        cityList.add("Agra")
        cityList.add("Delhi")
        cityList.add("Jaipur")
        cityList.add("Pune")
        cityList.add("Allahabad")
        cityList.add("Bhopal")
        cityList.add("Indore")
        cityList.add("Hydrabad")
        cityList.add("Visakhapatnam's")

        *//**//*-----class spinner data------*//**//*
        classList.add(ClassListModel("Select Class",""))
        classList.add(ClassListModel("Class I",""))
        classList.add(ClassListModel("Class II",""))
        classList.add(ClassListModel("Class III",""))
        classList.add(ClassListModel("Class IV",""))
        classList.add(ClassListModel("Class V",""))
        classList.add(ClassListModel("Class VI",""))
        classList.add(ClassListModel("Class VII",""))
        classList.add(ClassListModel("Class VIII",""))
        classList.add(ClassListModel("Class XI",""))
        classList.add(ClassListModel("Class X",""))



        *//**//*-----Subject spinner data------*//**//*
        subjectList.add("Math")
        subjectList.add("Hindi")
        subjectList.add("Physics")
        subjectList.add("Chemestry")
        subjectList.add("SST")
        subjectList.add("Account")*//*

      *//*  cityAdapter.notifyDataSetChanged()
        classAdapter.notifyDataSetChanged()
        subjectAdapter.notifyDataSetChanged()*//*
        adapter.notifyDataSetChanged()

    }*/

    fun callSearchAPI(apiUserMobileNo : String , password :String, city : String, classId : String,
                    subjectId : String, lat : String ,  log : String )
    {
        val pDialog = ProgressDialog(this)
        pDialog.setMessage("Loading...")
        pDialog.setCancelable(false)
        pDialog.show()

        val homeApi = StringRequest(
            Request.Method.GET, searchListUrl + "?APIUserMobileNumber=" + apiUserMobileNo +
                    "&Password=" + password + "&City=" + city + "&ClassId=" + classId +
                    "&SubjectId=" + subjectId + "&Lat=" + lat + "&Long=" + log,
            Response.Listener { response ->
                try {
                    Log.e("Search Api List", response)
                    val jsonObject = JSONObject(response)
                    val Status = jsonObject.getString("Status")

                    if (tutorList.size > 0) {
                        tutorList.clear()
                    }

                    if (Status.equals("1", ignoreCase = true)) {
                        val jsonArray = jsonObject.getJSONArray("TutorList")

                        //ADD Tutor_list
                        for (i in 0 until jsonArray.length()) {
                            val `object` = jsonArray.getJSONObject(i)
                            val Name = `object`.getString("Name")
                            val RefereneId = `object`.getString("RefereneId")
                            val Image = `object`.getString("Image")
                            val SubjectTeach = `object`.getString("SubjectTeach")
                            val Qualification = `object`.getString("Qualification")
                            val Experience = `object`.getString("Experience")
                            val Gender = `object`.getString("Gender")
                            val Year = `object`.getString("Year")

                            tutorList.add(TutorListActivityModel(Name,RefereneId,Gender,Year,Experience,
                                Qualification,SubjectTeach,Image))

                        }

                    }

                    adapter.notifyDataSetChanged()
                    pDialog.dismiss()

                } catch (e: JSONException) {
                    Log.e("checking json excption", e.message)
                    e.printStackTrace()
                }
            }, Response.ErrorListener { error ->
                VolleyLog.d("Login", "Error: " + error.message)


                pDialog.dismiss()
            })

        homeApi.retryPolicy = DefaultRetryPolicy(
            SettingConstant.RetryTime,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT
        )

        AppController.mInstance?.addToRequestQueue(homeApi, "SearchApi")

    }
}
