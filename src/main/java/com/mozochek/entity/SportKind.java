package com.mozochek.entity;

import javax.persistence.*;
import java.util.Objects;

import static com.mozochek.utils.LengthConstants.CODE_LENGTH;
import static com.mozochek.utils.LengthConstants.SPORT_KIND_NAME_LENGTH;

@Entity
@Table(name = "sport_kinds", schema = "webdb")
public class SportKind {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = CODE_LENGTH)
    private String code;

    @Column(length = SPORT_KIND_NAME_LENGTH)
    private String name;

    public SportKind() {

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
