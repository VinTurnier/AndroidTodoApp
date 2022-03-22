package com.vincentturnier.androidtodoapp.todo.repository

import com.vincentturnier.androidtodoapp.todo.dao.TodoDao
import com.vincentturnier.androidtodoapp.todo.model.Todo
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject
import javax.inject.Singleton

class TodoRepository @Inject constructor(
    private val todoDao: TodoDao
) {

    fun addTodo(todo: Todo) = todoDao.insert(todo)

    fun allTodos(): Flowable<List<Todo>> = todoDao.all()

    fun todo(id: Int) = todoDao.getTodoBy(id)

}