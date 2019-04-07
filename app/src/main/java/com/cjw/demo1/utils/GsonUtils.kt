package com.cjw.demo1.utils

import com.google.gson.Gson
import java.net.URLEncoder

class GsonUtils {

  companion object {

    fun toJson(obj: Any): String {
      val gson = Gson()
      return URLEncoder.encode(gson.toJson(obj), "utf-8")
    }
  }
}