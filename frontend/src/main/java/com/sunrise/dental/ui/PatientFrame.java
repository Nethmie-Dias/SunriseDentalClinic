package com.sunrise.dental.ui;

import com.sunrise.dental.api.ApiClient;
import com.sunrise.dental.model.Patient;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PatientFrame extends JFrame {

    // =========================================================
    // API
    // =========================================================

    private final ApiClient apiClient;

    // =========================================================
    // FORM FIELDS
    // =========================================================

    private JTextField nameField;
    private JTextField contactNumberField;
    private JTextField addressField;

    // =========================================================
    // TABLE
    // =========================================================

    private JTable patientTable;
    private DefaultTableModel tableModel;

    // =========================================================
    // BUTTONS
    // =========================================================

    private JButton addButton;
    private JButton clearButton;
    private JButton refreshButton;
    private JButton closeButton;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public PatientFrame() {

        apiClient = new ApiClient();

        initializeWindow();
        initializeComponents();
        createLayout();

        loadPatients();
    }

    // =========================================================
    // WINDOW
    // =========================================================

    private void initializeWindow() {

        setTitle(
                "Sunrise Dental Clinic - Patient Management"
        );

        setSize(
                1000,
                650
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setResizable(false);
    }

    // =========================================================
    // INITIALIZE COMPONENTS
    // =========================================================

    private void initializeComponents() {

        nameField =
                new JTextField();

        contactNumberField =
                new JTextField();

        addressField =
                new JTextField();

        // =====================================================
        // BUTTONS
        // =====================================================

        addButton =
                new JButton("Add Patient");

        clearButton =
                new JButton("Clear");

        refreshButton =
                new JButton("Refresh");

        closeButton =
                new JButton("Close");

        // =====================================================
        // TABLE MODEL
        // =====================================================

        tableModel =
                new DefaultTableModel(
                        new Object[]{
                                "ID",
                                "Patient Name",
                                "Contact Number",
                                "Address"
                        },
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {

                        return false;
                    }
                };

        patientTable =
                new JTable(tableModel);

        patientTable.setRowHeight(30);

        patientTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        patientTable.setAutoCreateRowSorter(true);

        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        addButton.addActionListener(
                e -> addPatient()
        );

        clearButton.addActionListener(
                e -> clearForm()
        );

        refreshButton.addActionListener(
                e -> loadPatients()
        );

        closeButton.addActionListener(
                e -> dispose()
        );

        // =====================================================
        // TABLE SELECTION
        // =====================================================

        patientTable
                .getSelectionModel()
                .addListSelectionListener(e -> {

                    if (!e.getValueIsAdjusting()) {

                        int row =
                                patientTable.getSelectedRow();

                        if (row >= 0) {

                            int modelRow =
                                    patientTable.convertRowIndexToModel(
                                            row
                                    );

                            nameField.setText(
                                    getTableValue(
                                            modelRow,
                                            1
                                    )
                            );

                            contactNumberField.setText(
                                    getTableValue(
                                            modelRow,
                                            2
                                    )
                            );

                            addressField.setText(
                                    getTableValue(
                                            modelRow,
                                            3
                                    )
                            );
                        }
                    }
                });
    }

    // =========================================================
    // CREATE LAYOUT
    // =========================================================

    private void createLayout() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        mainPanel.setBorder(
                new EmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        mainPanel.setBackground(
                new Color(
                        245,
                        250,
                        252
                )
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel =
                createHeaderPanel();

        // =====================================================
        // FORM
        // =====================================================

        JPanel formPanel =
                createFormPanel();

        // =====================================================
        // TABLE
        // =====================================================

        JPanel tablePanel =
                createTablePanel();

        // =====================================================
        // BUTTONS
        // =====================================================

        JPanel buttonPanel =
                createButtonPanel();

        // =====================================================
        // CENTER
        // =====================================================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        centerPanel.setOpaque(false);

        centerPanel.add(
                formPanel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                tablePanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // MAIN
        // =====================================================

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        setContentPane(mainPanel);
    }

    // =========================================================
    // HEADER
    // =========================================================

    private JPanel createHeaderPanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setOpaque(false);

        JLabel title =
                new JLabel(
                        "Patient Management"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        28
                )
        );

        title.setForeground(
                new Color(
                        35,
                        55,
                        65
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Register and view Sunrise Dental Clinic patients"
                );

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(
                new Color(
                        110,
                        120,
                        125
                )
        );

        JPanel textPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        textPanel.setOpaque(false);

        textPanel.add(title);
        textPanel.add(subtitle);

        panel.add(
                textPanel,
                BorderLayout.WEST
        );

        return panel;
    }

    // =========================================================
    // FORM PANEL
    // =========================================================

    private JPanel createFormPanel() {

        JPanel panel =
                new JPanel(
                        new GridBagLayout()
                );

        panel.setBorder(
                BorderFactory.createTitledBorder(
                        "Patient Information"
                )
        );

        panel.setBackground(
                Color.WHITE
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        8,
                        8,
                        8,
                        8
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // =====================================================
        // PATIENT NAME
        // =====================================================

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;

        panel.add(
                new JLabel(
                        "Patient Name:"
                ),
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(
                nameField,
                gbc
        );

        // =====================================================
        // CONTACT NUMBER
        // =====================================================

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;

        panel.add(
                new JLabel(
                        "Contact Number:"
                ),
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(
                contactNumberField,
                gbc
        );

        // =====================================================
        // ADDRESS
        // =====================================================

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;

        panel.add(
                new JLabel(
                        "Address:"
                ),
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        panel.add(
                addressField,
                gbc
        );

        return panel;
    }

    // =========================================================
    // TABLE PANEL
    // =========================================================

    private JPanel createTablePanel() {

        JPanel panel =
                new JPanel(
                        new BorderLayout()
                );

        panel.setBorder(
                BorderFactory.createTitledBorder(
                        "Registered Patients"
                )
        );

        panel.setBackground(
                Color.WHITE
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        patientTable
                );

        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        return panel;
    }

    // =========================================================
    // BUTTON PANEL
    // =========================================================

    private JPanel createButtonPanel() {

        JPanel panel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                5
                        )
                );

        panel.setOpaque(false);

        panel.add(
                addButton
        );

        panel.add(
                clearButton
        );

        panel.add(
                refreshButton
        );

        panel.add(
                closeButton
        );

        return panel;
    }

    // =========================================================
    // ADD PATIENT
    // =========================================================

    private void addPatient() {

        String name =
                nameField
                        .getText()
                        .trim();

        String contactNumber =
                contactNumberField
                        .getText()
                        .trim();

        String address =
                addressField
                        .getText()
                        .trim();

        // =====================================================
        // VALIDATION
        // =====================================================

        if (name.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the patient name.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            nameField.requestFocus();

            return;
        }

        if (contactNumber.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the contact number.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            contactNumberField.requestFocus();

            return;
        }

        if (address.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the patient address.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            addressField.requestFocus();

            return;
        }

        // =====================================================
        // CREATE PATIENT OBJECT
        // =====================================================

        Patient patient =
                new Patient(
                        name,
                        address,
                        contactNumber
                );

        // =====================================================
        // DISABLE BUTTON
        // =====================================================

        addButton.setEnabled(false);

        addButton.setText(
                "Adding..."
        );

        // =====================================================
        // BACKGROUND REQUEST
        // =====================================================

        SwingWorker<Patient, Void> worker =
                new SwingWorker<>() {

                    @Override
                    protected Patient doInBackground()
                            throws Exception {

                        return apiClient.addPatient(
                                patient
                        );
                    }

                    @Override
                    protected void done() {

                        addButton.setEnabled(true);

                        addButton.setText(
                                "Add Patient"
                        );

                        try {

                            Patient savedPatient =
                                    get();

                            if (savedPatient == null) {

                                throw new Exception(
                                        "Server returned an empty patient."
                                );
                            }

                            JOptionPane.showMessageDialog(
                                    PatientFrame.this,

                                    "Patient added successfully.\n\n"
                                            + "Patient ID: "
                                            + savedPatient.getPatientId(),

                                    "Success",

                                    JOptionPane.INFORMATION_MESSAGE
                            );

                            clearForm();

                            loadPatients();

                        } catch (Exception ex) {

                            ex.printStackTrace();

                            JOptionPane.showMessageDialog(
                                    PatientFrame.this,

                                    "Unable to add patient.\n\n"
                                            + getErrorMessage(ex),

                                    "Error",

                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                };

        worker.execute();
    }

    // =========================================================
    // LOAD PATIENTS
    // =========================================================

    private void loadPatients() {

        refreshButton.setEnabled(false);

        refreshButton.setText(
                "Loading..."
        );

        SwingWorker<List<Patient>, Void> worker =
                new SwingWorker<>() {

                    @Override
                    protected List<Patient> doInBackground()
                            throws Exception {

                        return apiClient.getPatients();
                    }

                    @Override
                    protected void done() {

                        refreshButton.setEnabled(true);

                        refreshButton.setText(
                                "Refresh"
                        );

                        try {

                            List<Patient> patients =
                                    get();

                            updateTable(
                                    patients
                            );

                        } catch (Exception ex) {

                            ex.printStackTrace();

                            JOptionPane.showMessageDialog(
                                    PatientFrame.this,

                                    "Unable to load patients.\n\n"
                                            + getErrorMessage(ex),

                                    "Error",

                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                };

        worker.execute();
    }

    // =========================================================
    // UPDATE TABLE
    // =========================================================

    private void updateTable(
            List<Patient> patients
    ) {

        tableModel.setRowCount(0);

        if (patients == null) {
            return;
        }

        for (Patient patient : patients) {

            tableModel.addRow(
                    new Object[]{
                            patient.getPatientId(),
                            patient.getPatientName(),
                            patient.getContactNumber(),
                            patient.getAddress()
                    }
            );
        }
    }

    // =========================================================
    // CLEAR FORM
    // =========================================================

    private void clearForm() {

        nameField.setText("");

        contactNumberField.setText("");

        addressField.setText("");

        patientTable.clearSelection();

        nameField.requestFocus();
    }

    // =========================================================
    // GET TABLE VALUE
    // =========================================================

    private String getTableValue(
            int row,
            int column
    ) {

        Object value =
                patientTable.getModel().getValueAt(
                        row,
                        column
                );

        if (value == null) {

            return "";
        }

        return value.toString();
    }

    // =========================================================
    // ERROR MESSAGE
    // =========================================================

    private String getErrorMessage(
            Exception exception
    ) {

        Throwable cause =
                exception;

        while (cause.getCause() != null) {

            cause =
                    cause.getCause();
        }

        if (cause.getMessage() != null &&
                !cause.getMessage().isBlank()) {

            return cause.getMessage();
        }

        return exception.toString();
    }

    // =========================================================
    // MAIN - TESTING ONLY
    // =========================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(() -> {

            try {

                UIManager.setLookAndFeel(
                        UIManager
                                .getSystemLookAndFeelClassName()
                );

            } catch (Exception ignored) {
            }

            PatientFrame frame =
                    new PatientFrame();

            frame.setVisible(true);
        });
    }
}