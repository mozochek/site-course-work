package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

//TODO Класс почти готов, решить вопрос по поводу хранения счета и затем сделать заключительную проверку
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

    private Match() {

    }

    public Match(Integer id, String score, Tournament tournament) {
        this.id = id;
        this.score = score;
        this.tournament = tournament;
    }

    public Integer getId() {
        return id;
    }

    public String getScore() {
        return score;
    }

    public Tournament getTournament() {
        return tournament;
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
                ", games=" + games +
                ", matchParticipants=" + matchParticipants +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return id.equals(match.id) &&
                score.equals(match.score) &&
                tournament.equals(match.tournament) &&
                Objects.equals(games, match.games) &&
                Objects.equals(matchParticipants, match.matchParticipants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, tournament, games, matchParticipants);
    }
}
