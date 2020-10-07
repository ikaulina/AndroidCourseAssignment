package com.example.androidcourse

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
import kotlinx.android.synthetic.main.activity_main.*
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    val database = Firebase.database.reference
    private val keepItemList: MutableList<KeepItemText> = mutableListOf()
    private var keepItemList34: MutableList<KeepItemText> = mutableListOf()

    private lateinit var adapter: KeepItemRecyclerAdapter
    private lateinit var layoutManager: StaggeredGridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.e("TAG", "works!!")

        layoutManager =
            StaggeredGridLayoutManager(
                resources.getInteger(R.integer.span_count), StaggeredGridLayoutManager.VERTICAL
            ).apply {
                gapStrategy = GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            }

        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                keepItemList.clear()
                dataSnapshot.children.mapNotNullTo(keepItemList) { it.getValue<KeepItemText>(KeepItemText::class.java) }
                keepItemList34 = keepItemList.toMutableList()
                Log.e("keepListCreate",keepItemList34.toMutableList().toString())
                mainItems.layoutManager = layoutManager
                adapter = KeepItemRecyclerAdapter(keepItemList34)
                mainItems.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled ${databaseError.toException()}")
            }
        }
        database.child("Notes").addValueEventListener(menuListener)
        floatingAddButton.setOnClickListener{
            val intent=Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    override fun onRestart() {
        layoutManager =
            StaggeredGridLayoutManager(
                resources.getInteger(R.integer.span_count), StaggeredGridLayoutManager.VERTICAL
            ).apply {
                gapStrategy = GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            }
        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                keepItemList.clear()
                dataSnapshot.children.mapNotNullTo(keepItemList) { it.getValue<KeepItemText>(KeepItemText::class.java) }
                keepItemList34.clear()
                keepItemList34 = keepItemList.toMutableList()
                Log.e("keepListUpdate",keepItemList34.toMutableList().toString())
                mainItems.layoutManager = layoutManager
                adapter = KeepItemRecyclerAdapter(keepItemList34)
                mainItems.adapter = adapter
                adapter.notifyDataSetChanged()
                mainItems.smoothScrollToPosition(0)
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled ${databaseError.toException()}")
            }
        }
        database.child("Notes").addValueEventListener(menuListener)
        super.onRestart()
    }

    data class KeepItemText(
        val title: String = "",
        val description: String = "",
        var uuid: String = "")
 /*
    fun loadDatabase(firebaseData: DatabaseReference) {
        val availableSalads: List<KeepItemText> = mutableListOf(
            KeepItemText("Transports", "Autobusu saraksts"),
            KeepItemText("Anna", "Piezvanīt")
        )
        availableSalads.forEach {
            val key = firebaseData.child("Notes").push().key
            if (key != null) {
                it.uuid = key
            }
            if (key != null) {
                firebaseData.child("Notes").child(key).setValue(it)
            }
        }
    } */
}