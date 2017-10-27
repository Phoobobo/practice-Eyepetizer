package com.phoobobo.eyepetizer

import android.app.Application
import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager
import android.util.Log
import com.phoobobo.eyepetizer.utils.DisplayManager

/**
 * Created by phoobobo on 2017/10/24.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        DisplayManager.init(this)

//        setDeviceInfo()
    }

    private fun setDeviceInfo() {
        val packageInfo = packageManager.getPackageInfo(packageName, 0)
        val tm: TelephonyManager = this.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        Log.d("App", "deviceId: ${tm.deviceId} \n")
        DeviceInfo.udid = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            tm.imei
        } else {
            tm.deviceId
        }
        DeviceInfo.versionCode = packageInfo.versionCode
        DeviceInfo.versionName = packageInfo.versionName
    }

    companion object DeviceInfo {
        var udid: String? = null
        val deviceModel: String = Build.MODEL
        val systemVersionCode: Int = Build.VERSION.SDK_INT
        var versionCode: Int? = null
        var versionName: String? = null

    }
}