package quanvan.todoapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import quanvan.todoapp.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
