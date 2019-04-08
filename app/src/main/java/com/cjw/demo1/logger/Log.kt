package com.cjw.demo1.logger

import android.text.TextUtils
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

object Log {

  init {
    Logger.addLogAdapter(AndroidLogAdapter())
  }

  fun info(
    message: String?,
    vararg args: Any
  ) {
    try {
      if (message?.isNotEmpty()!!) {
        Logger.i(message, args)
      }
    } catch (ex: Exception) {
      printMessageBySystem(message)
    }
  }

  fun debug(obj: Any?) {
    try {
      Logger.d(obj)
    } catch (ex: Exception) {
      printMessageBySystem(obj.toString())
    }
  }

  fun debug(
    message: String?,
    vararg args: Any
  ) {
    try {
      if (message?.isNotEmpty()!!) {
        Logger.d(message, args)
      }
    } catch (ex: Exception) {
      printMessageBySystem(message)
    }
  }

  fun error(
    message: String?,
    vararg args: Any
  ) {
    try {
      if (message?.isNotEmpty()!!) {
        Logger.e(message, args)
      }
    } catch (ex: Exception) {
      printMessageBySystem(message)
    }
  }

  fun error(
    throwable: Throwable?,
    message: String?,
    vararg args: Any
  ) {
    try {
      if (message?.isNotEmpty()!!) {
        Logger.e(throwable, message, args)
      }
    } catch (ex: Exception) {
      printMessageBySystem(message)
    }
  }

  fun warn(
    message: String?,
    vararg args: Any
  ) {
    try {
      if (message?.isNotEmpty()!!) {
        Logger.w(message, args)
      }
    } catch (ex: Exception) {
      printMessageBySystem(message)
    }
  }

  fun verbose(
    message: String?,
    vararg args: Any
  ) {
    try {
      if (message?.isNotEmpty()!!) {
        Logger.v(message, args)
      }
    } catch (ex: Exception) {
      printMessageBySystem(message)
    }
  }

  private fun printMessageBySystem(msg: String?) {
    if (!TextUtils.isEmpty(msg)) {
      try {
        println(msg)
      } catch (ex: Exception) {
        ex.printStackTrace()
      }
    }
  }
}