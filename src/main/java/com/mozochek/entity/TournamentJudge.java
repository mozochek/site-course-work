package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;

//TODO Класс готов!
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

    private TournamentJudge() {

    }

    public TournamentJudge(Tournament tournament, Human human) {
        this.tournament = tournament;
        this.human = human;
        this.id = new TournamentJudgeId(human.getId(), tournament.getId());
    }

    public TournamentJudgeId getId() {
        return id;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public Human getHuman() {
        return human;
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
        return id.equals(that.id) &&
                tournament.equals(that.tournament) &&
                human.equals(that.human);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tournament, human);
    }
}
