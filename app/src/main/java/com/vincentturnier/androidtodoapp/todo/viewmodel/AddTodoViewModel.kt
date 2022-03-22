package com.vincentturnier.androidtodoapp.todo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vincentturnier.androidtodoapp.core.rx.RxResult
import com.vincentturnier.androidtodoapp.core.viewmodel.BaseViewModel
import com.vincentturnier.androidtodoapp.todo.model.Todo
import com.vincentturnier.androidtodoapp.todo.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class AddTodoViewModel @Inject constructor(
    private val repository: TodoRepository
    ): BaseViewModel() {

    private val inputFailedNotificationMutableLiveData = MutableLiveData<RxResult<String>>()
    val inputFailedNotification: LiveData<RxResult<String>>
        get() = inputFailedNotificationMutableLiveData

    private val addMutableLiveData = MutableLiveData<RxResult<Boolean>>()
    val addTodo: LiveData<RxResult<Boolean>>
        get() = addMutableLiveData

    fun addTodo(todo: Todo) {

        if(!verifyTodoInputs(todo))
            return

        addMutableLiveData.postValue(RxResult.Loading())
        disposables.add(repository.addTodo(todo)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
            .subscribe({
                addMutableLiveData.postValue(RxResult.Success(true))
            },{
                addMutableLiveData.postValue(RxResult.Error(error = it))
            }))
    }

    fun verifyTodoInputs(todo: Todo): Boolean {
        if(todo.title.isNullOrBlank()){
            inputFailedNotificationMutableLiveData.postValue(RxResult.Success(
                "Title Cannot be Blank"
            ))
            return false
        }

        return true
    }


}