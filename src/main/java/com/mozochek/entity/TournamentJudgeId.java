package com.mozochek.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TournamentJudgeId implements Serializable {

    @ManyToOne(targetEntity = Human.class)
    @OneToOne(targetEntity = Tournament.class, mappedBy = "mainJudge")
    @JoinColumn(name = "human_id", nullable = false)
    private Human human;

    @ManyToOne(targetEntity = Tournament.class)
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    public TournamentJudgeId() {

    }

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @Override
    public String toString() {
        return "TournamentJudgeId{" +
                "human=" + human +
                ", tournament=" + tournament +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TournamentJudgeId)) return false;
        TournamentJudgeId that = (TournamentJudgeId) o;
        return Objects.equals(human, that.human) &&
                Objects.equals(tournament, that.tournament);
    }

    @Override
    public int hashCode() {
        return Objects.hash(human, tournament);
    }
}
