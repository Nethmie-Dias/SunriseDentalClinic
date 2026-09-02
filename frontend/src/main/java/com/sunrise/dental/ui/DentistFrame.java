package com.sunrise.dental.ui;

import com.sunrise.dental.api.ApiClient;
import com.sunrise.dental.model.Dentist;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class DentistFrame extends JFrame {

    // =========================================================
    // API
    // =========================================================

    private final ApiClient apiClient;

    // =========================================================
    // FORM FIELDS
    // =========================================================

    private JTextField nameField;
    private JTextField specializationField;
    private JTextField phoneField;
    private JTextField emailField;

    // =========================================================
    // TABLE
    // =========================================================

    private JTable dentistTable;
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

    public DentistFrame() {

        apiClient =
                new ApiClient();

        initializeWindow();

        initializeComponents();

        createLayout();

        loadDentists();
    }

    // =========================================================
    // WINDOW
    // =========================================================

    private void initializeWindow() {

        setTitle(
                "Sunrise Dental Clinic - Dentist Management"
        );

        setSize(
                1050,
                650
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );
    }

    // =========================================================
    // COMPONENTS
    // =========================================================

    private void initializeComponents() {

        nameField =
                new JTextField();

        specializationField =
                new JTextField();

        phoneField =
                new JTextField();

        emailField =
                new JTextField();

        addButton =
                new JButton(
                        "Add Dentist"
                );

        clearButton =
                new JButton(
                        "Clear"
                );

        refreshButton =
                new JButton(
                        "Refresh"
                );

        closeButton =
                new JButton(
                        "Close"
                );

        // =====================================================
        // TABLE MODEL
        // =====================================================

        tableModel =
                new DefaultTableModel(
                        new Object[]{
                                "ID",
                                "Dentist Name",
                                "Specialization",
                                "Phone",
                                "Email"
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

        dentistTable =
                new JTable(
                        tableModel
                );

        dentistTable.setRowHeight(
                30
        );

        dentistTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        // =====================================================
        // BUTTON EVENTS
        // =====================================================

        addButton.addActionListener(
                e -> addDentist()
        );

        clearButton.addActionListener(
                e -> clearForm()
        );

        refreshButton.addActionListener(
                e -> loadDentists()
        );

        closeButton.addActionListener(
                e -> dispose()
        );

        // =====================================================
        // TABLE SELECTION
        // =====================================================

        dentistTable
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                int row =
                                        dentistTable
                                                .getSelectedRow();

                                if (row >= 0) {

                                    nameField.setText(
                                            getTableValue(
                                                    row,
                                                    1
                                            )
                                    );

                                    specializationField.setText(
                                            getTableValue(
                                                    row,
                                                    2
                                            )
                                    );

                                    phoneField.setText(
                                            getTableValue(
                                                    row,
                                                    3
                                            )
                                    );

                                    emailField.setText(
                                            getTableValue(
                                                    row,
                                                    4
                                            )
                                    );
                                }
                            }
                        }
                );
    }

    // =========================================================
    // LAYOUT
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
                        249,
                        252
                )
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setOpaque(
                false
        );

        JLabel title =
                new JLabel(
                        "Dentist Management"
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
                        34,
                        93,
                        120
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Manage Sunrise Dental Clinic dentists"
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
                        90,
                        100,
                        110
                )
        );

        JPanel titlePanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        titlePanel.setOpaque(
                false
        );

        titlePanel.add(title);
        titlePanel.add(subtitle);

        headerPanel.add(
                titlePanel,
                BorderLayout.WEST
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // FORM
        // =====================================================

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Dentist Information"
                )
        );

        formPanel.setBackground(
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

        gbc.weightx = 1;

        // NAME

        gbc.gridx = 0;
        gbc.gridy = 0;

        formPanel.add(
                new JLabel(
                        "Dentist Name:"
                ),
                gbc
        );

        gbc.gridx = 1;

        formPanel.add(
                nameField,
                gbc
        );

        // SPECIALIZATION

        gbc.gridx = 2;

        formPanel.add(
                new JLabel(
                        "Specialization:"
                ),
                gbc
        );

        gbc.gridx = 3;

        formPanel.add(
                specializationField,
                gbc
        );

        // PHONE

        gbc.gridx = 0;
        gbc.gridy = 1;

        formPanel.add(
                new JLabel(
                        "Phone:"
                ),
                gbc
        );

        gbc.gridx = 1;

        formPanel.add(
                phoneField,
                gbc
        );

        // EMAIL

        gbc.gridx = 2;

        formPanel.add(
                new JLabel(
                        "Email:"
                ),
                gbc
        );

        gbc.gridx = 3;

        formPanel.add(
                emailField,
                gbc
        );

        // =====================================================
        // CENTER
        // =====================================================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        centerPanel.setOpaque(
                false
        );

        centerPanel.add(
                formPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // TABLE
        // =====================================================

        JPanel tablePanel =
                new JPanel(
                        new BorderLayout()
                );

        tablePanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Registered Dentists"
                )
        );

        tablePanel.setBackground(
                Color.WHITE
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        dentistTable
                );

        tablePanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        centerPanel.add(
                tablePanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                5
                        )
                );

        buttonPanel.setOpaque(
                false
        );

        buttonPanel.add(
                addButton
        );

        buttonPanel.add(
                clearButton
        );

        buttonPanel.add(
                refreshButton
        );

        buttonPanel.add(
                closeButton
        );

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        setContentPane(
                mainPanel
        );
    }

    // =========================================================
    // ADD DENTIST
    // =========================================================

    private void addDentist() {

        String name =
                nameField
                        .getText()
                        .trim();

        String specialization =
                specializationField
                        .getText()
                        .trim();

        String phone =
                phoneField
                        .getText()
                        .trim();

        String email =
                emailField
                        .getText()
                        .trim();

        // =====================================================
        // VALIDATION
        // =====================================================

        if (name.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the dentist name.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            nameField.requestFocus();

            return;
        }

        if (specialization.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the specialization.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            specializationField.requestFocus();

            return;
        }

        if (phone.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the phone number.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            phoneField.requestFocus();

            return;
        }

        if (email.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the email.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            emailField.requestFocus();

            return;
        }

        // =====================================================
        // CREATE DENTIST
        // =====================================================

        Dentist dentist =
                new Dentist(
                        name,
                        specialization,
                        phone,
                        email
                );

        addButton.setEnabled(
                false
        );

        addButton.setText(
                "Saving..."
        );

        SwingWorker<Dentist, Void>
                worker =
                new SwingWorker<>() {

                    @Override
                    protected Dentist
                    doInBackground()
                            throws Exception {

                        System.out.println(
                                "Sending dentist to API..."
                        );

                        return apiClient.addDentist(
                                dentist
                        );
                    }

                    @Override
                    protected void done() {

                        addButton.setEnabled(
                                true
                        );

                        addButton.setText(
                                "Add Dentist"
                        );

                        try {

                            Dentist savedDentist =
                                    get();

                            if (savedDentist == null) {

                                throw new Exception(
                                        "Server returned no dentist."
                                );
                            }

                            JOptionPane.showMessageDialog(
                                    DentistFrame.this,

                                    "Dentist added successfully.\n\n"
                                            + "ID: "
                                            + savedDentist
                                            .getDentistId(),

                                    "Success",

                                    JOptionPane.INFORMATION_MESSAGE
                            );

                            clearForm();

                            loadDentists();

                        } catch (Exception ex) {

                            String message =
                                    getErrorMessage(
                                            ex
                                    );

                            JOptionPane.showMessageDialog(
                                    DentistFrame.this,

                                    "Unable to add dentist.\n\n"
                                            + message,

                                    "Error",

                                    JOptionPane.ERROR_MESSAGE
                            );

                            System.err.println(
                                    "=================================="
                            );

                            System.err.println(
                                    "FRONTEND DENTIST SAVE ERROR"
                            );

                            ex.printStackTrace();

                            System.err.println(
                                    "=================================="
                            );
                        }
                    }
                };

        worker.execute();
    }

    // =========================================================
    // LOAD DENTISTS
    // =========================================================

    private void loadDentists() {

        refreshButton.setEnabled(
                false
        );

        SwingWorker<List<Dentist>, Void>
                worker =
                new SwingWorker<>() {

                    @Override
                    protected List<Dentist>
                    doInBackground()
                            throws Exception {

                        return apiClient.getDentists();
                    }

                    @Override
                    protected void done() {

                        refreshButton.setEnabled(
                                true
                        );

                        try {

                            List<Dentist> dentists =
                                    get();

                            updateTable(
                                    dentists
                            );

                        } catch (Exception ex) {

                            JOptionPane.showMessageDialog(
                                    DentistFrame.this,

                                    "Unable to load dentists.\n\n"
                                            + getErrorMessage(
                                            ex
                                    ),

                                    "Error",

                                    JOptionPane.ERROR_MESSAGE
                            );

                            ex.printStackTrace();
                        }
                    }
                };

        worker.execute();
    }

    // =========================================================
    // UPDATE TABLE
    // =========================================================

    private void updateTable(
            List<Dentist> dentists
    ) {

        tableModel.setRowCount(
                0
        );

        if (dentists == null) {

            return;
        }

        for (Dentist dentist : dentists) {

            tableModel.addRow(
                    new Object[]{

                            dentist.getDentistId(),

                            dentist.getDentistName(),

                            dentist.getSpecialization(),

                            dentist.getContactNumber(),

                            dentist.getEmail()
                    }
            );
        }
    }

    // =========================================================
    // CLEAR
    // =========================================================

    private void clearForm() {

        nameField.setText("");

        specializationField.setText("");

        phoneField.setText("");

        emailField.setText("");

        dentistTable.clearSelection();

        nameField.requestFocus();
    }

    // =========================================================
    // TABLE VALUE
    // =========================================================

    private String getTableValue(
            int row,
            int column
    ) {

        Object value =
                dentistTable.getValueAt(
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

        while (
                cause.getCause() != null
        ) {

            cause =
                    cause.getCause();
        }

        if (
                cause.getMessage() != null
        ) {

            return cause.getMessage();
        }

        return exception.toString();
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    DentistFrame frame =
                            new DentistFrame();

                    frame.setVisible(
                            true
                    );
                }
        );
    }
}