package com.sunrise.dental.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private final int userId;
    private final String username;
    private final String role;

    public DashboardFrame(
            int userId,
            String username,
            String role
    ) {

        this.userId = userId;
        this.username = username;
        this.role = role;

        setTitle(
                "Sunrise Dental Clinic - Dashboard"
        );

        setSize(
                1100,
                700
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        createInterface();
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

        headerPanel.setBackground(
                new Color(
                        34,
                        93,
                        120
                )
        );

        headerPanel.setBorder(
                new EmptyBorder(
                        20,
                        30,
                        20,
                        30
                )
        );


        JLabel clinicLabel =
                new JLabel(
                        "SUNRISE DENTAL CLINIC"
                );

        clinicLabel.setForeground(
                Color.WHITE
        );

        clinicLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        26
                )
        );


        JLabel subtitleLabel =
                new JLabel(
                        "Dental Appointment & Patient Management System"
                );

        subtitleLabel.setForeground(
                Color.WHITE
        );

        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );


        JPanel titlePanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        titlePanel.setOpaque(false);

        titlePanel.add(
                clinicLabel
        );

        titlePanel.add(
                subtitleLabel
        );

        headerPanel.add(
                titlePanel,
                BorderLayout.WEST
        );


        // =====================================================
        // USER INFORMATION
        // =====================================================

        JPanel userPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        userPanel.setOpaque(false);


        JLabel welcomeLabel =
                new JLabel(
                        "Welcome, " + username
                );

        welcomeLabel.setForeground(
                Color.WHITE
        );

        welcomeLabel.setHorizontalAlignment(
                SwingConstants.RIGHT
        );

        welcomeLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );


        JLabel roleLabel =
                new JLabel(
                        "Role: " + role
                );

        roleLabel.setForeground(
                Color.WHITE
        );

        roleLabel.setHorizontalAlignment(
                SwingConstants.RIGHT
        );

        roleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );


        userPanel.add(
                welcomeLabel
        );

        userPanel.add(
                roleLabel
        );

        headerPanel.add(
                userPanel,
                BorderLayout.EAST
        );


        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // CENTER CONTENT
        // =====================================================

        JPanel contentPanel =
                new JPanel(
                        new BorderLayout()
                );

        contentPanel.setOpaque(false);

        contentPanel.setBorder(
                new EmptyBorder(
                        30,
                        40,
                        20,
                        40
                )
        );


        JLabel dashboardLabel =
                new JLabel(
                        "Clinic Dashboard"
                );

        dashboardLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        25
                )
        );

        dashboardLabel.setForeground(
                Color.BLACK
        );

        contentPanel.add(
                dashboardLabel,
                BorderLayout.NORTH
        );


        // =====================================================
        // MENU PANEL
        // =====================================================

        JPanel menuPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                4,
                                20,
                                20
                        )
                );

        menuPanel.setOpaque(false);

        menuPanel.setBorder(
                new EmptyBorder(
                        30,
                        0,
                        30,
                        0
                )
        );


        // =====================================================
        // PATIENT MANAGEMENT
        // =====================================================

        JButton patientButton =
                createMenuButton(
                        "PATIENT MANAGEMENT",
                        "Register and manage patients"
                );

        patientButton.addActionListener(
                e -> openPatientManagement()
        );


        // =====================================================
        // APPOINTMENTS
        // =====================================================

        JButton appointmentButton =
                createMenuButton(
                        "APPOINTMENTS",
                        "Register and search appointments"
                );

        appointmentButton.addActionListener(
                e -> openAppointmentManagement()
        );


        // =====================================================
        // DENTISTS
        // =====================================================

        JButton dentistButton =
                createMenuButton(
                        "DENTISTS",
                        "View and manage clinic dentists"
                );

        dentistButton.addActionListener(
                e -> openDentistManagement()
        );


        // =====================================================
        // TREATMENTS
        // =====================================================

        JButton treatmentButton =
                createMenuButton(
                        "TREATMENTS",
                        "View and manage treatments"
                );

        treatmentButton.addActionListener(
                e -> openTreatmentManagement()
        );


        // =====================================================
        // BILLING
        // =====================================================

        JButton billingButton =
                createMenuButton(
                        "BILLING",
                        "Calculate and print patient bills"
                );

        billingButton.addActionListener(
                e -> openBillingManagement()
        );


        // =====================================================
        // REPORTS
        // =====================================================

        JButton reportsButton =
                createMenuButton(
                        "REPORTS",
                        "View clinic reports"
                );

        reportsButton.addActionListener(
                e -> openReportManagement()
        );


        // =====================================================
        // HELP
        // =====================================================

        JButton helpButton =
                createMenuButton(
                        "HELP",
                        "System usage instructions"
                );

        helpButton.addActionListener(
                e -> showHelp()
        );


        // =====================================================
        // LOGOUT
        // =====================================================

        JButton logoutButton =
                createMenuButton(
                        "LOGOUT",
                        "Return to login screen"
                );

        logoutButton.addActionListener(
                e -> logout()
        );


        // =====================================================
        // ADD BUTTONS
        // =====================================================

        menuPanel.add(
                patientButton
        );

        menuPanel.add(
                appointmentButton
        );

        menuPanel.add(
                dentistButton
        );

        menuPanel.add(
                treatmentButton
        );

        menuPanel.add(
                billingButton
        );

        menuPanel.add(
                reportsButton
        );

        menuPanel.add(
                helpButton
        );

        menuPanel.add(
                logoutButton
        );


        contentPanel.add(
                menuPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // FOOTER
        // =====================================================

        JPanel footerPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER
                        )
                );

        footerPanel.setBackground(
                new Color(
                        230,
                        238,
                        243
                )
        );


        JLabel footerLabel =
                new JLabel(
                        "Sunrise Dental Clinic | "
                                + "Appointment & Patient Management System"
                );

        footerLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        footerLabel.setForeground(
                Color.BLACK
        );

        footerPanel.add(
                footerLabel
        );

        mainPanel.add(
                footerPanel,
                BorderLayout.SOUTH
        );


        setContentPane(
                mainPanel
        );
    }


    // =========================================================
    // CREATE MENU BUTTON
    // =========================================================

    private JButton createMenuButton(
            String title,
            String description
    ) {

        JButton button =
                new JButton();

        button.setLayout(
                new BorderLayout()
        );

        button.setBackground(
                Color.WHITE
        );

        button.setForeground(
                Color.BLACK
        );

        button.setFocusPainted(
                false
        );

        button.setOpaque(
                true
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );


        button.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        160,
                                        175,
                                        185
                                ),
                                1
                        ),
                        new EmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );


        JLabel titleLabel =
                new JLabel(
                        title
                );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        19
                )
        );

        titleLabel.setForeground(
                Color.BLACK
        );


        JLabel descriptionLabel =
                new JLabel(
                        description
                );

        descriptionLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        descriptionLabel.setForeground(
                new Color(
                        70,
                        70,
                        70
                )
        );


        JPanel textPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        textPanel.setOpaque(
                false
        );

        textPanel.add(
                titleLabel
        );

        textPanel.add(
                descriptionLabel
        );


        button.add(
                textPanel,
                BorderLayout.CENTER
        );


        return button;
    }


    // =========================================================
    // PATIENT MANAGEMENT
    // =========================================================

    private void openPatientManagement() {

        try {

            PatientFrame patientFrame =
                    new PatientFrame();

            patientFrame.setLocationRelativeTo(
                    this
            );

            patientFrame.setVisible(
                    true
            );

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to open Patient Management.\n\n"
                            + ex.getMessage(),
                    "Patient Management Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================================================
    // APPOINTMENT MANAGEMENT
    // =========================================================

    private void openAppointmentManagement() {

        try {

            AppointmentFrame appointmentFrame =
                    new AppointmentFrame();

            appointmentFrame.setLocationRelativeTo(
                    this
            );

            appointmentFrame.setVisible(
                    true
            );

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to open Appointment Management.\n\n"
                            + ex.getMessage(),
                    "Appointment Management Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================================================
    // DENTIST MANAGEMENT
    // =========================================================

    private void openDentistManagement() {

        try {

            DentistFrame dentistFrame =
                    new DentistFrame();

            dentistFrame.setLocationRelativeTo(
                    this
            );

            dentistFrame.setVisible(
                    true
            );

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to open Dentist Management.\n\n"
                            + ex.getMessage(),
                    "Dentist Management Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================================================
    // TREATMENT MANAGEMENT
    // =========================================================

    private void openTreatmentManagement() {

        try {

            TreatmentFrame treatmentFrame =
                    new TreatmentFrame();

            treatmentFrame.setLocationRelativeTo(
                    this
            );

            treatmentFrame.setVisible(
                    true
            );

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to open Treatment Management.\n\n"
                            + ex.getMessage(),
                    "Treatment Management Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================================================
    // BILLING MANAGEMENT
    // =========================================================

    private void openBillingManagement() {

        try {

            BillingFrame billingFrame =
                    new BillingFrame();

            billingFrame.setLocationRelativeTo(
                    this
            );

            billingFrame.setVisible(
                    true
            );

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to open Billing Management.\n\n"
                            + ex.getMessage(),
                    "Billing Management Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================================================
    // REPORT MANAGEMENT
    // =========================================================

    private void openReportManagement() {

        try {

            ReportFrame reportFrame =
                    new ReportFrame(
                            userId,
                            username,
                            role
                    );

            reportFrame.setLocationRelativeTo(
                    this
            );

            reportFrame.setVisible(
                    true
            );

        } catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to open Reports.\n\n"
                            + ex.getMessage(),
                    "Reports Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }


    // =========================================================
    // HELP
    // =========================================================

    private void showHelp() {

        String helpText =
                """
                SUNRISE DENTAL CLINIC
                SYSTEM HELP

                1. Login
                   Enter your authorized username and password.

                2. Patient Management
                   Register and manage patient information.

                3. Appointments
                   Register appointments and search appointment
                   details using the appointment number.

                4. Dentists
                   View available dentists.

                5. Treatments
                   Register, view and manage treatment information.

                6. Billing
                   Calculate the treatment cost and consultation fee.

                7. Reports
                   View appointment, patient and billing reports.

                8. Logout
                   Safely return to the login screen.

                9. Exit
                   Close the application safely.
                """;


        JTextArea textArea =
                new JTextArea(
                        helpText
                );

        textArea.setEditable(
                false
        );

        textArea.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        textArea.setForeground(
                Color.BLACK
        );

        textArea.setBackground(
                Color.WHITE
        );

        textArea.setLineWrap(
                true
        );

        textArea.setWrapStyleWord(
                true
        );

        textArea.setBorder(
                new EmptyBorder(
                        15,
                        15,
                        15,
                        15
                )
        );


        JScrollPane scrollPane =
                new JScrollPane(
                        textArea
                );

        scrollPane.setPreferredSize(
                new Dimension(
                        600,
                        450
                )
        );


        JOptionPane.showMessageDialog(
                this,
                scrollPane,
                "System Help",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    // =========================================================
    // LOGOUT
    // =========================================================

    private void logout() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to logout?",
                        "Confirm Logout",
                        JOptionPane.YES_NO_OPTION
                );


        if (
                result ==
                        JOptionPane.YES_OPTION
        ) {

            dispose();

            LoginFrame loginFrame =
                    new LoginFrame();

            loginFrame.setVisible(
                    true
            );
        }
    }
}
