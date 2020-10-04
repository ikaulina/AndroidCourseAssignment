package com.example.androidcourse

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_keep.view.*
import kotlinx.android.synthetic.main.item_keep.view.keepClose
import kotlinx.android.synthetic.main.item_keep_image.view.*
import kotlinx.android.synthetic.main.item_keep_radio.view.*
import kotlin.coroutines.CoroutineContext

class KeepItemRecyclerAdapter(private val items: MutableList<KeepItemText>) :
    RecyclerView.Adapter<KeepItemRecyclerAdapter.KeepViewHolder>() {

    abstract class KeepViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(position: Int)
    }

    inner class TextViewHolder(view: View) : KeepViewHolder(view) {
        override fun bind(position: Int) {
            val item = items[position] as KeepItemText
            itemView.keepTitle.text = item.title
            itemView.keepText.text = item.text
        }
    }

    inner class ImageViewHolder(view: View) : KeepViewHolder(view) {
        override fun bind(position: Int) {
            val item = items[position] as KeepItemImage
            Glide.with(itemView)
                .load(item.uri)
                .into(itemView.keepImage)
        }
    }

    inner class RadioViewHolder(view: View) : KeepViewHolder(view) {
        override fun bind(position: Int) {
            val item = items[position] as KeepItemRadio
            itemView.option1.text = item.a
            itemView.option2.text = item.b
            itemView.option3.text = item.c
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeepViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            IMAGE_KEEP -> ImageViewHolder(inflater.inflate(R.layout.item_keep_image, parent, false))
            TEXT_KEEP -> TextViewHolder(inflater.inflate(R.layout.item_keep, parent, false))
            else -> RadioViewHolder(inflater.inflate(R.layout.item_keep_radio, parent, false))
        }
    }

    override fun getItemViewType(position: Int) =
        when (items[position]) {
            is KeepItemText -> TEXT_KEEP
            //is KeepItemImage -> IMAGE_KEEP
            else -> RADIO_KEEP
        }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: KeepViewHolder, position: Int) {
        holder.bind(position)
        val item = items[position]
        val context = holder.itemView.context

        holder.itemView.keepClose.setOnClickListener {
            val currentPosition = items.indexOf(item)
            items.removeAt(currentPosition)
            notifyItemRemoved(currentPosition)
        }
        holder.itemView.setOnClickListener {
            val intent = Intent(context, MainActivity2::class.java)
            intent.putExtra("NoteTitle",item.title)
            intent.putExtra("NoteBody",item.text)
            intent.putExtra("NotePoz", items.indexOf(item))
            context.startActivity(intent)
        }
    }

    companion object {
        private const val TEXT_KEEP = 0
        private const val IMAGE_KEEP = 1
        private const val RADIO_KEEP = 2
    }

}