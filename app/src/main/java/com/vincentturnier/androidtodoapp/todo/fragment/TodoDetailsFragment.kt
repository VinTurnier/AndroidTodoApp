package com.vincentturnier.androidtodoapp.todo.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.vincentturnier.androidtodoapp.R
import com.vincentturnier.androidtodoapp.core.fragment.BaseFragment
import com.vincentturnier.androidtodoapp.core.rx.RxResult
import com.vincentturnier.androidtodoapp.databinding.FragmentTodoDetailsBinding
import com.vincentturnier.androidtodoapp.todo.viewmodel.TodoDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoDetailsFragment : BaseFragment<FragmentTodoDetailsBinding>() {

    private val args: TodoDetailsFragmentArgs by navArgs()
    private val viewModel: TodoDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTodoDetailsBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.selectTodo(args.todoId)

        viewModel.todo.observe(viewLifecycleOwner) {
            when(it) {
                is RxResult.Loading -> {

                }
                is RxResult.Success -> {
                    binding!!.todo = it.data
                }
                is RxResult.Error -> {
                    Snackbar.make(
                        binding!!.root, "An error occured while getting details", Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

}