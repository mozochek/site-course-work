package com.mozochek.controller;

import com.mozochek.entity.Human;
import com.mozochek.service.HumanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/human")
public class HumanController {

    private HumanService humanService;

    public HumanController(HumanService humanService) {
        this.humanService = humanService;
    }

    @GetMapping("/add")
    public String add() {
        return "human_add";
    }

    @PostMapping("/add")
    public String addHuman(@RequestParam String humanName,
                           @RequestParam String humanSurname,
                           @RequestParam String humanPatronymic,
                           @RequestParam String humanBirthDate,
                           @RequestParam String humanCity,
                           Model model) {
        Human human = new Human();
        human.setName(humanName);
        human.setSurname(humanSurname);
        human.setPatronymic(humanPatronymic);
        human.setCity(humanCity);

        boolean isAdded = humanService.addHuman(human, humanBirthDate);

        if (isAdded) {
            model.addAttribute("dataIsValid", "Успешно добавлено!");
        } else {
            model.addAllAttributes(humanService.getErrors());
            model.addAllAttributes(humanService.getPreviousValues());
        }
        return "human_add";
    }

}
