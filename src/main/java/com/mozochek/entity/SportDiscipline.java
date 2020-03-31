package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;

import static com.mozochek.utils.LengthConstants.CODE_LENGTH;
import static com.mozochek.utils.LengthConstants.SPORT_DISCIPLINE_NAME_LENGTH;

@Entity
@Table(name = "sport_disciplines", schema = "webdb")
public class SportDiscipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = CODE_LENGTH)
    private String code;

    @Column(length = SPORT_DISCIPLINE_NAME_LENGTH)
    private String name;

    @ManyToOne(targetEntity = SportKind.class)
    @JoinColumn(name = "sport_kind_id", nullable = false)
    private SportKind sportKind;

    public SportDiscipline() {

    }

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

    public SportKind getSportKind() {
        return sportKind;
    }

    public void setSportKind(SportKind sportKind) {
        this.sportKind = sportKind;
    }

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

    public String getDescription() {
        return sportKind.getName() + " - " + name;
    }
}
