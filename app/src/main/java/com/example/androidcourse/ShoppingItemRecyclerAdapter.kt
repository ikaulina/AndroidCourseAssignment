package com.example.androidcourse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_shopping.view.*
/*
class ShoppingItemRecyclerAdapter(private val items: MutableList<ShoppingItem>) :
    RecyclerView.Adapter<ShoppingItemRecyclerAdapter.ShoppingViewHolder>() {

    class ShoppingViewHolder(view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShoppingViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_shopping, parent, false)
        return ShoppingViewHolder(view)
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ShoppingViewHolder, position: Int) {
        val item = items[position]
        val context = holder.itemView.context
        holder.itemView.shoppingName.text = item.name
        holder.itemView.shoppingQuantity.text = context.resources
            .getString(R.string.quantity_text, item.quantity, item.unit)

        loadIcon(holder.itemView, item)

        holder.itemView.setOnClickListener {
            Toast.makeText(context, item.name, Toast.LENGTH_SHORT).show()
        }

        holder.itemView.shoppingRemove.setOnClickListener {
            items.removeAt(position)
            notifyDataSetChanged()
        }
    }

    private fun loadIcon(itemView: View, item: ShoppingItem) {
        itemView.shoppingIcon.visibility =
            if (item.imageUrl == null) View.INVISIBLE else View.VISIBLE
        item.imageUrl?.let {
            Glide.with(itemView)
                .load(it)
                .circleCrop()
                .into(itemView.shoppingIcon)
        }
    }
}

 */