package com.hms.model;

// stores one payment record
public class Payment {
    private String paymentID;
    private String patientID;
    private double amount;
    private double discount;
    private String paymentMethod;
    private boolean isPaid;

    public Payment(String paymentID, String patientID, double amount, double discount, String paymentMethod) {
        this.paymentID = paymentID;
        this.patientID = patientID;
        this.amount = amount;
        this.discount = discount;
        this.paymentMethod = paymentMethod;
        this.isPaid = false;
    }

    public String getPaymentID() { return paymentID; }
    public String getPatientID() { return patientID; }
    public double getAmount() { return amount; }
    public double getDiscount() { return discount; }
    public String getPaymentMethod() { return paymentMethod; }
    public boolean isPaid() { return isPaid; }

    // works out the final price after taking discount off
    public double getFinalAmount() {
        return amount - (amount * discount / 100.0);
    }

    public void markAsPaid() {
        this.isPaid = true;
    }

    public void display() {
        String paid = "No";
        if (isPaid) {
            paid = "Yes";
        }
        System.out.println("Payment ID: " + paymentID + " | Patient: " + patientID
                + " | Amount: Rs. " + amount + " | Discount: " + discount + "%"
                + " | Final: Rs. " + getFinalAmount()
                + " | Method: " + paymentMethod + " | Paid: " + paid);
    }
}
