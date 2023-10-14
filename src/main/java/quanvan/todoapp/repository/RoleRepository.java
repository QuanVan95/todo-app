package quanvan.todoapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import quanvan.todoapp.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
