package com.mozochek.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "tournament_participants", schema = "webdb")
public class TournamentParticipant {

    @EmbeddedId
    private TournamentParticipantId id;

    public TournamentParticipant() {

    }

    public TournamentParticipantId getId() {
        return id;
    }

    public void setId(TournamentParticipantId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TournamentParticipant{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TournamentParticipant)) return false;
        TournamentParticipant that = (TournamentParticipant) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
