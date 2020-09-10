package com.example.smscategory.ui.main

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.smscategory.R
import com.example.smscategory.di.component.ActivityComponent
import com.example.smscategory.model.ChatModelObject
import com.example.smscategory.model.DateObject
import com.example.smscategory.model.ListObject
import com.example.smscategory.model.Sms
import com.example.smscategory.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    private val PERMISSION_REQ_CODE = 1010


    // BroadcastReceiver listening to the incoming messages
    private val SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED"

    private var intentFilter = IntentFilter(SMS_RECEIVED)

    private lateinit var adapter: SmsAdapter

    override fun provideLayoutId(): Int = R.layout.activity_main

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.requestPermissions(this, PERMISSION_REQ_CODE)
    }

    override fun setupView(savedInstanceState: Bundle?) {

        recycler_sms_list.layoutManager =
            GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false)
        adapter = SmsAdapter(arrayListOf())

        recycler_sms_list.layoutManager
        recycler_sms_list.adapter = adapter

    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String?>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQ_CODE -> if (grantResults.size > 0) {
                val readSms =
                    grantResults[0] == PackageManager.PERMISSION_GRANTED
                val receiveSms =
                    grantResults[1] == PackageManager.PERMISSION_GRANTED
                if (readSms && receiveSms) {
                    viewModel.readMessages(this)
                } else {
                    viewModel.requestPermissions(this, PERMISSION_REQ_CODE)
                    Toast.makeText(this, "Approve permissions to read SMS.", Toast.LENGTH_LONG)
                        .show()
                }
            }
        }
    }


    override fun setupObservers() {
        super.setupObservers()

        viewModel.permissionResult.observe(this, Observer {
            if (it) {
                viewModel.readMessages(this)
            }
        })

        viewModel.smsData.observe(this, Observer {


            adapter.addData(it)
            adapter.notifyDataSetChanged()

            if (it.size > 1) {
                (recycler_sms_list.layoutManager as GridLayoutManager).scrollToPosition(it.size - 1)
            }
        })

        viewModel.loader.observe(this, Observer {
            pb_loading.visibility = if (it) View.VISIBLE else View.GONE
        })
    }


    private val smsReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == SMS_RECEIVED) {
                viewModel.requestPermissions(this@MainActivity, PERMISSION_REQ_CODE)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // registering a BroadcastReceiver to listen to incoming messages
        registerReceiver(smsReceiver, intentFilter)
    }

    override fun onPause() {
        super.onPause()
        // unregistering BroadcastReceiver
        unregisterReceiver(smsReceiver)
    }


}