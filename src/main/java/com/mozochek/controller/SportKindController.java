package com.mozochek.controller;

import com.mozochek.service.SportKindService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static com.mozochek.utils.LengthConstants.CODE_LENGTH;
import static com.mozochek.utils.LengthConstants.SPORT_KIND_NAME_LENGTH;

@Controller
@RequestMapping("/sport_kind")
public class SportKindController {

    private SportKindService sportKindService;

    public SportKindController(SportKindService sportKindService) {
        this.sportKindService = sportKindService;
    }

    @GetMapping("/add")
    public String add(Model model) {
        addFieldsLengthConstants(model);
        return "sport_kind_add";
    }

    @PostMapping("/add")
    public String save(@RequestParam(name = "sportKindName") String sportKindName,
                      @RequestParam(name = "sportKindCode") String sportKindCode,
                      Model model) {
        addFieldsLengthConstants(model);
        boolean isAdded = sportKindService.addSportKind(sportKindName, sportKindCode);
        if (isAdded) {
            model.addAttribute("dataIsValid", "Успешно добавлено!");
        } else {
            model.addAllAttributes(sportKindService.getErrors());
            model.addAllAttributes(sportKindService.getPreviousValues());
        }
        return "sport_kind_add";
    }

    private void addFieldsLengthConstants(Model model) {
        model.addAttribute("sportKindNameLength", SPORT_KIND_NAME_LENGTH);
        model.addAttribute("codeLength", CODE_LENGTH);
    }
}
