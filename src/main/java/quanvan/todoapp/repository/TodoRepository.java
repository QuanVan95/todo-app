package quanvan.todoapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import quanvan.todoapp.entity.Todo;

public interface TodoRepository extends JpaRepository<Todo, Long> {

}
