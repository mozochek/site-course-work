package com.mozochek.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TournamentParticipantId implements Serializable {

    @ManyToOne(targetEntity = Team.class)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToOne(targetEntity = Human.class)
    @JoinColumn(name = "human_id", nullable = false)
    private Human human;

    public TournamentParticipantId() {

    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Human getHuman() {
        return human;
    }

    public void setHuman(Human human) {
        this.human = human;
    }

    @Override
    public String toString() {
        return "TournamentParticipantId{" +
                "team=" + team +
                ", human=" + human +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TournamentParticipantId)) return false;
        TournamentParticipantId that = (TournamentParticipantId) o;
        return Objects.equals(team, that.team) &&
                Objects.equals(human, that.human);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, human);
    }
}
