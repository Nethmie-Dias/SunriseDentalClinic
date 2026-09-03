package com.sunrise.dental.ui;

import com.sunrise.dental.api.ApiClient;
import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.model.Dentist;
import com.sunrise.dental.model.Patient;
import com.sunrise.dental.model.Treatment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.time.LocalDate;
import java.time.LocalTime;

import java.time.format.DateTimeFormatter;

import java.util.List;

public class AppointmentFrame extends JFrame {

    // =========================================================
    // API
    // =========================================================

    private final ApiClient apiClient;

    // =========================================================
    // FORM FIELDS
    // =========================================================

    private JTextField appointmentNumberField;

    private JComboBox<Patient> patientComboBox;

    private JComboBox<Dentist> dentistComboBox;

    private JComboBox<Treatment> treatmentComboBox;

    private JTextField dateField;

    private JTextField timeField;

    private JTextArea notesArea;

    // =========================================================
    // SEARCH
    // =========================================================

    private JTextField searchField;

    // =========================================================
    // TABLE
    // =========================================================

    private JTable appointmentTable;

    private DefaultTableModel tableModel;

    // =========================================================
    // DATA
    // =========================================================

    private List<Patient> patients;

    private List<Dentist> dentists;

    private List<Treatment> treatments;

    private List<Appointment> appointments;

    // =========================================================
    // FORMATTERS
    // =========================================================

    private final DateTimeFormatter dateFormatter =
            DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private final DateTimeFormatter timeFormatter =
            DateTimeFormatter.ofPattern("HH:mm");

    // =========================================================
    // COLORS
    // =========================================================

    private static final Color PRIMARY_COLOR =
            new Color(25, 94, 122);

    private static final Color PRIMARY_DARK =
            new Color(18, 70, 92);

    private static final Color SUCCESS_COLOR =
            new Color(25, 125, 85);

    private static final Color WARNING_COLOR =
            new Color(210, 145, 35);

    private static final Color DANGER_COLOR =
            new Color(190, 65, 65);

    private static final Color BACKGROUND_COLOR =
            new Color(245, 249, 252);

    private static final Color TEXT_COLOR =
            new Color(35, 45, 55);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public AppointmentFrame() {

        apiClient =
                new ApiClient();

        setTitle(
                "Sunrise Dental Clinic - Appointment Management"
        );

        setSize(
                1350,
                850
        );

        setMinimumSize(
                new Dimension(
                        1100,
                        700
                )
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        createInterface();

        loadAppointmentData();
    }

    // =========================================================
    // CREATE INTERFACE
    // =========================================================

    private void createInterface() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                BACKGROUND_COLOR
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel =
                new JPanel(
                        new BorderLayout()
                );

        headerPanel.setBackground(
                PRIMARY_COLOR
        );

        headerPanel.setBorder(
                new EmptyBorder(
                        20,
                        30,
                        20,
                        30
                )
        );

        JLabel titleLabel =
                new JLabel(
                        "Appointment Management"
                );

        titleLabel.setForeground(
                Color.WHITE
        );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        25
                )
        );

        JLabel subtitleLabel =
                new JLabel(
                        "Sunrise Dental Clinic"
                );

        subtitleLabel.setForeground(
                new Color(
                        225,
                        240,
                        248
                )
        );

        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        JPanel headerText =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                0,
                                4
                        )
                );

        headerText.setOpaque(
                false
        );

        headerText.add(
                titleLabel
        );

        headerText.add(
                subtitleLabel
        );

        headerPanel.add(
                headerText,
                BorderLayout.WEST
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // CONTENT
        // =====================================================

        JPanel contentPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                12
                        )
                );

        contentPanel.setOpaque(
                false
        );

        contentPanel.setBorder(
                new EmptyBorder(
                        18,
                        25,
                        18,
                        25
                )
        );

        // FORM

        contentPanel.add(
                createFormPanel(),
                BorderLayout.NORTH
        );

        // SEARCH + TABLE

        JPanel bottomPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                10
                        )
                );

        bottomPanel.setOpaque(
                false
        );

        bottomPanel.add(
                createSearchPanel(),
                BorderLayout.NORTH
        );

        bottomPanel.add(
                createTablePanel(),
                BorderLayout.CENTER
        );

        contentPanel.add(
                bottomPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // FOOTER
        // =====================================================

        JLabel footer =
                new JLabel(
                        "Sunrise Dental Clinic  |  Appointment Management System"
                );

        footer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        footer.setBorder(
                new EmptyBorder(
                        8,
                        8,
                        8,
                        8
                )
        );

        footer.setForeground(
                new Color(
                        80,
                        90,
                        100
                )
        );

        footer.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        mainPanel.add(
                footer,
                BorderLayout.SOUTH
        );

        setContentPane(
                mainPanel
        );
    }

    // =========================================================
    // FORM PANEL
    // =========================================================

    private JPanel createFormPanel() {

        JPanel outerPanel =
                new JPanel(
                        new BorderLayout()
                );

        outerPanel.setBackground(
                Color.WHITE
        );

        outerPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        205,
                                        218,
                                        225
                                ),
                                1
                        ),
                        new EmptyBorder(
                                15,
                                18,
                                15,
                                18
                        )
                )
        );

        // =====================================================
        // TITLE
        // =====================================================

        JLabel formTitle =
                new JLabel(
                        "Register / Manage Appointment"
                );

        formTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        18
                )
        );

        formTitle.setForeground(
                PRIMARY_DARK
        );

        formTitle.setBorder(
                new EmptyBorder(
                        0,
                        0,
                        10,
                        0
                )
        );

        outerPanel.add(
                formTitle,
                BorderLayout.NORTH
        );

        // =====================================================
        // FORM
        // =====================================================

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setBackground(
                Color.WHITE
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        5,
                        7,
                        5,
                        7
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.WEST;

        // -----------------------------------------------------
        // APPOINTMENT NUMBER
        // -----------------------------------------------------

        addLabel(
                formPanel,
                gbc,
                "Appointment Number:",
                0,
                0
        );

        appointmentNumberField =
                new JTextField();

        styleTextField(
                appointmentNumberField
        );

        appointmentNumberField.setPreferredSize(
                new Dimension(
                        250,
                        34
                )
        );

        appointmentNumberField.setToolTipText(
                "Example: APT-2026-00125"
        );

        addComponent(
                formPanel,
                gbc,
                appointmentNumberField,
                1,
                0
        );

        // -----------------------------------------------------
        // PATIENT
        // -----------------------------------------------------

        addLabel(
                formPanel,
                gbc,
                "Patient:",
                0,
                1
        );

        patientComboBox =
                new JComboBox<>();

        styleComboBox(
                patientComboBox
        );

        patientComboBox.setPreferredSize(
                new Dimension(
                        250,
                        34
                )
        );

        addComponent(
                formPanel,
                gbc,
                patientComboBox,
                1,
                1
        );

        // -----------------------------------------------------
        // DENTIST
        // -----------------------------------------------------

        addLabel(
                formPanel,
                gbc,
                "Dentist:",
                0,
                2
        );

        dentistComboBox =
                new JComboBox<>();

        styleComboBox(
                dentistComboBox
        );

        dentistComboBox.setPreferredSize(
                new Dimension(
                        250,
                        34
                )
        );

        addComponent(
                formPanel,
                gbc,
                dentistComboBox,
                1,
                2
        );

        // -----------------------------------------------------
        // TREATMENT
        // -----------------------------------------------------

        addLabel(
                formPanel,
                gbc,
                "Treatment:",
                0,
                3
        );

        treatmentComboBox =
                new JComboBox<>();

        styleComboBox(
                treatmentComboBox
        );

        treatmentComboBox.setPreferredSize(
                new Dimension(
                        250,
                        34
                )
        );

        addComponent(
                formPanel,
                gbc,
                treatmentComboBox,
                1,
                3
        );

        // -----------------------------------------------------
        // DATE
        // -----------------------------------------------------

        addLabel(
                formPanel,
                gbc,
                "Appointment Date:",
                2,
                0
        );

        dateField =
                new JTextField();

        styleTextField(
                dateField
        );

        dateField.setPreferredSize(
                new Dimension(
                        180,
                        34
                )
        );

        dateField.setToolTipText(
                "Format: YYYY-MM-DD"
        );

        addComponent(
                formPanel,
                gbc,
                dateField,
                3,
                0
        );

        // -----------------------------------------------------
        // TIME
        // -----------------------------------------------------

        addLabel(
                formPanel,
                gbc,
                "Appointment Time:",
                2,
                1
        );

        timeField =
                new JTextField();

        styleTextField(
                timeField
        );

        timeField.setPreferredSize(
                new Dimension(
                        180,
                        34
                )
        );

        timeField.setToolTipText(
                "Format: HH:MM"
        );

        addComponent(
                formPanel,
                gbc,
                timeField,
                3,
                1
        );

        // -----------------------------------------------------
        // NOTES
        // -----------------------------------------------------

        addLabel(
                formPanel,
                gbc,
                "Notes:",
                2,
                2
        );

        notesArea =
                new JTextArea(
                        3,
                        20
                );

        notesArea.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        notesArea.setForeground(
                TEXT_COLOR
        );

        notesArea.setBackground(
                Color.WHITE
        );

        notesArea.setLineWrap(
                true
        );

        notesArea.setWrapStyleWord(
                true
        );

        JScrollPane notesScrollPane =
                new JScrollPane(
                        notesArea
                );

        notesScrollPane.setPreferredSize(
                new Dimension(
                        250,
                        80
                )
        );

        addComponent(
                formPanel,
                gbc,
                notesScrollPane,
                3,
                2
        );

        // =====================================================
        // BUTTONS
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                8,
                                8
                        )
                );

        buttonPanel.setBackground(
                Color.WHITE
        );

        // REGISTER

        JButton registerButton =
                createButton(
                        "Register Appointment",
                        Color.WHITE
                );

        registerButton.setPreferredSize(
                new Dimension(
                        190,
                        42
                )
        );

        registerButton.addActionListener(
                e -> registerAppointment()
        );

        // VIEW

        JButton viewButton =
                createButton(
                        "View",
                        Color.WHITE
                );

        viewButton.addActionListener(
                e -> viewSelectedAppointment()
        );

        // UPDATE

        JButton updateButton =
                createButton(
                        "Update",
                        Color.WHITE
                );

        updateButton.addActionListener(
                e -> updateAppointment()
        );

        // CANCEL

        JButton cancelButton =
                createButton(
                        "Cancel",
                        Color.WHITE
                );

        cancelButton.addActionListener(
                e -> cancelSelectedAppointment()
        );

        // CLEAR

        JButton clearButton =
                createButton(
                        "Clear",
                        Color.WHITE
                );

        clearButton.addActionListener(
                e -> clearForm()
        );

        // REFRESH

        JButton refreshButton =
                createButton(
                        "Refresh",
                        Color.WHITE
                );

        refreshButton.addActionListener(
                e -> loadAppointments()
        );

        // CLOSE

        JButton closeButton =
                createButton(
                        "Close",
                        Color.WHITE
                );

        closeButton.addActionListener(
                e -> closeWindow()
        );

        buttonPanel.add(
                registerButton
        );

        buttonPanel.add(
                viewButton
        );

        buttonPanel.add(
                updateButton
        );

        buttonPanel.add(
                cancelButton
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

        gbc.gridx = 0;

        gbc.gridy = 4;

        gbc.gridwidth = 4;

        gbc.weightx = 1.0;

        formPanel.add(
                buttonPanel,
                gbc
        );

        outerPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        return outerPanel;
    }

    // =========================================================
    // SEARCH PANEL
    // =========================================================

    private JPanel createSearchPanel() {

        JPanel searchPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                12,
                                10
                        )
                );

        searchPanel.setBackground(
                Color.WHITE
        );

        searchPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder(
                                "Search Appointment"
                        ),
                        new EmptyBorder(
                                2,
                                8,
                                2,
                                8
                        )
                )
        );

        JLabel label =
                new JLabel(
                        "Appointment Number:"
                );

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(
                TEXT_COLOR
        );

        searchField =
                new JTextField(
                        22
                );

        styleTextField(
                searchField
        );

        searchField.setPreferredSize(
                new Dimension(
                        250,
                        32
                )
        );

        searchField.setToolTipText(
                "Example: APT-2026-00125"
        );

        JButton searchButton =
                createButton(
                        "Search",
                        Color.WHITE
                );

        searchButton.setPreferredSize(
                new Dimension(
                        110,
                        34
                )
        );

        searchButton.addActionListener(
                e -> searchAppointment()
        );

        JButton showAllButton =
                createButton(
                        "Show All",
                        Color.WHITE
                );

        showAllButton.setPreferredSize(
                new Dimension(
                        110,
                        34
                )
        );

        showAllButton.addActionListener(
                e -> {

                    searchField.setText("");

                    loadAppointments();
                }
        );

        searchField.addActionListener(
                e -> searchAppointment()
        );

        searchPanel.add(
                label
        );

        searchPanel.add(
                searchField
        );

        searchPanel.add(
                searchButton
        );

        searchPanel.add(
                showAllButton
        );

        return searchPanel;
    }

    // =========================================================
    // TABLE
    // =========================================================

    private JPanel createTablePanel() {

        JPanel tablePanel =
                new JPanel(
                        new BorderLayout()
                );

        tablePanel.setBackground(
                Color.WHITE
        );

        tablePanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Registered Appointments"
                )
        );

        String[] columns = {

                "ID",
                "Appointment No.",
                "Patient",
                "Contact",
                "Dentist",
                "Treatment",
                "Date",
                "Time",
                "Status"
        };

        tableModel =
                new DefaultTableModel(
                        columns,
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

        appointmentTable =
                new JTable(
                        tableModel
                );

        appointmentTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        appointmentTable.setRowHeight(
                30
        );

        appointmentTable.setAutoCreateRowSorter(
                true
        );

        appointmentTable.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        appointmentTable.setForeground(
                TEXT_COLOR
        );

        appointmentTable.setBackground(
                Color.WHITE
        );

        appointmentTable.setGridColor(
                new Color(
                        220,
                        228,
                        233
                )
        );

        appointmentTable.setSelectionBackground(
                new Color(
                        210,
                        230,
                        240
                )
        );

        appointmentTable.setSelectionForeground(
                TEXT_COLOR
        );

        appointmentTable.getTableHeader()
                .setReorderingAllowed(
                        false
                );

        appointmentTable.getTableHeader()
                .setFont(
                        new Font(
                                "SansSerif",
                                Font.BOLD,
                                13
                        )
                );

        appointmentTable.getTableHeader()
                .setForeground(
                        Color.WHITE
                );

        appointmentTable.getTableHeader()
                .setBackground(
                        PRIMARY_COLOR
                );

        appointmentTable.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            MouseEvent e
                    ) {

                        if (
                                e.getClickCount() == 2
                        ) {

                            loadSelectedAppointmentIntoForm();
                        }
                    }
                }
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        appointmentTable
                );

        tablePanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        return tablePanel;
    }

    // =========================================================
    // CREATE BUTTON
    // =========================================================

    private JButton createButton(
            String text,
            Color background
    ) {

        JButton button =
                new JButton(
                        text
                );

        // Same background for every button

        button.setBackground(
                Color.WHITE
        );

        // Clearly visible black text

        button.setForeground(
                Color.BLACK
        );

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        button.setFocusPainted(
                false
        );

        button.setOpaque(
                true
        );

        button.setContentAreaFilled(
                true
        );

        // Black border

        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                Color.BLACK,
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                8,
                                16,
                                8,
                                16
                        )
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =========================================================
    // TEXT FIELD STYLE
    // =========================================================

    private void styleTextField(
            JTextField field
    ) {

        field.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        field.setForeground(
                TEXT_COLOR
        );

        field.setBackground(
                Color.WHITE
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        190,
                                        202,
                                        210
                                ),
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                5,
                                8,
                                5,
                                8
                        )
                )
        );
    }

    // =========================================================
    // COMBO BOX STYLE
    // =========================================================

    private void styleComboBox(
            JComboBox<?> comboBox
    ) {

        comboBox.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        comboBox.setForeground(
                TEXT_COLOR
        );

        comboBox.setBackground(
                Color.WHITE
        );
    }

    // =========================================================
    // LOAD DATA
    // =========================================================

    private void loadAppointmentData() {

        try {

            patients =
                    apiClient.getPatients();

            dentists =
                    apiClient.getDentists();

            treatments =
                    apiClient.getTreatments();

            // -------------------------------------------------
            // PATIENTS
            // -------------------------------------------------

            patientComboBox.removeAllItems();

            if (patients != null) {

                for (
                        Patient patient :
                        patients
                ) {

                    patientComboBox.addItem(
                            patient
                    );
                }
            }

            // -------------------------------------------------
            // DENTISTS
            // -------------------------------------------------

            dentistComboBox.removeAllItems();

            if (dentists != null) {

                for (
                        Dentist dentist :
                        dentists
                ) {

                    dentistComboBox.addItem(
                            dentist
                    );
                }
            }

            // -------------------------------------------------
            // TREATMENTS
            // -------------------------------------------------

            treatmentComboBox.removeAllItems();

            if (treatments != null) {

                for (
                        Treatment treatment :
                        treatments
                ) {

                    treatmentComboBox.addItem(
                            treatment
                    );
                }
            }

            loadAppointments();

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load appointment data.\n\n"
                            + getErrorMessage(ex),
                    "Appointment Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // REGISTER
    // =========================================================

    private void registerAppointment() {

        try {

            String appointmentNumber =
                    appointmentNumberField
                            .getText()
                            .trim()
                            .toUpperCase();

            // -------------------------------------------------
            // APPOINTMENT NUMBER
            // -------------------------------------------------

            if (
                    appointmentNumber.isEmpty()
            ) {

                showError(
                        "Please enter an appointment number."
                );

                appointmentNumberField.requestFocus();

                return;
            }

            if (
                    !appointmentNumber.matches(
                            "^APT(?:[0-9]{3,}|-[0-9]{4}-[0-9]{3,})$"
                    )
            ) {

                showError(
                        "Invalid appointment number.\n\n"
                                + "Examples:\n"
                                + "APT001\n"
                                + "APT-2026-00125"
                );

                appointmentNumberField.requestFocus();

                return;
            }

            // -------------------------------------------------
            // CHECK DUPLICATE
            // -------------------------------------------------

            try {

                Appointment existing =
                        apiClient.getAppointmentByNumber(
                                appointmentNumber
                        );

                if (existing != null) {

                    showError(
                            "Appointment number already exists.\n\n"
                                    + "Please use another appointment number."
                    );

                    appointmentNumberField.requestFocus();

                    return;
                }

            } catch (Exception ignored) {

                // Appointment not found is acceptable.
            }

            // -------------------------------------------------
            // PATIENT
            // -----------------------------------------------------

            Patient patient =
                    (Patient)
                            patientComboBox
                                    .getSelectedItem();

            if (patient == null) {

                showError(
                        "Please select a patient."
                );

                return;
            }

            // -------------------------------------------------
            // DENTIST
            // -----------------------------------------------------

            Dentist dentist =
                    (Dentist)
                            dentistComboBox
                                    .getSelectedItem();

            if (dentist == null) {

                showError(
                        "Please select a dentist."
                );

                return;
            }

            // -------------------------------------------------
            // TREATMENT
            // -----------------------------------------------------

            Treatment treatment =
                    (Treatment)
                            treatmentComboBox
                                    .getSelectedItem();

            if (treatment == null) {

                showError(
                        "Please select a treatment."
                );

                return;
            }

            // -------------------------------------------------
            // DATE
            // -----------------------------------------------------

            LocalDate appointmentDate =
                    parseDate();

            if (appointmentDate == null) {

                return;
            }

            // -------------------------------------------------
            // TIME
            // -----------------------------------------------------

            LocalTime appointmentTime =
                    parseTime();

            if (appointmentTime == null) {

                return;
            }

            // -------------------------------------------------
            // CLINIC TIME
            // -----------------------------------------------------

            if (
                    !isClinicTime(
                            appointmentTime
                    )
            ) {

                showError(
                        "Appointments must be between "
                                + "08:00 and 18:00."
                );

                return;
            }

            // -------------------------------------------------
            // CREATE APPOINTMENT
            // -----------------------------------------------------

            Appointment appointment =
                    new Appointment();

            appointment.setAppointmentNumber(
                    appointmentNumber
            );

            appointment.setPatientId(
                    patient.getPatientId()
            );

            appointment.setDentistId(
                    dentist.getDentistId()
            );

            appointment.setTreatmentId(
                    treatment.getTreatmentId()
            );

            appointment.setAppointmentDate(
                    appointmentDate
            );

            appointment.setAppointmentTime(
                    appointmentTime
            );

            appointment.setStatus(
                    "Scheduled"
            );

            appointment.setNotes(
                    notesArea
                            .getText()
                            .trim()
            );

            // -------------------------------------------------
            // CONFIRM
            // -------------------------------------------------

            int confirmation =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Register this appointment?\n\n"
                                    + "Appointment: "
                                    + appointmentNumber
                                    + "\nPatient: "
                                    + patient
                                    + "\nDentist: "
                                    + dentist
                                    + "\nDate: "
                                    + appointmentDate
                                    + "\nTime: "
                                    + appointmentTime,
                            "Confirm Appointment",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );

            if (
                    confirmation !=
                            JOptionPane.YES_OPTION
            ) {

                return;
            }

            // -------------------------------------------------
            // SEND TO SERVER
            // -----------------------------------------------------

            String message =
                    apiClient.addAppointment(
                            appointment
                    );

            // -------------------------------------------------
            // SUCCESS
            // -----------------------------------------------------

            JOptionPane.showMessageDialog(
                    this,
                    message
                            + "\n\nAppointment Number: "
                            + appointmentNumber,
                    "Appointment Registered",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearForm();

            loadAppointments();

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment registration failed.\n\n"
                            + getErrorMessage(ex),
                    "Registration Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // SEARCH
    // =========================================================

    private void searchAppointment() {

        String appointmentNumber =
                searchField
                        .getText()
                        .trim()
                        .toUpperCase();

        if (
                appointmentNumber.isEmpty()
        ) {

            showError(
                    "Please enter an appointment number."
            );

            searchField.requestFocus();

            return;
        }

        try {

            Appointment appointment =
                    apiClient.getAppointmentByNumber(
                            appointmentNumber
                    );

            if (appointment == null) {

                showError(
                        "Appointment not found."
                );

                return;
            }

            loadAppointmentIntoForm(
                    appointment
            );

            selectAppointmentInTable(
                    appointment.getAppointmentId()
            );

            JTextArea details =
                    createAppointmentDetails(
                            appointment
                    );

            JOptionPane.showMessageDialog(
                    this,
                    details,
                    "Appointment Found",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Search failed.\n\n"
                            + getErrorMessage(ex),
                    "Search Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // VIEW SELECTED
    // =========================================================

    private void viewSelectedAppointment() {

        Appointment appointment =
                getSelectedAppointment();

        if (appointment == null) {

            showError(
                    "Please select an appointment from the table."
            );

            return;
        }

        JOptionPane.showMessageDialog(
                this,
                createAppointmentDetails(
                        appointment
                ),
                "Appointment Details",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================================================
    // LOAD SELECTED INTO FORM
    // =========================================================

    private void loadSelectedAppointmentIntoForm() {

        Appointment appointment =
                getSelectedAppointment();

        if (appointment == null) {

            return;
        }

        loadAppointmentIntoForm(
                appointment
        );
    }

    // =========================================================
    // UPDATE
    // =========================================================

    private void updateAppointment() {

        Appointment selectedAppointment =
                getSelectedAppointment();

        if (selectedAppointment == null) {

            showError(
                    "Please select an appointment from the table first."
            );

            return;
        }

        try {

            Patient patient =
                    (Patient)
                            patientComboBox
                                    .getSelectedItem();

            Dentist dentist =
                    (Dentist)
                            dentistComboBox
                                    .getSelectedItem();

            Treatment treatment =
                    (Treatment)
                            treatmentComboBox
                                    .getSelectedItem();

            if (patient == null) {

                showError(
                        "Please select a patient."
                );

                return;
            }

            if (dentist == null) {

                showError(
                        "Please select a dentist."
                );

                return;
            }

            if (treatment == null) {

                showError(
                        "Please select a treatment."
                );

                return;
            }

            LocalDate date =
                    parseDate();

            if (date == null) {

                return;
            }

            LocalTime time =
                    parseTime();

            if (time == null) {

                return;
            }

            if (
                    !isClinicTime(time)
            ) {

                showError(
                        "Appointments must be between "
                                + "08:00 and 18:00."
                );

                return;
            }

            int confirmation =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Update appointment "
                                    + selectedAppointment
                                    .getAppointmentNumber()
                                    + "?",
                            "Confirm Update",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.QUESTION_MESSAGE
                    );

            if (
                    confirmation !=
                            JOptionPane.YES_OPTION
            ) {

                return;
            }

            Appointment appointment =
                    new Appointment();

            appointment.setAppointmentId(
                    selectedAppointment
                            .getAppointmentId()
            );

            appointment.setAppointmentNumber(
                    selectedAppointment
                            .getAppointmentNumber()
            );

            appointment.setPatientId(
                    patient.getPatientId()
            );

            appointment.setDentistId(
                    dentist.getDentistId()
            );

            appointment.setTreatmentId(
                    treatment.getTreatmentId()
            );

            appointment.setAppointmentDate(
                    date
            );

            appointment.setAppointmentTime(
                    time
            );

            appointment.setStatus(
                    selectedAppointment
                            .getStatus()
            );

            appointment.setNotes(
                    notesArea
                            .getText()
                            .trim()
            );

            String message =
                    apiClient.updateAppointment(
                            appointment
                    );

            JOptionPane.showMessageDialog(
                    this,
                    message,
                    "Appointment Updated",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadAppointments();

            selectAppointmentInTable(
                    appointment.getAppointmentId()
            );

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Appointment update failed.\n\n"
                            + getErrorMessage(ex),
                    "Update Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // CANCEL
    // =========================================================

    private void cancelSelectedAppointment() {

        Appointment appointment =
                getSelectedAppointment();

        if (appointment == null) {

            showError(
                    "Please select an appointment from the table first."
            );

            return;
        }

        if (
                "Cancelled".equalsIgnoreCase(
                        appointment.getStatus()
                )
        ) {

            showError(
                    "This appointment is already cancelled."
            );

            return;
        }

        int confirmation =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to cancel:\n\n"
                                + appointment
                                .getAppointmentNumber()
                                + "?",
                        "Confirm Cancellation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (
                confirmation !=
                        JOptionPane.YES_OPTION
        ) {

            return;
        }

        try {

            String message =
                    apiClient.cancelAppointment(
                            appointment
                                    .getAppointmentId()
                    );

            JOptionPane.showMessageDialog(
                    this,
                    message,
                    "Appointment Cancelled",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadAppointments();

            clearForm();

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to cancel appointment.\n\n"
                            + getErrorMessage(ex),
                    "Cancellation Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // LOAD APPOINTMENTS
    // =========================================================

    private void loadAppointments() {

        try {

            appointments =
                    apiClient.getAppointments();

            tableModel.setRowCount(0);

            if (
                    appointments == null
                            ||
                            appointments.isEmpty()
            ) {

                return;
            }

            for (
                    Appointment appointment :
                    appointments
            ) {

                tableModel.addRow(
                        new Object[]{

                                appointment
                                        .getAppointmentId(),

                                appointment
                                        .getAppointmentNumber(),

                                appointment
                                        .getPatientName(),

                                appointment
                                        .getContactNumber(),

                                appointment
                                        .getDentistName(),

                                appointment
                                        .getTreatmentName(),

                                appointment
                                        .getAppointmentDate(),

                                appointment
                                        .getAppointmentTime(),

                                appointment
                                        .getStatus()
                        }
                );
            }

        } catch (Exception ex) {

            ex.printStackTrace();

            tableModel.setRowCount(0);

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load appointments.\n\n"
                            + getErrorMessage(ex),
                    "Appointment Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // GET SELECTED
    // =========================================================

    private Appointment getSelectedAppointment() {

        int selectedRow =
                appointmentTable
                        .getSelectedRow();

        if (
                selectedRow == -1
        ) {

            return null;
        }

        int modelRow =
                appointmentTable
                        .convertRowIndexToModel(
                                selectedRow
                        );

        if (
                appointments == null
                        ||
                        modelRow < 0
                        ||
                        modelRow >= appointments.size()
        ) {

            return null;
        }

        return appointments.get(
                modelRow
        );
    }

    // =========================================================
    // LOAD INTO FORM
    // =========================================================

    private void loadAppointmentIntoForm(
            Appointment appointment
    ) {

        appointmentNumberField.setText(
                appointment
                        .getAppointmentNumber()
        );

        if (
                appointment.getAppointmentDate()
                        != null
        ) {

            dateField.setText(
                    appointment
                            .getAppointmentDate()
                            .format(
                                    dateFormatter
                            )
            );
        }

        if (
                appointment.getAppointmentTime()
                        != null
        ) {

            timeField.setText(
                    appointment
                            .getAppointmentTime()
                            .format(
                                    timeFormatter
                            )
            );
        }

        notesArea.setText(
                appointment.getNotes() == null
                        ?
                        ""
                        :
                        appointment.getNotes()
        );

        selectPatient(
                appointment.getPatientId()
        );

        selectDentist(
                appointment.getDentistId()
        );

        selectTreatment(
                appointment.getTreatmentId()
        );
    }

    // =========================================================
    // SELECT PATIENT
    // =========================================================

    private void selectPatient(
            int patientId
    ) {

        for (
                int i = 0;
                i < patientComboBox.getItemCount();
                i++
        ) {

            Patient patient =
                    patientComboBox
                            .getItemAt(i);

            if (
                    patient != null
                            &&
                            patient.getPatientId()
                                    == patientId
            ) {

                patientComboBox
                        .setSelectedIndex(i);

                return;
            }
        }
    }

    // =========================================================
    // SELECT DENTIST
    // =========================================================

    private void selectDentist(
            int dentistId
    ) {

        for (
                int i = 0;
                i < dentistComboBox.getItemCount();
                i++
        ) {

            Dentist dentist =
                    dentistComboBox
                            .getItemAt(i);

            if (
                    dentist != null
                            &&
                            dentist.getDentistId()
                                    == dentistId
            ) {

                dentistComboBox
                        .setSelectedIndex(i);

                return;
            }
        }
    }

    // =========================================================
    // SELECT TREATMENT
    // =========================================================

    private void selectTreatment(
            int treatmentId
    ) {

        for (
                int i = 0;
                i < treatmentComboBox.getItemCount();
                i++
        ) {

            Treatment treatment =
                    treatmentComboBox
                            .getItemAt(i);

            if (
                    treatment != null
                            &&
                            treatment.getTreatmentId()
                                    == treatmentId
            ) {

                treatmentComboBox
                        .setSelectedIndex(i);

                return;
            }
        }
    }

    // =========================================================
    // SELECT TABLE ROW
    // =========================================================

    private void selectAppointmentInTable(
            int appointmentId
    ) {

        for (
                int viewRow = 0;
                viewRow < appointmentTable.getRowCount();
                viewRow++
        ) {

            int modelRow =
                    appointmentTable
                            .convertRowIndexToModel(
                                    viewRow
                            );

            Object id =
                    tableModel.getValueAt(
                            modelRow,
                            0
                    );

            if (
                    id instanceof Number
                            &&
                            ((Number) id)
                                    .intValue()
                                    == appointmentId
            ) {

                appointmentTable
                        .setRowSelectionInterval(
                                viewRow,
                                viewRow
                        );

                appointmentTable
                        .scrollRectToVisible(
                                appointmentTable
                                        .getCellRect(
                                                viewRow,
                                                0,
                                                true
                                        )
                        );

                return;
            }
        }
    }

    // =========================================================
    // PARSE DATE
    // =========================================================

    private LocalDate parseDate() {

        String text =
                dateField
                        .getText()
                        .trim();

        if (text.isEmpty()) {

            showError(
                    "Please enter the appointment date."
            );

            dateField.requestFocus();

            return null;
        }

        try {

            LocalDate date =
                    LocalDate.parse(
                            text,
                            dateFormatter
                    );

            if (
                    date.isBefore(
                            LocalDate.now()
                    )
            ) {

                showError(
                        "Appointment date cannot be in the past."
                );

                return null;
            }

            return date;

        } catch (Exception ex) {

            showError(
                    "Invalid date.\n\n"
                            + "Use YYYY-MM-DD.\n"
                            + "Example: 2026-09-15"
            );

            return null;
        }
    }

    // =========================================================
    // PARSE TIME
    // =========================================================

    private LocalTime parseTime() {

        String text =
                timeField
                        .getText()
                        .trim();

        if (text.isEmpty()) {

            showError(
                    "Please enter the appointment time."
            );

            timeField.requestFocus();

            return null;
        }

        try {

            return LocalTime.parse(
                    text,
                    timeFormatter
            );

        } catch (Exception ex) {

            showError(
                    "Invalid time.\n\n"
                            + "Use HH:MM.\n"
                            + "Example: 09:30"
            );

            return null;
        }
    }

    // =========================================================
    // CLINIC TIME
    // =========================================================

    private boolean isClinicTime(
            LocalTime time
    ) {

        LocalTime opening =
                LocalTime.of(
                        8,
                        0
                );

        LocalTime closing =
                LocalTime.of(
                        18,
                        0
                );

        return !time.isBefore(opening)
                &&
                !time.isAfter(closing);
    }

    // =========================================================
    // APPOINTMENT DETAILS
    // =========================================================

    private JTextArea createAppointmentDetails(
            Appointment appointment
    ) {

        String details =
                """
                Appointment Details
                ================================

                Appointment Number : %s
                Patient             : %s
                Address             : %s
                Contact Number      : %s
                Dentist             : %s
                Treatment           : %s
                Appointment Date    : %s
                Appointment Time    : %s
                Status              : %s
                Notes               : %s
                """.formatted(

                        safe(
                                appointment
                                        .getAppointmentNumber()
                        ),

                        safe(
                                appointment
                                        .getPatientName()
                        ),

                        safe(
                                appointment
                                        .getAddress()
                        ),

                        safe(
                                appointment
                                        .getContactNumber()
                        ),

                        safe(
                                appointment
                                        .getDentistName()
                        ),

                        safe(
                                appointment
                                        .getTreatmentName()
                        ),

                        appointment
                                .getAppointmentDate(),

                        appointment
                                .getAppointmentTime(),

                        safe(
                                appointment
                                        .getStatus()
                        ),

                        safe(
                                appointment
                                        .getNotes()
                        )
                );

        JTextArea textArea =
                new JTextArea(
                        details
                );

        textArea.setEditable(
                false
        );

        textArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        13
                )
        );

        textArea.setForeground(
                TEXT_COLOR
        );

        textArea.setBackground(
                Color.WHITE
        );

        textArea.setBorder(
                new EmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

        return textArea;
    }

    // =========================================================
    // CLEAR
    // =========================================================

    private void clearForm() {

        appointmentNumberField.setText("");

        searchField.setText("");

        dateField.setText("");

        timeField.setText("");

        notesArea.setText("");

        appointmentTable.clearSelection();

        if (
                patientComboBox.getItemCount() > 0
        ) {

            patientComboBox.setSelectedIndex(
                    0
            );
        }

        if (
                dentistComboBox.getItemCount() > 0
        ) {

            dentistComboBox.setSelectedIndex(
                    0
            );
        }

        if (
                treatmentComboBox.getItemCount() > 0
        ) {

            treatmentComboBox.setSelectedIndex(
                    0
            );
        }
    }

    // =========================================================
    // CLOSE WINDOW
    // =========================================================

    private void closeWindow() {

        // Close this frame and return to the
        // dashboard that opened it.

        dispose();
    }

    // =========================================================
    // LABEL
    // =========================================================

    private void addLabel(
            JPanel panel,
            GridBagConstraints gbc,
            String text,
            int column,
            int row
    ) {

        gbc.gridx =
                column;

        gbc.gridy =
                row;

        gbc.gridwidth =
                1;

        gbc.weightx =
                0;

        JLabel label =
                new JLabel(
                        text
                );

        label.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(
                TEXT_COLOR
        );

        panel.add(
                label,
                gbc
        );
    }

    // =========================================================
    // COMPONENT
    // =========================================================

    private void addComponent(
            JPanel panel,
            GridBagConstraints gbc,
            Component component,
            int column,
            int row
    ) {

        gbc.gridx =
                column;

        gbc.gridy =
                row;

        gbc.gridwidth =
                1;

        gbc.weightx =
                1.0;

        panel.add(
                component,
                gbc
        );
    }

    // =========================================================
    // ERROR
    // =========================================================

    private void showError(
            String message
    ) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Validation / System Message",
                JOptionPane.WARNING_MESSAGE
        );
    }

    // =========================================================
    // SAFE STRING
    // =========================================================

    private String safe(
            String value
    ) {

        return value == null
                ?
                ""
                :
                value;
    }

    // =========================================================
    // ERROR MESSAGE
    // =========================================================

    private String getErrorMessage(
            Exception ex
    ) {

        if (
                ex.getMessage() != null
                        &&
                        !ex.getMessage()
                                .trim()
                                .isEmpty()
        ) {

            return ex.getMessage();
        }

        return "An unexpected error occurred.";
    }
}