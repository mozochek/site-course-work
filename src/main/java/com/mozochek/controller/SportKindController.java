package com.mozochek.controller;

import com.mozochek.entity.SportKind;
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
@RequestMapping("admin/sport_kind")
public class SportKindController {

    private SportKindService sportKindService;

    public SportKindController(SportKindService sportKindService) {
        this.sportKindService = sportKindService;
    }

    @GetMapping("/add")
    public String addSportKind(Model model) {
        addFieldsLengthConstants(model);
        return "sport_kind_add";
    }

    @PostMapping("/add")
    public String saveSportKind(@RequestParam String sportKindName,
                                @RequestParam String sportKindCode,
                                Model model) {
        boolean isSavedSuccessfully = sportKindService.addSportKind(new SportKind(sportKindName, sportKindCode));
        if (isSavedSuccessfully) {
            model.addAttribute("saveSuccessful", "Успешно добавлено!");
        } else {
            model.addAllAttributes(sportKindService.getErrors());
            model.addAllAttributes(sportKindService.getPreviousValues());
        }
        addFieldsLengthConstants(model);
        return "sport_kind_add";
    }

    private void addFieldsLengthConstants(Model model) {
        model.addAttribute("sportKindNameLength", SPORT_KIND_NAME_LENGTH);
        model.addAttribute("codeLength", CODE_LENGTH);
    }
}
