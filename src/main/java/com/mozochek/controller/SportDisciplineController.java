package com.mozochek.controller;

import com.mozochek.entity.SportDiscipline;
import com.mozochek.entity.SportKind;
import com.mozochek.repository.SportDisciplineRepository;
import com.mozochek.repository.SportKindRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/sport_discipline")
public class SportDisciplineController {

    @Autowired
    private SportKindRepository sportKindRepository;

    @Autowired
    private SportDisciplineRepository sportDisciplineRepository;

    @GetMapping("/add")
    public String sportDisciplineAdd() {
        return "sport_discipline_add";
    }

    @PostMapping("/add")
    public String add(@RequestParam(name = "inputSportKindName")String sportKindName,
                    @RequestParam(name = "inputSportDisciplineName")String sportDisciplineName) {
        System.out.println("POST SPORT DISCIPLINE");
        SportKind sportKind = sportKindRepository.findByName(sportKindName);
        SportDiscipline sportDiscipline = new SportDiscipline(sportDisciplineName, sportKind);
        sportDisciplineRepository.save(sportDiscipline);
        return "sport_discipline_add";
    }

}
