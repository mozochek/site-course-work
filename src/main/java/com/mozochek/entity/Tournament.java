package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tournaments", schema = "webdb")
public class Tournament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 50)
    private String format;

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

    public Tournament() {

    }

    public Tournament(String name, String format, String city) {
        this.name = name;
        this.format = format;
        this.city = city;
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public SportDiscipline getSportDiscipline() {
        return sportDiscipline;
    }

    public void setSportDiscipline(SportDiscipline sportDiscipline) {
        this.sportDiscipline = sportDiscipline;
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
                ", format='" + format + '\'' +
                ", city='" + city + '\'' +
                ", sportDiscipline=" + sportDiscipline +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tournament)) return false;
        Tournament that = (Tournament) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(format, that.format) &&
                Objects.equals(city, that.city) &&
                Objects.equals(sportDiscipline, that.sportDiscipline);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, format, city, sportDiscipline);
    }
}
