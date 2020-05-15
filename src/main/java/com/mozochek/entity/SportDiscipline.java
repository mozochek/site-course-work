package com.mozochek.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Objects;

import static com.mozochek.utils.LengthConstants.CODE_LENGTH;
import static com.mozochek.utils.LengthConstants.SPORT_DISCIPLINE_NAME_LENGTH;

@Entity
@Table(name = "sport_disciplines", schema = "webdb")
public class SportDiscipline implements IrremovableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = SPORT_DISCIPLINE_NAME_LENGTH)
    private String name;

    @Column(nullable = false, length = CODE_LENGTH)
    private String code;

    // Многие-к-одному с SportKind | Множество спортивных дисциплин относятся к одному виду спорта
    @ManyToOne(targetEntity = SportKind.class)
    @JoinColumn(name = "sport_kind_id", nullable = false)
    private SportKind sportKind;

    public SportDiscipline() {

    }

    public SportDiscipline(String name, String code, SportKind sportKind) {
        this.name = name;
        this.code = code;
        this.sportKind = sportKind;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public SportKind getSportKind() {
        return sportKind;
    }

    public void setSportKind(SportKind sportKind) {
        this.sportKind = sportKind;
    }

    /*
     * Helper methods
     */
    public String getDescription() {
        return sportKind.getName() + " - " + name;
    }

    /*
     * toString, equals, hashCode
     */
    @Override
    public String toString() {
        return "SportDiscipline{" +
                "id=" + id +
                ", code='" + code + '\'' +
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
                Objects.equals(code, that.code) &&
                Objects.equals(name, that.name) &&
                Objects.equals(sportKind, that.sportKind);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, code, name, sportKind);
    }
}
