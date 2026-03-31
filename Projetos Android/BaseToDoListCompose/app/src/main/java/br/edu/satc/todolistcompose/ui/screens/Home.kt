@file:OptIn(ExperimentalMaterial3Api::class)

package br.edu.satc.todolistcompose.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.edu.satc.todolistcompose.ui.components.TaskCard
import br.edu.satc.todolistcompose.viewmodel.TaskViewModel
import kotlinx.coroutines.launch


@Composable
fun HomeScreen(viewModel: TaskViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 8.dp)
    ) {
        Content(viewModel)
        NewTask(viewModel)
    }
}

@Composable
fun Content(viewModel: TaskViewModel) {

    val tasks by viewModel.tasks.collectAsState()

    LazyColumn {
        items(tasks) { task ->
            TaskCard(
                taskData = task,
                onTaskCheckedChange = {
                    viewModel.toggleTask(task)
                }
            )
        }
    }
}

@Composable
fun NewTask(viewModel: TaskViewModel) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()

    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }
    var showBottomSheet by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ExtendedFloatingActionButton(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),
            text = { Text("Nova tarefa") },
            icon = { Icon(Icons.Filled.Add, contentDescription = "") },
            onClick = {
                showBottomSheet = true
            }
        )
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            sheetState = sheetState,
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                OutlinedTextField(
                    value = taskTitle,
                    onValueChange = { taskTitle = it },
                    label = { Text("Título da tarefa") }
                )

                OutlinedTextField(
                    value = taskDescription,
                    onValueChange = { taskDescription = it },
                    label = { Text("Descrição da tarefa") }
                )

                Button(
                    modifier = Modifier.padding(top = 4.dp),
                    onClick = {

                        // 🔥 salva no banco
                        viewModel.addTask(taskTitle, taskDescription)

                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }

                        // limpa campos
                        taskTitle = ""
                        taskDescription = ""
                    }
                ) {
                    Text("Salvar")
                }
            }
        }
    }
}
