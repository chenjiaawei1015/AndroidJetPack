package com.cjw.demo1.utils

import com.google.gson.Gson

class GsonUtils {

  companion object {

    fun toJson(obj: Any): String {
      return Gson().toJson(obj)
    }
  }
}