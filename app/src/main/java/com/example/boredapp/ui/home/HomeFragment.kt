package com.example.boredapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.boredapp.R
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        declineButton.setOnClickListener {
            homeViewModel.getRandomActivity()
        }

        homeViewModel.randomActivity.observe(viewLifecycleOwner, Observer {
            text_home.text = it.activity
        })

        homeViewModel.dataVisibility.observe(viewLifecycleOwner, Observer {
            text_home.visibility = if (it) View.VISIBLE else View.INVISIBLE
            acceptButton.visibility = if (it) View.VISIBLE else View.INVISIBLE
            declineButton.visibility = if (it) View.VISIBLE else View.INVISIBLE
        })

        homeViewModel.progressBar.observe(viewLifecycleOwner, Observer {
            progressBar.visibility = if (it) View.VISIBLE else View.INVISIBLE
        })
    }
}