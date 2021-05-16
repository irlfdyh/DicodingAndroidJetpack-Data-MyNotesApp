package com.dicoding.practice.mynotesapp.ui.insert

import android.app.Application
import androidx.lifecycle.ViewModel
import com.dicoding.practice.mynotesapp.database.Note
import com.dicoding.practice.mynotesapp.repository.NoteRepository

class NoteAddUpdateViewModel (application: Application) : ViewModel() {

    private val _repository: NoteRepository = NoteRepository(application)

    fun insert(note: Note) {
        _repository.insert(note)
    }

    fun update(note: Note) {
        _repository.update(note)
    }

    fun delete(note: Note) {
        _repository.delete(note)
    }
}