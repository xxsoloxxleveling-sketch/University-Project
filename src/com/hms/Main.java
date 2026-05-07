package com.hms;

import com.hms.model.Doctor;
import com.hms.model.Patient;
import com.hms.model.BloodDonor;
import com.hms.model.TestResult;
import com.hms.model.Payment;
import com.hms.model.InPatient;
import com.hms.model.OutPatient;
import java.util.ArrayList;
import java.util.Scanner;

// main class - this is where the whole program runs from
public class Main {

    // these lists store all the data while the program is running
    static ArrayList<Patient> patientList = new ArrayList<>();
    static ArrayList<Doctor> doctorList = new ArrayList<>();
    static ArrayList<BloodDonor> donorList = new ArrayList<>();
    static ArrayList<TestResult> testResults = new ArrayList<>();
    static ArrayList<Payment> paymentList = new ArrayList<>();

    // blood bank inventory
    static String[] bloodTypes = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
    static int[] inventory = {10, 5, 8, 3, 4, 2, 15, 7};

    // counters for generating unique IDs
    static int patientCounter = 1000;
    static int donorCounter = 100;
    static int testCounter = 500;
    static int paymentCounter = 200;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = -1;

        // load some doctors so we can test right away
        loadSampleDoctors();

        System.out.println("\n========================================");
        System.out.println("  Welcome to the Hospital Management   ");
        System.out.println("            System (HMS)               ");
        System.out.println("========================================");

        // main loop - keeps going until user picks 0
        while (choice != 0) {
            displayMenu();

            try {
                choice = scanner.nextInt();
                scanner.nextLine(); // clear the enter key
            } catch (Exception e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    registerPatient(scanner);
                    break;
                case 2:
                    reserveDoctor(scanner);
                    break;
                case 3:
                    // sub menu for patient history
                    System.out.println("\n--- Patient Module ---");
                    System.out.println("1. View Patient History");
                    System.out.println("2. Add History Entry");
                    System.out.println("3. View All Patients");
                    System.out.print("Select: ");
                    try {
                        int subChoice = scanner.nextInt();
                        scanner.nextLine();
                        switch (subChoice) {
                            case 1:
                                viewHistory(scanner);
                                break;
                            case 2:
                                addHistoryEntry(scanner);
                                break;
                            case 3:
                                viewAllPatients();
                                break;
                            default:
                                System.out.println("Invalid choice.");
                        }
                    } catch (Exception e) {
                        System.out.println("Invalid input.");
                        scanner.nextLine();
                    }
                    break;
                case 4:
                    manageTests(scanner);
                    break;
                case 5:
                    manageDonation(scanner);
                    break;
                case 6:
                    processPayment(scanner);
                    break;
                case 7:
                    System.out.println("Launching Enhanced HMS GUI...");
                    java.awt.EventQueue.invokeLater(() -> {
                        new com.hms.gui.MainDashboardGUI(patientList, doctorList).setVisible(true);
                    });
                    break;
                case 0:
                    System.out.println("\n========================================");
                    System.out.println("  Thank you for using HMS. Goodbye!    ");
                    System.out.println("========================================");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // shows the main menu
    static void displayMenu() {
        System.out.println("\n========================================");
        System.out.println("    Hospital Management System (HMS)    ");
        System.out.println("========================================");
        System.out.println("  1. Patient Registration");
        System.out.println("  2. Doctor Reservation");
        System.out.println("  3. Patient History");
        System.out.println("  4. Test Results");
        System.out.println("  5. Blood Donation Department");
        System.out.println("  6. Payment Department");
        System.out.println("  7. Launch GUI Dashboard (Modern Design)");
        System.out.println("  0. Exit");
        System.out.println("========================================");
        System.out.print("  Select an option: ");
    }

    // prints a line to separate sections
    static void printSeparator() {
        System.out.println("----------------------------------------");
    }

    // adds some doctors so there is data to work with
    private static void loadSampleDoctors() {
        doctorList.add(new Doctor("D1", "Ahmed Khan", 45, 'M', "03001234567", "Cardiology"));
        doctorList.add(new Doctor("D2", "Sara Ali", 38, 'F', "03009876543", "Neurology"));
        doctorList.add(new Doctor("D3", "Fatima Noor", 50, 'F', "03005551234", "Cardiology"));
        doctorList.add(new Doctor("D4", "Bilal Hussain", 42, 'M', "03007778899", "Orthopedics"));
        doctorList.add(new Doctor("D5", "Ayesha Siddiqui", 35, 'F', "03003334455", "Dermatology"));
    }

    // ========================
    // PATIENT REGISTRATION
    // ========================

    static void registerPatient(Scanner scanner) {
        System.out.println("\n--- Patient Registration ---");

        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            System.out.println("Error: Name cannot be empty.");
            return;
        }

        int age;
        try {
            System.out.print("Enter Age: ");
            age = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Please enter a valid number for age.");
            scanner.nextLine();
            return;
        }
        if (age < 0 || age > 150) {
            System.out.println("Error: Age must be between 0 and 150.");
            return;
        }

        System.out.print("Enter Gender (M/F/O): ");
        String genderStr = scanner.nextLine();
        if (!genderStr.equalsIgnoreCase("M") && !genderStr.equalsIgnoreCase("F") && !genderStr.equalsIgnoreCase("O")) {
            System.out.println("Error: Gender must be M, F, or O.");
            return;
        }
        char gender = genderStr.toUpperCase().charAt(0);

        System.out.print("Enter Contact Number: ");
        String contact = scanner.nextLine();
        if (contact.isEmpty() || contact.length() < 10) {
            System.out.println("Error: Contact number must be at least 10 digits.");
            return;
        }

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        // make the patient object and add it to the list
        String patientId = "P" + patientCounter;
        patientCounter++;
        Patient newPatient = new Patient(patientId, name, age, gender, contact, address);
        patientList.add(newPatient);

        System.out.println("\n>> Patient Registered Successfully!");
        System.out.println(">> Patient ID: " + newPatient.getPatientID());
        printSeparator();
    }

    // find a patient by their ID
    static Patient findPatient(String patientId) {
        for (int i = 0; i < patientList.size(); i++) {
            if (patientList.get(i).getPatientID().equalsIgnoreCase(patientId)) {
                return patientList.get(i);
            }
        }
        return null;
    }

    // shows all registered patients
    static void viewAllPatients() {
        System.out.println("\n--- All Registered Patients ---");
        if (patientList.isEmpty()) {
            System.out.println("No patients registered yet.");
            return;
        }
        for (int i = 0; i < patientList.size(); i++) {
            patientList.get(i).display();
        }
        printSeparator();
    }

    // look up a patient and show their history
    static void viewHistory(Scanner scanner) {
        System.out.println("\n--- Patient History ---");
        if (patientList.isEmpty()) {
            System.out.println("No patients registered yet.");
            return;
        }

        System.out.print("Enter Patient ID (e.g., P1000): ");
        String patientId = scanner.nextLine().toUpperCase();

        Patient found = findPatient(patientId);

        if (found == null) {
            System.out.println("Error: Patient with ID " + patientId + " not found.");
            return;
        }

        System.out.println("\nPatient Details:");
        found.display();
        System.out.println("\nMedical History:");
        if (found.getHistory().isEmpty()) {
            System.out.println("  No history records yet.");
        } else {
            for (int i = 0; i < found.getHistory().size(); i++) {
                System.out.println("  " + (i + 1) + ". " + found.getHistory().get(i));
            }
        }
        printSeparator();
    }

    // add a note to a patients history
    static void addHistoryEntry(Scanner scanner) {
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine().toUpperCase();

        Patient found = findPatient(patientId);

        if (found == null) {
            System.out.println("Error: Patient not found.");
            return;
        }

        System.out.print("Enter diagnosis/history note: ");
        String entry = scanner.nextLine();
        found.addHistory(entry);
        System.out.println(">> History entry added for " + found.getName());
    }

    // ========================
    // DOCTOR RESERVATION
    // ========================

    static void reserveDoctor(Scanner scanner) {
        System.out.println("\n--- Doctor Reservation ---");

        if (doctorList.isEmpty()) {
            System.out.println("No doctors available in the system.");
            return;
        }

        // show what specialties are available
        System.out.println("Available Specialties:");
        ArrayList<String> specialties = new ArrayList<>();
        for (int i = 0; i < doctorList.size(); i++) {
            String spec = doctorList.get(i).getSpecialty();
            if (!specialties.contains(spec)) {
                specialties.add(spec);
                System.out.println("  - " + spec);
            }
        }

        System.out.print("\nEnter Specialty Required: ");
        String targetSpecialty = scanner.nextLine();

        // find doctors with that specialty
        boolean found = false;
        System.out.println("\nMatching Doctors:");
        printSeparator();
        for (int i = 0; i < doctorList.size(); i++) {
            Doctor doc = doctorList.get(i);
            if (doc.getSpecialty().equalsIgnoreCase(targetSpecialty) && doc.hasAvailableSlots()) {
                doc.display();
                System.out.println("  Available Slots: " + doc.getAvailableSlots());
                found = true;
            }
        }

        if (!found) {
            System.out.println("No available doctors found for specialty: " + targetSpecialty);
            return;
        }

        // let user pick a doctor and time
        printSeparator();
        System.out.print("Enter Doctor ID to book (e.g., D1): ");
        String docId = scanner.nextLine().toUpperCase();

        Doctor selectedDoc = null;
        for (int i = 0; i < doctorList.size(); i++) {
            if (doctorList.get(i).getDoctorID().equals(docId)) {
                selectedDoc = doctorList.get(i);
                break;
            }
        }

        if (selectedDoc == null) {
            System.out.println("Error: Doctor not found.");
            return;
        }

        if (!selectedDoc.hasAvailableSlots()) {
            System.out.println("Error: No available slots for this doctor.");
            return;
        }

        System.out.println("Available slots: " + selectedDoc.getAvailableSlots());
        System.out.print("Enter time slot to book (e.g., 09:00 AM): ");
        String slot = scanner.nextLine();

        if (selectedDoc.bookSlot(slot)) {
            System.out.println("\n>> Appointment booked successfully!");
            System.out.println(">> Doctor: Dr. " + selectedDoc.getName() + " | Time: " + slot);
        } else {
            System.out.println("Error: That time slot is not available.");
        }
        printSeparator();
    }

    // ========================
    // TEST RESULTS
    // ========================

    static void manageTests(Scanner scanner) {
        System.out.println("\n--- Test Results & History ---");
        System.out.println("1. Add New Test Result");
        System.out.println("2. View Test Results for Patient");
        System.out.println("3. View All Test Results");
        System.out.println("0. Back to Main Menu");
        System.out.print("Select: ");

        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input.");
            scanner.nextLine();
            return;
        }

        switch (choice) {
            case 1:
                addTestResult(scanner);
                break;
            case 2:
                viewPatientTests(scanner);
                break;
            case 3:
                viewAllTests();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // adds a test result for a patient
    static void addTestResult(Scanner scanner) {
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine().toUpperCase();

        Patient patient = findPatient(patientId);
        if (patient == null) {
            System.out.println("Error: Patient not found.");
            return;
        }

        System.out.print("Enter Test Name (e.g., Blood Test, X-Ray, ECG): ");
        String testName = scanner.nextLine();

        System.out.print("Enter Result: ");
        String result = scanner.nextLine();

        System.out.print("Enter Date (DD/MM/YYYY): ");
        String date = scanner.nextLine();

        String testId = "T" + testCounter;
        testCounter++;
        TestResult test = new TestResult(testId, patientId, testName, result, date);
        testResults.add(test);

        // also add to the patients history
        patient.addHistory("Test: " + testName + " - Result: " + result + " (" + date + ")");

        System.out.println("\n>> Test Result Added! Test ID: " + test.getTestID());
    }

    // show test results for one patient
    static void viewPatientTests(Scanner scanner) {
        System.out.print("Enter Patient ID: ");
        String patientId = scanner.nextLine().toUpperCase();

        System.out.println("\n--- Test Results for " + patientId + " ---");
        boolean found = false;
        for (int i = 0; i < testResults.size(); i++) {
            if (testResults.get(i).getPatientID().equals(patientId)) {
                testResults.get(i).display();
                found = true;
            }
        }

        if (!found) {
            System.out.println("No test results found for this patient.");
        }
        printSeparator();
    }

    // show all tests
    static void viewAllTests() {
        System.out.println("\n--- All Test Results ---");
        if (testResults.isEmpty()) {
            System.out.println("No test results recorded yet.");
            return;
        }
        for (int i = 0; i < testResults.size(); i++) {
            testResults.get(i).display();
        }
        printSeparator();
    }

    // ========================
    // BLOOD DONATION
    // ========================

    static void manageDonation(Scanner scanner) {
        System.out.println("\n--- Blood Donation Department ---");
        System.out.println("1. Register New Donor");
        System.out.println("2. Request Blood");
        System.out.println("3. View Blood Inventory");
        System.out.println("4. View All Donors");
        System.out.println("0. Back to Main Menu");
        System.out.print("Select: ");

        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input.");
            scanner.nextLine();
            return;
        }

        switch (choice) {
            case 1:
                registerDonor(scanner);
                break;
            case 2:
                requestBlood(scanner);
                break;
            case 3:
                viewInventory();
                break;
            case 4:
                viewDonors();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // register a blood donor
    static void registerDonor(Scanner scanner) {
        System.out.println("\n--- Register Blood Donor ---");

        System.out.print("Enter Donor Name: ");
        String name = scanner.nextLine();
        if (name.isEmpty()) {
            System.out.println("Error: Name cannot be empty.");
            return;
        }

        int age;
        try {
            System.out.print("Enter Age: ");
            age = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid age.");
            scanner.nextLine();
            return;
        }

        System.out.print("Enter Gender (M/F/O): ");
        String genderStr = scanner.nextLine();
        if (!genderStr.equalsIgnoreCase("M") && !genderStr.equalsIgnoreCase("F") && !genderStr.equalsIgnoreCase("O")) {
            System.out.println("Error: Gender must be M, F, or O.");
            return;
        }
        char gender = genderStr.toUpperCase().charAt(0);

        System.out.print("Enter Contact: ");
        String contact = scanner.nextLine();

        System.out.print("Enter Blood Type (e.g., O+, A-, B+): ");
        String bloodType = scanner.nextLine().toUpperCase();

        // check if the blood type is valid
        int bloodIndex = -1;
        for (int i = 0; i < bloodTypes.length; i++) {
            if (bloodTypes[i].equals(bloodType)) {
                bloodIndex = i;
                break;
            }
        }

        if (bloodIndex == -1) {
            System.out.println("Error: Invalid blood type.");
            return;
        }

        System.out.print("Enter Last Donation Date (DD/MM/YYYY): ");
        String lastDate = scanner.nextLine();

        String donorId = "BD" + donorCounter;
        donorCounter++;
        BloodDonor donor = new BloodDonor(donorId, name, age, gender, contact, bloodType, lastDate);
        donorList.add(donor);
        inventory[bloodIndex]++;

        System.out.println("\n>> Donor Registered! ID: " + donor.getDonorID());
        System.out.println(">> 1 unit of " + donor.getBloodType() + " added to inventory.");
    }

    // request blood from the bank
    static void requestBlood(Scanner scanner) {
        System.out.println("\n--- Request Blood ---");
        viewInventory();

        System.out.print("Enter Requested Blood Type (e.g., O+): ");
        String reqType = scanner.nextLine().toUpperCase();

        int reqUnits;
        try {
            System.out.print("Enter Units Required: ");
            reqUnits = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input.");
            scanner.nextLine();
            return;
        }

        // find the blood type in the array
        int bloodIndex = -1;
        for (int i = 0; i < bloodTypes.length; i++) {
            if (bloodTypes[i].equals(reqType)) {
                bloodIndex = i;
                break;
            }
        }

        if (bloodIndex == -1) {
            System.out.println("Error: Blood type not found.");
            return;
        }

        if (inventory[bloodIndex] < reqUnits) {
            System.out.println("Error: Not enough stock. Available: " + inventory[bloodIndex] + " units.");
            return;
        }

        inventory[bloodIndex] = inventory[bloodIndex] - reqUnits;
        System.out.println("\n>> Blood Request Fulfilled!");
        System.out.println(">> " + reqUnits + " unit(s) of " + reqType + " dispensed.");
        System.out.println(">> Remaining stock: " + inventory[bloodIndex] + " units.");
    }

    // show what blood we have
    static void viewInventory() {
        System.out.println("\n--- Blood Bank Inventory ---");
        System.out.printf("%-10s %-10s%n", "Type", "Units");
        printSeparator();
        for (int i = 0; i < bloodTypes.length; i++) {
            System.out.printf("%-10s %-10d%n", bloodTypes[i], inventory[i]);
        }
        printSeparator();
    }

    // show registered donors
    static void viewDonors() {
        System.out.println("\n--- Registered Donors ---");
        if (donorList.isEmpty()) {
            System.out.println("No donors registered yet.");
            return;
        }
        for (int i = 0; i < donorList.size(); i++) {
            donorList.get(i).display();
        }
        printSeparator();
    }

    // ========================
    // PAYMENT
    // ========================

    static void processPayment(Scanner scanner) {
        System.out.println("\n--- Payment Department ---");
        System.out.println("1. Generate Bill for In-Patient");
        System.out.println("2. Generate Bill for Out-Patient");
        System.out.println("3. View All Payments");
        System.out.println("0. Back to Main Menu");
        System.out.print("Select: ");

        int choice;
        try {
            choice = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid input.");
            scanner.nextLine();
            return;
        }

        switch (choice) {
            case 1:
                generateInPatientBill(scanner);
                break;
            case 2:
                generateOutPatientBill(scanner);
                break;
            case 3:
                viewAllPayments();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice.");
        }
    }

    // generate bill for someone who stayed in hospital
    static void generateInPatientBill(Scanner scanner) {
        System.out.println("\n--- In-Patient Billing ---");

        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();

        int age;
        try {
            System.out.print("Enter Age: ");
            age = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid age.");
            scanner.nextLine();
            return;
        }

        System.out.print("Enter Gender (M/F/O): ");
        String genderStr = scanner.nextLine();
        if (!genderStr.equalsIgnoreCase("M") && !genderStr.equalsIgnoreCase("F") && !genderStr.equalsIgnoreCase("O")) {
            System.out.println("Error: Invalid gender.");
            return;
        }
        char gender = genderStr.toUpperCase().charAt(0);

        System.out.print("Enter Contact: ");
        String contact = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        int roomNo;
        try {
            System.out.print("Enter Room Number: ");
            roomNo = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid room number.");
            scanner.nextLine();
            return;
        }

        System.out.print("Enter Admission Date (DD/MM/YYYY): ");
        String admitDate = scanner.nextLine();

        int days;
        try {
            System.out.print("Enter Days Admitted: ");
            days = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid number of days.");
            scanner.nextLine();
            return;
        }

        // optional discount
        double discount = 0;
        try {
            System.out.print("Enter Discount % (0 for none): ");
            discount = scanner.nextDouble();
            scanner.nextLine();
        } catch (Exception e) {
            scanner.nextLine();
            discount = 0;
        }

        System.out.print("Enter Payment Method (Cash/Card/Online): ");
        String method = scanner.nextLine();

        // create the inpatient and work out the bill
        String patientId = "IP" + paymentCounter;
        InPatient inPatient = new InPatient(patientId, name, age, gender, contact,
                address, roomNo, admitDate, days);
        double totalBill = inPatient.calculateBill();

        // show the bill
        inPatient.displayBill();

        String paymentId = "PAY" + paymentCounter;
        paymentCounter++;
        Payment payment = new Payment(paymentId, patientId, totalBill, discount, method);
        payment.markAsPaid();
        paymentList.add(payment);

        if (discount > 0) {
            System.out.println("Discount: " + discount + "%");
            System.out.println("Final Amount: Rs. " + payment.getFinalAmount());
        }

        System.out.println("\n>> Payment Processed! Payment ID: " + payment.getPaymentID());
    }

    // generate bill for someone who just visited
    static void generateOutPatientBill(Scanner scanner) {
        System.out.println("\n--- Out-Patient Billing ---");

        System.out.print("Enter Patient Name: ");
        String name = scanner.nextLine();

        int age;
        try {
            System.out.print("Enter Age: ");
            age = scanner.nextInt();
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("Invalid age.");
            scanner.nextLine();
            return;
        }

        System.out.print("Enter Gender (M/F/O): ");
        String genderStr = scanner.nextLine();
        if (!genderStr.equalsIgnoreCase("M") && !genderStr.equalsIgnoreCase("F") && !genderStr.equalsIgnoreCase("O")) {
            System.out.println("Error: Invalid gender.");
            return;
        }
        char gender = genderStr.toUpperCase().charAt(0);

        System.out.print("Enter Contact: ");
        String contact = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        System.out.print("Enter Visit Date (DD/MM/YYYY): ");
        String visitDate = scanner.nextLine();

        System.out.print("Enter Payment Method (Cash/Card/Online): ");
        String method = scanner.nextLine();

        // create the outpatient and work out the bill
        String patientId = "OP" + paymentCounter;
        OutPatient outPatient = new OutPatient(patientId, name, age, gender, contact, address, visitDate);

        // show the bill
        outPatient.displayBill();

        String paymentId = "PAY" + paymentCounter;
        paymentCounter++;
        Payment payment = new Payment(paymentId, patientId, outPatient.calculateBill(), 0, method);
        payment.markAsPaid();
        paymentList.add(payment);

        System.out.println("\n>> Payment Processed! Payment ID: " + payment.getPaymentID());
    }

    // shows all payments
    static void viewAllPayments() {
        System.out.println("\n--- All Payments ---");
        if (paymentList.isEmpty()) {
            System.out.println("No payments recorded yet.");
            return;
        }
        for (int i = 0; i < paymentList.size(); i++) {
            paymentList.get(i).display();
        }
        printSeparator();
    }
}
