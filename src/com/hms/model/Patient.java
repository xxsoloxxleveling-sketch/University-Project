package com.hms.model;

import java.util.ArrayList;

// patient class - inherits from Person so it gets name, age etc for free
public class Patient extends Person {
    private String patientID;
    private String address;
    private ArrayList<String> history; // stores things like "diagnosed with flu" etc

    public Patient(String patientID, String name, int age, char gender, String contact, String address) {
        super(name, age, gender, contact); // passes the basic info up to Person
        this.patientID = patientID;
        this.address = address;
        this.history = new ArrayList<>(); // had a crash here before when i forgot the "new"
    }

    public String getPatientID() { return patientID; }
    public String getAddress() { return address; }
    public ArrayList<String> getHistory() { return history; }

    // adds a new entry to the patients medical history
    public void addHistory(String entry) {
        history.add(entry);
    }

    @Override
    public void display() {
        System.out.println("Patient ID: " + patientID + " | " + toString() + " | Address: " + address);
    }
}
