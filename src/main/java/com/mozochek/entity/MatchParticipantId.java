package com.mozochek.entity;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class MatchParticipantId implements Serializable {

    @ManyToOne(targetEntity = Team.class)
    @JoinColumn(name = "team_id", nullable = false)
    private Team team;

    @ManyToOne(targetEntity = Match.class)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    public MatchParticipantId() {

    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    @Override
    public String toString() {
        return "MatchParticipantId{" +
                "team=" + team +
                ", match=" + match +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchParticipantId)) return false;
        MatchParticipantId that = (MatchParticipantId) o;
        return Objects.equals(team, that.team) &&
                Objects.equals(match, that.match);
    }

    @Override
    public int hashCode() {
        return Objects.hash(team, match);
    }
}
