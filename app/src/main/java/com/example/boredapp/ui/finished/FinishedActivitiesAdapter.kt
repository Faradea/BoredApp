package com.example.boredapp.ui.finished

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.boredapp.R
import com.example.boredapp.ui.model.Activity
import kotlinx.android.synthetic.main.item_finished_activity.view.*
import java.text.DateFormat
import java.util.*

class FinishedActivitiesAdapter : RecyclerView.Adapter<FinishedActivitiesAdapter.ViewHolder>() {

    private var mItems: List<Activity> = emptyList()

    fun setActivities(activities: List<Activity>) {
        mItems = activities
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = mItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_finished_activity, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mItems[position]

        holder.title.text = item.activity
        item.finishedAt?.let {
            holder.finishedAt.text = android.text.format.DateFormat.format("dd-MM-yyyy HH:mm", Date(it))
        }

        holder.setItem(item)
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private var mItem: Activity? = null

        val title: TextView = view.activityTitle
        val finishedAt: TextView = view.activityFinishedAt

        fun setItem(item:Activity) {
            mItem = item
        }
    }
}