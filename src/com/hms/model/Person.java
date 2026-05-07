package com.hms.model;

// base class for people in the system like patients and doctors
// i used inheritance here so i dont have to repeat name, age etc in every class
public class Person {
    private String name;
    private int age;
    private char gender;
    private String contact;

    // constructor to set up basic info
    public Person(String name, int age, char gender, String contact) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.contact = contact;
    }

    // getters
    public String getName() { return name; }
    public int getAge() { return age; }
    public char getGender() { return gender; }
    public String getContact() { return contact; }

    // setters
    public void setName(String name) { this.name = name; }
    public void setAge(int age) { this.age = age; }
    public void setContact(String contact) { this.contact = contact; }

    // prints the persons details
    public void display() {
        System.out.println("Name: " + name + " | Age: " + age + " | Gender: " + gender + " | Contact: " + contact);
    }

    @Override
    public String toString() {
        return "Name: " + name + " | Age: " + age + " | Gender: " + gender + " | Contact: " + contact;
    }
}
