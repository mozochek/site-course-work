package com.mozochek.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MatchParticipantId implements Serializable {

    @Column(name = "match_id", nullable = false)
    private Integer matchId;

    @Column(name = "team_id", nullable = false)
    private Integer teamId;

    public MatchParticipantId() {

    }

    public MatchParticipantId(Integer matchId, Integer teamId) {
        this.matchId = matchId;
        this.teamId = teamId;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public void setMatchId(Integer matchId) {
        this.matchId = matchId;
    }

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }

    @Override
    public String toString() {
        return "MatchParticipantId{" +
                "matchId=" + matchId +
                ", teamId=" + teamId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchParticipantId)) return false;
        MatchParticipantId that = (MatchParticipantId) o;
        return Objects.equals(matchId, that.matchId) &&
                Objects.equals(teamId, that.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, teamId);
    }
}
