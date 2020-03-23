package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;

//TODO Класс готов!
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

    private MatchParticipant() {

    }

    public MatchParticipant(Match match, Team team) {
        this.match = match;
        this.team = team;
        this.id = new MatchParticipantId(match.getId(), team.getId());
    }

    public MatchParticipantId getId() {
        return id;
    }

    public Match getMatch() {
        return match;
    }

    public Team getTeam() {
        return team;
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
        return id.equals(that.id) &&
                match.equals(that.match) &&
                team.equals(that.team);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, match, team);
    }
}
