package com.example.edudeep

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView

// Adapter class for the Dashboard items
class DashboardAdapter(
    private val items: List<Pair<String, String>>,  // List of items (title, description)
    private val clickListener: (String) -> Unit    // Lambda function for handling click events
) : RecyclerView.Adapter<DashboardAdapter.DashboardViewHolder>() {

    // ViewHolder class to hold the views for each item
    class DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.cardTitle)           // Title TextView
        val descriptionTextView: TextView = view.findViewById(R.id.cardDescription) // Description TextView
        val cardView: CardView = view.findViewById(R.id.dashboardCard)           // CardView
    }

    // Creates and returns a ViewHolder object when a new item is created
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.dashboard_card_item, parent, false)  // Inflate the layout for each item
        return DashboardViewHolder(view)  // Return a new ViewHolder
    }

    // Binds data to the views in each ViewHolder
    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val (title, desc) = items[position]  // Get the current item (title, description)
        holder.titleTextView.text = title   // Set title text
        holder.descriptionTextView.text = desc  // Set description text

        // Set a click listener on the card
        holder.cardView.setOnClickListener { clickListener(title) }
    }

    // Return the total number of items
    override fun getItemCount() = items.size
}
