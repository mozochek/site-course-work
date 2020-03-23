package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;

//TODO Класс готов!
@Entity
@Table(name = "tournament_participants", schema = "webdb")
public class TournamentParticipant {

    @EmbeddedId
    private TournamentParticipantId id;

    @ManyToOne(targetEntity = Human.class)
    @MapsId("humanId")
    private Human human;

    @ManyToOne(targetEntity = Team.class)
    @MapsId("teamId")
    private Team team;

    private TournamentParticipant() {

    }

    public TournamentParticipant(Human human, Team team) {
        this.human = human;
        this.team = team;
        this.id = new TournamentParticipantId(human.getId(), team.getId());
    }

    public TournamentParticipantId getId() {
        return id;
    }

    public Human getHuman() {
        return human;
    }

    public Team getTeam() {
        return team;
    }

    @Override
    public String toString() {
        return "TournamentParticipant{" +
                "id=" + id +
                ", human=" + human +
                ", team=" + team +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TournamentParticipant)) return false;
        TournamentParticipant that = (TournamentParticipant) o;
        return id.equals(that.id) &&
                human.equals(that.human) &&
                team.equals(that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, human, team);
    }
}
