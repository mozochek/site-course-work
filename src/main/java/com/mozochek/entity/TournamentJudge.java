package com.mozochek.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tournament_judges", schema = "webdb")
public class TournamentJudge implements Serializable {

    @EmbeddedId
    private TournamentJudgeId id;

    public TournamentJudge() {

    }

    public TournamentJudgeId getId() {
        return id;
    }

    public void setId(TournamentJudgeId id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "TournamentJudge{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TournamentJudge)) return false;
        TournamentJudge that = (TournamentJudge) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
