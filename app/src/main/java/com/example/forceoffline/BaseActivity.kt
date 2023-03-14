package com.example.forceoffline

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController


open class BaseActivity : AppCompatActivity() {
    lateinit var receiver:ForceOfflineReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ActivityCollector.addActivity(this)
    }

    override fun onResume() {
        super.onResume()
        val intentFilter = IntentFilter()
        intentFilter.addAction("com.example.broadcastbestpractice.FORCE_OFFLINE")
        receiver = ForceOfflineReceiver()
        registerReceiver(receiver,intentFilter)
    }

    override fun onPause() {
        super.onPause()
        unregisterReceiver(receiver)
    }

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeactivity(this)
    }

    inner class ForceOfflineReceiver: BroadcastReceiver(){
        override fun onReceive(context: Context?, intent: Intent?) {
            AlertDialog.Builder(context!!).apply {
                setTitle("警告")
                setMessage("你被迫下线，请重新登陆")
                setCancelable(false)
                setPositiveButton("OK"){_,_->
                    ActivityCollector.finishAll()
                    val i  = Intent(context,LoginActivity::class.java)
                    context.startActivity(i)
                }
                show()
            }
        }

    }
}