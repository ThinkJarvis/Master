package com.app.master

import android.app.Application
import com.app.master.data.ServiceLocator
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy


class MasterApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initLogger()
        ServiceLocator.init(this)
    }

    private fun initLogger() {
        Logger.addLogAdapter(object : AndroidLogAdapter(getStrategy()) {
            override fun isLoggable(priority: Int, tag: String?): Boolean {
                return BuildConfig.DEBUG
            }
        })
    }


    private fun getStrategy(): PrettyFormatStrategy =
        PrettyFormatStrategy.newBuilder()
            .showThreadInfo(false)  // 是否显示线程信息，默认为ture
            .methodCount(1)         // 显示的方法行数
            .methodOffset(0)        // 隐藏内部方法调用到偏移量
            .tag("MasterApp")
            .build()
}