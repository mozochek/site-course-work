package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "teams", schema = "webdb")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false, length = 30)
    private String name;

    @ManyToOne(targetEntity = Tournament.class)
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    @OneToMany(targetEntity = TournamentParticipant.class, mappedBy = "team")
    private Set<TournamentParticipant> tournamentParticipants;

    @OneToMany(targetEntity = MatchParticipant.class, mappedBy = "team")
    private Set<MatchParticipant> matchParticipants;

    public Team() {

    }

    public Team(String name, Tournament tournament) {
        this.name = name;
        this.tournament = tournament;
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

    public Set<TournamentParticipant> getTournamentParticipants() {
        return tournamentParticipants;
    }

    public void setTournamentParticipants(Set<TournamentParticipant> tournamentParticipants) {
        this.tournamentParticipants = tournamentParticipants;
    }

    public Set<MatchParticipant> getMatchParticipants() {
        return matchParticipants;
    }

    public void setMatchParticipants(Set<MatchParticipant> matchParticipants) {
        this.matchParticipants = matchParticipants;
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
