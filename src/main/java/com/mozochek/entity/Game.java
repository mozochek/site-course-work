package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "games", schema = "webdb")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "winner_score")
    private Short winnerScore;

    @Column(name = "looser_score")
    private Short looserScore;

    @ManyToOne(targetEntity = Team.class)
    @JoinColumn(name = "winner_team_id")
    private Team winnerTeam;

    @ManyToOne(targetEntity = Match.class)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    public Game() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Short getWinnerScore() {
        return winnerScore;
    }

    public void setWinnerScore(Short winnerScore) {
        this.winnerScore = winnerScore;
    }

    public Short getLooserScore() {
        return looserScore;
    }

    public void setLooserScore(Short looserScore) {
        this.looserScore = looserScore;
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

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", match=" + match +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id) &&
                Objects.equals(match, game.match);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, match);
    }
}
