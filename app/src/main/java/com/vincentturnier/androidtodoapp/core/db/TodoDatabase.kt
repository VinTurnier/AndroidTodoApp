package com.vincentturnier.androidtodoapp.core.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.vincentturnier.androidtodoapp.todo.converter.TodoConverter
import com.vincentturnier.androidtodoapp.todo.dao.TodoDao
import com.vincentturnier.androidtodoapp.todo.model.Todo

@Database(
    entities = [Todo::class], version = 1
)
@TypeConverters(TodoConverter::class)
abstract class TodoDatabase: RoomDatabase() {

    abstract fun todoDao(): TodoDao

    companion object {

        const val DATABASE_NAME = "todo-db"
    }
}