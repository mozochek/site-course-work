package com.mozochek.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class TournamentJudgeId implements Serializable {

    @Column(name = "human_id", nullable = false)
    private Integer humanId;

    @Column(name = "tournament_id", nullable = false)
    private Integer tournamentId;

    public TournamentJudgeId() {

    }

    public TournamentJudgeId(Integer humanId, Integer tournamentId) {
        this.humanId = humanId;
        this.tournamentId = tournamentId;
    }

    public Integer getHumanId() {
        return humanId;
    }

    public void setHumanId(Integer humanId) {
        this.humanId = humanId;
    }

    public Integer getTournamentId() {
        return tournamentId;
    }

    public void setTournamentId(Integer tournamentId) {
        this.tournamentId = tournamentId;
    }

    @Override
    public String toString() {
        return "TournamentJudgeId{" +
                "humanId=" + humanId +
                ", tournamentId=" + tournamentId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TournamentJudgeId)) return false;
        TournamentJudgeId that = (TournamentJudgeId) o;
        return Objects.equals(humanId, that.humanId) &&
                Objects.equals(tournamentId, that.tournamentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(humanId, tournamentId);
    }
}
