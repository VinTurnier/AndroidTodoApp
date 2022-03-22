package com.vincentturnier.androidtodoapp.todo.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.vincentturnier.androidtodoapp.todo.model.Todo
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Single

@Dao
interface TodoDao {

    @Query("select * from todo")
    fun all(): Flowable<List<Todo>>

    @Insert(onConflict = REPLACE)
    fun insert(todo: Todo): Single<Long>

    @Query("Delete from todo where id = :id")
    fun delete(id: Int): Maybe<Int>
}