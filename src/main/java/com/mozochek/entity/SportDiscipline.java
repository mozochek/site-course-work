package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

//TODO Класс почти готов, решить вопрос по поводу ID и затем сделать заключительную проверку
@Entity
@Table(name = "sport_disciplines", schema = "webdb")
public class SportDiscipline {

    //TODO 1. Подумать на счет ID'шника, мб сделать его строкой и записывать в него код из реестра
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

    private SportDiscipline() {

    }

    public SportDiscipline(Integer id, String name, SportKind sportKind) {
        this.id = id;
        this.name = name;
        this.sportKind = sportKind;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public SportKind getSportKind() {
        return sportKind;
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
                ", tournaments=" + tournaments +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SportDiscipline)) return false;
        SportDiscipline that = (SportDiscipline) o;
        return id.equals(that.id) &&
                name.equals(that.name) &&
                sportKind.equals(that.sportKind) &&
                Objects.equals(tournaments, that.tournaments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sportKind, tournaments);
    }
}
