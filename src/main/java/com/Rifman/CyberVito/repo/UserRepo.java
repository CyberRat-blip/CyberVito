package com.Rifman.CyberVito.repo;


import com.Rifman.CyberVito.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
