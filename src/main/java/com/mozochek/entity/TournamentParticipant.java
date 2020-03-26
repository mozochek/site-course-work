package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;

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

    public TournamentParticipant() {

    }

    public TournamentParticipant(Human human, Team team) {
        this.human = human;
        this.team = team;
    }

    public TournamentParticipantId getId() {
        return id;
    }

    public void setId(TournamentParticipantId id) {
        this.id = id;
    }

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
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
        return Objects.equals(id, that.id) &&
                Objects.equals(human, that.human) &&
                Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, human, team);
    }
}
