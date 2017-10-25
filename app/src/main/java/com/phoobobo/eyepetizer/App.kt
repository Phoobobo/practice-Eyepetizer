package com.phoobobo.eyepetizer

import android.app.Application
import com.phoobobo.eyepetizer.utils.DisplayManager

/**
 * Created by phoobobo on 2017/10/24.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        DisplayManager.init(this)
    }
}