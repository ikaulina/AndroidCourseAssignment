package com.example.androidcourse

import android.content.Intent
import android.icu.text.CaseMap
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy.LOG
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity2 : AppCompatActivity() {
    private lateinit var adapter: KeepItemRecyclerAdapter
    private lateinit var layoutManager: StaggeredGridLayoutManager
    private var database = Firebase.database.reference
    private val keepItemList: MutableList<MainActivity.KeepItemText> = mutableListOf()
    private var keepItemList34: MutableList<MainActivity.KeepItemText> = mutableListOf()
    private var let = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(findViewById(R.id.toolbar))

        var gg = "";
        var hj = "";


        if(intent.hasExtra("NoteTitle")){
            gg = intent.getStringExtra("NoteTitle")
            textNoteHeader.setText(intent.getStringExtra("NoteTitle"))
        }

        if(intent.hasExtra("NoteBody")){
            hj = intent.getStringExtra("NoteBody")
            textNoteBody.setText(intent.getStringExtra("NoteBody"))
        }

        if(gg != "" || hj != ""){
            let = true;
        }
    }

    override fun onDestroy() {
        val it = MainActivity.KeepItemText(textNoteHeader.text.toString(),textNoteBody.text.toString())
        if(let){
            if(intent.hasExtra("NotePoz")){
                it.uuid = intent.getStringExtra("NotePoz")
                database.child("Notes").child(intent.getStringExtra("NotePoz")).setValue(it)
            }
        }
        else{
            val key = database.child("Notes").push().key
            if (key != null) {
                it.uuid = key
            }
            if (key != null) {
                database.child("Notes").child(key).setValue(it)
            }
          //  val key = database.getReference("Note").push().key
          //  database.getReference("KeepItemText").child("Title").setValue("test")
          //  database.getReference("KeepItemText").child("Body").setValue("test3")

          //  database.child("Notes").child("4").setValue(addedNote)
            /*
val it = MainActivity.Salad(textNoteHeader.text.toString(),textNoteBody.text.toString())
            val key = database.child("salads").push().key
            if (key != null) {
                it.uuid = key
            }
            if (key != null) {
                database.child("salads").child(key).setValue(it)
            }
*/

           // database.child("salads").child("-MIzRCg88vGmEPH4f3It").setValue(null)

            //val myRef = database.getReference("KeepItemText")



           // myRef.setValue("Hello, World!")
        }
        //adapter.notifyDataSetChanged()
        //mainItems.adapter.

      //  val intent= Intent(this, MainActivity2::class.java)
      //  activity
     //   startActivity(intent)

      //  adapter.addItem(it)
     //   (activity as MainActivity).addItem()

        super.onDestroy()
    }

}