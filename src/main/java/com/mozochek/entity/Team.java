package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "teams", schema = "webdb")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 30)
    private String name;

    @ManyToOne(targetEntity = Tournament.class)
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    public Team() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tournament=" + tournament +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return Objects.equals(id, team.id) &&
                Objects.equals(name, team.name) &&
                Objects.equals(tournament, team.tournament);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tournament);
    }
}
