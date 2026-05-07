package com.hms.model;

// blood donor - extends Person and adds blood type info
public class BloodDonor extends Person {
    private String donorID;
    private String bloodType;
    private String lastDonationDate;

    public BloodDonor(String donorID, String name, int age, char gender, String contact,
                      String bloodType, String lastDonationDate) {
        super(name, age, gender, contact);
        this.donorID = donorID;
        this.bloodType = bloodType;
        this.lastDonationDate = lastDonationDate;
    }

    public String getDonorID() { return donorID; }
    public String getBloodType() { return bloodType; }
    public String getLastDonationDate() { return lastDonationDate; }

    @Override
    public void display() {
        System.out.println("Donor ID: " + donorID + " | " + getName()
                + " | Blood Type: " + bloodType + " | Last Donation: " + lastDonationDate);
    }
}
