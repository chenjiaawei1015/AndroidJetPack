package com.cjw.demo1.utils

import java.security.SecureRandom

object RandomUtils : SecureRandom() {

  fun nextString(length: Int): String {
    val dataArr =
      "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()[]{}-=_+;':\"<>,./?\\"

    val builder = StringBuilder()
    for (index in 0 until length) {
      builder.append(dataArr[nextInt(dataArr.length)])
    }
    return builder.toString()
  }

  fun nextInt(
    start: Int,
    end: Int
  ) = start + nextInt(end - start + 1)

}