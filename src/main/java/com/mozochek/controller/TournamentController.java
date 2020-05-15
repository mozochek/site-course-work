package com.mozochek.controller;

import com.mozochek.entity.Human;
import com.mozochek.entity.Tournament;
import com.mozochek.service.HumanService;
import com.mozochek.service.SportDisciplineService;
import com.mozochek.service.SportKindService;
import com.mozochek.service.TournamentService;
import com.mozochek.utils.FieldsLengthConstantsImplementing;
import com.mozochek.utils.GenderHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;

import static com.mozochek.utils.DateUtil.parseDate;

@Controller
public class TournamentController implements FieldsLengthConstantsImplementing {

    private TournamentService tournamentService;
    private SportKindService sportKindService;
    private SportDisciplineService sportDisciplineService;
    private HumanService humanService;

    public TournamentController(TournamentService tournamentService,
                                SportKindService sportKindService,
                                SportDisciplineService sportDisciplineService,
                                HumanService humanService) {
        this.tournamentService = tournamentService;
        this.sportKindService = sportKindService;
        this.sportDisciplineService = sportDisciplineService;
        this.humanService = humanService;
    }

    @GetMapping("/tournament/{tournamentId}")
    public String tournamentGet(@PathVariable Integer tournamentId,
                                HttpServletRequest httpServletRequest,
                                Model model) {
        Tournament tournament = tournamentService.findTournamentById(tournamentId);
        if (tournament == null) {
            return "redirect:" + httpServletRequest.getHeader("referer");
        }
        model.addAttribute("tournament", tournament);
        return "tournament";
    }

    @GetMapping("/api/tournament/registration")
    public Object tournamentRegistrationGet(RedirectAttributes redirectAttributes,
                                            Model model) {
        if (!sportKindService.findAllSportKinds().iterator().hasNext()) {
            RedirectView redirectView = new RedirectView("/api/sport_discipline/add");
            redirectAttributes.addFlashAttribute("sportDisciplinesEmpty", "Нельзя добавить турнир без спортивной дисциплины!");
            return redirectView;
        }
        model.addAttribute("sportKinds", sportKindService.findAllSportKinds());
        return "tournament_registration";
    }

    @PostMapping("/api/tournament/registration")
    public Object tournamentRegistrationPost(@RequestParam(name = "sportKind") Integer sportKindId,
                                             RedirectAttributes redirectAttributes,
                                             Model model) {
        if (sportDisciplineService.findSportDisciplinesBySportKindId(sportKindId).iterator().hasNext()) {
            RedirectView redirectView = new RedirectView("/api/tournament/settings");
            redirectAttributes.addFlashAttribute("sportDisciplines", sportDisciplineService.findSportDisciplinesBySportKindId(sportKindId));
            redirectAttributes.addFlashAttribute("isRedirected", true);
            return redirectView;
        }
        model.addAttribute("sportKinds", sportKindService.findAllSportKinds());
        model.addAttribute("sportDisciplinesError", "У выбранного Вами вида спорта отсутствуют спортивные дисциплины!");
        model.addAttribute("prevSelectedSportKind", sportKindId);
        return "tournament_registration";
    }

    @GetMapping("/api/tournament/settings")
    public String tournamentSettingsGet(@ModelAttribute("isRedirected") boolean isRedirected,
                                        Model model) {
        if (isRedirected) {
            addFieldsLengthConstantsIn(model);
            return "tournament_settings";
        }
        return "redirect:/api/tournament/registration";
    }

    @PostMapping("/api/tournament/settings")
    public String tournamentSettingsPost(@RequestParam String name,
                                         @RequestParam(name = "sportDiscipline") Integer sportDisciplineId,
                                         @RequestParam String city,
                                         @RequestParam String dateFrom,
                                         @RequestParam String dateTill,
                                         @RequestParam Integer genderId,
                                         @RequestParam String ageGroup,
                                         @RequestParam Short teamAmount,
                                         @RequestParam Short teamCapacity,
                                         @RequestParam String format,
                                         @RequestParam String category,
                                         @RequestParam String tournamentClass,
                                         Model model) {
        Tournament tournament = new Tournament(name.trim(), format.trim(), city.trim(), parseDate(dateFrom), parseDate(dateTill), ageGroup.trim(), GenderHandler.getGenderById(genderId), category.trim(), tournamentClass.trim(), teamCapacity, teamAmount, sportDisciplineService.findSportDisciplineById(sportDisciplineId));
        boolean isSavedSuccessfully = tournamentService.saveTournament(tournament, name.trim(), teamAmount, true);
        if (isSavedSuccessfully) {
            return "redirect:/tournament/" + tournament.getId();
        }
        model.addAllAttributes(tournamentService.getErrors());
        model.addAllAttributes(tournamentService.getPreviousValues());
        model.addAttribute("sportDisciplines", sportDisciplineService.findSportDisciplinesBySportKindId(tournament.getSportDiscipline().getSportKind().getId()));
        addFieldsLengthConstantsIn(model);
        return "tournament_settings";
    }

    @GetMapping("api/tournament/{tournamentId}/judge/add")
    public String addJudgeGet(@PathVariable Integer tournamentId,
                              HttpServletRequest httpServletRequest,
                              Model model) {
        Tournament tournament = tournamentService.findTournamentById(tournamentId);
        if (tournament == null) {
            return "redirect:" + httpServletRequest.getHeader("referer");
        }
        model.addAttribute("humans", humanService.findPeople());
        return "judge_add";
    }

    @PostMapping("api/tournament/{tournamentId}/judge/add")
    public String addJudgePost(@RequestBody String data,
                               @PathVariable Integer tournamentId,
                               HttpServletRequest httpServletRequest,
                               Model model) {
        Tournament tournament = tournamentService.findTournamentById(tournamentId);
        if (tournament == null) {
            return "redirect:" + httpServletRequest.getHeader("referer");
        }
        String[] splitData = data.split("&");
        String[] judgeData = splitData[0].split("=");
        Integer judgeId = Integer.parseInt(judgeData[1]);
        Human judge = humanService.findHumanById(judgeId);
        tournament.getTeams().forEach(t -> t.removeHuman(judge));
        tournament.addJudge(judge);
        tournamentService.saveTournament(tournament);
        model.addAttribute("saveSuccessful", "Успешно добавлено!");
        model.addAttribute("humans", humanService.findPeople());
        return "judge_add";
    }

    @GetMapping("api/tournament/{tournamentId}/edit")
    public String editTournamentGet(@PathVariable Integer tournamentId,
                                    HttpServletRequest httpServletRequest,
                                    Model model) {
        Tournament tournament = tournamentService.findTournamentById(tournamentId);
        if (tournament == null) {
            return "redirect:" + httpServletRequest.getHeader("referer");
        }
        model.addAttribute("tournament", tournament);
        model.addAttribute("sportDisciplines", sportDisciplineService.findSportDisciplinesBySportKindId(tournament.getSportDiscipline().getSportKind().getId()));
        model.addAttribute("edit", true);
        return "tournament_settings";
    }

    @PostMapping("api/tournament/{tournamentId}/edit")
    public String editTournamentPost(@PathVariable Integer tournamentId,
                                     @RequestParam String name,
                                     @RequestParam(name = "sportDiscipline") Integer sportDisciplineId,
                                     @RequestParam String city,
                                     @RequestParam String dateFrom,
                                     @RequestParam String dateTill,
                                     @RequestParam Integer genderId,
                                     @RequestParam String ageGroup,
                                     @RequestParam Short teamAmount,
                                     @RequestParam Short teamCapacity,
                                     @RequestParam String format,
                                     @RequestParam String category,
                                     @RequestParam String tournamentClass,
                                     @RequestParam Integer winnerTeam,
                                     HttpServletRequest httpServletRequest,
                                     Model model) {
        Tournament tournament = tournamentService.findTournamentById(tournamentId);
        if (tournament == null) {
            return "redirect:" + httpServletRequest.getHeader("referer");
        }
        tournament.setFormat(format.trim());
        tournament.setCity(city.trim());
        tournament.setDateFrom(parseDate(dateFrom));
        tournament.setDateTill(parseDate(dateTill));
        tournament.setAgeGroup(ageGroup.trim());
        tournament.setGender(GenderHandler.getGenderById(genderId));
        tournament.setCategory(category.trim());
        tournament.setTournamentClass(tournamentClass.trim());
        tournament.setTeamCapacity(teamCapacity);
        tournament.setSportDiscipline(sportDisciplineService.findSportDisciplineById(sportDisciplineId));
        tournamentService.setWinnerTeam(tournament, winnerTeam);
        boolean isSavedSuccessfully = tournamentService.saveTournament(tournament, name.trim(), teamAmount, false);
        if (isSavedSuccessfully) {
            return "redirect:/tournament/" + tournamentId;
        }
        model.addAllAttributes(tournamentService.getErrors());
        model.addAllAttributes(tournamentService.getPreviousValues());
        model.addAttribute("sportDisciplines", sportDisciplineService.findSportDisciplinesBySportKindId(tournament.getSportDiscipline().getSportKind().getId()));
        addFieldsLengthConstantsIn(model);
        return "tournament_settings";
    }

    @GetMapping("api/tournament/{tournamentId}/delete")
    public String deleteTournament(@PathVariable Integer tournamentId) {
        tournamentService.deleteTournament(tournamentId);
        return "redirect:/tournaments";
    }
}
