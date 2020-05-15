package com.mozochek.controller;

import com.mozochek.entity.SportDiscipline;
import com.mozochek.service.SportDisciplineService;
import com.mozochek.service.SportKindService;
import com.mozochek.utils.FieldsLengthConstantsImplementing;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class SportDisciplineController implements FieldsLengthConstantsImplementing {

    private SportKindService sportKindService;
    private SportDisciplineService sportDisciplineService;

    public SportDisciplineController(SportKindService sportKindService,
                                     SportDisciplineService sportDisciplineService) {
        this.sportKindService = sportKindService;
        this.sportDisciplineService = sportDisciplineService;
    }

    @GetMapping("api/sport_disciplines")
    public String showSportDisciplines(Model model) {
        model.addAttribute("sportDisciplines", sportDisciplineService.findAllSportDisciplines());
        return "sport_disciplines";
    }

    @GetMapping("api/sport_discipline/add")
    public Object addSportDisciplineGet(RedirectAttributes redirectAttributes,
                                        Model model) {
        if (!sportKindService.findAllSportKinds().iterator().hasNext()) {
            RedirectView redirectView = new RedirectView("/api/sport_kind/add");
            redirectAttributes.addFlashAttribute("sportKindsEmpty", "Нельзя добавить спортивную дисциплину без вида спорта!");
            return redirectView;
        }
        model.addAttribute("sportKinds", sportKindService.findAllSportKinds());
        addFieldsLengthConstantsIn(model);
        return "sport_discipline_add";
    }

    @PostMapping("api/sport_discipline/add")
    public String addSportDisciplinePost(@RequestParam(name = "sportKind") Integer sportKindId,
                                         @RequestParam String name,
                                         @RequestParam String code,
                                         Model model) {
        SportDiscipline sportDiscipline = new SportDiscipline(name.trim(), code.trim(), sportKindService.findSportKindById(sportKindId));
        boolean isSavedSuccessfully = sportDisciplineService.saveSportDiscipline(sportDiscipline);
        if (isSavedSuccessfully) {
            model.addAttribute("saveSuccessful", "Успешно добавлено!");
        } else {
            model.addAllAttributes(sportDisciplineService.getErrors());
            model.addAllAttributes(sportDisciplineService.getPreviousValues());
        }
        model.addAttribute("sportKinds", sportKindService.findAllSportKinds());
        addFieldsLengthConstantsIn(model);
        return "sport_discipline_add";
    }

    @GetMapping("api/sport_discipline/{sportDisciplineId}/edit")
    public String editSportDisciplineGet(@PathVariable Integer sportDisciplineId,
                                         HttpServletRequest httpServletRequest,
                                         Model model) {
        SportDiscipline sportDiscipline = sportDisciplineService.findSportDisciplineById(sportDisciplineId);
        if (sportDiscipline == null) {
            return "redirect:" + httpServletRequest.getHeader("referer");
        }
        model.addAttribute("sportKinds", sportKindService.findAllSportKinds());
        model.addAttribute("sportDiscipline", sportDiscipline);
        return "sport_discipline_add";
    }

    @PostMapping("api/sport_discipline/{sportDisciplineId}/edit")
    public String editSportDisciplinePost(@PathVariable Integer sportDisciplineId,
                                          @RequestParam(name = "sportKind") Integer sportKindId,
                                          @RequestParam String name,
                                          @RequestParam String code,
                                          HttpServletRequest httpServletRequest,
                                          Model model) {
        SportDiscipline sportDiscipline = sportDisciplineService.findSportDisciplineById(sportDisciplineId);
        if (sportDiscipline == null) {
            return "redirect:" + httpServletRequest.getHeader("referer");
        }
        sportDiscipline.setSportKind(sportKindService.findSportKindById(sportKindId));
        sportDiscipline.setName(name);
        sportDiscipline.setCode(code);
        boolean isSavedSuccessfully = sportDisciplineService.saveSportDiscipline(sportDiscipline);
        if (isSavedSuccessfully) {
            return "redirect:/api/sport_kinds";
        }
        model.addAllAttributes(sportDisciplineService.getErrors());
        model.addAllAttributes(sportDisciplineService.getPreviousValues());
        model.addAttribute("sportKinds", sportKindService.findAllSportKinds());
        addFieldsLengthConstantsIn(model);
        return "sport_discipline_add";
    }

    /*@GetMapping("api/sport_discipline/{sportDisciplineId}/delete")
    public String deleteSportDiscipline(@PathVariable Integer sportDisciplineId,
                                        HttpServletRequest httpServletRequest) {
        sportDisciplineService.deleteSportDisciplineById(sportDisciplineId);
        return "redirect:" + httpServletRequest.getHeader("referer");
    }*/
}
