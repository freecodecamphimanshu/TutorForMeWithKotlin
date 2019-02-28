package com.ekoebtech.tutorforme.Fragment

import android.app.ProgressDialog
import android.content.Intent
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.DefaultRetryPolicy
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.StringRequest
import com.ekoebtech.tutorforme.Activity.TutorListActivity
import com.ekoebtech.tutorforme.Adapter.SubjectListAdapter
import com.ekoebtech.tutorforme.Adapter.TutorListAdapter
import com.ekoebtech.tutorforme.Model.ClassListModel
import com.ekoebtech.tutorforme.Model.SubjectListModel
import com.ekoebtech.tutorforme.Model.TutorListModel

import com.ekoebtech.tutorforme.R
import com.ekoebtech.tutorforme.Source.AppController
import com.ekoebtech.tutorforme.Source.SettingConstant
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.fragment_dashboard.*
import kotlinx.android.synthetic.main.fragment_dashboard.view.*
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [DashboardFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [DashboardFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class DashboardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null
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
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var rootView = inflater.inflate(R.layout.fragment_dashboard, container, false)

        //expand height
        rootView.simpleGridView?.setExpanded(true)

        subjectListAdapter = SubjectListAdapter(requireActivity(),subjectListLimitedGrid)
        rootView.simpleGridView.setAdapter(subjectListAdapter)

        //city Spinner
        rootView.city_spinner.background.setColorFilter(resources.getColor(R.color.dark_blue_color), PorterDuff.Mode.SRC_ATOP)
        cityAdapter = ArrayAdapter(activity, R.layout.customize_spinner,cityList)
        cityAdapter.setDropDownViewResource(R.layout.customize_spinner)
        rootView.city_spinner.setAdapter(cityAdapter)

        //class Spinner
        rootView.class_spinner.background.setColorFilter(resources.getColor(R.color.dark_blue_color), PorterDuff.Mode.SRC_ATOP)
        classAdapter = ArrayAdapter(activity, R.layout.customize_spinner,classList)
        classAdapter.setDropDownViewResource(R.layout.customize_spinner)
        rootView.class_spinner.setAdapter(classAdapter)

        //subject Spinner
        rootView.subject_spinner.background.setColorFilter(resources.getColor(R.color.dark_blue_color), PorterDuff.Mode.SRC_ATOP)
        subjectAdapter = ArrayAdapter(activity,
            R.layout.customize_spinner,subjectList)
        subjectAdapter.setDropDownViewResource(R.layout.customize_spinner)
        rootView.subject_spinner.setAdapter(subjectAdapter)

        tutorListAdapter = TutorListAdapter(tutorList, this!!.activity)
        var mLayoutInflater: RecyclerView.LayoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,true)
        rootView.tutore_list.setLayoutManager(mLayoutInflater)
        rootView.tutore_list.setItemAnimator(DefaultItemAnimator())
        rootView.tutore_list.setAdapter(tutorListAdapter)

        rootView.tutore_list.scrollToPosition(1)

        //set the image drop down icon
        rootView.drop_down_btn.setBackgroundResource(R.drawable.ic_arrow_down)

        rootView.drop_down_btn.setOnClickListener {

            if (isFlag) {
                rootView.drop_down_btn.setBackgroundResource(R.drawable.ic_arrow_up)
                subjectListAdapter = SubjectListAdapter(activity!!, subjectListGrid)
                rootView.simpleGridView.setAdapter(subjectListAdapter)
                isFlag = false
            }else
            {
                rootView.drop_down_btn.setBackgroundResource(R.drawable.ic_arrow_down)
                subjectListAdapter = SubjectListAdapter(activity!!, subjectListLimitedGrid)
                rootView.simpleGridView.setAdapter(subjectListAdapter)
                isFlag = true

            }

        }

        rootView.simpleGridView.setOnItemClickListener { adapterView: AdapterView<*>, view1: View, i: Int, l: Long ->

            var intent = Intent(activity,TutorListActivity :: class.java )
            intent.putExtra("CityList",cityList)
            intent.putParcelableArrayListExtra("ClassList",classList)
            intent.putExtra("SubjectList",subjectList)
            intent.putExtra("SubjectName",subjectListGrid.get(i).subjectName)
            startActivity(intent) }

        /*rootView.simpleGridView.setOnItemClickListener(AdapterView.OnItemClickListener
        { parent, view, position, id ->



        })*/



        //call API
        callHomeAPI(SettingConstant.APIMOBILENUMBER,SettingConstant.APIPASSWORD)


        return rootView
    }

    fun callHomeAPI(apiUserMobileNo : String , password :String )
    {
        val pDialog = ProgressDialog(activity)
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

                        cityList.add("Select City")
                        //city_list
                        for (i in 0 until jsonArray.length()) {
                            val `object` = jsonArray.getJSONObject(i)
                            val CityName = `object`.getString("CityName")
                            cityList.add(CityName)
                        }

                        classList.add(ClassListModel("Select Class","0"))

                        for (l in 0 until classListArray.length())
                        {
                            val classListObj = classListArray.getJSONObject(l)
                            val className = classListObj.getString("ClassName")
                            val classId = classListObj.getString("ClassId")

                            classList.add(ClassListModel(className,classId))
                        }

                        subjectList.add("Select Subject")
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

  /*  // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }*/

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DashboardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            DashboardFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
