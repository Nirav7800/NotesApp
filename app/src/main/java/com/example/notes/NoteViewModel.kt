package com.example.notes

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NoteViewModel(application: Application) :AndroidViewModel(application) {


    private val repository:NoteRepository
    val allnotes:LiveData<List<Note>>
    init {
        val dao=NoteDatabase.getDatabase(application).getNoteDao()
        repository=NoteRepository(dao)
        allnotes=repository.allnotes
    }

    fun deletenote(note:Note)=viewModelScope.launch(Dispatchers.IO) {

         repository.delete(note)
    }

    fun insertnote(note:Note)=viewModelScope.launch(Dispatchers.IO) {
        repository.insert(note)
    }
}