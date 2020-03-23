package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

//TODO Класс почти готов, решить вопрос по поводу ID и затем сделать заключительную проверку
@Entity
@Table(name = "sport_kinds", schema = "webdb")
public class SportKind {

    //TODO 1. Подумать на счет ID'шника, мб сделать его строкой и записывать в него код из реестра
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false, length = 30)
    private String name;

    @OneToMany(targetEntity = SportDiscipline.class, mappedBy = "sportKind")
    private Set<SportDiscipline> sportDisciplines;

    private SportKind() {

    }

    public SportKind(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
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
                ", sportDisciplines=" + sportDisciplines +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SportKind)) return false;
        SportKind sportKind = (SportKind) o;
        return id.equals(sportKind.id) &&
                name.equals(sportKind.name) &&
                Objects.equals(sportDisciplines, sportKind.sportDisciplines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sportDisciplines);
    }
}
