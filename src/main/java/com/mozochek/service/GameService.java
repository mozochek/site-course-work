package com.mozochek.service;

import com.mozochek.entity.Game;
import com.mozochek.repository.GameRepository;
import org.springframework.stereotype.Service;

@Service
public class GameService {

    private GameRepository gameRepository;

    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Game findGameById(Integer id) {
        return gameRepository.findById(id).orElse(null);
    }

    public void saveGame(Game game) {
        gameRepository.save(game);
    }

    public void saveAllGames(Iterable<Game> games) {
        gameRepository.saveAll(games);
    }

    public void deleteGameById(Integer id) {
        gameRepository.deleteById(id);
    }

    public void deleteAllGames(Iterable<Game> games) {
        gameRepository.deleteAll(games);
    }
}
