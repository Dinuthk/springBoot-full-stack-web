package springbootacademy.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springbootacademy.security.model.User;

import java.awt.*;
import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    List<User> findByEmail(String username);
}
