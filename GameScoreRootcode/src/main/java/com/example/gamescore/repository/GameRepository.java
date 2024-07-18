package com.example.gamescore.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.gamescore.entity.Game;

public interface GameRepository extends JpaRepository<Game, Integer> {
	
}