package com.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Users;

import java.util.Optional;

@Repository
public interface UsersRepo extends JpaRepository<Users, Integer> {
    Optional<Users> findByUsername(String username);
}
