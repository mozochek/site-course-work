package com.mozochek.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

import static com.mozochek.utils.LengthConstants.*;

@Entity
@Table(name = "humans", schema = "webdb")
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = HUMAN_NAME_LENGTH)
    private String name;

    @Column(nullable = false, length = HUMAN_SURNAME_LENGTH)
    private String surname;

    @Column(length = HUMAN_PATRONYMIC_LENGTH)
    private String patronymic;

    @Column(length = CITY_LENGTH)
    private String city;

    @Column(name = "birth_date")
    private Date birthDate;

    public Human() {

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "Human{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", city='" + city + '\'' +
                ", birthDate=" + birthDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Human)) return false;
        Human human = (Human) o;
        return Objects.equals(id, human.id) &&
                Objects.equals(name, human.name) &&
                Objects.equals(surname, human.surname) &&
                Objects.equals(patronymic, human.patronymic) &&
                Objects.equals(city, human.city) &&
                Objects.equals(birthDate, human.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, patronymic, city, birthDate);
    }
}
