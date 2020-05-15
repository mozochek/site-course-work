package com.mozochek.service;

import com.mozochek.entity.Game;
import com.mozochek.entity.Match;
import com.mozochek.entity.Team;
import com.mozochek.repository.MatchRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;

@Service
public class MatchService extends AbstractService {

    private MatchRepository matchRepository;
    private GameService gameService;
    private TeamService teamService;

    public MatchService(MatchRepository matchRepository,
                        GameService gameService,
                        TeamService teamService) {
        this.matchRepository = matchRepository;
        this.gameService = gameService;
        this.teamService = teamService;
    }

    public boolean saveMatch(Match match, Integer gamesCount) {
        errors = new HashMap<>();

        if (match.getTeams().contains(null)) {
            return false;
        }
        if (match.getTeams().size() != 2) {
            errors.put("teamError", "Ошибка выбора команды!");
            return false;
        }
        match.getTeams().forEach(team -> {
            if (!match.getTournament().getTeams().contains(team)) {
                errors.put("teamError", "Ошибка выбора команды!");
            }
        });
        if (errors.isEmpty()) {
            ArrayList<Game> games = new ArrayList<>();
            for (int i = 0; i < gamesCount; i++) {
                games.add(new Game(match));
            }
            matchRepository.save(match);
            gameService.saveAllGames(games);
            return true;
        }
        return false;
    }

    public void updateMatch(Match match, String data) { // Убогий парсер. Ничего лучше не придумал
        String[] splitData = data.split("&");
        for (String string : splitData) {
            String[] actionAndValue = string.split("=");
            String gameId;
            if (actionAndValue[0].startsWith("firstTeamScoreGame")) {
                gameId = actionAndValue[0].replaceFirst("firstTeamScoreGame", "").trim();
                Game game = gameService.findGameById(Integer.parseInt(gameId));
                if (!(game.getFirstTeamScore() == Short.parseShort(actionAndValue[1]))) {
                    game.setFirstTeamScore(Short.parseShort(actionAndValue[1]));
                    gameService.saveGame(game);
                }
            } else if (actionAndValue[0].startsWith("game")) {
                gameId = actionAndValue[0].substring(actionAndValue[0].indexOf("game") + 4, actionAndValue[0].length() - 6);
                Integer winnerTeamId = Integer.parseInt(actionAndValue[1]);
                Game game = gameService.findGameById(Integer.parseInt(gameId));
                if (winnerTeamId.equals(-1)) {
                    game.setWinnerTeam(null);
                } else {
                    Team gameWinner = teamService.findTeamById(winnerTeamId);
                    if (gameWinner != null && match.getTeams().contains(gameWinner)) {
                        game.setWinnerTeam(gameWinner);
                    }
                }
                gameService.saveGame(game);
            } else if (actionAndValue[0].startsWith("secondTeamScoreGame")) {
                gameId = actionAndValue[0].replaceFirst("secondTeamScoreGame", "").trim();
                Game game = gameService.findGameById(Integer.parseInt(gameId));
                if (!(game.getSecondTeamScore() == Short.parseShort(actionAndValue[1]))) {
                    game.setSecondTeamScore(Short.parseShort(actionAndValue[1]));
                    gameService.saveGame(game);
                }
            } else if (actionAndValue[0].startsWith("matchWinner")) {
                Integer matchWinnerId = Integer.parseInt(actionAndValue[1]);
                if (matchWinnerId.equals(-1)) {
                    match.setWinnerTeam(null);
                } else {
                    Team matchWinner = teamService.findTeamById(Integer.parseInt(actionAndValue[1]));
                    if (matchWinner != null) {
                        match.setWinnerTeam(matchWinner);
                    }
                }
            } else {
                System.out.println("Неизвестная ошибка");
            }
        }
        match.updateScores();
        saveMatch(match);
    }

    public Match findMatchById(Integer id) {
        return matchRepository.findById(id).orElse(null);
    }

    public void saveMatch(Match match) {
        matchRepository.save(match);
    }

    public void saveAllMatches(Iterable<Match> matches) {
        matchRepository.saveAll(matches);
    }

    public void deleteMatchById(Integer id) {
        matchRepository.deleteById(id);
    }

    public void deleteMatch(Match match) {
        matchRepository.delete(match);
    }

    public void deleteAllMatches(Iterable<Match> matches) {
        matchRepository.deleteAll(matches);
    }
}
