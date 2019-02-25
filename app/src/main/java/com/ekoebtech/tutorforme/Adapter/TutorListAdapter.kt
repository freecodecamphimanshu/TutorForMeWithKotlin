package com.ekoebtech.tutorforme.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ekoebtech.tutorforme.Model.TutorListModel
import com.ekoebtech.tutorforme.R
import java.util.ArrayList

class TutorListAdapter(var list: ArrayList<TutorListModel>, var activity: Activity) :
    RecyclerView.Adapter<TutorListAdapter.ViewHolder>() {

   // var list = ArrayList<TutorListModel>()

   /* init {
        this.list = list
    }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tutor_item_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

       // list
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}
