package br.edu.satc.todolistcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.edu.satc.todolistcompose.data.TaskDao

class TaskViewModelFactory(
    private val dao: TaskDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return TaskViewModel(dao) as T
    }
}