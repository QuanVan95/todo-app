package quanvan.todoapp.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String username;
	private String password;

	@OneToMany(cascade = CascadeType.MERGE)
	private List<Todo> todoList = new ArrayList<>();
	
	public User(Long id, String username, String password, List<Todo> todoList) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.todoList = todoList;
	}

	public User() {

	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Todo> getTodoList() {
		return todoList;
	}
	
	public void setTodoList(List<Todo> todoList) {
		this.todoList = todoList;
	}


}
