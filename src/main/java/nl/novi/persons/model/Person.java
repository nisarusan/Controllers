package nl.novi.persons.model;

import java.time.LocalDate;

public class Person {


    private static Long idCounter = 1L;

    //Instance fields
    private String name;
    private int age;
    private LocalDate dob;
    private char gender;
    private Long id;

    public Person(String name, int age, LocalDate dob, char gender) {
        this.name = name;
        this.age = age;
        this.dob = dob;
        this.gender = gender;
        this.id = idCounter++;
    }

    //getName
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

    public void setId(Long id) {
        this.id = id;
    }
}
