package quanvan.todoapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import quanvan.todoapp.entity.Todo;
import quanvan.todoapp.entity.User;
import quanvan.todoapp.repository.TodoRepository;
import quanvan.todoapp.repository.UserRepository;

@SpringBootApplication
public class TodoAppApplication implements CommandLineRunner{

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TodoRepository todoRepository;

	public static void main(String[] args) {
		SpringApplication.run(TodoAppApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		User user = new User();
		user.setPassword("Should be hashed");
		user.setUsername("Quan Van");

		Todo todo = new Todo();
		todo.setContent("This is the content");
		
		user.getTodoList().add(todo);
		todoRepository.save(todo);
		userRepository.save(user);
	}

}
