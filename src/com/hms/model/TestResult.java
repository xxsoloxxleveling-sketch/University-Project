package com.hms.model;

// stores one lab test result for a patient
public class TestResult {
    private String testID;
    private String patientID;
    private String testName;
    private String result;
    private String date;

    public TestResult(String testID, String patientID, String testName, String result, String date) {
        this.testID = testID;
        this.patientID = patientID;
        this.testName = testName;
        this.result = result;
        this.date = date;
    }

    public String getTestID() { return testID; }
    public String getPatientID() { return patientID; }
    public String getTestName() { return testName; }
    public String getResult() { return result; }
    public String getDate() { return date; }

    public void display() {
        System.out.println("Test ID: " + testID + " | Patient: " + patientID
                + " | Test: " + testName + " | Result: " + result + " | Date: " + date);
    }

    @Override
    public String toString() {
        return testName + " - " + result + " (" + date + ")";
    }
}
