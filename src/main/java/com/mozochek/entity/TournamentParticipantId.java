package com.mozochek.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TournamentParticipantId implements Serializable {

    @Column(name = "human_id", nullable = false)
    private Integer humanId;

    @Column(name = "team_id", nullable = false)
    private Integer teamId;

    public TournamentParticipantId() {

    }

    public TournamentParticipantId(Integer humanId, Integer teamId) {
        this.humanId = humanId;
        this.teamId = teamId;
    }

    public Integer getHumanId() {
        return humanId;
    }

    public void setHumanId(Integer humanId) {
        this.humanId = humanId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "TournamentParticipantId{" +
                "humanId=" + humanId +
                ", teamId=" + teamId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TournamentParticipantId)) return false;
        TournamentParticipantId that = (TournamentParticipantId) o;
        return Objects.equals(humanId, that.humanId) &&
                Objects.equals(teamId, that.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(humanId, teamId);
    }
}
