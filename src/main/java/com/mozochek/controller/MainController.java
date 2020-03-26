package com.mozochek.controller;

import com.mozochek.entity.Tournament;
import com.mozochek.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @Autowired
    TournamentRepository tournamentRepository;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/tournaments")
    public String main(Model model) {
        Iterable<Tournament> tournaments = tournamentRepository.findAll();
        model.addAttribute("tournaments", tournaments);
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