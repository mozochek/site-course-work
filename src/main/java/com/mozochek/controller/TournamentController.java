package com.mozochek.controller;

import com.mozochek.entity.SportDiscipline;
import com.mozochek.entity.SportKind;
import com.mozochek.entity.Tournament;
import com.mozochek.repository.SportDisciplineRepository;
import com.mozochek.repository.SportKindRepository;
import com.mozochek.repository.TournamentRepository;
import com.mozochek.service.TournamentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.ArrayList;
import java.util.Optional;

import static com.mozochek.utils.LengthConstants.*;

@Controller
@RequestMapping("/tournament")
public class TournamentController {

    private SportKindRepository sportKindRepository;
    private SportDisciplineRepository sportDisciplineRepository;
    private TournamentRepository tournamentRepository;
    private TournamentService tournamentService;

    private Iterable<SportKind> sportKinds;
    private Tournament tournament;

    public TournamentController(SportKindRepository sportKindRepository,
                                SportDisciplineRepository sportDisciplineRepository,
                                TournamentRepository tournamentRepository,
                                TournamentService tournamentService) {
        this.sportKindRepository = sportKindRepository;
        this.sportDisciplineRepository = sportDisciplineRepository;
        this.tournamentRepository = tournamentRepository;
        this.tournamentService = tournamentService;
    }

    @GetMapping("/{id}")
    public String showTournamentInfo(Model model, @PathVariable(name = "id") Integer id) {
        Optional<Tournament> tournament = tournamentRepository.findById(id);
        if (tournament.isPresent()) {
            model.addAttribute("tournament", tournament.get());
            return "tournament";
        }
        return "redirect:/tournaments";
    }

    @GetMapping("/registration")
    public String tournamentRegistration(Model model) {
        model.addAttribute("sportKinds", sportKindRepository.findAll());
        return "tournament_registration";
    }

    @PostMapping("/registration")
    public Object tournamentRegistration(@RequestParam(name = "sportKind") Integer sportKindId,
                                         RedirectAttributes redirectAttributes,
                                         Model model) {
        ArrayList<SportDiscipline> sportDisciplines = (ArrayList<SportDiscipline>) sportDisciplineRepository.findBySportKindId(sportKindId);

        RedirectView redirectView;
        if (isSportKindHaveDisciplines(sportKindId)) {
            redirectView = new RedirectView("/tournament/settings");
            redirectAttributes.addFlashAttribute("sportDisciplines", sportDisciplines);
            redirectAttributes.addFlashAttribute("isRedirected", "yes");
            return redirectView;
        }
        model.addAttribute("sportKinds", sportKindRepository.findAll());
        model.addAttribute("sportDisciplinesError", "У выбранного Вами вида спорта отсутствуют спортивные дисциплины!");
        model.addAttribute("prevSelectedSportKind", sportKindId);
        return "tournament_registration";
    }

    @GetMapping("/settings")
    public String tournamentSettings(@ModelAttribute("isRedirected") String isRedirected,
                                     Model model) {
        if (isRedirected == null) {
            return "redirect:/tournaments";
        }
        addFieldsLengthConstants(model);
        return "tournament_settings";
    }

    @PostMapping("/settings")
    public String tournamentSettings(@RequestParam(name = "tournamentSportDiscipline") Integer tournamentSportDisciplineId,
                                     @RequestParam String tournamentName,
                                     @RequestParam String tournamentCity,
                                     @RequestParam String tournamentDateFrom,
                                     @RequestParam String tournamentDateTill,
                                     @RequestParam String tournamentFormat,
                                     @RequestParam String tournamentGender,
                                     @RequestParam String tournamentAgeGroup,
                                     @RequestParam String tournamentCategory,
                                     @RequestParam String tournamentClass,
                                     Model model) {
        tournament = new Tournament();
        tournament.setName(tournamentName);
        tournament.setSportDiscipline(sportDisciplineRepository.findById(tournamentSportDisciplineId).get());
        tournament.setCity(tournamentCity);
        tournament.setFormat(tournamentFormat);
        tournament.setGender(tournamentGender);
        tournament.setAgeGroup(tournamentAgeGroup);
        tournament.setCategory(tournamentCategory);
        tournament.setTournamentClass(tournamentClass);

        boolean isAdded = tournamentService.addTournament(tournament, tournamentDateFrom, tournamentDateTill);

        if (isAdded) {
            return "redirect:/tournament/" + tournament.getId();
        }
        addFieldsLengthConstants(model);
        model.addAllAttributes(tournamentService.getErrors());
        model.addAllAttributes(tournamentService.getPreviousValues());
        model.addAttribute("sportDisciplines", sportDisciplineRepository.findBySportKindId(sportDisciplineRepository.findById(tournament.getSportDiscipline().getId()).get().getSportKind().getId()));
        return "tournament_settings";

    }

    private void addFieldsLengthConstants(Model model) {
        model.addAttribute("tournamentNameLength", TOURNAMENT_NAME_LENGTH);
        model.addAttribute("tournamentCityLength", CITY_LENGTH);
        model.addAttribute("dateLength", DATE_LENGTH);
        model.addAttribute("tournamentFormatLength", TOURNAMENT_FORMAT_LENGTH);
        model.addAttribute("tournamentGenderLength", TOURNAMENT_GENDER_LENGTH);
        model.addAttribute("tournamentAgeGroupLength", TOURNAMENT_AGE_GROUP_LENGTH);
        model.addAttribute("tournamentCategoryLength", TOURNAMENT_CATEGORY_LENGTH);
        model.addAttribute("tournamentClassLength", TOURNAMENT_CLASS_LENGTH);
    }

    private boolean isSportKindHaveDisciplines(Integer sportKindId) {
        ArrayList<SportDiscipline> sportDisciplines = (ArrayList<SportDiscipline>) sportDisciplineRepository.findBySportKindId(sportKindId);
        return sportDisciplines.size() > 0;
    }
}
