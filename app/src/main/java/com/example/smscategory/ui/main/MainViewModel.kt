package com.example.smscategory.ui.main

import android.Manifest
import android.app.Activity
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.lifecycle.MutableLiveData
import com.example.smscategory.model.ChatModelObject
import com.example.smscategory.model.DateObject
import com.example.smscategory.model.ListObject
import com.example.smscategory.model.Sms
import com.example.smscategory.ui.base.BaseViewModel
import com.example.smscategory.utils.common.TimeUtils
import com.example.smscategory.utils.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import java.sql.Time
import java.util.*
import kotlin.collections.ArrayList


class MainViewModel (schedulerProvider: SchedulerProvider,
                     compositeDisposable: CompositeDisposable

): BaseViewModel(schedulerProvider, compositeDisposable){

    private val READ_SMS = Manifest.permission.READ_SMS
    private val RECEIVE_SMS = Manifest.permission.RECEIVE_SMS

    var smsList : ArrayList<Sms> = ArrayList()
    var smsData: MutableLiveData<List<ListObject>> = MutableLiveData()

    var FILTER: String? = null




    companion object
    {
        val TAG = "MainViewModel"
    }

    override fun onCreate() {
    }

    val loader: MutableLiveData<Boolean> = MutableLiveData()
    val permissionResult: MutableLiveData<Boolean> = MutableLiveData()


    fun requestPermissions(activity: Activity, PERMISSION_REQ_CODE: Int ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ActivityCompat.requestPermissions(
                activity, arrayOf(
                    READ_SMS,
                    RECEIVE_SMS
                ), PERMISSION_REQ_CODE
            )
        } else {
            permissionResult.postValue(true)
        }
    }


    fun readMessages(activity: Activity) {

        loader.postValue(true)
        // new sms object declared
        var sms: Sms

        // read sms are stored in cursor
        val c: Cursor? = activity.getContentResolver().query(Uri.parse("content://sms/inbox"), arrayOf("date", "body","address"),
            FILTER,
            null,
            null
        )
        val total = c!!.count

        // all messages are read from bottom because when new sms gets inserted they are inserted in the position zero
        // thus to keep the latest messages up in the list

        if (c.moveToLast()) {
            for (i in 0 until total) {
                sms = Sms()

                // body, date and address read from cursor
                val date = c.getString(c.getColumnIndexOrThrow("date"))
                val body = c.getString(c.getColumnIndexOrThrow("body"))
                val address = c.getString(c.getColumnIndexOrThrow("address"))

                // keeping track of a filter to prevent reading of messages already read
                FILTER = "date>$date"

                // date is set to the sms object
                // body is set to the sms object
                // address is set to the sms object
                sms.msgDate = date
                sms.msgBody = body
                sms.msgAddress = address



                //Add same day message
                if (TimeUtils.getDay(date).equals(TimeUtils.getCurrentDate()))
                {
//                    Log.e("Date", TimeUtils.getDay(date).toString())

                    smsList.add(smsList.size, sms)
                }

                c.moveToPrevious()
            }
        } else {
            // if no messages to read than a toast is displayed
            Toast.makeText(activity, "No sms to read!!", Toast.LENGTH_SHORT).show()
        }

        c.close()


        groupDataIntoHashMap(smsList)


        loader.postValue(false)
    }


    private fun groupDataIntoHashMap(chatModelList: List<Sms>) {
        val groupedHashMap: LinkedHashMap<String, MutableSet<Sms?>?> =
            LinkedHashMap()
        var list: MutableSet<Sms?>? = null



        for (chatModel in chatModelList) {

            val differenceTime: Int =  TimeUtils.getDifferenceHour(chatModel.time).toInt()

            Log.e("Difference", differenceTime.toString())

            var hashMapKey  = ""

            if (differenceTime < 1 )
            {
                hashMapKey =  "0 Hours ago"
            }
            else if (differenceTime in 1..1)
            {
                hashMapKey =  "1 Hours ago"
            }
            else if (differenceTime in 2..2)
            {
                hashMapKey =  "2 Hours ago"
            }
            else if (differenceTime in 3..5)
            {
                hashMapKey =  "3 Hours ago"
            }
            else if (differenceTime in 6..11)
            {
                hashMapKey =  "6 Hours ago"
            }
            else if (differenceTime in 12..23)
            {
                hashMapKey =  "12 Hours ago"
            }
            else if (differenceTime >= 24 )
            {
                hashMapKey =  "1 Day ago"
            }

            //Log.d(TAG, travelActivityDTO.toString());

            //Log.d(TAG, "start date: " + DateParser.convertDateToString(travelActivityDTO.getStartDate()));
            if (groupedHashMap.containsKey(hashMapKey)) {
                // The key is already in the HashMap; add the pojo object
                // against the existing key.
                groupedHashMap[hashMapKey]!!.add(chatModel)
            } else {
                // The key is not there in the HashMap; create a new key-value pair
                list = LinkedHashSet()
                list!!.add(chatModel)
                groupedHashMap[hashMapKey] = list
            }
        }
        //Generate list from map

        smsData.postValue(generateListFromMap(groupedHashMap))
    }


    private fun generateListFromMap(groupedHashMap: LinkedHashMap<String, MutableSet<Sms?>?>): MutableList<ListObject> {
        // We linearly add every item into the consolidatedList.
        val consolidatedList: MutableList<ListObject> = ArrayList()
        for (date in groupedHashMap.keys) {

            Log.e("HASH DATE",date)

            val dateItem = DateObject()
            dateItem.date = date
            consolidatedList.add(dateItem)
            for (chatModel in groupedHashMap[date]!!) {
                val generalItem = ChatModelObject()
                generalItem.setChatModel(chatModel)
                consolidatedList.add(generalItem)

                Log.e("List", generalItem.toString())
            }
        }



        return consolidatedList
    }








}


