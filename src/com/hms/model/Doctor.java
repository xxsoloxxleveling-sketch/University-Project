package com.hms.model;

import java.util.ArrayList;

// doctor class - also extends Person like Patient does
public class Doctor extends Person {
    private String doctorID;
    private String specialty;
    private ArrayList<String> availableSlots; // time slots the doctor is free

    public Doctor(String doctorID, String name, int age, char gender, String contact, String specialty) {
        super(name, age, gender, contact);
        this.doctorID = doctorID;
        this.specialty = specialty;
        this.availableSlots = new ArrayList<>();

        // adding some default times - in a real system this would come from a database
        availableSlots.add("09:00 AM");
        availableSlots.add("10:00 AM");
        availableSlots.add("11:00 AM");
        availableSlots.add("02:00 PM");
        availableSlots.add("03:00 PM");
    }

    public String getDoctorID() { return doctorID; }
    public String getSpecialty() { return specialty; }
    public ArrayList<String> getAvailableSlots() { return availableSlots; }

    // removes a slot from the list when someone books it
    // returns true if it worked, false if that slot wasnt available
    public boolean bookSlot(String slot) {
        if (availableSlots.contains(slot)) {
            availableSlots.remove(slot);
            return true;
        }
        return false;
    }

    public boolean hasAvailableSlots() {
        return !availableSlots.isEmpty();
    }

    @Override
    public void display() {
        System.out.println("Doctor ID: " + doctorID + " | Dr. " + getName()
                + " | Specialty: " + specialty + " | Available Slots: " + availableSlots.size());
    }
}
