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

import java.util.Optional;

import static com.mozochek.utils.LengthConstants.*;

@Controller
//@RequestMapping("/admin/tournament")
public class TournamentController {

    private SportKindRepository sportKindRepository;
    private SportDisciplineRepository sportDisciplineRepository;
    private TournamentRepository tournamentRepository;
    private TournamentService tournamentService;

    private Iterable<SportKind> sportKinds;

    public TournamentController(SportKindRepository sportKindRepository,
                                SportDisciplineRepository sportDisciplineRepository,
                                TournamentRepository tournamentRepository,
                                TournamentService tournamentService) {
        this.sportKindRepository = sportKindRepository;
        this.sportDisciplineRepository = sportDisciplineRepository;
        this.tournamentRepository = tournamentRepository;
        this.tournamentService = tournamentService;
    }

    @GetMapping("/tournament/{id}")
    public String showTournamentInfo(Model model,
                                     @PathVariable(name = "id") Integer id) {
        Optional<Tournament> tournament = tournamentRepository.findById(id);
        if (tournament.isPresent()) {
            model.addAttribute("tournament", tournament.get());
            return "tournament";
        }
        return "redirect:/tournaments";
    }

    @GetMapping("/admin/tournament/registration")
    public String tournamentRegistration(Model model) {
        model.addAttribute("sportKinds", sportKindRepository.findAll());
        return "tournament_registration";
    }

    @PostMapping("/admin/tournament/registration")
    public Object tournamentRegistration(@RequestParam(name = "sportKind") Integer sportKindId,
                                         RedirectAttributes redirectAttributes,
                                         Model model) {
        if (isSportKindHaveDisciplines(sportKindId)) {
            RedirectView redirectView = new RedirectView("/admin/tournament/settings");
            redirectAttributes.addFlashAttribute("sportKindId", sportKindId);//
            redirectAttributes.addFlashAttribute("sportDisciplines", sportDisciplineRepository.findBySportKindId(sportKindId));
            redirectAttributes.addFlashAttribute("isRedirected", "yes");
            return redirectView;
        }
        model.addAttribute("sportKinds", sportKindRepository.findAll());
        model.addAttribute("sportDisciplinesError", "У выбранного Вами вида спорта отсутствуют спортивные дисциплины!");
        model.addAttribute("prevSelectedSportKind", sportKindId);
        return "tournament_registration";
    }

    @GetMapping("/admin/tournament/settings")
    public String tournamentSettings(@ModelAttribute("isRedirected") String isRedirected,
                                     Model model) {
        if (isRedirected == null) {
            return "redirect:/tournaments";
        }
        addFieldsLengthConstants(model);
        return "tournament_settings";
    }

    @PostMapping("/admin/tournament/settings")
    public String tournamentSettings(@RequestParam String tournamentName,
                                     @RequestParam String tournamentFormat,
                                     @RequestParam String tournamentCity,
                                     @RequestParam String tournamentAgeGroup,
                                     @RequestParam String tournamentGender,
                                     @RequestParam String tournamentCategory,
                                     @RequestParam String tournamentClass,
                                     @RequestParam Integer tournamentSportDisciplineId,
                                     @RequestParam String tournamentDateFrom,
                                     @RequestParam String tournamentDateTill,
                                     Model model) {
        // Тут не проверяется ID вида спорта у спортивной дисциплины, поэтому тут может быть ошибка, если HTML-форму ручками подправить и отправить POST
        Optional<SportDiscipline> sportDiscipline = sportDisciplineRepository.findById(tournamentSportDisciplineId);
        if (sportDiscipline.isEmpty()) {
            return "redirect:/admin/tournament/registration";
        }

        Tournament tournament = new Tournament(tournamentName, tournamentFormat, tournamentCity, tournamentAgeGroup, tournamentGender, tournamentCategory, tournamentClass);
        tournament.setSportDiscipline(sportDisciplineRepository.findById(tournamentSportDisciplineId).get());

        boolean isSavedSuccessfully = tournamentService.addTournament(tournament, tournamentDateFrom, tournamentDateTill);
        if (isSavedSuccessfully) {
            return "redirect:tournament/" + tournament.getId();
        }
        model.addAllAttributes(tournamentService.getErrors());
        model.addAllAttributes(tournamentService.getPreviousValues());
        model.addAttribute("sportDisciplines", sportDisciplineRepository.findBySportKindId(sportDisciplineRepository.findById(tournament.getSportDiscipline().getId()).get().getSportKind().getId()));
        addFieldsLengthConstants(model);
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
        return sportDisciplineRepository.findBySportKindId(sportKindId).iterator().hasNext();
    }
}
