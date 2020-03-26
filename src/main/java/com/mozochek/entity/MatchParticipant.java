package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "match_participants", schema = "webdb")
public class MatchParticipant {

    @EmbeddedId
    private MatchParticipantId id;

    @ManyToOne(targetEntity = Match.class)
    @MapsId("matchId")
    private Match match;

    @ManyToOne(targetEntity = Team.class)
    @MapsId("teamId")
    private Team team;

    public MatchParticipant() {

    }

    public MatchParticipant(Match match, Team team) {
        this.match = match;
        this.team = team;
    }

    public MatchParticipantId getId() {
        return id;
    }

    public void setId(MatchParticipantId id) {
        this.id = id;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    @Override
    public String toString() {
        return "MatchParticipant{" +
                "id=" + id +
                ", match=" + match +
                ", team=" + team +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchParticipant)) return false;
        MatchParticipant that = (MatchParticipant) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(match, that.match) &&
                Objects.equals(team, that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, match, team);
    }
}
