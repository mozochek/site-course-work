package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

//TODO Класс почти готов, решить вопрос по поводу Enum и затем сделать заключительную проверку
@Entity
@Table(name = "tournaments", schema = "webdb")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    //TODO 1. Подумать по поводу Enum
    @Column(nullable = false, length = 50)
    private String type;

    @Column(nullable = false, length = 30)
    private String city;

    @ManyToOne(targetEntity = SportDiscipline.class)
    @JoinColumn(name = "sport_discipline_id", nullable = false)
    private SportDiscipline sportDiscipline;

    @OneToMany(targetEntity = TournamentJudge.class, mappedBy = "tournament")
    private Set<TournamentJudge> judges;

    @OneToMany(targetEntity = Team.class, mappedBy = "tournament")
    private Set<Team> teams;

    @OneToMany(targetEntity = Match.class, mappedBy = "tournament")
    private Set<Match> matches;

    private Tournament() {

    }

    public Tournament(Integer id, String name, String type, String city, SportDiscipline sportDiscipline) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.city = city;
        this.sportDiscipline = sportDiscipline;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getCity() {
        return city;
    }

    public SportDiscipline getSportDiscipline() {
        return sportDiscipline;
    }

    public Set<TournamentJudge> getJudges() {
        return judges;
    }

    public void setJudges(Set<TournamentJudge> judges) {
        this.judges = judges;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public Set<Match> getMatches() {
        return matches;
    }

    public void setMatches(Set<Match> matches) {
        this.matches = matches;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", city='" + city + '\'' +
                ", sportDiscipline=" + sportDiscipline +
                ", judges=" + judges +
                ", teams=" + teams +
                ", matches=" + matches +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tournament)) return false;
        Tournament that = (Tournament) o;
        return id.equals(that.id) &&
                name.equals(that.name) &&
                type.equals(that.type) &&
                city.equals(that.city) &&
                sportDiscipline.equals(that.sportDiscipline) &&
                Objects.equals(judges, that.judges) &&
                Objects.equals(teams, that.teams) &&
                Objects.equals(matches, that.matches);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, city, sportDiscipline, judges, teams, matches);
    }
}
