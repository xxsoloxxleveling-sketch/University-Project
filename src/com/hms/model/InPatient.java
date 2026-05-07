package com.hms.model;

import com.hms.interfaces.Billable;

// inpatient is someone who stays in the hospital for treatment
// they get charged per day plus a consultation fee
// implements Billable because they need to have a bill
public class InPatient extends Patient implements Billable {
    private int roomNo;
    private String admitDate;
    private String dischargeDate;
    private int daysAdmitted;

    public InPatient(String patientID, String name, int age, char gender, String contact,
                     String address, int roomNo, String admitDate, int daysAdmitted) {
        super(patientID, name, age, gender, contact, address);
        this.roomNo = roomNo;
        this.admitDate = admitDate;
        this.dischargeDate = "N/A";
        this.daysAdmitted = daysAdmitted;
    }

    public int getRoomNo() { return roomNo; }
    public String getAdmitDate() { return admitDate; }
    public int getDaysAdmitted() { return daysAdmitted; }
    public void setDischargeDate(String date) { this.dischargeDate = date; }

    // works out how much the patient has to pay
    public double calculateBill() {
        // room is 5000 per day and consultation is 2000
        double roomTotal = 5000.0 * daysAdmitted;
        double total = 2000.0 + roomTotal;
        return total;
    }

    // prints the bill details
    public void displayBill() {
        System.out.println("\n--- In-Patient Bill ---");
        System.out.println("Patient: " + getName() + " (ID: " + getPatientID() + ")");
        System.out.println("Room No: " + roomNo + " | Days: " + daysAdmitted);
        System.out.println("Consultation Fee: Rs. 2000.0");
        System.out.println("Room Charges: Rs. " + (5000.0 * daysAdmitted));
        System.out.println("Total Bill: Rs. " + calculateBill());
        System.out.println("-----------------------");
    }

    @Override
    public void display() {
        System.out.println("[InPatient] ID: " + getPatientID() + " | " + getName()
                + " | Room: " + roomNo + " | Admitted: " + admitDate + " | Days: " + daysAdmitted);
    }
}
