package com.example.gamescore.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.gamescore.entity.GameScore;
import com.example.gamescore.entity.User;
import com.example.gamescore.repository.UserRepository;
import com.example.gamescore.service.GameScoreService;

@RestController
@RequestMapping("/scores")
public class ScoreController {
    @Autowired
    private GameScoreService gameScoreService;

    @Autowired
    private UserRepository userRepository;

    
    
    @PostMapping
    public ResponseEntity<GameScore> saveScore(@RequestBody GameScore gameScore) {
        GameScore savedScore = gameScoreService.saveGameScore(gameScore);
        return new ResponseEntity<>(savedScore, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/highest")
    public ResponseEntity<List<GameScore>> getUserHighestScores(@PathVariable int userId) {
        List<GameScore> highestScores = gameScoreService.getUserHighestScores(userId);
        return new ResponseEntity<>(highestScores, HttpStatus.OK);
    }

    @GetMapping("/game/{gameId}/top")
    public ResponseEntity<List<GameScore>> getTopScores(@PathVariable int gameId) {
        List<GameScore> topScores = gameScoreService.getTopScores(gameId);
        return new ResponseEntity<>(topScores, HttpStatus.OK);
    }
    
    @GetMapping("/game/{gameId}/highest-scorer")
    public ResponseEntity<Map<String, Object>> getHighestScorerByGameId(@PathVariable int gameId) {
        GameScore highestScorer = gameScoreService.getHighestScorerByGameId(gameId);
        if (highestScorer == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        User user = userRepository.findById(highestScorer.getUserId()).orElse(null);
        Map<String, Object> response = new HashMap<>();
        response.put("score", highestScorer);
        response.put("user", user);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}