package com.vincentturnier.androidtodoapp.todo.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.vincentturnier.androidtodoapp.R
import com.vincentturnier.androidtodoapp.core.fragment.BaseFragment
import com.vincentturnier.androidtodoapp.core.rx.RxResult
import com.vincentturnier.androidtodoapp.databinding.FragmentTodoListBinding
import com.vincentturnier.androidtodoapp.todo.adapter.TodoItemAdapter
import com.vincentturnier.androidtodoapp.todo.viewmodel.TodoListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TodoListFragment : BaseFragment<FragmentTodoListBinding>() {

    private val viewModel: TodoListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentTodoListBinding.inflate(layoutInflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val todoAdapter = TodoItemAdapter {
            val destination = TodoListFragmentDirections.navigateToTodoDetails(it.id!!)
            findNavController().navigate(destination)
        }

        viewModel.todos.observe(viewLifecycleOwner) {
            when(it) {
                is RxResult.Loading -> {
                    Log.d(TodoListFragment::class.simpleName, "Loading Todos")
                }
                is RxResult.Success -> {
                    todoAdapter.setItems(it.data)
                }
                is RxResult.Error -> {
                    Snackbar.make(
                        binding!!.root, "An error occured while loading todos", Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

        binding!!.addTodoFab.setOnClickListener {
            val destination = TodoListFragmentDirections.navigateToAddTodo()
            findNavController().navigate(destination)
        }



        binding!!.todoRecyclerView.apply {
            adapter = todoAdapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL))
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadTodos()
    }

}