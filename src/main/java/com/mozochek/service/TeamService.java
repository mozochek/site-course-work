package com.mozochek.service;

import com.mozochek.entity.Human;
import com.mozochek.entity.Team;
import com.mozochek.entity.Tournament;
import com.mozochek.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Optional;

@Service
public class TeamService extends AbstractService {

    private TeamRepository teamRepository;
    private HumanService humanService;

    public TeamService(TeamRepository teamRepository, HumanService humanService) {
        this.teamRepository = teamRepository;
        this.humanService = humanService;
    }

    public boolean addPeopleToTeam(Tournament tournament, String data) {
        errors = new HashMap<>();
        String[] splitData = data.split("&");
        String[] teamData = splitData[0].split("=");
        Team team = getTeamById(Integer.parseInt(teamData[1]));
        if (team == null) {
            return false;
        }
        String[] peopleData = Arrays.copyOfRange(splitData, 1, splitData.length);
        team.setPeople(null);
        for (int i = 0; i < tournament.getTeamCapacity(); i++) {
            String[] humanData = peopleData[i].split("=");
            Human human = humanService.findHumanById(Integer.parseInt(humanData[1]));
            if (human == null) {
                continue;
            }
            tournament.getTeams().forEach(t -> t.removeHuman(human));
            tournament.removeJudge(human);
            team.addHuman(human);
        }
        if (errors.isEmpty()) {
            teamRepository.save(team);
            return true;
        }
        return false;
    }

    private Team getTeamById(Integer teamId) {
        Optional<Team> optionalTeam = teamRepository.findById(teamId);
        if (optionalTeam.isEmpty()) {
            errors.put("teamError", "Такой команды не существует!");
            return null;
        }
        return optionalTeam.get();
    }

    /*private Human getHumanById(Integer humanId, String error) {
        Human human = humanService.findHumanById(humanId);
        if (human == null) {
            errors.put(error, "Такого человека нет в базе данных!");
            return null;
        }
        return human;
    }*/

    public void saveTeam(Team team) {
        teamRepository.save(team);
    }

    public void deleteAllTeams(Iterable<Team> teams) {
        teamRepository.deleteAll(teams);
    }

    public void saveAllTeams(Iterable<Team> teams) {
        teamRepository.saveAll(teams);
    }

    public Team findTeamById(Integer id) {
        return teamRepository.findById(id).orElse(null);
    }

    public Iterable<Team> findAllTeamsByTournamentIdOrderByDesc(Integer id) {
        return teamRepository.findAllByTournamentIdOrderByIdDesc(id);
    }
}
