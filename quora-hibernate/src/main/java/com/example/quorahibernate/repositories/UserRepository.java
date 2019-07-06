package com.example.quorahibernate.repositories;

import com.example.quorahibernate.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * UserRepository
 */
public interface UserRepository extends JpaRepository<User, Long> {
}