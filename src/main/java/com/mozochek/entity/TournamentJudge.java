package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "tournament_judges")
public class TournamentJudge {

    @EmbeddedId
    private TournamentJudgeId id;

    @ManyToOne(targetEntity = Tournament.class)
    @MapsId("tournamentId")
    private Tournament tournament;

    @ManyToOne(targetEntity = Human.class)
    @MapsId("humanId")
    private Human human;

    public TournamentJudge() {

    }

    public TournamentJudge(Tournament tournament, Human human) {
        this.tournament = tournament;
        this.human = human;
    }

    public TournamentJudgeId getId() {
        return id;
    }

    public void setId(TournamentJudgeId id) {
        this.id = id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }

    @Override
    public String toString() {
        return "TournamentJudge{" +
                "id=" + id +
                ", tournament=" + tournament +
                ", human=" + human +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TournamentJudge)) return false;
        TournamentJudge that = (TournamentJudge) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(tournament, that.tournament) &&
                Objects.equals(human, that.human);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tournament, human);
    }
}
