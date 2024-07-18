package com.example.gamescore.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.gamescore.entity.Game;
import com.example.gamescore.entity.GameScore;
import com.example.gamescore.entity.User;
import com.example.gamescore.repository.GameRepository;
import com.example.gamescore.repository.GameScoreRepository;

@Service
public class GameService {
    @Autowired
    private GameRepository gameRepository;
    public Game saveGame(Game game) {
        return gameRepository.save(game);
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Optional<Game> getGameById(int gameId) {
        return gameRepository.findById(gameId);
    }
}