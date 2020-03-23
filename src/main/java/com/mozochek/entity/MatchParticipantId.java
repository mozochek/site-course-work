package com.mozochek.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

//TODO Класс готов!
@Embeddable
public class MatchParticipantId implements Serializable {

    @Column(name = "match_id", nullable = false)
    private Integer matchId;

    @Column(name = "team_id", nullable = false)
    private Integer teamId;

    private MatchParticipantId() {

    }

    public MatchParticipantId(Integer matchId, Integer teamId) {
        this.matchId = matchId;
        this.teamId = teamId;
    }

    public Integer getMatchId() {
        return matchId;
    }

    public Integer getTeamId() {
        return teamId;
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
        return matchId.equals(that.matchId) &&
                teamId.equals(that.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(matchId, teamId);
    }
}
