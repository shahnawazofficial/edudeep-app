package com.example.edudeep

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

// Rename the class to DashboardItem instead of DashboardAdapter
class DashboardItem(
    private val items: List<Pair<String, String>>,
    private val clickListener: (String) -> Unit
) : RecyclerView.Adapter<DashboardItem.DashboardViewHolder>() {

    // ViewHolder class for holding the views for each item
    class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById(R.id.cardTitle)
        val desc: TextView = view.findViewById(R.id.cardDescription)
        val card: CardView = view.findViewById(R.id.dashboardCard)
    }

    // Creates and returns a ViewHolder for each item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dashboard_card_item, parent, false)
        return DashboardViewHolder(view)
    }

    // Binds data to each item in the RecyclerView
    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val (title, desc) = items[position]
        holder.title.text = title
        holder.desc.text = desc
        holder.card.setOnClickListener { clickListener(title) }
    }

    // Returns the number of items in the list
    override fun getItemCount() = items.size
}
