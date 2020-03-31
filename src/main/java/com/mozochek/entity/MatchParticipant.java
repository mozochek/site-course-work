package com.mozochek.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "match_participants", schema = "webdb")
public class MatchParticipant {

    @EmbeddedId
    private MatchParticipantId id;

    public MatchParticipant() {

    }

    public MatchParticipantId getId() {
        return id;
    }

    public void setId(MatchParticipantId id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchParticipant)) return false;
        MatchParticipant that = (MatchParticipant) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
