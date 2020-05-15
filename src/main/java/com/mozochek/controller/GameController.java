package com.mozochek.controller;

import com.mozochek.entity.Game;
import com.mozochek.entity.Match;
import com.mozochek.service.GameService;
import com.mozochek.service.MatchService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GameController {

    private GameService gameService;
    private MatchService matchService;

    public GameController(GameService gameService, MatchService matchService) {
        this.gameService = gameService;
        this.matchService = matchService;
    }

    @GetMapping("api/game/{gameId}/delete")
    public String deleteGame(@PathVariable Integer gameId,
                             HttpServletRequest httpServletRequest) {
        Game game = gameService.findGameById(gameId);
        Match match = game.getMatch();
        match.removeGame(game);
        match.updateScores();
        gameService.deleteGameById(gameId);
        if (match.getGames().isEmpty()) {
            matchService.deleteMatch(match);
            return "redirect:" + httpServletRequest.getHeader("referer");
        }
        match.setWinnerTeam(null);
        matchService.saveMatch(match);
        return "redirect:" + httpServletRequest.getHeader("referer");
    }
}
