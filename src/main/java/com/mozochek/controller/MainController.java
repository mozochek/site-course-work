package com.mozochek.controller;

import com.mozochek.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class MainController {

    private TournamentService tournamentService;

    public MainController(TournamentService tournamentService) {
        this.tournamentService = tournamentService;
    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/tournaments")
    public String tournaments(Model model) {
        model.addAttribute("tournaments", tournamentService.findAllTournaments());
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

    @GetMapping("/reference")
    public String reference() {
        return "reference";
    }
}