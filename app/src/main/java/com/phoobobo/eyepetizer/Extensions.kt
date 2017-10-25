package com.phoobobo.eyepetizer

import android.view.View
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

/**
 * Created by phoobobo on 2017/10/20.
 */
fun  <T> Observable<T>.io_main(): Observable<T> {
    return subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
}

fun View.durationFormat(duration: Long?): String {
    val minute = duration!! / 60
    val second = duration % 60
    if (minute <= 9) {
        if (second <= 9) {
            return "0${minute}' 0${second}''"
        } else {
            return "0${minute}' ${second}''"
        }
    } else {
        if (second <= 9) {
            return "${minute}' 0${second}''"
        } else {
            return "${minute}' ${second}''"
        }
    }
}

fun View.timeFormat(time: Long): String {
    val date = Date(time)
    val timeCalendar = Calendar.getInstance()
    timeCalendar.time = date


    val today = Calendar.getInstance()
    val todayDate = Date(System.currentTimeMillis())
    today.time = todayDate

    if (timeCalendar.get(Calendar.YEAR) === today.get(Calendar.YEAR)) {
        val diffDay = timeCalendar.get(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR)

        if (diffDay == 0) {
            //是今天
            val hours = timeCalendar.get(Calendar.HOUR_OF_DAY)
            val minues = timeCalendar.get(Calendar.MINUTE)
            return "${if (hours < 10) "0" + hours else hours}:${if (minues < 10) "0" + minues else minues}"
        }
    }
    val year = timeCalendar.get(Calendar.YEAR)
    val month = timeCalendar.get(Calendar.MONTH)
    val day = timeCalendar.get(Calendar.DAY_OF_MONTH)
    return "${year}/${if (month < 10) "0" + month else month}/${if (day < 10) "0" + day else day}"
}

/**
 * 几天前  几小时前
 */
fun View.timePreFormat(time: Long): String {

    val now = System.currentTimeMillis()
    val pre = now - time//多久前


    val min = pre / 1000 / 60
    if (min<1){
        return "刚刚"
    }else if(min<60){
        return ""+min+"分钟前"
    }else if(min<60*24){
        return ""+min/60+"小时前"
    }else {
        return ""+min/60/24+"天前"
    }
}