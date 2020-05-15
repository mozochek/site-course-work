package com.mozochek.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.mozochek.utils.LengthConstants.TEAM_NAME_LENGTH;

@Entity
@Table(name = "teams", schema = "webdb")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = TEAM_NAME_LENGTH)
    private String name;

    // Один-к-одному с Tournament | У одной команды может быть выигран только один турнир (команды формируются на уровне турнира)
    @OneToOne(targetEntity = Tournament.class, mappedBy = "winnerTeam")
    private Tournament tournamentWon;

    // Многие-к-одному с Tournament | У множества команд может быть один турнир
    @ManyToOne(targetEntity = Tournament.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "tournament_id", nullable = false)
    private Tournament tournament;

    // Многие-ко-многим с Human | У команды может множество участников команды
    @ManyToMany(targetEntity = Human.class)
    @JoinTable(joinColumns = @JoinColumn(name = "team_id", nullable = false), inverseJoinColumns = @JoinColumn(name = "human_id", nullable = false))
    private Set<Human> people;

    // Многие-ко-многим с Match | У команды может быть множества матчей в пределах турнира
    @ManyToMany(mappedBy = "teams")
    private Set<Match> matches;

    public Team() {

    }

    public Team(String name, Tournament tournament) {
        this.name = name;
        this.tournament = tournament;
    }

    /*
     * Getters and setters
     */
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

    public Tournament getTournamentWon() {
        return tournamentWon;
    }

    public void setTournamentWon(Tournament tournamentWon) {
        this.tournamentWon = tournamentWon;
    }

    public Tournament getTournament() {
        return tournament;
    }

    public void setTournament(Tournament tournament) {
        this.tournament = tournament;
    }

    public Set<Human> getPeople() {
        return people;
    }

    public void setPeople(Set<Human> people) {
        this.people = people;
    }

    public Set<Match> getMatches() {
        return matches;
    }

    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }

    /*
     * Helper methods
     */
    public void addMatch(Match match) {
        if (matches == null) {
            matches = new HashSet<>();
        }
        matches.add(match);
    }

    public void removeMatch(Match match) {
        if (matches != null) {
            matches.remove(match);
        }
    }

    public void clearMatches() {
        matches.clear();
    }

    public void addHuman(Human human) {
        if (people == null) {
            people = new HashSet<>();
        }
        people.add(human);
    }

    public void removeHuman(Human human) {
        if (people != null) {
            people.remove(human);
        }
    }

    /*
     * toString, equals, hashCode
     */
    @Override
    public String toString() {
        return "Team{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", tournamentWon=" + tournamentWon +
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
