package com.example.smscategory.ui.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.smscategory.R
import com.example.smscategory.model.ChatModelObject
import com.example.smscategory.model.DateObject
import com.example.smscategory.model.ListObject
import com.example.smscategory.model.Sms
import com.example.smscategory.utils.common.TimeUtils
import kotlinx.android.synthetic.main.card_sms.view.*
import kotlinx.android.synthetic.main.card_time.view.*


class SmsAdapter (

        private var sms: List<ListObject> ) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    class DateViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(date: DateObject) {
            itemView.tv_hours_ago.text  =  date.date
        }
    }

    class DataViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bind(chat: ChatModelObject ,position : Int) {
            val sms = chat.getChatModel();

            itemView.tv_address.text = sms!!.msgAddress
            itemView.tv_time.text = sms.time12
            itemView.tv_message.text = sms.msgBody


        }
    }


    override fun getItemCount(): Int = sms.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        when (holder.getItemViewType()) {


            ListObject.TYPE_DATE->
            {
                val dateViewHolder: DateViewHolder = holder as DateViewHolder

                dateViewHolder.bind(sms.get(position) as DateObject)
            }

            ListObject.TYPE_MESSAGE ->
            {
                val chatViewHolder: DataViewHolder = holder as DataViewHolder

                chatViewHolder.bind(sms.get(position) as ChatModelObject,position)
            }


        }

    }


    fun addData(list: List<ListObject> ) {
        sms = list

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        lateinit var viewHolder: RecyclerView.ViewHolder

        val inflater = LayoutInflater.from(parent.getContext())

        Log.e("viewType", viewType.toString())

        when (viewType) {

            ListObject.TYPE_DATE -> {
                val v2: View =
                    inflater.inflate(R.layout.card_time, parent, false)
                viewHolder = DateViewHolder(v2)
            }

            ListObject.TYPE_MESSAGE -> {
                val currentUserView: View =
                    inflater
                        .inflate(R.layout.card_sms, parent, false)
                viewHolder = DataViewHolder(currentUserView) // view holder for normal items
            }


        }

        return viewHolder

    }

    override fun getItemViewType(position: Int): Int {

        return sms.get(position).getType()
    }




}