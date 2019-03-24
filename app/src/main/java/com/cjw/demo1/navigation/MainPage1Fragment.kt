package com.cjw.demo1.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.cjw.demo1.R
import kotlinx.android.synthetic.main.fragment_main_page1.*

class MainPage1Fragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_main_page1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bt1.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_mainPage1Fragment_to_mainPage2Fragment)
        }

        bt2.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_mainPage1Fragment_to_mainPage3Fragment)
        }

        bt3.setOnClickListener {
            Navigation.findNavController(it).navigateUp()
        }

        bt4.setOnClickListener {
            activity?.finish()
        }
    }
}