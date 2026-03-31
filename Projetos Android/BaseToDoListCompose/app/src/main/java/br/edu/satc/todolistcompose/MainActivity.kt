package br.edu.satc.todolistcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import br.edu.satc.todolistcompose.data.AppDatabase
import br.edu.satc.todolistcompose.ui.screens.HomeScreen
import br.edu.satc.todolistcompose.ui.theme.ToDoListComposeTheme
import br.edu.satc.todolistcompose.viewmodel.TaskViewModel
import br.edu.satc.todolistcompose.viewmodel.TaskViewModelFactory

class MainActivity : ComponentActivity() {

    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 🔥 cria banco
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "todo_db"
        ).build()

        setContent {
            ToDoListComposeTheme {

                val viewModel: TaskViewModel = viewModel(
                    factory = TaskViewModelFactory(database.taskDao())
                )

                HomeScreen(viewModel)
            }
        }
    }
}