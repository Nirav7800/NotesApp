package com.example.notes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), INotedRvAdapter {

    lateinit var viewModel: NoteViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvView.layoutManager=LinearLayoutManager(this)

        val adapter=NotesRvAdapter(this,this)
        rvView.adapter=adapter
        viewModel=ViewModelProvider(this,
        ViewModelProvider.AndroidViewModelFactory.getInstance(application)).
        get(NoteViewModel::class.java)
        viewModel.allnotes.observe(this, Observer {
            it?.let {
                adapter.updateList(it)
            }

        })


    }

    override fun onitemClicked(note: Note) {
  viewModel.deletenote(note)
        Toast.makeText(this,"${note.text} Deleted",Toast.LENGTH_LONG).show()
    }

    fun SubmitData(view: View) {

        val noteText=edttext.text.toString()
        if(noteText.isNotEmpty())
        {
            viewModel.insertnote(Note(noteText))
            Toast.makeText(this,"${noteText} is Inserted",Toast.LENGTH_LONG).show()
        }
    }
}