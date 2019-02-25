package com.ekoebtech.tutorforme.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.ekoebtech.tutorforme.Model.SubjectListModel
import com.ekoebtech.tutorforme.R

class SubjectListAdapter(var activity: Activity,var listModel: ArrayList<SubjectListModel>) : BaseAdapter() {


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView
        var model : SubjectListModel = listModel.get(position)
        var layoutInflater : LayoutInflater = LayoutInflater.from(activity)
        convertView = layoutInflater.inflate(R.layout.subject_list_item_layout,null)

        val subjectNameTxt = convertView.findViewById(R.id.subject_name) as TextView
        subjectNameTxt.setText(model.subjectName)

        return convertView

    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
      return 0
    }

    override fun getCount(): Int {
        return listModel.size
    }
}