package io.jaziu.todoapi.api;

import io.jaziu.todoapi.api.viewmodel.TodoViewModel;
import io.jaziu.todoapi.model.Todo;
import io.jaziu.todoapi.repository.TodoRepository;
import io.jaziu.todoapi.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.reducing;
import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("api/todos")
@CrossOrigin
public class TodoController {

    private TodoRepository todoRepository;
    private UserRepository userRepository;

    public TodoController(TodoRepository todoRepository, UserRepository userRepository) {
        this.todoRepository = todoRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/all") // Get Todos of all users
    public List<TodoViewModel> all() {
        return this.todoRepository.findAll()
                .stream()
                .map(TodoViewModel::new)
                .collect((toList()));
    }

    @GetMapping("byId/{id}")
    public TodoViewModel byId(@PathVariable Long id){
        var  temp = todoRepository.findById(id);
        var todo = new TodoViewModel(temp.get());
        return todo;
    }

    @GetMapping("/byUser/{userId}")
    public List<TodoViewModel> byUser(@PathVariable(name = "userId") Long userId) {
        var temp = userRepository.findById(userId);
        return todoRepository.findAllByUser(temp).stream().map(TodoViewModel::new).collect(toList());

    }

    @PutMapping("/add/{userId}")
    public void addTodo(@PathVariable(name = "userId") Long userId, @RequestParam String text){
        var user = userRepository.findById(userId);
        var todos = user.get().getTodos();
        var todo = new Todo(text,user.get());
        todoRepository.save(todo);
        todos.add(todo);
        user.get().setTodos(todos);
        userRepository.save(user.get());

    }

    @DeleteMapping("/delete/{todoId}")
    public void deleteTodo(@PathVariable Long todoId){


        if(todoRepository.findById(todoId).isPresent()) {
            // getting _Todo to delete than getting user based on _Todo
            Todo deletedTodo = todoRepository.findById(todoId).get();
            var user = deletedTodo.getUser();
            var todos = user.getTodos();
            // Deleting _Todo from todoRepository and userRepository
            todos.remove(deletedTodo);
            todoRepository.delete(deletedTodo);
            user.setTodos(todos);
            userRepository.save(user);
        }

    }

    @PostMapping("/update/{todoId}")
    public void updateTodo(@PathVariable Long todoId, @RequestParam String text){

       var updatedTodo = todoRepository.findById(todoId).get();
       updatedTodo.setText(text);
       todoRepository.save(updatedTodo);

    }

    @PostMapping("/active/{todoId}")
    public void updateTodoActive(@PathVariable Long todoId, @RequestParam boolean active){

        var updatedTodo = todoRepository.findById(todoId).get();
        updatedTodo.setActive(active);
        todoRepository.save(updatedTodo);

    }


}
