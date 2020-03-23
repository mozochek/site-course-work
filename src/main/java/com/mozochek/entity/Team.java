package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

//TODO Класс почти готов, сделать заключительную проверку
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

    private Team() {

    }

    public Team(Integer id, String name, Tournament tournament) {
        this.id = id;
        this.name = name;
        this.tournament = tournament;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Tournament getTournament() {
        return tournament;
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
                ", tournamentParticipants=" + tournamentParticipants +
                ", matchParticipants=" + matchParticipants +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return id.equals(team.id) &&
                name.equals(team.name) &&
                tournament.equals(team.tournament) &&
                Objects.equals(tournamentParticipants, team.tournamentParticipants) &&
                Objects.equals(matchParticipants, team.matchParticipants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, tournament, tournamentParticipants, matchParticipants);
    }
}
