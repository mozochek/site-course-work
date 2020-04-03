package com.mozochek.controller;

import com.mozochek.repository.TournamentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    TournamentRepository tournamentRepository;

    public MainController(TournamentRepository tournamentRepository) {
        this.tournamentRepository = tournamentRepository;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/tournaments")
    public String main(Model model) {
        model.addAttribute("tournaments", tournamentRepository.findAll());
        return "tournaments";
    }

    @GetMapping("/test")
    public String test() {
        return "test";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}