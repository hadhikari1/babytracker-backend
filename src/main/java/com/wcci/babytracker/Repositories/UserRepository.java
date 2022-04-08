package com.wcci.babytracker.Repositories;

import com.wcci.babytracker.pojos.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
   List<User> findAllByUsername(String username);
   User findByUsername(String username);
}
