package com.sunrise.dental.ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class DashboardFrame extends JFrame {

    private final int userId;
    private final String username;
    private final String role;

    // =========================================================
    // COLOURS
    // =========================================================

    private final Color PRIMARY_COLOR =
            new Color(34, 93, 120);

    private final Color PRIMARY_LIGHT =
            new Color(225, 240, 246);

    private final Color BACKGROUND_COLOR =
            new Color(245, 249, 252);

    private final Color CARD_COLOR =
            Color.WHITE;

    private final Color TEXT_COLOR =
            new Color(35, 45, 50);

    private final Color SECONDARY_TEXT =
            new Color(95, 105, 110);

    private final Color BORDER_COLOR =
            new Color(210, 220, 225);


    // =========================================================
    // CONSTRUCTOR
    // =========================================================

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
                1150,
                720
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
                        22,
                        35,
                        22,
                        35
                )
        );


        // -----------------------------------------------------
        // CLINIC TITLE
        // -----------------------------------------------------

        JPanel titlePanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                0,
                                5
                        )
                );

        titlePanel.setOpaque(false);


        JLabel clinicLabel =
                new JLabel(
                        "Sunrise Dental Clinic"
                );

        clinicLabel.setForeground(
                Color.WHITE
        );

        clinicLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        27
                )
        );


        JLabel subtitleLabel =
                new JLabel(
                        "Dental Appointment & Patient Management System"
                );

        subtitleLabel.setForeground(
                PRIMARY_LIGHT
        );

        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );


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


        // -----------------------------------------------------
        // USER INFORMATION
        // -----------------------------------------------------

        JPanel userPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                0,
                                4
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
                PRIMARY_LIGHT
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
        // MAIN CONTENT
        // =====================================================

        JPanel contentPanel =
                new JPanel(
                        new BorderLayout()
                );

        contentPanel.setBackground(
                BACKGROUND_COLOR
        );

        contentPanel.setBorder(
                new EmptyBorder(
                        25,
                        40,
                        20,
                        40
                )
        );


        // =====================================================
        // DASHBOARD TITLE
        // =====================================================

        JPanel dashboardTitlePanel =
                new JPanel(
                        new BorderLayout()
                );

        dashboardTitlePanel.setOpaque(false);


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
                TEXT_COLOR
        );


        JLabel dashboardDescription =
                new JLabel(
                        "Select an option below to manage clinic operations."
                );

        dashboardDescription.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        dashboardDescription.setForeground(
                SECONDARY_TEXT
        );


        JPanel titleText =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                0,
                                4
                        )
                );

        titleText.setOpaque(false);

        titleText.add(
                dashboardLabel
        );

        titleText.add(
                dashboardDescription
        );


        dashboardTitlePanel.add(
                titleText,
                BorderLayout.WEST
        );


        contentPanel.add(
                dashboardTitlePanel,
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
                                18,
                                18
                        )
                );

        menuPanel.setOpaque(false);

        menuPanel.setBorder(
                new EmptyBorder(
                        25,
                        0,
                        15,
                        0
                )
        );


        // =====================================================
        // PATIENT MANAGEMENT
        // =====================================================

        JButton patientButton =
                createMenuButton(
                        "Patient Management",
                        "Register and manage patients",
                        "P"
                );

        patientButton.addActionListener(
                e -> openPatientManagement()
        );


        // =====================================================
        // APPOINTMENT MANAGEMENT
        // =====================================================

        JButton appointmentButton =
                createMenuButton(
                        "Appointment Management",
                        "Register and search appointments",
                        "A"
                );

        appointmentButton.addActionListener(
                e -> openAppointmentManagement()
        );


        // =====================================================
        // DENTISTS
        // =====================================================

        JButton dentistButton =
                createMenuButton(
                        "Dentists",
                        "View and manage clinic dentists",
                        "D"
                );

        dentistButton.addActionListener(
                e -> openDentistManagement()
        );


        // =====================================================
        // TREATMENTS
        // =====================================================

        JButton treatmentButton =
                createMenuButton(
                        "Treatments",
                        "View and manage dental treatments",
                        "T"
                );

        treatmentButton.addActionListener(
                e -> openTreatmentManagement()
        );


        // =====================================================
        // BILLING
        // =====================================================

        JButton billingButton =
                createMenuButton(
                        "Billing",
                        "Calculate and print patient bills",
                        "B"
                );

        billingButton.addActionListener(
                e -> openBillingManagement()
        );


        // =====================================================
        // REPORTS
        // =====================================================

        JButton reportsButton =
                createMenuButton(
                        "Reports",
                        "View clinic reports",
                        "R"
                );

        reportsButton.addActionListener(
                e -> openReportManagement()
        );


        // =====================================================
        // HELP
        // =====================================================

        JButton helpButton =
                createMenuButton(
                        "Help",
                        "System usage instructions",
                        "H"
                );

        helpButton.addActionListener(
                e -> showHelp()
        );


        // =====================================================
        // LOGOUT
        // =====================================================

        JButton logoutButton =
                createMenuButton(
                        "Logout",
                        "Return to login screen",
                        "L"
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
                        new BorderLayout()
                );

        footerPanel.setBackground(
                new Color(
                        230,
                        238,
                        243
                )
        );

        footerPanel.setBorder(
                new EmptyBorder(
                        10,
                        30,
                        10,
                        30
                )
        );


        JLabel footerLabel =
                new JLabel(
                        "Sunrise Dental Clinic"
                );

        footerLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        12
                )
        );

        footerLabel.setForeground(
                PRIMARY_COLOR
        );


        JLabel footerRight =
                new JLabel(
                        "Appointment & Patient Management System"
                );

        footerRight.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        footerRight.setForeground(
                SECONDARY_TEXT
        );


        footerPanel.add(
                footerLabel,
                BorderLayout.WEST
        );

        footerPanel.add(
                footerRight,
                BorderLayout.EAST
        );


        mainPanel.add(
                footerPanel,
                BorderLayout.SOUTH
        );


        // =====================================================
        // SET CONTENT
        // =====================================================

        setContentPane(
                mainPanel
        );
    }


    // =========================================================
    // CREATE MENU BUTTON
    // =========================================================

    private JButton createMenuButton(
            String title,
            String description,
            String iconText
    ) {

        JButton button =
                new JButton();

        button.setLayout(
                new BorderLayout(
                        15,
                        0
                )
        );

        button.setBackground(
                CARD_COLOR
        );

        button.setForeground(
                TEXT_COLOR
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
                                BORDER_COLOR,
                                1
                        ),
                        new EmptyBorder(
                                18,
                                18,
                                18,
                                18
                        )
                )
        );


        // =====================================================
        // ICON
        // =====================================================

        JLabel iconLabel =
                new JLabel(
                        iconText
                );

        iconLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        iconLabel.setVerticalAlignment(
                SwingConstants.CENTER
        );

        iconLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        23
                )
        );

        iconLabel.setForeground(
                PRIMARY_COLOR
        );

        iconLabel.setBackground(
                PRIMARY_LIGHT
        );

        iconLabel.setOpaque(
                true
        );

        iconLabel.setPreferredSize(
                new Dimension(
                        52,
                        52
                )
        );


        button.add(
                iconLabel,
                BorderLayout.WEST
        );


        // =====================================================
        // TEXT
        // =====================================================

        JPanel textPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                0,
                                5
                        )
                );

        textPanel.setOpaque(false);


        JLabel titleLabel =
                new JLabel(
                        title
                );

        titleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        17
                )
        );

        titleLabel.setForeground(
                TEXT_COLOR
        );


        JLabel descriptionLabel =
                new JLabel(
                        description
                );

        descriptionLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        12
                )
        );

        descriptionLabel.setForeground(
                SECONDARY_TEXT
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


        // =====================================================
        // HOVER EFFECT
        // =====================================================

        button.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                new Color(
                                        238,
                                        247,
                                        250
                                )
                        );

                        button.setBorder(
                                BorderFactory.createCompoundBorder(
                                        BorderFactory.createLineBorder(
                                                PRIMARY_COLOR,
                                                2
                                        ),
                                        new EmptyBorder(
                                                17,
                                                17,
                                                17,
                                                17
                                        )
                                )
                        );

                        iconLabel.setBackground(
                                Color.WHITE
                        );
                    }


                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        button.setBackground(
                                CARD_COLOR
                        );

                        button.setBorder(
                                BorderFactory.createCompoundBorder(
                                        BorderFactory.createLineBorder(
                                                BORDER_COLOR,
                                                1
                                        ),
                                        new EmptyBorder(
                                                18,
                                                18,
                                                18,
                                                18
                                        )
                                )
                        );

                        iconLabel.setBackground(
                                PRIMARY_LIGHT
                        );
                    }
                }
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

        JDialog helpDialog =
                new JDialog(
                        this,
                        "Sunrise Dental Clinic - Help & User Guide",
                        true
                );

        helpDialog.setSize(
                900,
                700
        );

        helpDialog.setLocationRelativeTo(
                this
        );

        helpDialog.setDefaultCloseOperation(
                JDialog.DISPOSE_ON_CLOSE
        );


        // =====================================================
        // MAIN HELP PANEL
        // =====================================================

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                BACKGROUND_COLOR
        );


        // =====================================================
        // HELP HEADER
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
                        22,
                        30,
                        22,
                        30
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

        headerText.setOpaque(false);


        JLabel titleLabel =
                new JLabel(
                        "Sunrise Dental Clinic"
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
                        "Help & User Guide"
                );

        subtitleLabel.setForeground(
                PRIMARY_LIGHT
        );

        subtitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        15
                )
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


        // =====================================================
        // HELP ICON - H
        // =====================================================

        JLabel helpIcon =
                new JLabel(
                        "H"
                );

        helpIcon.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        helpIcon.setVerticalAlignment(
                SwingConstants.CENTER
        );

        helpIcon.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        28
                )
        );

        helpIcon.setForeground(
                PRIMARY_COLOR
        );

        helpIcon.setBackground(
                Color.WHITE
        );

        helpIcon.setOpaque(
                true
        );

        helpIcon.setPreferredSize(
                new Dimension(
                        52,
                        52
                )
        );

        helpIcon.setBorder(
                BorderFactory.createLineBorder(
                        Color.WHITE,
                        1
                )
        );


        headerPanel.add(
                helpIcon,
                BorderLayout.EAST
        );


        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // INTRODUCTION
        // =====================================================

        JPanel introPanel =
                new JPanel(
                        new BorderLayout()
                );

        introPanel.setBackground(
                Color.WHITE
        );

        introPanel.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        18,
                        25
                )
        );


        JLabel introTitle =
                new JLabel(
                        "How to use the system"
                );

        introTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        20
                )
        );

        introTitle.setForeground(
                PRIMARY_COLOR
        );


        JLabel introText =
                new JLabel(
                        "<html>"
                                + "Use this guide to understand the main functions "
                                + "available in the Sunrise Dental Clinic Management System."
                                + "</html>"
                );

        introText.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        introText.setForeground(
                SECONDARY_TEXT
        );


        JPanel introContent =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                0,
                                6
                        )
                );

        introContent.setOpaque(false);

        introContent.add(
                introTitle
        );

        introContent.add(
                introText
        );


        introPanel.add(
                introContent,
                BorderLayout.CENTER
        );


        // =====================================================
        // HELP CONTENT
        // =====================================================

        JPanel helpContent =
                new JPanel();

        helpContent.setLayout(
                new BoxLayout(
                        helpContent,
                        BoxLayout.Y_AXIS
                )
        );

        helpContent.setBackground(
                BACKGROUND_COLOR
        );

        helpContent.setBorder(
                new EmptyBorder(
                        10,
                        25,
                        20,
                        25
                )
        );


        helpContent.add(
                createHelpCard(
                        "1. Login",
                        "Enter your authorized username and password "
                                + "to access the system. Only registered users "
                                + "can access the dashboard."
                )
        );

        helpContent.add(
                Box.createVerticalStrut(12)
        );


        helpContent.add(
                createHelpCard(
                        "2. Patient Management",
                        "Use Patient Management to register new patients "
                                + "and manage existing patient information."
                )
        );

        helpContent.add(
                Box.createVerticalStrut(12)
        );


        helpContent.add(
                createHelpCard(
                        "3. Appointments",
                        "Use Appointments to register new appointments "
                                + "and search for appointment information using "
                                + "the appointment number."
                )
        );

        helpContent.add(
                Box.createVerticalStrut(12)
        );


        helpContent.add(
                createHelpCard(
                        "4. Dentists",
                        "Use the Dentists section to view and manage "
                                + "dentist information available at the clinic."
                )
        );

        helpContent.add(
                Box.createVerticalStrut(12)
        );


        helpContent.add(
                createHelpCard(
                        "5. Treatments",
                        "Use Treatments to view and manage available "
                                + "dental treatments and their information."
                )
        );

        helpContent.add(
                Box.createVerticalStrut(12)
        );


        helpContent.add(
                createHelpCard(
                        "6. Billing",
                        "Use Billing to calculate treatment charges, "
                                + "consultation fees and generate the patient's bill."
                )
        );

        helpContent.add(
                Box.createVerticalStrut(12)
        );


        helpContent.add(
                createHelpCard(
                        "7. Reports",
                        "Use Reports to view relevant clinic information "
                                + "such as appointments, patients and billing details."
                )
        );

        helpContent.add(
                Box.createVerticalStrut(12)
        );


        helpContent.add(
                createHelpCard(
                        "8. Logout",
                        "Select Logout when you have finished using "
                                + "the system. You will be safely returned "
                                + "to the login screen."
                )
        );


        // =====================================================
        // SCROLL PANE
        // =====================================================

        JScrollPane scrollPane =
                new JScrollPane(
                        helpContent
                );

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder()
        );

        scrollPane.setBackground(
                BACKGROUND_COLOR
        );

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(
                        16
                );


        // =====================================================
        // CENTER PANEL
        // =====================================================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout()
                );

        centerPanel.setOpaque(false);

        centerPanel.add(
                introPanel,
                BorderLayout.NORTH
        );

        centerPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );


        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // HELP FOOTER
        // =====================================================

        JPanel footerPanel =
                new JPanel(
                        new BorderLayout()
                );

        footerPanel.setBackground(
                Color.WHITE
        );

        footerPanel.setBorder(
                new EmptyBorder(
                        12,
                        20,
                        12,
                        20
                )
        );


        // =====================================================
        // CLOSE BUTTON
        // =====================================================

        JButton closeButton =
                new JButton(
                        "Close"
                );

        closeButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );

        // BLACK TEXT
        closeButton.setForeground(
                Color.BLACK
        );

        closeButton.setBackground(
                PRIMARY_COLOR
        );

        closeButton.setFocusPainted(
                false
        );

        closeButton.setOpaque(
                true
        );

        closeButton.setContentAreaFilled(
                true
        );

        closeButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        // BLACK BORDER
        closeButton.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                Color.BLACK,
                                2
                        ),
                        BorderFactory.createEmptyBorder(
                                10,
                                35,
                                10,
                                35
                        )
                )
        );


        // =====================================================
        // CLOSE BUTTON HOVER EFFECT
        // =====================================================

        closeButton.addMouseListener(
                new MouseAdapter() {

                    @Override
                    public void mouseEntered(
                            MouseEvent e
                    ) {

                        closeButton.setBackground(
                                new Color(
                                        25,
                                        72,
                                        95
                                )
                        );

                        // Keep text BLACK
                        closeButton.setForeground(
                                Color.BLACK
                        );
                    }


                    @Override
                    public void mouseExited(
                            MouseEvent e
                    ) {

                        closeButton.setBackground(
                                PRIMARY_COLOR
                        );

                        // Keep text BLACK
                        closeButton.setForeground(
                                Color.BLACK
                        );
                    }
                }
        );


        closeButton.addActionListener(
                e -> helpDialog.dispose()
        );


        footerPanel.add(
                closeButton,
                BorderLayout.EAST
        );


        mainPanel.add(
                footerPanel,
                BorderLayout.SOUTH
        );


        // =====================================================
        // SET HELP CONTENT
        // =====================================================

        helpDialog.setContentPane(
                mainPanel
        );

        helpDialog.setVisible(
                true
        );
    }


    // =========================================================
    // CREATE HELP CARD
    // =========================================================

    private JPanel createHelpCard(
            String title,
            String description
    ) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(
                Color.WHITE
        );


        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER_COLOR,
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


        card.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        100
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
                        16
                )
        );

        titleLabel.setForeground(
                PRIMARY_COLOR
        );


        JLabel descriptionLabel =
                new JLabel(
                        "<html>"
                                + "<div style='width:700px;'>"
                                + description
                                + "</div>"
                                + "</html>"
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
                        65,
                        65,
                        65
                )
        );


        JPanel textPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1,
                                0,
                                6
                        )
                );

        textPanel.setOpaque(false);


        textPanel.add(
                titleLabel
        );

        textPanel.add(
                descriptionLabel
        );


        card.add(
                textPanel,
                BorderLayout.CENTER
        );


        return card;
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