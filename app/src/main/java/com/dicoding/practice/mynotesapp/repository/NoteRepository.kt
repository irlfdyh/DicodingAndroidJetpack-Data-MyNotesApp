package com.dicoding.practice.mynotesapp.repository

import android.app.Application
import androidx.paging.DataSource
import com.dicoding.practice.mynotesapp.database.Note
import com.dicoding.practice.mynotesapp.database.NoteDao
import com.dicoding.practice.mynotesapp.database.NoteDatabase
import com.dicoding.practice.mynotesapp.helper.SortUtils
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository (application: Application) {

    private val notesDao: NoteDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = NoteDatabase.getDatabase(application)
        notesDao = db.noteDao()
    }

    fun getAllNotes(sort: String): DataSource.Factory<Int, Note> {
        val query = SortUtils.getSortedQuery(sort)
        return notesDao.getAllNotes(query)
    }

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
