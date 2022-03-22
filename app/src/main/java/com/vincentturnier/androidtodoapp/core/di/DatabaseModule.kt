package com.vincentturnier.androidtodoapp.core.di

import android.content.Context
import androidx.room.Room
import com.vincentturnier.androidtodoapp.core.db.TodoDatabase
import com.vincentturnier.androidtodoapp.core.db.TodoDatabase.Companion.DATABASE_NAME
import com.vincentturnier.androidtodoapp.todo.dao.TodoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideTodoDatabase(@ApplicationContext context: Context): TodoDatabase {
        return Room.databaseBuilder(
            context,
            TodoDatabase::class.java,
            DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideTodoDao(database: TodoDatabase): TodoDao = database.todoDao()
}