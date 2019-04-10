package com.cjw.demo1.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import com.cjw.demo1.R
import com.cjw.demo1.logger.Log
import com.cjw.demo1.utils.RandomUtils
import com.cjw.demo1.viewmodel.DataViewModel
import com.cjw.demo1.viewmodel.RandomViewModel
import kotlinx.android.synthetic.main.fragment_main_page5.bt1
import kotlinx.android.synthetic.main.fragment_main_page5.data_tv
import kotlinx.android.synthetic.main.fragment_main_page5.generate_number_bt
import kotlinx.android.synthetic.main.fragment_main_page5.number_tv
import kotlinx.android.synthetic.main.fragment_main_page5.send_bt

class MainPage5Fragment : Fragment() {

  private lateinit var randomViewModel: RandomViewModel
  private lateinit var activityDataViewModel: DataViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_main_page5, container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    randomViewModel = ViewModelProviders.of(this)
        .get(RandomViewModel::class.java)

    activityDataViewModel = ViewModelProviders.of(activity!!)
        .get(DataViewModel::class.java)

    randomViewModel.randomLiveData.observe(this, Observer<Int> {
      number_tv.text = getString(R.string.view_model_page_random_number, it)
    })

    generate_number_bt.setOnClickListener {
      randomViewModel.createNumberValue()
    }

    val numberValue = randomViewModel.getNumberValue()
    if (numberValue != null) {
      number_tv.text = getString(R.string.view_model_page_random_number, numberValue)
    }

    bt1.setOnClickListener {
      Navigation.findNavController(it)
          .navigate(R.id.action_mainPage5Fragment_to_mainPage6Fragment)
    }

    activityDataViewModel.objLiveData.observe(activity!!, Observer {
      if (data_tv == null) {
        view.findViewById<TextView>(R.id.data_tv)
            .text = it
        return@Observer
      }
      data_tv.text = it
    })

    val dataValue = activityDataViewModel.getDataValue()
    if (dataValue != null) {
      data_tv.text = dataValue
    }

    send_bt.setOnClickListener {
      val newData = RandomUtils.nextString(4)
      Log.debug(getString(R.string.generate_new_random_number_data_string, newData))
      activityDataViewModel.setDataValue(newData)
    }

  }
}