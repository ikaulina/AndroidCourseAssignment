package com.example.androidcourse

import android.icu.text.CaseMap
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity2 : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        setSupportActionBar(findViewById(R.id.toolbar))

        //val toast = Toast.makeText(this, intent.getIntExtra("NotePoz",0),Toast.LENGTH_LONG)
        //toast.show()

        textNoteHeader.setText(intent.getStringExtra("NoteTitle"))
        textNoteBody.setText(intent.getStringExtra("NoteBody"))

        val gh = textNoteHeader.text;
        val hj = textNoteBody.text;

        if(gh.isNotEmpty() || hj.isNotEmpty()){
            if(RandomData.setList.isNotEmpty()){
               // RandomData.setList.remove(
               //     KeepItemText(
               //         textNoteHeader.text.toString(),
               //         textNoteBody.text.toString()
               //     )
               // )
                RandomData.setList.removeAt(intent.getIntExtra("NotePoz",0))
            }
        }
    }

    override fun onDestroy() {
        if(textNoteHeader.text.isNotEmpty() || textNoteBody.text.isNotEmpty()){
            var addedNote = KeepItemText(textNoteHeader.text.toString(),textNoteBody.text.toString())
            RandomData.setList.add(intent.getIntExtra("NotePoz",0), addedNote)
        }
        super.onDestroy()
    }
}