package com.vincentturnier.androidtodoapp.todo.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "todo")
data class Todo(
    @PrimaryKey val id: Int? = null,
    @ColumnInfo(name ="title") var title: String,
    @ColumnInfo(name = "description") var description: String,
    @ColumnInfo(name = "priority") var priority: TodoPriority,
    @ColumnInfo(name = "datetime") var dateTime: LocalDateTime
)
