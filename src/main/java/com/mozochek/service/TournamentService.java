package com.mozochek.service;

import com.mozochek.entity.Game;
import com.mozochek.entity.Match;
import com.mozochek.entity.Team;
import com.mozochek.entity.Tournament;
import com.mozochek.repository.TournamentRepository;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.mozochek.utils.LengthConstants.*;

@Service
public class TournamentService extends AbstractService {

    private TournamentRepository tournamentRepository;
    private MatchService matchService;
    private TeamService teamService;
    private GameService gameService;

    public TournamentService(TournamentRepository tournamentRepository,
                             MatchService matchService,
                             TeamService teamService,
                             GameService gameService) {
        this.tournamentRepository = tournamentRepository;
        this.matchService = matchService;
        this.teamService = teamService;
        this.gameService = gameService;
    }

    public boolean saveTournament(Tournament tournament, String name, Short teamAmount, boolean isCreated) {
        errors = new HashMap<>();
        previousValues = new HashMap<>();

        validateData(tournament, name);

        if (errors.isEmpty()) {
            tournament.setName(name);
            tournamentRepository.save(tournament);
            if (isCreated) { // при создании турнира
                for (int i = 0; i < teamAmount; i++) {
                    tournament.addTeam(new Team("Команда " + (i + 1), tournament));
                }
                teamService.saveAllTeams(tournament.getTeams());
                return true;
            } else { // при редактировании турнира
                Short prevAmount = tournament.getTeamAmount();
                if (prevAmount > teamAmount) { // уменьшение кол-ва команд
                    Iterator<Team> iterator = teamService.findAllTeamsByTournamentIdOrderByDesc(tournament.getId()).iterator();
                    Team team;
                    Set<Team> deletedTeams = new HashSet<>();
                    Set<Match> deletedMatches = new HashSet<>();
                    Set<Game> deletedGames = new HashSet<>();
                    for (int i = teamAmount; i < prevAmount; i++) {
                        team = iterator.next();
                        Set<Match> matches = team.getMatches();
                        for (Match match : matches) {
                            deletedGames.addAll(match.getGames());
                        }
                        deletedMatches.addAll(matches);
                        deletedTeams.add(team);
                    }
                    gameService.deleteAllGames(deletedGames);
                    matchService.deleteAllMatches(deletedMatches);
                    if (deletedTeams.contains(tournament.getWinnerTeam())) {
                        tournament.setWinnerTeam(null);
                        tournamentRepository.save(tournament);
                    }
                    teamService.deleteAllTeams(deletedTeams);
                } else if (prevAmount < teamAmount) { // увеличение кол-ва команд
                    for (int i = prevAmount; i < teamAmount; i++) {
                        tournament.addTeam(new Team("Команда " + (i + 1), tournament));
                    }
                    teamService.saveAllTeams(tournament.getTeams());
                }
                tournament.setTeamAmount(teamAmount);
                tournamentRepository.save(tournament);
                return true;
            }
        }
        previousValues.put("tournament", tournament);
        return false;
    }

    public void deleteTournament(Integer tournamentId) {
        Tournament tournament = findTournamentById(tournamentId);
        Set<Match> deletedMatches = tournament.getMatches();
        Set<Game> deletedGames = new HashSet<>();
        Set<Team> deletedTeams = new HashSet<>();//tournament.getTeams();
        deletedMatches.forEach(m -> {
            deletedGames.addAll(m.getGames());
            deletedTeams.addAll(m.getTeams());
        });
        /*for (Match match : deletedMatches) {
            deletedGames.addAll(match.getGames());
            deletedTeams.addAll(match.getTeams());
        }*/
        gameService.deleteAllGames(deletedGames);
        matchService.deleteAllMatches(deletedMatches);
        teamService.deleteAllTeams(tournament.getTeams());
        tournamentRepository.delete(tournament);
    }

    public void setWinnerTeam(Tournament tournament, Integer teamId) {
        Team team = teamService.findTeamById(teamId);
        if (teamId != -1 && team == null) {
            errors.put("teamError", "Ошибка выбора команды!");
        } else {
            tournament.setWinnerTeam(team);
        }
    }

    private void validateData(Tournament tournament, String name) {
        validateField(name, TOURNAMENT_NAME_LENGTH, "nameError");
        validateField(tournament.getFormat(), TOURNAMENT_FORMAT_LENGTH, "formatError");
        validateField(tournament.getCity(), CITY_LENGTH, "cityError");
        validateField(tournament.getAgeGroup(), TOURNAMENT_AGE_GROUP_LENGTH, "ageGroupError");
        //validateField(tournament.getGender(), TOURNAMENT_GENDER_LENGTH, "genderError");
        validateField(tournament.getCategory(), TOURNAMENT_CATEGORY_LENGTH, "categoryError");
        validateField(tournament.getTournamentClass(), TOURNAMENT_CLASS_LENGTH, "tournamentClassError");
        if (tournament.getDateFrom() == null) {
            errors.put("dateFromError", "Введите дату в формате 'дд.мм.гггг'!");
        }
        if (tournament.getDateTill() == null) {
            errors.put("dateTillError", "Введите дату в формате 'дд.мм.гггг'!");
        }
    }

    public Tournament findTournamentById(Integer id) {
        return tournamentRepository.findById(id).orElse(null);
    }

    public void saveTournament(Tournament tournament) {
        tournamentRepository.save(tournament);
    }

    public Iterable<Tournament> findAllTournaments() {
        Iterable<Tournament> tournaments = tournamentRepository.findAll();
        if (!tournaments.iterator().hasNext()) {
            return null;
        }
        return tournaments;
    }
}
