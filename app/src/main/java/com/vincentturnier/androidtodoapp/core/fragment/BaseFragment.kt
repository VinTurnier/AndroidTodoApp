package com.vincentturnier.androidtodoapp.core.fragment

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

open class BaseFragment<T: ViewBinding>: Fragment() {

    protected var binding: T? = null

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null

    }
}