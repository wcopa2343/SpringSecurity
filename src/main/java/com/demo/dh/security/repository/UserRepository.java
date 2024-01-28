package com.demo.dh.security.repository;

import com.demo.dh.security.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
