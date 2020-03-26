package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "sport_kinds", schema = "webdb")
public class SportKind {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false, length = 30)
    private String name;

    @OneToMany(targetEntity = SportDiscipline.class, mappedBy = "sportKind")
    private Set<SportDiscipline> sportDisciplines;

    public SportKind() {
    }

    public SportKind(String name) {
        this.name = name;
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

    public Set<SportDiscipline> getSportDisciplines() {
        return sportDisciplines;
    }

    public void setSportDisciplines(Set<SportDiscipline> sportDisciplines) {
        this.sportDisciplines = sportDisciplines;
    }

    @Override
    public String toString() {
        return "SportKind{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SportKind)) return false;
        SportKind sportKind = (SportKind) o;
        return Objects.equals(id, sportKind.id) &&
                Objects.equals(name, sportKind.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
