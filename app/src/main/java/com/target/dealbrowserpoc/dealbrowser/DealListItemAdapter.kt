package com.target.dealbrowserpoc.dealbrowser

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.target.dealbrowserpoc.dealbrowser.service.model.DealItem
import kotlinx.android.synthetic.main.deal_list_item.view.*
import kotlinx.android.synthetic.main.fragment_details.*

class DealListItemAdapter(
        private val context: Context,
        private val items: List<DealItem>,
        private val onItemClickListener: OnItemClickListener) : RecyclerView.Adapter<DealListItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.deal_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemPrice.text = item.originalPrice
        holder.title.text = item.title
        Picasso.with(context).load(item.image).placeholder(R.drawable.image_14741397)
                .fit().into(holder.productImage)
        holder.location.text = item.aisle
        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClicked(item)
        }
    }

    private fun getItem(position: Int): DealItem {
        return items.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.deal_list_item_image_view
        val title: TextView = itemView.deal_list_item_title
        val itemPrice: TextView = itemView.deal_list_item_price
        val location: TextView = itemView.location
    }

    interface OnItemClickListener {
        fun onItemClicked(item: DealItem)
    }
}