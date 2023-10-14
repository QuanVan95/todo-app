package quanvan.todoapp.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import quanvan.todoapp.dto.UserDto;
import quanvan.todoapp.entity.Todo;
import quanvan.todoapp.entity.User;
import quanvan.todoapp.repository.TodoRepository;
import quanvan.todoapp.repository.UserRepository;
import quanvan.todoapp.request.AddTodoRequest;
import quanvan.todoapp.request.AddUserRequest;
import quanvan.todoapp.request.UpdateTodoRequest;
import quanvan.todoapp.service.UserService;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;
    private TodoRepository todoRepository;
    private UserService userService;

    @GetMapping
    public ModelAndView users(Model model){
        List<UserDto> users = userService.findAllUsers();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("users", users).setViewName("users"); // return users.html template
        return modelAndView;
    }

    public UserController(UserRepository userRepository,
                          TodoRepository todoRepository,
                          UserService userService) {
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
        this.userService = userService;
    }

    @GetMapping("/{userId}")
    public User getUserById(@PathVariable Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
    }

    @PostMapping
    public User addUser(@RequestBody AddUserRequest userRequest) {
        User user = new User();
        user.setName(userRequest.getName());
        user.setPassword(userRequest.getPassword());
        return userRepository.save(user);
    }

    @PostMapping("/{userId}/todos")
    public void addTodos(@PathVariable Long userId, @RequestBody AddTodoRequest todoRequest) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        Todo todo = new Todo();
        todo.setContent(todoRequest.getContent());
        user.getTodoList().add(todo);
        todoRepository.save(todo);
        userRepository.save(user);
    }

    @PutMapping("/todos/{todoId}")
    public void toggleTodoCompleted(@PathVariable Long todoId, @RequestBody UpdateTodoRequest todoRequest) {
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
        todo.setCompleted(todoRequest.getCompleted());
        todo.setContent(todoRequest.getContent());
        todoRepository.save(todo);
    }

    @DeleteMapping("{userId}/todos/{todoId}")
    public void deleteTodo(@PathVariable Long userId, @PathVariable Long todoId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        Todo todo = todoRepository.findById(todoId).orElseThrow(() -> new NoSuchElementException());
        user.getTodoList().remove(todo);
        todoRepository.delete(todo);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException());
        userRepository.delete(user);
    }
}
