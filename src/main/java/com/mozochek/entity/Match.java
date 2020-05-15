package com.mozochek.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.SortedSet;

@Entity
@Table(name = "matches", schema = "webdb")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Short firstTeamScore = 0;

    @Column
    private Short secondTeamScore = 0;

    // Многие-к-одному с Tournament | У множества матчей может быть один турнир
    @ManyToOne(targetEntity = Tournament.class)
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    // Многие-к-одному с Team | У множества матчей победителем может быть одна команда
    @ManyToOne(targetEntity = Team.class)
    @JoinColumn(name = "winner_team_id")
    private Team winnerTeam;

    // Многие-ко-многим с Team | Команды-участницы конкретного матча (в одном матче учавствуют только две команды)
    @ManyToMany(targetEntity = Team.class)
    @JoinTable(name = "matches_teams", joinColumns = @JoinColumn(name = "match_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "team_id", nullable = false))
    private Set<Team> teams;

    // Один-ко-многим с Game | Все игры данного матча
    @OrderBy("id")
    @OneToMany(targetEntity = Game.class, mappedBy = "match")
    private Set<Game> games;

    public Match() {

    }

    public Match(Tournament tournament, Team firstTeam, Team secondTeam) {

        this.tournament = tournament;
        addTeam(firstTeam);
        addTeam(secondTeam);
    }

    /*
     * Getters and setters
     */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getFirstTeamScore() {
        return firstTeamScore;
    }

    public void setFirstTeamScore(Short firstTeamScore) {
        this.firstTeamScore = firstTeamScore;
    }

    public Short getSecondTeamScore() {
        return secondTeamScore;
    }

    public void setSecondTeamScore(Short secondTeamScore) {
        this.secondTeamScore = secondTeamScore;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Team getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(Team winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    public Team getFirstTeam() {
        return teams.stream().findFirst().orElse(null);
    }

    public Team getSecondTeam() {
        return teams.stream().skip(1).findFirst().orElse(null);
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(SortedSet<Game> games) {
        this.games = games;
    }

    /*
     * Helper methods
     */
    public void addTeam(Team team) {
        if (teams == null) {
            teams = new HashSet<>();
        }
        teams.add(team);
    }

    public void removeTeam(Team team) {
        teams.remove(team);
    }

    public void addGame(Game game) {
        if (games == null) {
            games = new HashSet<>();
        }
        games.add(game);
    }

    public void removeGame(Game game) {
        if (games != null) {
            games.remove(game);
        }
    }

    public void clearGames() {
        games.clear();
    }

    public void updateScores() {
        if (!games.isEmpty()) {
            firstTeamScore = 0;
            secondTeamScore = 0;
            for (Game game : games) {
                if (game.getWinnerTeam() != null) {
                    if (game.getWinnerTeam().equals(getFirstTeam())) {
                        firstTeamScore++;
                    } else if (game.getWinnerTeam().equals(getSecondTeam())) {
                        secondTeamScore++;
                    }
                }
            }
        }
    }

    /*
     * toString, equals, hashCode
     */
    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", winnerScore=" + firstTeamScore +
                ", looserScore=" + secondTeamScore +
                ", tournament=" + tournament +
                ", winnerTeam=" + winnerTeam +
                ", teams=" + teams +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return Objects.equals(id, match.id) &&
                Objects.equals(firstTeamScore, match.firstTeamScore) &&
                Objects.equals(secondTeamScore, match.secondTeamScore) &&
                Objects.equals(tournament, match.tournament) &&
                Objects.equals(winnerTeam, match.winnerTeam);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstTeamScore, secondTeamScore, tournament, winnerTeam);
    }
}
