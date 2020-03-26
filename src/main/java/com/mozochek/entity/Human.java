package com.mozochek.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "humans", schema = "webdb")
public class Human {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false, updatable = false)
    private Integer id;

    @Column(nullable = false, length = 35)
    private String name;

    @Column(nullable = false, length = 35)
    private String surname;

    @Column(nullable = false, length = 35)
    private String patronymic;

    @Column(nullable = false, length = 30)
    private String city;

    @Column(name = "birth_date")
    private Date birthDate;

    @OneToMany(targetEntity = TournamentJudge.class, mappedBy = "human")
    private Set<TournamentJudge> judges;

    @OneToMany(targetEntity = TournamentParticipant.class, mappedBy = "human")
    private Set<TournamentParticipant> participants;

    public Human() {

    }

    public Human(String name, String surname, String patronymic, String city, Date birthDate) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.city = city;
        this.birthDate = birthDate;
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

    public Set<TournamentJudge> getJudges() {
        return judges;
    }

    public void setJudges(Set<TournamentJudge> judges) {
        this.judges = judges;
    }

    public Set<TournamentParticipant> getParticipants() {
        return participants;
    }

    public void setParticipants(Set<TournamentParticipant> participants) {
        this.participants = participants;
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
