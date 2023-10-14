package quanvan.todoapp.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String username;
	private String password;
	@OneToMany(cascade = CascadeType.MERGE)
	private List<Todo> todoList = new ArrayList<>();
}
