package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "matches", schema = "webdb")
public class Match {

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

    @ManyToOne(targetEntity = Tournament.class)
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    public Match() {

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

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", tournament=" + tournament +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return Objects.equals(id, match.id) &&
                Objects.equals(tournament, match.tournament);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tournament);
    }
}
