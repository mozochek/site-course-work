package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "games", schema = "webdb")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tournament_id", nullable = false, insertable = false, updatable = false)
    private Integer id;

    @Column(length = 10)
    private String score;

    @ManyToOne(targetEntity = Match.class)
    @JoinColumn(name = "match_id", nullable = false)
    private Match match;

    public Game() {

    }

    public Game(String score, Match match) {
        this.score = score;
        this.match = match;
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

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id=" + id +
                ", score='" + score + '\'' +
                ", match=" + match +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Game)) return false;
        Game game = (Game) o;
        return Objects.equals(id, game.id) &&
                Objects.equals(score, game.score) &&
                Objects.equals(match, game.match);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, match);
    }
}
