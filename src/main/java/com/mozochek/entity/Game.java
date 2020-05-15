package com.mozochek.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;


@Entity
@Table(name = "games", schema = "webdb")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Short firstTeamScore = 0;

    @Column
    private Short secondTeamScore = 0;

    // Многие-к-одному с Team | У множества игр победителем может быть одна команда
    @ManyToOne(targetEntity = Team.class)
    @JoinColumn(name = "winner_team_id")
    private Team winnerTeam;

    // Многие-к-одному с Match | У множества игр может быть один матч
    @ManyToOne(targetEntity = Match.class)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    public Game() {

    }

    /*
     * Getters and setters
     */
    public Game(Match match) {
        this.match = match;
    }

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

    public Team getWinnerTeam() {
        return winnerTeam;
    }

    public void setWinnerTeam(Team winnerTeam) {
        this.winnerTeam = winnerTeam;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    /*
     * toString, equals, hashCode
     */
    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", winnerScore=" + firstTeamScore +
                ", looserScore=" + secondTeamScore +
                ", winnerTeam=" + winnerTeam +
                ", match=" + match +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id) &&
                Objects.equals(firstTeamScore, game.firstTeamScore) &&
                Objects.equals(secondTeamScore, game.secondTeamScore) &&
                Objects.equals(winnerTeam, game.winnerTeam) &&
                Objects.equals(match, game.match);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstTeamScore, secondTeamScore, winnerTeam, match);
    }
}
