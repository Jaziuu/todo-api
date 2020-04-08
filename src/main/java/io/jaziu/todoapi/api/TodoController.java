package io.jaziu.todoapi.api;

import io.jaziu.todoapi.api.viewmodel.TodoViewModel;
import io.jaziu.todoapi.model.Todo;
import io.jaziu.todoapi.model.User;
import io.jaziu.todoapi.repository.TodoRepository;
import io.jaziu.todoapi.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api/todos")
@CrossOrigin(origins = "*", maxAge = 3600)
public class TodoController {

    private TodoRepository todoRepository;
    private UserRepository userRepository;

    public TodoController(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all") // Get Todos of all users
    public List<TodoViewModel> all() {
        return this.todoRepository.findAll()
                .stream()
                .map(TodoViewModel::new)
                .collect((toList()));
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("byId/{id}")
    public TodoViewModel byId(@PathVariable Long id) {
        Optional<Todo> temp = todoRepository.findById(id);
        TodoViewModel todo = new TodoViewModel(temp.get());
        return todo;
    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @GetMapping("/byUser/{userId}")
    public List<TodoViewModel> byUser(@PathVariable(name = "userId") Long userId) {
        Optional<User> temp = userRepository.findById(userId);
        return todoRepository.findAllByUser(temp).stream().map(TodoViewModel::new).collect(toList());

    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PutMapping("/add")
    public TodoViewModel addTodo(@RequestBody TodoViewModel todo) {
        Optional<User> user = userRepository.findById(todo.getUserId());
        Set<Todo> todos = user.get().getTodos();
        Todo temp = new Todo(todo.getText(), user.get());
        todoRepository.save(temp);
        todos.add(temp);
        user.get().setTodos(todos);
        userRepository.save(user.get());
        return new TodoViewModel(temp);

    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @DeleteMapping("/delete/{todoId}")
    public void deleteTodo(@PathVariable Long todoId) {


        if (todoRepository.findById(todoId).isPresent()) {
            // getting _Todo to delete than getting user based on _Todo
            Todo deletedTodo = todoRepository.findById(todoId).get();
            User user = deletedTodo.getUser();
            Set<Todo> todos = user.getTodos();
            // Deleting _Todo from todoRepository and userRepository
            todos.remove(deletedTodo);
            todoRepository.delete(deletedTodo);
            user.setTodos(todos);
            userRepository.save(user);
        }

    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/update")
    public void updateTodo(@RequestBody TodoViewModel todo) {

        Todo updatedTodo = todoRepository.findById(todo.getId()).get();
        updatedTodo.setText(todo.getText());
        updatedTodo.setActive(todo.isActive());
        todoRepository.save(updatedTodo);

    }

    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    @PostMapping("/active/{todoId}")
    public void updateTodoActive(@PathVariable Long todoId, @RequestParam boolean active) {

        Todo updatedTodo = todoRepository.findById(todoId).get();
        updatedTodo.setActive(active);
        todoRepository.save(updatedTodo);

    }


}
