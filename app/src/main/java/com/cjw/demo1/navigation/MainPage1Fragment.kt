package com.cjw.demo1.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.cjw.demo1.R
import kotlinx.android.synthetic.main.fragment_main_page1.bt1
import kotlinx.android.synthetic.main.fragment_main_page1.bt2
import kotlinx.android.synthetic.main.fragment_main_page1.bt3
import kotlinx.android.synthetic.main.fragment_main_page1.bt4
import kotlinx.android.synthetic.main.fragment_main_page1.bt5
import kotlinx.android.synthetic.main.fragment_main_page1.bt6
import kotlinx.android.synthetic.main.fragment_main_page1.bt7
import kotlinx.android.synthetic.main.fragment_main_page1.bt8
import kotlinx.android.synthetic.main.fragment_main_page1.et1

class MainPage1Fragment : Fragment() {

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    return inflater.inflate(R.layout.fragment_main_page1, container, false)
  }

  override fun onViewCreated(
    view: View,
    savedInstanceState: Bundle?
  ) {
    super.onViewCreated(view, savedInstanceState)
    bt1.setOnClickListener {
      Navigation.findNavController(it)
          .navigate(R.id.action_mainPage1Fragment_to_mainPage2Fragment)
    }

    bt2.setOnClickListener {
      Navigation.findNavController(it)
          .navigate(R.id.action_mainPage1Fragment_to_mainPage3Fragment)
    }

    bt3.setOnClickListener {
      Navigation.findNavController(it)
          .navigateUp()
    }

    bt4.setOnClickListener {
      activity?.finish()
    }

    bt5.setOnClickListener {
      val bundle = Bundle()
      bundle.putString(getString(R.string.args_et_text1), et1.text.toString())
      Navigation.findNavController(it)
          .navigate(R.id.action_mainPage1Fragment_to_mainPage2Fragment, bundle)
    }

    bt6.setOnClickListener {
      var bundle = Bundle()
      bundle.putString(getString(R.string.args_et_text2), et1.text.toString())
      bundle = MainPage1FragmentArgs.fromBundle(bundle)
          .toBundle()
      Navigation.findNavController(it)
          .navigate(R.id.action_mainPage1Fragment_to_mainPage2Fragment, bundle)
    }

    bt7.setOnClickListener {
      Navigation.findNavController(it)
          .navigate(R.id.action_mainPage1Fragment_to_mainPage5Fragment)
    }

    bt8.setOnClickListener {
      Navigation.findNavController(it)
          .navigate(R.id.action_mainPage1Fragment_to_mainPage6Fragment)
    }

    val defaultEtData = arguments?.getString(getString(R.string.value_fragment_page1_et_text))
    et1.setText(defaultEtData)
  }
}