package br.edu.satc.todolistcompose.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TaskData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract fun taskDao(): TaskDao
}