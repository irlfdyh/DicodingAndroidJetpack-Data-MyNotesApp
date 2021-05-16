package com.dicoding.practice.mynotesapp.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.practice.mynotesapp.ui.insert.NoteAddUpdateViewModel
import com.dicoding.practice.mynotesapp.ui.main.MainViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory private constructor(
    private val application: Application
): ViewModelProvider.Factory {

    companion object {

        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = ViewModelFactory(application)
                    }
                }
            }
            return INSTANCE as ViewModelFactory
        }

    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(application) as T
            }
            modelClass.isAssignableFrom(NoteAddUpdateViewModel::class.java) -> {
                NoteAddUpdateViewModel(application) as T
            }
            else -> "Unknown View Model" as T
        }
    }

}