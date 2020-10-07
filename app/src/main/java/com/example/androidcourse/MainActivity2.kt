package com.example.androidcourse

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlinx.android.synthetic.main.content_main.*

class MainActivity2 : AppCompatActivity() {

    private var database = Firebase.database.reference
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
            if(textNoteHeader.text.toString() !="" || textNoteBody.text.toString() != ""){
                val key = database.child("Notes").push().key
                if (key != null) {
                    it.uuid = key
                }
                if (key != null) {
                    database.child("Notes").child(key).setValue(it)
                }
            }
        }
        Toast.makeText(this,"Saved",Toast.LENGTH_LONG).show()
        super.onDestroy()
    }

}