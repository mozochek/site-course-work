package com.mozochek.controller;

import com.mozochek.entity.SportDiscipline;
import com.mozochek.entity.Tournament;
import com.mozochek.repository.SportDisciplineRepository;
import com.mozochek.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/tournament")
public class TournamentController {

    @Autowired
    SportDisciplineRepository sportDisciplineRepository;

    @Autowired
    TournamentRepository tournamentRepository;

    @GetMapping("/add")
    public String tournamentAdd() {
        return "tournament_add";
    }

    @PostMapping("/add")
    public String add(@RequestParam(name = "tournamentName") String name,
                      @RequestParam(name = "sportDisciplineName") String sportDisciplineName,
                      @RequestParam(name = "tournamentFormat") String format,
                      @RequestParam(name = "tournamentCity") String city) {
        SportDiscipline sportDiscipline = sportDisciplineRepository.findByName(sportDisciplineName);
        System.out.println(sportDiscipline.toString());
        Tournament tournament = new Tournament(name, format, city);
        tournament.setSportDiscipline(sportDiscipline);
        System.out.println(tournament.toString());
        tournamentRepository.save(tournament);
        return "tournament_add";
    }

    @GetMapping("/{id}")
    public String showTournamentInfo(Model model, @PathVariable(name = "id") Integer id) {
        Tournament tournament = tournamentRepository.findById(id).get();
        model.addAttribute("tournament", tournament);
        return "tournament";
    }
}
