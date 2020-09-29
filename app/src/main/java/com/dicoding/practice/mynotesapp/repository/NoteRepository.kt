package com.dicoding.practice.mynotesapp.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.dicoding.practice.mynotesapp.database.Note
import com.dicoding.practice.mynotesapp.database.NoteDao
import com.dicoding.practice.mynotesapp.database.NoteDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository (application: Application) {
    private val notesDao: NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()


    init {
        val db = NoteDatabase.getDatabase(application)
        notesDao = db.noteDao()
    }

    fun getAllNotes(): LiveData<List<Note>> = notesDao.getAllNotes()

    fun insert(note: Note) {
        executorService.execute { notesDao.insert(note) }
    }

    fun delete(note: Note) {
        executorService.execute { notesDao.delete(note) }
    }

    fun update(note: Note) {
        executorService.execute { notesDao.update(note) }
    }

}