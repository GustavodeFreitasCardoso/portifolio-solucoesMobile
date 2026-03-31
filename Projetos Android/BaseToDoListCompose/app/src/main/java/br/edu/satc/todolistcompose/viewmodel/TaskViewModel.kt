package br.edu.satc.todolistcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.edu.satc.todolistcompose.data.TaskData
import br.edu.satc.todolistcompose.data.TaskDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TaskViewModel(private val dao: TaskDao) : ViewModel() {

    val tasks = dao.getAllTasks().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(),
        initialValue = emptyList()
    )

    fun addTask(title: String, description: String) {
        viewModelScope.launch {
            dao.insert(TaskData(title = title, description = description))
        }
    }

    fun deleteTask(task: TaskData) {
        viewModelScope.launch {
            dao.delete(task)
        }
    }

    fun toggleTask(task: TaskData) {
        viewModelScope.launch {
            dao.update(task.copy(complete = !task.complete))
        }
    }
}