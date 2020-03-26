package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "matches", schema = "webdb")
public class Match {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Integer id;

    @Column(length = 10)
    private String score;

    @ManyToOne(targetEntity = Tournament.class)
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @OneToMany(targetEntity = Game.class, mappedBy = "match")
    private Set<Game> games;

    @OneToMany(targetEntity = MatchParticipant.class, mappedBy = "match")
    private Set<MatchParticipant> matchParticipants;

    public Match() {

    }

    public Match(String score, Tournament tournament) {
        this.score = score;
        this.tournament = tournament;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Set<Game> getGames() {
        return games;
    }

    public void setGames(Set<Game> games) {
        this.games = games;
    }

    public Set<MatchParticipant> getMatchParticipants() {
        return matchParticipants;
    }

    public void setMatchParticipants(Set<MatchParticipant> matchParticipants) {
        this.matchParticipants = matchParticipants;
    }

    @Override
    public String toString() {
        return "Match{" +
                "id=" + id +
                ", score='" + score + '\'' +
                ", tournament=" + tournament +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return Objects.equals(id, match.id) &&
                Objects.equals(score, match.score) &&
                Objects.equals(tournament, match.tournament);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, tournament);
    }
}
