package com.dicoding.practice.mynotesapp.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.practice.mynotesapp.NoteAdapter
import com.dicoding.practice.mynotesapp.R
import com.dicoding.practice.mynotesapp.database.Note
import com.dicoding.practice.mynotesapp.ui.ViewModelFactory
import com.dicoding.practice.mynotesapp.ui.insert.NoteAddUpdateActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: NoteAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = obtainViewModel(this)
        viewModel.getAllNotes().observe(this, noteObserver)

        adapter = NoteAdapter(this@MainActivity)
        rv_notes.layoutManager = LinearLayoutManager(this)
        rv_notes.setHasFixedSize(true)
        rv_notes.adapter = adapter

        fab_add.setOnClickListener {
            val intent = Intent(this@MainActivity, NoteAddUpdateActivity::class.java)
            startActivityForResult(intent, NoteAddUpdateActivity.REQUEST_ADD)
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null) {
            if (requestCode == NoteAddUpdateActivity.REQUEST_ADD) {
                if (resultCode == NoteAddUpdateActivity.RESULT_ADD) {
                    showSnackbarMessage(getString(R.string.added))
                }
            } else if (requestCode == NoteAddUpdateActivity.REQUEST_UPDATE) {
                if (resultCode == NoteAddUpdateActivity.RESULT_UPDATE) {
                    showSnackbarMessage(getString(R.string.changed))
                } else if (resultCode == NoteAddUpdateActivity.RESULT_DELETE) {
                    showSnackbarMessage(getString(R.string.deleted))
                }
            }
        }
    }

    private val noteObserver = Observer<List<Note>> { noteList ->
        if (noteList != null) {
            adapter.setListNotes(noteList)
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): MainViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MainViewModel::class.java]
    }

    private fun showSnackbarMessage(message: String) {
        Snackbar.make(rv_notes, message, Snackbar.LENGTH_SHORT).show()
    }

}