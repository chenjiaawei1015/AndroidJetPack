package com.cjw.demo1.logger

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class Log private constructor() {

    init {
        Logger.addLogAdapter(AndroidLogAdapter())
    }

    companion object {

        private var sLog: Log? = null

        fun getSingleInstance(): Log {
            if (sLog == null) {
                sLog = Log()
            }
            return sLog as Log
        }
    }

    fun info(message: String, vararg args: Any) {
        Logger.i(message, args)
    }

    fun debug(obj: Any?) {
        Logger.d(obj)
    }

    fun debug(message: String, vararg args: Any) {
        Logger.d(message, args)
    }

    fun error(message: String, vararg args: Any) {
        Logger.e(message, args)
    }

    fun error(throwable: Throwable?, message: String, vararg args: Any) {
        Logger.e(throwable, message, args)
    }
}