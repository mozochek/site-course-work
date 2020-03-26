package com.mozochek.controller;

import com.mozochek.entity.SportKind;
import com.mozochek.repository.SportKindRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/sport_kind")
public class SportKindController {

    @Autowired
    private SportKindRepository sportKindRepository;

    @GetMapping("/add")
    public String sportKindAdd() {
        return "sport_kind_add";
    }

    @PostMapping("/add")
    public String add(@RequestParam(name = "inputSportKindName") String sportKindName, Model model) {
        if(StringUtils.isEmpty(sportKindName)) {
            System.out.println("empty");
            model.addAttribute("textError", "Введите название вида спорта!");
        } else {
            SportKind sportKind = new SportKind(sportKindName);
            sportKindRepository.save(sportKind);
            model.addAttribute("textAccept", "Успешно добалено!");
        }
        return "sport_kind_add";
    }
}
