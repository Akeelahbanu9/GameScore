package com.example.gamescore.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.gamescore.entity.GameScore;

public interface GameScoreRepository extends JpaRepository<GameScore, Integer> {
    List<GameScore> findTop10ByGameIdOrderByScoreDesc(int gameId);
    List<GameScore> findByUserIdAndGameIdOrderByScoreDesc(int userId, int gameId);
    
    @Query("SELECT gs FROM GameScore gs WHERE gs.gameId = :gameId ORDER BY gs.score DESC")
    List<GameScore> findTopScoresByGameId(@Param("gameId") int gameId);
}