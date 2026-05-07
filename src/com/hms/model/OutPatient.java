package com.hms.model;

import com.hms.interfaces.Billable;

// outpatient just comes for a checkup and goes home same day
// they only pay the consultation fee, no room charges
// also implements Billable like InPatient
public class OutPatient extends Patient implements Billable {
    private String visitDate;

    public OutPatient(String patientID, String name, int age, char gender, String contact,
                      String address, String visitDate) {
        super(patientID, name, age, gender, contact, address);
        this.visitDate = visitDate;
    }

    public String getVisitDate() { return visitDate; }

    // outpatient only pays consultation
    public double calculateBill() {
        return 1500.0;
    }

    public void displayBill() {
        System.out.println("\n--- Out-Patient Bill ---");
        System.out.println("Patient: " + getName() + " (ID: " + getPatientID() + ")");
        System.out.println("Visit Date: " + visitDate);
        System.out.println("Consultation Fee: Rs. 1500.0");
        System.out.println("Total Bill: Rs. " + calculateBill());
        System.out.println("------------------------");
    }

    @Override
    public void display() {
        System.out.println("[OutPatient] ID: " + getPatientID() + " | " + getName()
                + " | Visit: " + visitDate);
    }
}
