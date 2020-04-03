package com.mozochek.controller;

import com.mozochek.entity.Human;
import com.mozochek.service.HumanService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.mozochek.utils.LengthConstants.*;

@Controller
@RequestMapping("admin/human")
public class HumanController {

    private HumanService humanService;

    public HumanController(HumanService humanService) {
        this.humanService = humanService;
    }

    @GetMapping("/add")
    public String add(Model model) {
        addFieldsLengthConstants(model);
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

        boolean isSavedSuccessfully = humanService.addHuman(human, humanBirthDate);

        if (isSavedSuccessfully) {
            model.addAttribute("saveSuccessful", "Успешно добавлено!");
        } else {
            model.addAllAttributes(humanService.getErrors());
            model.addAllAttributes(humanService.getPreviousValues());
        }
        addFieldsLengthConstants(model);
        return "human_add";
    }

    private void addFieldsLengthConstants(Model model) {
        model.addAttribute("humanNameLength", HUMAN_NAME_LENGTH);
        model.addAttribute("humanSurnameLength", HUMAN_SURNAME_LENGTH);
        model.addAttribute("humanPatronymicLength", HUMAN_PATRONYMIC_LENGTH);
        model.addAttribute("dateLength", DATE_LENGTH);
        model.addAttribute("cityLength", CITY_LENGTH);
    }
}
