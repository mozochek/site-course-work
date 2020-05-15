package com.mozochek.controller;

import com.mozochek.entity.Human;
import com.mozochek.service.HumanService;
import com.mozochek.utils.FieldsLengthConstantsImplementing;
import com.mozochek.utils.GenderHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

import static com.mozochek.utils.DateUtil.parseDate;

@Controller
public class HumanController implements FieldsLengthConstantsImplementing {

    private HumanService humanService;

    public HumanController(HumanService humanService) {
        this.humanService = humanService;
    }

    @GetMapping("api/people")
    public String showPeople(Model model) {
        Iterable<Human> people = humanService.findPeople();
        if (!people.iterator().hasNext()) {
            people = null;
        }
        model.addAttribute("people", people);
        return "people";
    }

    @GetMapping("api/human/add")
    public String addHumanGet(Model model) {
        addFieldsLengthConstantsIn(model);
        return "human_add";
    }

    @PostMapping("api/human/add")
    public String addHumanPost(@RequestParam String name,
                               @RequestParam String surname,
                               @RequestParam String patronymic,
                               @RequestParam String birthDate,
                               @RequestParam String city,
                               @RequestParam Integer genderId,
                               Model model) {
        Human human = new Human(name.trim(), surname.trim(), patronymic.trim(), city.trim(), parseDate(birthDate), GenderHandler.getGenderById(genderId));
        boolean isSavedSuccessfully = humanService.saveHuman(human);
        if (isSavedSuccessfully) {
            model.addAttribute("saveSuccessful", "Успешно добавлено!");
        } else {
            model.addAllAttributes(humanService.getErrors());
            model.addAllAttributes(humanService.getPreviousValues());
        }
        addFieldsLengthConstantsIn(model);
        return "human_add";
    }

    @GetMapping("api/human/{humanId}/edit")
    public String editHumanGet(@PathVariable Integer humanId,
                               HttpServletRequest httpServletRequest,
                               Model model) {
        Human human = humanService.findHumanById(humanId);
        if (human == null) {
            return "redirect:" + httpServletRequest.getHeader("referer");
        }
        model.addAttribute("human", human);
        return "human_add";
    }

    @PostMapping("api/human/{humanId}/edit")
    public String editHumanPost(@PathVariable Integer humanId,
                                @RequestParam String name,
                                @RequestParam String surname,
                                @RequestParam String patronymic,
                                @RequestParam String birthDate,
                                @RequestParam String city,
                                @RequestParam Integer genderId,
                                HttpServletRequest httpServletRequest,
                                Model model) {
        Human human = humanService.findHumanById(humanId);
        if (human == null) {
            return "redirect:" + httpServletRequest.getHeader("referer");
        }
        human.setName(name.trim());
        human.setSurname(surname.trim());
        human.setPatronymic(patronymic.trim());
        human.setBirthDate(parseDate(birthDate));
        human.setCity(city.trim());
        human.setGender(GenderHandler.getGenderById(genderId));
        boolean isSavedSuccessfully = humanService.saveHuman(human);
        if (isSavedSuccessfully) {
            return "redirect:/api/people";
        } else {
            model.addAllAttributes(humanService.getErrors());
            model.addAllAttributes(humanService.getPreviousValues());
        }
        addFieldsLengthConstantsIn(model);
        return "human_add";
    }

    /*@GetMapping("api/human/{humanId}/delete")
    public String deleteHuman(@PathVariable Integer humanId,
                              HttpServletRequest httpServletRequest) {
        humanService.deleteHumanById(humanId);
        return "redirect:" + httpServletRequest.getHeader("referer");
    }*/
}
