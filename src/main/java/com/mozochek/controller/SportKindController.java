package com.mozochek.controller;

import com.mozochek.entity.SportKind;
import com.mozochek.service.SportKindService;
import com.mozochek.utils.FieldsLengthConstantsImplementing;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SportKindController implements FieldsLengthConstantsImplementing {

    private SportKindService sportKindService;

    public SportKindController(SportKindService sportKindService) {
        this.sportKindService = sportKindService;
    }

    @GetMapping("api/sport_kinds")
    public String showSportKinds(Model model) {
        model.addAttribute("sportKinds", sportKindService.findAllSportKinds());
        return "sport_kinds";
    }

    @GetMapping("api/sport_kind/add")
    public String addSportKindGet(Model model) {
        addFieldsLengthConstantsIn(model);
        return "sport_kind_add";
    }

    @PostMapping("api/sport_kind/add")
    public String addSportKindPost(@RequestParam String name,
                                   @RequestParam String code,
                                   Model model) {
        SportKind sportKind = new SportKind(name.trim(), code.trim());
        boolean isSavedSuccessfully = sportKindService.saveSportKind(sportKind);
        if (isSavedSuccessfully) {
            model.addAttribute("saveSuccessful", "Успешно добавлено!");
        } else {
            model.addAllAttributes(sportKindService.getErrors());
            model.addAllAttributes(sportKindService.getPreviousValues());
        }
        addFieldsLengthConstantsIn(model);
        return "sport_kind_add";
    }

    @GetMapping("api/sport_kind/{sportKindId}/edit")
    public String editSportKindGet(@PathVariable Integer sportKindId,
                                   HttpServletRequest httpServletRequest,
                                   Model model) {
        SportKind sportKind = sportKindService.findSportKindById(sportKindId);
        if (sportKind == null) {
            return "redirect:" + httpServletRequest.getHeader("referer");
        }
        model.addAttribute("sportKind", sportKind);
        return "sport_kind_add";
    }

    @PostMapping("api/sport_kind/{sportKindId}/edit")
    public String editSportKindPost(@PathVariable Integer sportKindId,
                                    @RequestParam String name,
                                    @RequestParam String code,
                                    HttpServletRequest httpServletRequest,
                                    Model model) {
        SportKind sportKind = sportKindService.findSportKindById(sportKindId);
        if (sportKind == null) {
            return "redirect:" + httpServletRequest.getHeader("referer");
        }
        sportKind.setName(name.trim());
        sportKind.setCode(code.trim());
        boolean isSavedSuccessfully = sportKindService.saveSportKind(sportKind);
        if (isSavedSuccessfully) {
            return "redirect:/api/sport_kinds";
        }
        model.addAllAttributes(sportKindService.getErrors());
        model.addAllAttributes(sportKindService.getPreviousValues());
        addFieldsLengthConstantsIn(model);
        return "sport_kind_add";
    }

    /*@GetMapping("api/sport_kind/{sportKindId}/delete")
    public String deleteSportKindGet(@PathVariable Integer sportKindId,
                                     HttpServletRequest httpServletRequest) {
        sportKindService.deleteSportKindById(sportKindId);
        return "redirect:" + httpServletRequest.getHeader("referer");
    }*/
}
