package springbootacademy.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootacademy.security.model.User;

public interface UserRepo extends JpaRepository<User,Integer> {
}
