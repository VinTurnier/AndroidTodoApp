package com.vincentturnier.androidtodoapp.todo.converter

import androidx.room.TypeConverter
import com.vincentturnier.androidtodoapp.todo.model.TodoPriority
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*

class TodoConverter {

    @TypeConverter
    fun toTodoPriority(value: String) = enumValueOf<TodoPriority>(value)

    @TypeConverter
    fun fromTodoPriority(value: TodoPriority) = value.name

    @TypeConverter
    fun toLocalDateTime(value: Long) = LocalDateTime.ofInstant(
        Instant.ofEpochMilli(value),
        ZoneOffset.UTC)

    @TypeConverter
    fun fromLocalDateTime(value: LocalDateTime): Long = value.toEpochSecond(ZoneOffset.UTC)
}