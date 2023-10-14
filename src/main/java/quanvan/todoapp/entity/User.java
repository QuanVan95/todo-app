package quanvan.todoapp.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
@Table(name="user")
public class User {

//	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;

	@Column(nullable=false, unique=true)
	private String email;

	private String password;
	@OneToMany(cascade = CascadeType.MERGE)
	private List<Todo> todoList = new ArrayList<>();

	@ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
	@JoinTable(
			name="user_role",
			joinColumns={@JoinColumn(name="USER_ID", referencedColumnName="ID")},
			inverseJoinColumns={@JoinColumn(name="ROLE_ID", referencedColumnName="ID")})
	private List<Role> role = new ArrayList<>();
}
