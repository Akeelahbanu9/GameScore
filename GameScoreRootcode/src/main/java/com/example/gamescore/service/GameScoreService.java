package com.example.gamescore.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.gamescore.entity.Game;
import com.example.gamescore.entity.GameScore;
import com.example.gamescore.repository.GameRepository;
import com.example.gamescore.repository.GameScoreRepository;

@Service
public class GameScoreService {
    @Autowired
    private GameScoreRepository gameScoreRepository;
    @Autowired
    private GameRepository gameRepository;

    public GameScore saveGameScore(GameScore gameScore) {
        return gameScoreRepository.save(gameScore);
    }

    public List<GameScore> getUserHighestScores(int userId) {
       
        List<Game> games = gameRepository.findAll();
        List<GameScore> highestScores = new ArrayList<>();
        for (Game game : games) {
      
            List<GameScore> scores = gameScoreRepository.findByUserIdAndGameIdOrderByScoreDesc(userId, game.getId());
            if (!scores.isEmpty()) {
                highestScores.add(scores.get(0));
            }
        }
        return highestScores;
    }

    public List<GameScore> getTopScores(int gameId) {
        return gameScoreRepository.findTop10ByGameIdOrderByScoreDesc(gameId);
    }
    
    public GameScore getHighestScorerByGameId(int gameId) {
        List<GameScore> scores = gameScoreRepository.findTopScoresByGameId(gameId);
        return scores.isEmpty() ? null : scores.get(0);
    }
}