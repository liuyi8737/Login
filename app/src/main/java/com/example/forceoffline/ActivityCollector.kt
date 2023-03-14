package com.example.forceoffline

import android.app.Activity

object ActivityCollector {
    private var activities = ArrayList<Activity>()
    fun addActivity(activity: Activity){
        activities.add(activity)
    }
    fun removeactivity(activity: Activity){
        activities.remove(activity)
    }
    fun finishAll(){
        for ( activity in activities){
            if (!activity.isFinishing){
                activity.finish()
            }
        }
        activities.clear()
    }

}