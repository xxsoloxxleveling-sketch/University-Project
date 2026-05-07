package com.hms.interfaces;

// this interface makes sure that any class that can be billed
// has to have these two methods - i learned this in class
// both InPatient and OutPatient use this
public interface Billable {
    double calculateBill();
    void displayBill();
}
