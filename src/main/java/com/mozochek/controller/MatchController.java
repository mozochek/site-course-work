package com.mozochek.controller;

import com.mozochek.entity.Match;
import com.mozochek.entity.Tournament;
import com.mozochek.service.GameService;
import com.mozochek.service.MatchService;
import com.mozochek.service.TeamService;
import com.mozochek.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MatchController {

    private TournamentService tournamentService;
    private MatchService matchService;
    private TeamService teamService;
    private GameService gameService;

    public MatchController(TournamentService tournamentService,
                           MatchService matchService,
                           TeamService teamService,
                           GameService gameService) {
        this.tournamentService = tournamentService;
        this.matchService = matchService;
        this.teamService = teamService;
        this.gameService = gameService;
    }

    @GetMapping("api/tournament/{tournamentId}/match/add")
    public String matchAddGet(@PathVariable Integer tournamentId,
                              HttpServletRequest httpServletRequest,
                              Model model) {
        Tournament tournament = tournamentService.findTournamentById(tournamentId);
        if (tournament == null) {
            return "redirect:" + httpServletRequest.getHeader("referer");
        }
        model.addAttribute("tournament", tournament);
        return "match_add";
    }

    @PostMapping("api/tournament/{tournamentId}/match/add")
    public String matchAddPost(@PathVariable Integer tournamentId,
                               @RequestParam(name = "firstTeam") Integer firstTeamId,
                               @RequestParam(name = "secondTeam") Integer secondTeamId,
                               @RequestParam Integer gamesCount,
                               HttpServletRequest httpServletRequest,
                               Model model) {
        Tournament tournament = tournamentService.findTournamentById(tournamentId);
        if (tournament == null) {
            return "redirect:" + httpServletRequest.getHeader("referer");
        }
        model.addAttribute("tournament", tournament);
        Match match = new Match(tournament, teamService.findTeamById(firstTeamId), teamService.findTeamById(secondTeamId));
        boolean isSavedSuccessfully = matchService.saveMatch(match, gamesCount);
        if (isSavedSuccessfully) {
            model.addAttribute("saveSuccessful", "Успешно добавлено!");
        } else {
            model.addAllAttributes(matchService.getErrors());
        }
        return "match_add";
    }

    @GetMapping("api/tournament/{tournamentId}/match/{matchId}/settings")
    public String matchSettingsGet(@PathVariable Integer tournamentId,
                                   @PathVariable Integer matchId,
                                   HttpServletRequest httpServletRequest,
                                   Model model) {
        Tournament tournament = tournamentService.findTournamentById(tournamentId);
        if (tournament == null) {
            return "redirect:" + httpServletRequest.getHeader("referer");
        }
        Match match = matchService.findMatchById(matchId);
        if (match == null) {
            return "redirect:/tournament/" + tournamentId;
        }
        model.addAttribute("match", match);
        return "match_settings";
    }

    @PostMapping("api/tournament/{tournamentId}/match/{matchId}/settings")
    public String matchSettingsPost(@PathVariable Integer tournamentId,
                                    @PathVariable Integer matchId,
                                    @RequestBody String data,
                                    HttpServletRequest httpServletRequest,
                                    Model model) {
        Tournament tournament = tournamentService.findTournamentById(tournamentId);
        if (tournament == null) {
            return "redirect:" + httpServletRequest.getHeader("referer");
        }
        Match match = matchService.findMatchById(matchId);
        if (match == null) {
            return "redirect:/tournament/" + tournamentId;
        }
        matchService.updateMatch(match, data);
        model.addAttribute("match", match);
        return "redirect:/tournament/" + tournamentId;
    }

    @GetMapping("api/tournament/{tournamentId}/match/{matchId}/delete")
    public String deleteMatch(@PathVariable Integer tournamentId,
                              @PathVariable Integer matchId,
                              HttpServletRequest httpServletRequest) {
        Tournament tournament = tournamentService.findTournamentById(tournamentId);
        if (tournament == null) {
            return "redirect:" + httpServletRequest.getHeader("referer");
        }
        Match match = matchService.findMatchById(matchId);
        gameService.deleteAllGames(match.getGames());
        matchService.deleteMatch(match);
        return "redirect:/tournament/" + tournamentId;
    }
}
