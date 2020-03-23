package com.mozochek.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

//TODO Класс готов!
@Embeddable
public class TournamentJudgeId implements Serializable {

    @Column(name = "human_id", nullable = false)
    private Integer humanId;

    @Column(name = "tournament_id", nullable = false)
    private Integer tournamentId;

    private TournamentJudgeId() {

    }

    public TournamentJudgeId(Integer humanId, Integer tournamentId) {
        this.humanId = humanId;
        this.tournamentId = tournamentId;
    }

    public Integer getHumanId() {
        return humanId;
    }

    public Integer getTournamentId() {
        return tournamentId;
    }

    @Override
    public String toString() {
        return "TournamentJudgesId{" +
                "humanId=" + humanId +
                ", tournamentId=" + tournamentId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TournamentJudgeId)) return false;
        TournamentJudgeId that = (TournamentJudgeId) o;
        return humanId.equals(that.humanId) &&
                tournamentId.equals(that.tournamentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(humanId, tournamentId);
    }
}
