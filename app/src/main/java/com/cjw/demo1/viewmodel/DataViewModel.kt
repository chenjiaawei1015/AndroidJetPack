package com.cjw.demo1.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData

class DataViewModel(application: Application) : AndroidViewModel(application) {

  val objLiveData: MutableLiveData<String> by lazy {
    MutableLiveData<String>()
  }

  fun setDataValue(data: String) {
    objLiveData.postValue(data)
  }

  fun getDataValue() = objLiveData.value

}