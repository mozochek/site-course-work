package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;

//TODO Класс почти готов, сделать последнюю проверку
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

    private Game() {

    }

    public Game(Integer id, String score, Match match) {
        this.id = id;
        this.score = score;
        this.match = match;
    }

    public Integer getId() {
        return id;
    }

    public String getScore() {
        return score;
    }

    public Match getMatch() {
        return match;
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
        return id.equals(game.id) &&
                score.equals(game.score) &&
                match.equals(game.match);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, score, match);
    }
}
