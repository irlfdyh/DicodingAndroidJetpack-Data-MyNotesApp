package com.dicoding.practice.mynotesapp.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.practice.mynotesapp.database.Note
import com.dicoding.practice.mynotesapp.repository.NoteRepository

class MainViewModel (application: Application) : ViewModel() {

    private val _repository: NoteRepository = NoteRepository(application)

    fun getAllNotes(): LiveData<List<Note>> = _repository.getAllNotes()

}