package com.hms.gui;

import java.util.HashMap;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class BloodBankGUI extends javax.swing.JFrame {

    private static Map<String, Integer> bloodInventory = new HashMap<>();

    static {
        if (bloodInventory.isEmpty()) {
            bloodInventory.put("A+", 50);
            bloodInventory.put("A-", 15);
            bloodInventory.put("B+", 40);
            bloodInventory.put("B-", 10);
            bloodInventory.put("AB+", 25);
            bloodInventory.put("AB-", 5);
            bloodInventory.put("O+", 60);
            bloodInventory.put("O-", 20);
        }
    }

    private JComboBox<String> cmbAction;
    private JComboBox<String> cmbType;
    private JTextField txtUnits;
    private JButton btnProcess;
    private JTable inventoryTable;

    public BloodBankGUI() {
        initComponents(); // Keeps NetBeans happy
        buildCustomUI();
    }
    
    private void buildCustomUI() {
        setTitle("Blood Donation Department");
        setSize(650, 450);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 20));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(Color.WHITE);
        
        // Top Panel
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        topPanel.setBackground(Color.WHITE);
        
        topPanel.add(new JLabel("Manage Inventory:"));
        
        cmbAction = new JComboBox<>(new String[]{"Register Donation", "Request Blood"});
        topPanel.add(cmbAction);
        
        cmbType = new JComboBox<>(new String[]{"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"});
        topPanel.add(cmbType);
        
        txtUnits = new JTextField(5);
        txtUnits.setText("1");
        topPanel.add(txtUnits);
        
        btnProcess = new JButton("Process");
        btnProcess.setBackground(new Color(37, 99, 235));
        btnProcess.setForeground(Color.WHITE);
        btnProcess.setFont(new Font("Segoe UI", Font.BOLD, 12));
        btnProcess.addActionListener(e -> processTransaction());
        topPanel.add(btnProcess);
        
        mainPanel.add(topPanel, BorderLayout.NORTH);
        
        // Center Panel (Table)
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(Color.WHITE);
        JLabel lblTitle = new JLabel("Live Blood Inventory:");
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 14));
        centerPanel.add(lblTitle, BorderLayout.NORTH);
        
        String[] columns = {"Blood Type", "Available Units"};
        inventoryTable = new JTable(new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        centerPanel.add(new JScrollPane(inventoryTable), BorderLayout.CENTER);
        
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        
        setContentPane(mainPanel);
        refreshInventoryTable();
    }

    private void refreshInventoryTable() {
        DefaultTableModel model = (DefaultTableModel) inventoryTable.getModel();
        model.setRowCount(0);
        String[] types = {"A+", "A-", "B+", "B-", "AB+", "AB-", "O+", "O-"};
        for (String type : types) {
            model.addRow(new Object[]{type, bloodInventory.getOrDefault(type, 0) + " Units"});
        }
    }
    
    private void processTransaction() {
        try {
            String action = cmbAction.getSelectedItem().toString();
            String type = cmbType.getSelectedItem().toString();
            int units = Integer.parseInt(txtUnits.getText());

            if (units <= 0) throw new NumberFormatException();

            int currentStock = bloodInventory.getOrDefault(type, 0);

            if (action.equals("Request Blood")) {
                if (units > currentStock) {
                    JOptionPane.showMessageDialog(this, "Insufficient stock! Only " + currentStock + " units available.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                bloodInventory.put(type, currentStock - units);
            } else {
                bloodInventory.put(type, currentStock + units);
            }

            JOptionPane.showMessageDialog(this, "Successfully processed " + action + " for " + units + " units of " + type + "!", "Success", JOptionPane.INFORMATION_MESSAGE);
            refreshInventoryTable();

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid positive number for Units.", "Input Error", JOptionPane.ERROR_MESSAGE);
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
