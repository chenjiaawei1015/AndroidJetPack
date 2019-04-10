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
import kotlinx.android.synthetic.main.fragment_main_page6.bt1
import kotlinx.android.synthetic.main.fragment_main_page6.data_tv
import kotlinx.android.synthetic.main.fragment_main_page6.send_bt

class MainPage6Fragment : Fragment() {

  private lateinit var activityViewModel: DataViewModel

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_main_page6, container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    bt1.setOnClickListener {
      Navigation.findNavController(it)
          .navigate(R.id.action_mainPage6Fragment_to_mainPage5Fragment)
    }

    activityViewModel = ViewModelProviders.of(activity!!)
        .get(DataViewModel::class.java)

    activityViewModel.objLiveData.observe(activity!!, Observer {
      if (data_tv == null) {
        view.findViewById<TextView>(R.id.data_tv)
            .text = it
        return@Observer
      }
      data_tv.text = it
    })

    if (activityViewModel.getDataValue() != null) {
      data_tv.text = activityViewModel.getDataValue()
    }

    send_bt.setOnClickListener {
      val newData = RandomUtils.nextString(4)
      Log.debug(getString(R.string.generate_new_random_number_data_string, newData))
      activityViewModel.setDataValue(newData)
    }
  }
}