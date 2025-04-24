import model.TodoItem

// shared/src/commonMain/kotlin/TodoRepository.kt
class TodoRepository {
    private val todos = mutableListOf<TodoItem>()
    private var nextId = 1L

    fun addTodo(title: String): TodoItem {
        val todo = TodoItem(id = nextId++, title = title)
        todos.add(todo)
        return todo
    }

    fun getAllTodos(): List<TodoItem> = todos

    fun toggleTodo(id: Long): TodoItem? {
        val index = todos.indexOfFirst { it.id == id }
        if (index != -1) {
            val todo = todos[index]
            val updated = todo.copy(isDone = !todo.isDone)
            todos[index] = updated
            return updated
        }
        return null
    }

    fun deleteTodo(id: Long): Boolean {
        return todos.removeAll { it.id == id }
    }
}
