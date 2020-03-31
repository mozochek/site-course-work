package com.mozochek.controller;

import com.mozochek.entity.SportKind;
import com.mozochek.repository.SportKindRepository;
import com.mozochek.service.SportDisciplineService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

import static com.mozochek.utils.LengthConstants.CODE_LENGTH;
import static com.mozochek.utils.LengthConstants.SPORT_DISCIPLINE_NAME_LENGTH;

@Controller
@RequestMapping("/sport_discipline")
public class SportDisciplineController {

    private SportKindRepository sportKindRepository;
    private SportDisciplineService sportDisciplineService;

    private ArrayList<SportKind> sportKinds;


    public SportDisciplineController(SportKindRepository sportKindRepository, SportDisciplineService sportDisciplineService) {
        this.sportKindRepository = sportKindRepository;
        this.sportDisciplineService = sportDisciplineService;
    }

    @GetMapping("/add")
    public String add(Model model) {
        addFieldsLengthConstants(model);
        System.out.println(model.getAttribute("prevSelectedSportKind"));
        sportKinds = (ArrayList<SportKind>) sportKindRepository.findAll();
        model.addAttribute("sportKinds", sportKinds);
        return "sport_discipline_add";
    }

    @PostMapping("/add")
    public String save(@RequestParam(name = "sportKind") Integer sportKindId,
                      @RequestParam(name = "sportDisciplineName") String sportDisciplineName,
                      @RequestParam(name = "sportDisciplineCode") String sportDisciplineCode,
                      Model model) {
        addFieldsLengthConstants(model);
        sportKinds = (ArrayList<SportKind>) sportKindRepository.findAll();
        model.addAttribute("sportKinds", sportKinds);
        boolean isAdded = sportDisciplineService.addSportDiscipline(sportKindId, sportDisciplineName, sportDisciplineCode);
        if (isAdded) {
            model.addAttribute("dataIsValid", "Успешно добавлено!");
        } else {
            model.addAllAttributes(sportDisciplineService.getErrors());
            model.addAllAttributes(sportDisciplineService.getPreviousValues());
        }
        return "sport_discipline_add";
    }

    private void addFieldsLengthConstants(Model model) {
        model.addAttribute("sportDisciplineNameLength", SPORT_DISCIPLINE_NAME_LENGTH);
        model.addAttribute("codeLength", CODE_LENGTH);
    }
}
