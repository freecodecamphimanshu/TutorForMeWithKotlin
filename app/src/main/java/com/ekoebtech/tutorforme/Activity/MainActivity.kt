package com.ekoebtech.tutorforme.Activity

import android.graphics.PorterDuff
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ekoebtech.tutorforme.Adapter.SubjectListAdapter
import com.ekoebtech.tutorforme.Adapter.TutorListAdapter
import com.ekoebtech.tutorforme.Model.SubjectListModel
import com.ekoebtech.tutorforme.Model.TutorListModel
import com.ekoebtech.tutorforme.R

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    var cityList = ArrayList<String>()
    var classList = ArrayList<String>()
    var subjectList = ArrayList<String>()
    var tutorList = ArrayList<TutorListModel>()
    var subjectListGrid = ArrayList<SubjectListModel>()
    lateinit var cityAdapter: ArrayAdapter<String>
    lateinit var classAdapter: ArrayAdapter<String>
    lateinit var subjectAdapter: ArrayAdapter<String>
    lateinit var subjectListAdapter : SubjectListAdapter
    lateinit var tutorListAdapter: TutorListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //expand height
        this.simpleGridView.setExpanded(true)

        subjectListAdapter = SubjectListAdapter(this,subjectListGrid)
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


        /*  fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/

        getLoadData()
    }

    fun getLoadData()
    {
        var model = SubjectListModel("Math")
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
        subjectListGrid.add(model)

        /*------Tutor List -------*/
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


        /*--------city spinner--------*/
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

        /*-----class spinner data------*/
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

        /*-----Subject spinner data------*/
        subjectList.add("Select Subject")
        subjectList.add("Math")
        subjectList.add("Hindi")
        subjectList.add("Physics")
        subjectList.add("Chemestry")
        subjectList.add("SST")
        subjectList.add("Account")


        cityAdapter.notifyDataSetChanged()
        classAdapter.notifyDataSetChanged()
        subjectAdapter.notifyDataSetChanged()
        subjectListAdapter.notifyDataSetChanged()
        tutorListAdapter.notifyDataSetChanged()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
