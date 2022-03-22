package com.vincentturnier.androidtodoapp.todo.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.vincentturnier.androidtodoapp.R
import com.vincentturnier.androidtodoapp.core.fragment.BaseFragment
import com.vincentturnier.androidtodoapp.core.rx.RxResult
import com.vincentturnier.androidtodoapp.databinding.FragmentAddTodoBinding
import com.vincentturnier.androidtodoapp.todo.model.Todo
import com.vincentturnier.androidtodoapp.todo.model.TodoPriority
import com.vincentturnier.androidtodoapp.todo.viewmodel.AddTodoViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.time.LocalDateTime

@AndroidEntryPoint
class AddTodoFragment : BaseFragment<FragmentAddTodoBinding>() {

    private val viewModel: AddTodoViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddTodoBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.addTodo.observe(viewLifecycleOwner) {
            when(it) {
                is RxResult.Loading -> {
                    Log.d(AddTodoFragment::class.simpleName,"Loading Add Todo")
                }
                is RxResult.Success -> {
                    findNavController().popBackStack()
                }
                is RxResult.Error -> {
                    Snackbar.make(
                        binding!!.root, "An error occured while adding todo", Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

        viewModel.inputFailedNotification.observe(viewLifecycleOwner) {
            when(it) {
                is RxResult.Success -> {
                    Snackbar.make(binding!!.root, it.data, Snackbar.LENGTH_SHORT).show()
                }
                else -> {

                }
            }
        }

        binding!!.addTodo.setOnClickListener {
            viewModel.addTodo(Todo(
                title = binding!!.title.text.toString(),
                description = binding!!.descriptionEditText.text.toString(),
                priority = TodoPriority.CAUTION,
                dateTime = LocalDateTime.now()
            ))

        }
    }

}