package com.cjw.demo1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.cjw.demo1.R
import com.cjw.demo1.logger.Log
import com.cjw.demo1.utils.RandomUtils

class RandomViewModel(application: Application) : AndroidViewModel(application) {

  val randomLiveData: MutableLiveData<Int> by lazy {
    MutableLiveData<Int>()
  }

  fun getNumberValue(): Int? {
    return if (randomLiveData.value == null) {
      createNumberValue()
      null
    } else {
      Log.info(
          getApplication<Application>().getString(
              R.string.get_random_number_data, randomLiveData.value
          )
      )
      randomLiveData.value
    }
  }

  fun createNumberValue() {
    val randomNumber = RandomUtils.nextInt(1, 100)
    Log.info(
        getApplication<Application>().getString(
            R.string.generate_new_random_number_data, randomNumber
        )
    )
    randomLiveData.value = randomNumber
  }

  // ViewModel 在销毁的时候会回调 onClear 方法
  override fun onCleared() {
    super.onCleared()
    Log.info(getApplication<Application>().getString(R.string.view_model_clear))
  }

}