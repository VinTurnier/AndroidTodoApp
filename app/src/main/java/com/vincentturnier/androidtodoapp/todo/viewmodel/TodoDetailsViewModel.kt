package com.vincentturnier.androidtodoapp.todo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.vincentturnier.androidtodoapp.core.rx.RxResult
import com.vincentturnier.androidtodoapp.core.viewmodel.BaseViewModel
import com.vincentturnier.androidtodoapp.todo.model.Todo
import com.vincentturnier.androidtodoapp.todo.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class TodoDetailsViewModel @Inject constructor(
    private val repository: TodoRepository
): BaseViewModel() {

    private val todoMutableLiveData = MutableLiveData<RxResult<Todo>>()
    val todo: LiveData<RxResult<Todo>>
        get() = todoMutableLiveData


    fun selectTodo(id: Int) {
        todoMutableLiveData.postValue(RxResult.Loading())
        disposables.add(repository.todo(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                todoMutableLiveData.postValue(RxResult.Success(it))
            },{
                todoMutableLiveData.postValue(RxResult.Error(error = it))
            }))
    }
}