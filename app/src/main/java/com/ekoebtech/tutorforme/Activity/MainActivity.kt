package com.ekoebtech.tutorforme.Activity

import android.app.ProgressDialog
import android.graphics.PorterDuff
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.*
import com.android.volley.toolbox.StringRequest
import com.ekoebtech.tutorforme.Adapter.SubjectListAdapter
import com.ekoebtech.tutorforme.Adapter.TutorListAdapter
import com.ekoebtech.tutorforme.Model.ClassListModel
import com.ekoebtech.tutorforme.Model.SubjectListModel
import com.ekoebtech.tutorforme.Model.TutorListModel
import com.ekoebtech.tutorforme.R
import com.ekoebtech.tutorforme.Source.AppController
import com.ekoebtech.tutorforme.Source.SettingConstant

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import org.json.JSONException
import org.json.JSONObject
import java.io.Serializable
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    var cityList = ArrayList<String>()
    var classList = ArrayList<ClassListModel>()
    var subjectList = ArrayList<String>()
    var tutorList = ArrayList<TutorListModel>()
    var subjectListGrid = ArrayList<SubjectListModel>()
    var subjectListLimitedGrid = ArrayList<SubjectListModel>()
    lateinit var cityAdapter: ArrayAdapter<String>
    lateinit var classAdapter: ArrayAdapter<ClassListModel>
    lateinit var subjectAdapter: ArrayAdapter<String>
    lateinit var subjectListAdapter : SubjectListAdapter
    lateinit var tutorListAdapter: TutorListAdapter
    var homeApiUrl : String = SettingConstant.BASEURL + "homeapi/Home";
    var isFlag : Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)


       /* //expand height
        this.simpleGridView.setExpanded(true)

        subjectListAdapter = SubjectListAdapter(this,subjectListLimitedGrid)
        simpleGridView.setAdapter(subjectListAdapter)


        //city Spinner
        city_spinner.background.setColorFilter(resources.getColor(R.color.dark_blue_color),PorterDuff.Mode.SRC_ATOP)
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

        tutorListAdapter = TutorListAdapter(tutorList,this)
        var mLayoutInflater:RecyclerView.LayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true)
        tutore_list.setLayoutManager(mLayoutInflater)
        tutore_list.setItemAnimator(DefaultItemAnimator())
        tutore_list.setAdapter(tutorListAdapter)

        tutore_list.scrollToPosition(1)

        //set the image drop down icon
        drop_down_btn.setBackgroundResource(R.drawable.ic_arrow_down)

        drop_down_btn.setOnClickListener {

            if (isFlag) {
                drop_down_btn.setBackgroundResource(R.drawable.ic_arrow_up)
                subjectListAdapter = SubjectListAdapter(this, subjectListGrid)
                simpleGridView.setAdapter(subjectListAdapter)
                isFlag = false
            }else
            {
                drop_down_btn.setBackgroundResource(R.drawable.ic_arrow_down)
                subjectListAdapter = SubjectListAdapter(this, subjectListLimitedGrid)
                simpleGridView.setAdapter(subjectListAdapter)
                isFlag = true

            }



        }*/


       // getLoadData()


        callHomeAPI("1111111111","123456")

    }

/*
    fun getLoadData()

    {
*//* var model = SubjectListModel("Math")
subjectListGrid.add(model)

model = SubjectListModel("English")
subjectListGrid.add(model)

model = SubjectListModel("Hindi")
subjectListGrid.add(model)

model = SubjectListModel("Physics")
subjectListGrid.add(model)

model = SubjectListModel("Chemistry")
subjectListGrid.add(model)


model = SubjectListModel("Account")
subjectListGrid.add(model)*//*

*//*------Tutor List -------*//*
var tutorModel = TutorListModel("")
tutorList.add(tutorModel)

tutorModel = TutorListModel("")
tutorList.add(tutorModel)

tutorModel = TutorListModel("")
tutorList.add(tutorModel)

tutorModel = TutorListModel("")
tutorList.add(tutorModel)

tutorModel = TutorListModel("")
tutorList.add(tutorModel)


tutorModel = TutorListModel("")
tutorList.add(tutorModel)


*//*   *//**//*--------city spinner--------*//**//*
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
cityList.add("Visakhapatnam's")*//*

*//*   *//**//*-----class spinner data------*//**//*
classList.add("Select Class")
classList.add("Class I")
classList.add("Class II")
classList.add("Class III")
classList.add("Class IV")
classList.add("Class V")
classList.add("Class VI")
classList.add("Class VII")
classList.add("Class VIII")
classList.add("Class XI")
classList.add("Class X")
*//*
*//*-----Subject spinner data------*//*
*//*   subjectList.add("Select Subject")
subjectList.add("Math")
subjectList.add("Hindi")
subjectList.add("Physics")
subjectList.add("Chemestry")
subjectList.add("SST")
subjectList.add("Account")*//*


//cityAdapter.notifyDataSetChanged()
//classAdapter.notifyDataSetChanged()
// subjectAdapter.notifyDataSetChanged()
// subjectListAdapter.notifyDataSetChanged()
        tutorListAdapter.notifyDataSetChanged()

    }*/

    fun callHomeAPI(apiUserMobileNo : String , password :String )
    {
        val pDialog = ProgressDialog(this)
        pDialog.setMessage("Loading...")
        pDialog.setCancelable(false)
        pDialog.show()

        val homeApi = StringRequest(
            Request.Method.GET, homeApiUrl + "?APIUserMobileNumber=" + apiUserMobileNo + "&Password=" + password,
            Response.Listener { response ->
                try {
                    Log.e("Home Api List", homeApiUrl + "?APIUserMobileNumber=" + apiUserMobileNo + "&Password=" + password)
                    val jsonObject = JSONObject(response)
                    val Status = jsonObject.getString("Status")

                    if (cityList.size > 0) {
                        cityList.clear()
                        classList.clear()
                        subjectList.clear()
                        subjectListGrid.clear()
                        subjectListLimitedGrid.clear()

                    }

                    if (Status.equals("1", ignoreCase = true)) {
                        val jsonArray = jsonObject.getJSONArray("CityList")
                        val classListArray = jsonObject.getJSONArray("ClassList")
                        val subjectListArray = jsonObject.getJSONArray("SubjectList")
                        val tutorListArray = jsonObject.getJSONArray("TutorList")

                        //city_list
                        for (i in 0 until jsonArray.length()) {
                            val `object` = jsonArray.getJSONObject(i)
                            val CityName = `object`.getString("CityName")
                            cityList.add(CityName)
                        }

                        for (l in 0 until classListArray.length())
                        {
                            val classListObj = classListArray.getJSONObject(l)
                            val className = classListObj.getString("ClassName")
                            val classId = classListObj.getString("ClassId")

                            classList.add(ClassListModel(className,classId))
                        }

                        for (k in 0 until subjectListArray.length())
                        {
                            val subjectListObj = subjectListArray.getJSONObject(k)
                            val subjectName = subjectListObj.getString("SubjectName")
                            val SubjectId = subjectListObj.getString("SubjectId");
                            val Color = subjectListObj.getString("Color")

                            subjectList.add(subjectName)
                            subjectListGrid.add(SubjectListModel(subjectName,SubjectId,Color))

                        }

                        for (p in 0 until 6)
                        {
                            subjectListLimitedGrid.add(SubjectListModel(subjectListGrid.get(p).subjectName,
                                subjectListGrid.get(p).subjectId,subjectListGrid.get(p).subjectColor))

                        }

                        for (q in 0 until tutorListArray.length())
                        {
                            val tutorListObj = tutorListArray.getJSONObject(q)
                            val Image = tutorListObj.getString("Image")
                            val Name = tutorListObj.getString("Name")
                            val TotalExperience = tutorListObj.getString("TotalExperience")
                            val Gender = tutorListObj.getString("Gender")
                            val EmailId = tutorListObj.getString("EmailId")
                            val MobileNumber = tutorListObj.getString("MobileNumber")
                            val Qualification = tutorListObj.getString("Qualification")
                            val Year = tutorListObj.getString("Year")
                            val SubjectTeach = tutorListObj.getString("SubjectTeach")
                            val TeachingArea = tutorListObj.getString("TeachingArea")
                            val City= tutorListObj.getString("City")
                            val StateName = tutorListObj.getString("StateName")
                            val AddressLine1 = tutorListObj.getString("AddressLine1")

                            tutorList.add(TutorListModel(Name,Image,TotalExperience,Gender,EmailId,MobileNumber,
                                Qualification,Year,SubjectTeach,TeachingArea,City,StateName,AddressLine1))

                        }

                    } else if (Status.equals("3", ignoreCase = true)) {

                        /* val Message = jsonObject.getString("Message")

           viewDialog.showLogoutDialog(
               this@TeacherStudentListActivity,
               "You are already logged in to another device. You will now be logged \"out\" from that device."
           )*/


                    }

                    cityAdapter.notifyDataSetChanged()
                    classAdapter.notifyDataSetChanged()
                    subjectAdapter.notifyDataSetChanged()
                    subjectListAdapter.notifyDataSetChanged()
                    tutorListAdapter.notifyDataSetChanged()
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

        AppController.mInstance?.addToRequestQueue(homeApi, "HomeApi")

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
     menuInflater.inflate(R.menu.menu_main, menu)

        return true

    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }

    }

}
