package com.vincentturnier.androidtodoapp.todo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vincentturnier.androidtodoapp.core.rx.RxResult
import com.vincentturnier.androidtodoapp.core.viewmodel.BaseViewModel
import com.vincentturnier.androidtodoapp.todo.model.Todo
import com.vincentturnier.androidtodoapp.todo.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class TodoListViewModel @Inject constructor(
    private val repository: TodoRepository
): BaseViewModel() {

    private val todosMutableLiveData = MutableLiveData<RxResult<List<Todo>>>()
    val todos: LiveData<RxResult<List<Todo>>>
        get() = todosMutableLiveData


    fun loadTodos() {
        todosMutableLiveData.postValue(RxResult.Loading())
        disposables.add(repository.allTodos().subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                todosMutableLiveData.postValue(RxResult.Success(it))
            },{
                todosMutableLiveData.postValue(RxResult.Error(error = it))
            }))
    }


}