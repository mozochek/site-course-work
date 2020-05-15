package com.mozochek.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import static com.mozochek.utils.LengthConstants.CODE_LENGTH;
import static com.mozochek.utils.LengthConstants.SPORT_KIND_NAME_LENGTH;

@Entity
@Table(name = "sport_kinds", schema = "webdb")
public class SportKind implements IrremovableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = CODE_LENGTH)
    private String code;

    @Column(nullable = false, length = SPORT_KIND_NAME_LENGTH)
    private String name;

    @OneToMany(targetEntity = SportDiscipline.class, mappedBy = "sportKind")
    private Set<SportDiscipline> sportDisciplines;

    public SportKind() {

    }

    public SportKind(String name, String code) {
        this.name = name;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    /*
     * Helper methods
     */
    public void addSportDiscipline(SportDiscipline sportDiscipline) {
        if (sportDisciplines == null) {
            sportDisciplines = new HashSet<>();
        }
        sportDisciplines.add(sportDiscipline);
    }

    /*
     * toString, equals, hashCode
     */
    @Override
    public String toString() {
        return "SportKind{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SportKind)) return false;
        SportKind sportKind = (SportKind) o;
        return Objects.equals(id, sportKind.id) &&
                Objects.equals(code, sportKind.code) &&
                Objects.equals(name, sportKind.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name);
    }
}
