package com.example.androidcourse

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.item_keep.view.*
import kotlinx.android.synthetic.main.item_keep.view.keepClose

class KeepItemRecyclerAdapter(private val items: MutableList<MainActivity.KeepItemText>) :
    RecyclerView.Adapter<KeepItemRecyclerAdapter.KeepViewHolder>() {

    private val database = Firebase.database.reference

    abstract class KeepViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        abstract fun bind(position: Int)
    }


inner class TextViewHolder(view: View) : KeepViewHolder(view) {
    override fun bind(position: Int) {
        val item = items[position] as MainActivity.KeepItemText
        itemView.keepTitle.text = item.title.toString()
        itemView.keepText.text = item.description.toString()
    }
}

override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeepViewHolder {
    val inflater = LayoutInflater.from(parent.context)
    Log.e("Error",viewType.toString())
   return TextViewHolder(inflater.inflate(R.layout.item_keep, parent, false))
}

override fun getItemCount() = items.size

override fun onBindViewHolder(holder: KeepViewHolder, position: Int) {
    holder.bind(position)
    val item = items[position]
    val context = holder.itemView.context

    holder.itemView.keepClose.setOnClickListener {
        val currentPosition = items.indexOf(item)
        items.removeAt(currentPosition)
        database.child("Notes").child(item.uuid).setValue(null) //Firebase delete
        Toast.makeText(context,"Deleted",Toast.LENGTH_LONG).show()
    }
    holder.itemView.setOnClickListener {
        val intent = Intent(context, MainActivity2::class.java)
        intent.putExtra("NoteTitle",item.title)
        intent.putExtra("NoteBody",item.description)
        intent.putExtra("NotePoz", item.uuid)
        context.startActivity(intent)
    }
}

}