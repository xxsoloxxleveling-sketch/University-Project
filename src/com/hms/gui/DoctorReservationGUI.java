package com.hms.gui;

import com.hms.model.Doctor;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class DoctorReservationGUI extends javax.swing.JFrame {

    private ArrayList<Doctor> doctorList;
    private JComboBox<String> cmbSpecialty;
    private JTable doctorTable;
    private JButton btnBook;

    public DoctorReservationGUI(ArrayList<Doctor> doctorList) {
        this.doctorList = doctorList;
        initComponents(); // Keeps NetBeans happy if it looks for it
        buildCustomUI();
    }
    
    private void buildCustomUI() {
        setTitle("Doctor Reservation");
        setSize(600, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        
        // Top Panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.setBackground(Color.WHITE);
        topPanel.add(new JLabel("Filter by Specialty:"));
        
        cmbSpecialty = new JComboBox<>(new String[]{"All Specialists", "Cardiologist", "Neurologist", "Pediatrician", "Dermatologist"});
        cmbSpecialty.addActionListener(e -> refreshTable());
        topPanel.add(cmbSpecialty);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        
        // Table
        String[] columns = {"Doctor ID", "Name", "Specialty", "Available Slots"};
        doctorTable = new JTable(new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        mainPanel.add(new JScrollPane(doctorTable), BorderLayout.CENTER);
        
        // Bottom Panel
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBackground(Color.WHITE);
        
        btnBook = new JButton("Book Selected");
        btnBook.setBackground(new Color(37, 99, 235));
        btnBook.setForeground(Color.WHITE);
        btnBook.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnBook.setCursor(new Cursor(Cursor.HAND_CURSOR));
        
        btnBook.addActionListener(e -> bookSelected());
        bottomPanel.add(btnBook);
        
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        
        setContentPane(mainPanel);
        refreshTable();
    }

    private void refreshTable() {
        String specialty = cmbSpecialty.getSelectedItem().toString();
        DefaultTableModel model = (DefaultTableModel) doctorTable.getModel();
        model.setRowCount(0);

        for (Doctor d : doctorList) {
            if (specialty.equals("All Specialists") || d.getSpecialty().equalsIgnoreCase(specialty)) {
                String slots = d.getAvailableSlots().isEmpty() ? "No Slots" : d.getAvailableSlots().toString();
                model.addRow(new Object[]{d.getDoctorID(), d.getName(), d.getSpecialty(), slots});
            }
        }
    }
    
    private void bookSelected() {
        int selectedRow = doctorTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a doctor to book.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String doctorId = doctorTable.getValueAt(selectedRow, 0).toString();
        
        for (Doctor d : doctorList) {
            if (d.getDoctorID().equals(doctorId)) {
                if (d.getAvailableSlots().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Doctor has no available slots.", "Booking Failed", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                
                String slotToBook = d.getAvailableSlots().get(0); 
                d.bookSlot(slotToBook);
                
                JOptionPane.showMessageDialog(this, "Successfully booked Dr. " + d.getName() + " at " + slotToBook, "Booking Confirmed", JOptionPane.INFORMATION_MESSAGE);
                refreshTable();
                return;
            }
        }
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
