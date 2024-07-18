package com.example.gamescore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamescore.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
}