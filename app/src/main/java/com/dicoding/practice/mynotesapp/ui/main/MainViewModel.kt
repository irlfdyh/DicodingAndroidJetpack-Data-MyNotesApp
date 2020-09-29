package com.dicoding.practice.mynotesapp.ui.main

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.practice.mynotesapp.database.Note
import com.dicoding.practice.mynotesapp.repository.NoteRepository

class MainViewModel (application: Application) : ViewModel() {

    private val _repository: NoteRepository = NoteRepository(application)

    fun getAllNotes(): LiveData<PagedList<Note>>
            = LivePagedListBuilder(_repository.getAllNotes(), 20).build()

}