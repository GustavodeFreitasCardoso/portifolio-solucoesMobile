package br.edu.satc.todolistcompose.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: TaskData)

    @Update
    suspend fun update(task: TaskData)

    @Delete
    suspend fun delete(task: TaskData)

    @Query("SELECT * FROM tasks ORDER BY id DESC")
    fun getAllTasks(): Flow<List<TaskData>>
}