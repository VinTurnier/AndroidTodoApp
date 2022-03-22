package com.vincentturnier.androidtodoapp.todo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vincentturnier.androidtodoapp.databinding.TodoRecyclerItemBinding
import com.vincentturnier.androidtodoapp.todo.model.Todo

class TodoItemAdapter(private val onItemClick: (todo: Todo)->Unit): RecyclerView.Adapter<TodoItemAdapter.TodoItemViewHolder>() {

    private var todolist = mutableListOf<Todo>()

    fun setItems(items: List<Todo>) {
        todolist = items.toMutableList()
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return todolist.size
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        holder.bind(todolist[position])
        holder.itemView.setOnClickListener { onItemClick(todolist[position]) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        val binding = TodoRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TodoItemViewHolder(binding)
    }

    inner class TodoItemViewHolder(
        private val binding: TodoRecyclerItemBinding
        ): RecyclerView.ViewHolder(binding.root) {

            fun bind(todo: Todo) {
                binding.todo = todo
            }

    }
}