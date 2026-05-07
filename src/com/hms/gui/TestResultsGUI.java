package com.hms.gui;

import com.hms.model.Patient;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class TestResultsGUI extends javax.swing.JFrame {

    private ArrayList<Patient> patientList;
    private JComboBox<String> cmbPatients;
    private JTable testTable;
    private JButton btnFetch;

    public TestResultsGUI(ArrayList<Patient> patientList) {
        this.patientList = patientList;
        initComponents(); // Keeps NetBeans happy
        buildCustomUI();
    }
    
    private void buildCustomUI() {
        setTitle("Test Results Viewer");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        
        // Top Panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.WHITE);
        topPanel.add(new JLabel("Select Patient:"));
        
        cmbPatients = new JComboBox<>();
        populatePatientCombo();
        topPanel.add(cmbPatients);
        
        btnFetch = new JButton("Fetch Results");
        btnFetch.setBackground(new Color(37, 99, 235));
        btnFetch.setForeground(Color.WHITE);
        btnFetch.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnFetch.addActionListener(e -> fetchResults());
        topPanel.add(btnFetch);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        
        // Table
        String[] columns = {"Test Name", "Result", "Normal Range"};
        testTable = new JTable(new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        mainPanel.add(new JScrollPane(testTable), BorderLayout.CENTER);
        
        setContentPane(mainPanel);
    }

    private void populatePatientCombo() {
        cmbPatients.addItem("Select Patient...");
        for (Patient p : patientList) {
            cmbPatients.addItem(p.getPatientID() + " - " + p.getName());
        }
    }
    
    private void fetchResults() {
        if (cmbPatients.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Please select a valid patient.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        DefaultTableModel model = (DefaultTableModel) testTable.getModel();
        model.setRowCount(0); // Clear previous

        // Generate Dummy Data based on patient selection
        model.addRow(new Object[]{"Complete Blood Count (CBC)", "Normal", "Normal"});
        model.addRow(new Object[]{"Blood Sugar (Fasting)", "110 mg/dL", "70-100 mg/dL (Slightly High)"});
        model.addRow(new Object[]{"Lipid Profile", "Pending...", "-"});
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
