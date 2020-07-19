package com.example.boredapp.ui.finished

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.boredapp.R
import kotlinx.android.synthetic.main.fragment_done_list.*

class FinishedActivitiesFragment : Fragment() {

    private lateinit var viewModel: FinishedActivitiesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewModel =
                ViewModelProviders.of(this).get(FinishedActivitiesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_done_list, container, false)
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val finishedActivitiesAdapter = FinishedActivitiesAdapter()
        finishedActivitiesRv.adapter = finishedActivitiesAdapter
        finishedActivitiesRv.layoutManager = LinearLayoutManager(this.requireContext(), RecyclerView.VERTICAL, false)

        viewModel.finishedActivities.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                emptyLayout.visibility = View.VISIBLE
            } else {
                emptyLayout.visibility = View.GONE
            }

            finishedActivitiesAdapter.setActivities(it)
        })

    }
}