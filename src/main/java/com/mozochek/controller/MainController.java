package com.mozochek.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    public MainController() {

    }

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/tournaments")
    public String main(Model model) {
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

    @GetMapping("/tournament/add")
    public String tournamentAdd() {
        return "tournament_add";
    }
}