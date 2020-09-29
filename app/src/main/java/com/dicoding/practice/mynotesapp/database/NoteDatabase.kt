package com.dicoding.practice.mynotesapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import java.util.concurrent.Executors

@Database(entities = [Note::class], version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao

    companion object {
        @Volatile
        private var INSTANCE: NoteDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): NoteDatabase {
            if (INSTANCE == null) {
                synchronized(NoteDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE  = Room.databaseBuilder(
                            context.applicationContext,
                            NoteDatabase::class.java,
                            "note_database"
                        )
                            .addCallback(object : Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    generateData()
                                }
                            })
                            .build()
                    }
                }
            }
            return INSTANCE as NoteDatabase
        }

        fun generateData() {
            Executors.newSingleThreadExecutor().execute {
                val list: MutableList<Note> = ArrayList()
                for (i in 0..99) {
                    val dummyNote = Note()
                    dummyNote.title = "Tugas $i"
                    dummyNote.description = "Belajar Modul $i"
                    dummyNote.title = "2019/09/09 09:09:0$i"
                    list.add(dummyNote)
                }
                INSTANCE?.noteDao()?.bulkInsert(list)
            }
        }

    }

}