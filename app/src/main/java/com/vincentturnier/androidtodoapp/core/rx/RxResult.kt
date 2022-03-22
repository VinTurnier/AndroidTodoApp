package com.vincentturnier.androidtodoapp.core.rx

sealed class RxResult<T> {
    class Loading<T>(val data: T? = null): RxResult<T>()
    class Success<T>(val data: T): RxResult<T>()
    class Error<T>(val data: T? = null, error: Throwable): RxResult<T>()

}