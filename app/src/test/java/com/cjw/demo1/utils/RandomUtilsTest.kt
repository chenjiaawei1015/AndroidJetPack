package com.cjw.demo1.utils

import org.junit.Test

class RandomUtilsTest {

  @Test
  fun nextString() {
    println(RandomUtils.nextString(6))
  }

  @Test
  fun nextInt() {
    for (i in 0..30) {
      println(RandomUtils.nextInt(10, 13))
    }
  }
}