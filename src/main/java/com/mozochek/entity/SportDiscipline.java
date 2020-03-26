package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "sport_disciplines", schema = "webdb")
public class SportDiscipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToOne(targetEntity = SportKind.class)
    @JoinColumn(name = "sport_kind_id", nullable = false)
    private SportKind sportKind;

    @OneToMany(targetEntity = Tournament.class, mappedBy = "sportDiscipline")
    private Set<Tournament> tournaments;

    public SportDiscipline() {

    }

    public SportDiscipline(String name, SportKind sportKind) {
        this.name = name;
        this.sportKind = sportKind;
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

    public SportKind getSportKind() {
        return sportKind;
    }

    public void setSportKind(SportKind sportKind) {
        this.sportKind = sportKind;
    }

    public Set<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(Set<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    @Override
    public String toString() {
        return "SportDiscipline{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sportKind=" + sportKind +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SportDiscipline)) return false;
        SportDiscipline that = (SportDiscipline) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(sportKind, that.sportKind);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sportKind);
    }
}
