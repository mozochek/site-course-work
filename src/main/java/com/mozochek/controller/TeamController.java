package com.mozochek.controller;

import com.mozochek.entity.Human;
import com.mozochek.entity.Tournament;
import com.mozochek.service.HumanService;
import com.mozochek.service.TeamService;
import com.mozochek.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Set;

@Controller
public class TeamController {

    private TeamService teamService;
    private TournamentService tournamentService;
    private HumanService humanService;

    public TeamController(TeamService teamService,
                          TournamentService tournamentService,
                          HumanService humanService) {
        this.teamService = teamService;
        this.tournamentService = tournamentService;
        this.humanService = humanService;
    }

    @GetMapping("api/tournament/{tournamentId}/team/settings")
    public Object addTeamGet(@PathVariable Integer tournamentId,
                             RedirectAttributes redirectAttributes,
                             Model model) {
        Tournament tournament = tournamentService.findTournamentById(tournamentId);
        if (tournament == null) {
            return "redirect:/tournaments";
        }
        //Iterable<Human> people = humanService.findPeopleByGender(tournament.getGender().toLowerCase());
        Iterable<Human> people = humanService.findPeopleByGender(tournament.getGender());

        if (!people.iterator().hasNext()) {
            redirectAttributes.addFlashAttribute("peopleEmpty", "Отсутствуют люди для добавления в команду!");
            return new RedirectView("/api/human/add");
        }
        model.addAttribute("humans", people);
        model.addAttribute("tournament", tournament);
        return "team_settings";
    }


    @PostMapping("api/tournament/{tournamentId}/team/settings")
    public RedirectView addTeamPost(@PathVariable Integer tournamentId,
                                    @RequestBody String data,
                                    RedirectAttributes redirectAttributes) {
        Tournament tournament = tournamentService.findTournamentById(tournamentId);
        if (tournament == null) {
            return new RedirectView("/tournaments");
        }
        boolean isAddedSuccessfully = teamService.addPeopleToTeam(tournament, data);
        if (isAddedSuccessfully) {
            redirectAttributes.addFlashAttribute("saveSuccessful", "Успешно добавлено!");
        } else {
            teamService.getErrors().forEach(redirectAttributes::addFlashAttribute);
        }
        return new RedirectView("settings");
    }
}
