package nl.novi.persons.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "persons")
public class Person {

//    private static Long idCounter = 1L;

    @Id
    @GeneratedValue
    private Long id;
    //Instance fields
    @Column
    private String name;
    @Column
    private int age;
    @Column
    private LocalDate dob;
    private char gender;



    //constructor objecten bouwer for object instansties
    public Person(String name, int age, LocalDate dob, char gender) {
        this.name = name;
        this.age = age;
        this.dob = dob;
        this.gender = gender;
//        this.id = idCounter++;
    }

    public Person() {
        // default constructor logic, if needed
    }

    //getName om veiliger het variabel te pakkn
    public String getName() {
        return name;
    }

    //getAge
    public int getAge() {
        return age;
    }

    //getDob
    public LocalDate getDob() {
        return dob;
    }

    //getGender
    public char getGender() {
        return gender;
    }

    //getId
    public Long getId() {
        return id;
    }

    //setName
    public void setName(String name) {
        this.name = name;
    }

    //setAge
    public void setAge(int age) {
        this.age = age;
    }

    //setDob
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    //setGender
    public void setGender(char gender) {
        this.gender = gender;
    }

    //setId
    public void setId(Long id) {
        this.id = id;
    }
}
