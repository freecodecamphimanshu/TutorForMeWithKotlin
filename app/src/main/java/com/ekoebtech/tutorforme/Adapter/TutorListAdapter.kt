package com.ekoebtech.tutorforme.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.ekoebtech.tutorforme.Activity.TutorListActivity
import com.ekoebtech.tutorforme.Activity.TutorProfileActivity
import com.ekoebtech.tutorforme.Model.TutorListModel
import com.ekoebtech.tutorforme.R
import com.ekoebtech.tutorforme.Source.SettingConstant
import kotlinx.android.synthetic.main.tutor_item_layout.view.*
import java.util.ArrayList

class TutorListAdapter(var list: ArrayList<TutorListModel>, var activity: Activity?) :
    RecyclerView.Adapter<TutorListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.tutor_item_layout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var model : TutorListModel = list.get(position)

        holder.tutorName.setText(model.tutorName)
        holder.tutorage.setText(model.gender+","+model.year)
        holder.tutorEmalId.setText(model.emailId)
        holder.mobileNo.setText(model.mobileNumber)
        holder.tutorExp.setText(model.totalExp)
        holder.tutorQuali.setText(model.qualificcation)
        holder.tutorTeaches.setText(model.teachingArea)
        holder.tutorArea.setText(model.teachingArea)

        Glide.with(activity!!).load(SettingConstant.BASEURL + model.image).apply(
            RequestOptions()
            .placeholder(R.drawable.prf)
        ).into(holder.tutorImage);


        holder.profileBtn.setOnClickListener(View.OnClickListener {
            var intent = Intent(activity, TutorProfileActivity :: class.java )
            activity!!.startActivity(intent) })


    }

    override fun getItemCount(): Int {
        return list.size
    }

     class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
     {
         var tutorImage = itemView.tutor_image
         var tutorName = itemView.tutorname
         var tutorage = itemView.tutor_gend_age
         var tutorEmalId = itemView.tutor_email_id
         var mobileNo = itemView.tutor_mobile
         var tutorExp = itemView.tutor_exp
         var tutorQuali = itemView.tutor_qualification
         var tutorTeaches = itemView.tutor_teaches
         var tutorArea = itemView.tutor_area
         var profileBtn = itemView.profile_btn

     }
}
