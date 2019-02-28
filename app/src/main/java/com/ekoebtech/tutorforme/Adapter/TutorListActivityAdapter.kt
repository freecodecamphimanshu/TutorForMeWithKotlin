package com.ekoebtech.tutorforme.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ekoebtech.tutorforme.Model.TutorListActivityModel
import com.ekoebtech.tutorforme.R
import com.ekoebtech.tutorforme.Source.SettingConstant
import kotlinx.android.synthetic.main.tutor_list_activity_layout.view.*

class TutorListActivityAdapter(var activity : Activity, var tutorList : ArrayList<TutorListActivityModel>) :
    RecyclerView.Adapter<TutorListActivityAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tutor_list_activity_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return tutorList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
      var model : TutorListActivityModel = tutorList.get(position)

        holder.tutorName.setText(model.tutorName)
        holder.tutorRefrence.setText(model.RefereneId)
        holder.tutorage.setText(model.Gender + "," + model.Year)
        holder.tutorExp.setText(model.Experience)
        holder.tutorTeaches.setText(model.SubjectTeach)
        holder.tutorQuali.setText(model.Qualification)

        Glide.with(activity!!).load(SettingConstant.BASEURL + model.Image).apply(
            RequestOptions()
                .placeholder(R.drawable.prf)
        ).into(holder.tutorImage);

    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    {
        var tutorImage = itemView.tutorimage
        var tutorName = itemView.tutorname
        var tutorage = itemView.gender_age
        var tutorExp = itemView.tutor_experence
        var tutorQuali = itemView.tutorqualification
        var tutorTeaches = itemView.tutorteaches
        var tutorRefrence = itemView.tutor_refrence_no

    }
}