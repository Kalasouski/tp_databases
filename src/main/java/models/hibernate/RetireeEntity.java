package models.hibernate;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "retiree", schema = "public", catalog = "postgres")
public class RetireeEntity {
    private Long id;
    private String surname;
    private String name;
    private String patronymic;
    private String gender;
    private String nationality;
    private Integer birthYear;
    private Long phone;
    private String address;
    private Integer retirementExperience;
    private Double retirement;
    private Long jobId;


    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "surname", nullable = false, length = -1)
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Basic
    @Column(name = "name", nullable = false, length = -1)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "patronymic", nullable = true, length = -1)
    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    @Basic
    @Column(name = "gender", nullable = true, length = -1)
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Basic
    @Column(name = "nationality", nullable = true, length = -1)
    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Basic
    @Column(name = "birth_year", nullable = true)
    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    @Basic
    @Column(name = "phone", nullable = true)
    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "address", nullable = true, length = -1)
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "retirement_experience", nullable = true)
    public Integer getRetirementExperience() {
        return retirementExperience;
    }

    public void setRetirementExperience(Integer retirementExperience) {
        this.retirementExperience = retirementExperience;
    }

    @Basic
    @Column(name = "retirement", nullable = true, precision = 0)
    public Double getRetirement() {
        return retirement;
    }

    public void setRetirement(Double retirement) {
        this.retirement = retirement;
    }

    @Basic
    @Column(name = "job_id")
    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }



    public String toString() {
        return id + "\t" + surname + '\t' + name + '\t' + patronymic + '\t' + gender + '\t' + nationality +
                '\t' + birthYear + '\t' + phone + '\t'
                + address + '\t' + retirementExperience +
                '\t' + retirement + '\t' + jobId;
    }


}