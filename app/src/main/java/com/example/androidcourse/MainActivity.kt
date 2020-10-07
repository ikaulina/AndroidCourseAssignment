package com.example.androidcourse

import android.content.Intent
import android.os.Bundle
import android.text.method.TextKeyListener.clear
import android.util.Log
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    //private var keepItems = RandomData.setList

    //private val menu: MutableList<Salad> = mutableListOf()
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

        //loadDatabase(database)
        //initSaladMenu(database)

        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                keepItemList.clear()
                dataSnapshot.children.mapNotNullTo(keepItemList) { it.getValue<KeepItemText>(KeepItemText::class.java) }
                Log.e("TAG4",keepItemList.toMutableList().count().toString())
                keepItemList34 = keepItemList.toMutableList()
                mainItems.layoutManager = layoutManager
                adapter = KeepItemRecyclerAdapter(keepItemList34)
                mainItems.adapter = adapter
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled ${databaseError.toException()}")
            }
        }
        database.child("Notes").addValueEventListener(menuListener)
            //.addListenerForSingleValueEvent(menuListener)
/*
        mainItems.layoutManager = layoutManager
        adapter = KeepItemRecyclerAdapter(keepItemList34)
        Log.e("TAG",keepItemList34.toMutableList().toString())
        //adapter = KeepItemRecyclerAdapter(menu)
        mainItems.adapter = adapter */

        //addRandomKeepItem( KeepItemText("Transports", "Autobusu saraksts","556"))



        //Log.e("TAG",menu.toString())
        //print(menu)
        //Toast.makeText(this, menu.first().toString(),Toast.LENGTH_LONG).show()

        //mainButtonAddText.setOnClickListener { addRandomKeepItem(KeepItemText("","","")) }
       // mainButtonAddImage.setOnClickListener { addRandomKeepItem(RandomData.imageItem) }
       // mainButtonAddRadio.setOnClickListener { addRandomKeepItem(RandomData.radioItem) }

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
        //Thread.sleep(5_000)
        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                keepItemList.clear()
                dataSnapshot.children.mapNotNullTo(keepItemList) { it.getValue<KeepItemText>(KeepItemText::class.java) }
                Log.e("TAGYY",keepItemList.toMutableList().count().toString())
                keepItemList34.clear()
                keepItemList34 = keepItemList.toMutableList()
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
    }

    private fun initSaladMenu(firebaseData: DatabaseReference) {
        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                keepItemList.clear()
                dataSnapshot.children.mapNotNullTo(keepItemList) { it.getValue<KeepItemText>(KeepItemText::class.java) }
            Log.e("TAG4",keepItemList.toMutableList().count().toString())
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled ${databaseError.toException()}")
            }
        }
        firebaseData.child("Notes").addListenerForSingleValueEvent(menuListener)
    }

    private fun addRandomKeepItem(item: KeepItemText) {
        adapter.notifyItemInserted(0)
        mainItems.smoothScrollToPosition(0)
    }
}