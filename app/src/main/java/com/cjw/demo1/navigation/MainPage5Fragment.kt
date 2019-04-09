package com.cjw.demo1.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.cjw.demo1.R
import com.cjw.demo1.viewmodel.RandomViewModel
import kotlinx.android.synthetic.main.fragment_main_page5.generate_number_bt
import kotlinx.android.synthetic.main.fragment_main_page5.number_tv

class MainPage5Fragment : Fragment() {

  private lateinit var randomViewModel: RandomViewModel

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
  }
}