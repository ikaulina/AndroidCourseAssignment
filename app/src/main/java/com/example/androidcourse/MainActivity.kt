package com.example.androidcourse

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
import kotlinx.android.synthetic.main.activity_main.*
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController

class MainActivity : AppCompatActivity() {

    private var keepItems = RandomData.setList

    private lateinit var adapter: KeepItemRecyclerAdapter
    private lateinit var layoutManager: StaggeredGridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        layoutManager =
            StaggeredGridLayoutManager(
                resources.getInteger(R.integer.span_count), StaggeredGridLayoutManager.VERTICAL
            ).apply {
                gapStrategy = GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            }
        mainItems.layoutManager = layoutManager
        adapter = KeepItemRecyclerAdapter(keepItems)
        mainItems.adapter = adapter

        mainButtonAddText.setOnClickListener { addRandomKeepItem(RandomData.textItem) }
       // mainButtonAddImage.setOnClickListener { addRandomKeepItem(RandomData.imageItem) }
       // mainButtonAddRadio.setOnClickListener { addRandomKeepItem(RandomData.radioItem) }

        floatingAddButton.setOnClickListener{
            val intent=Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }

    private fun addRandomKeepItem(item: KeepItemText) {
        adapter.notifyItemInserted(0)
        mainItems.smoothScrollToPosition(0)
    }
}