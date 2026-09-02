package com.sunrise.dental.ui;

import com.sunrise.dental.api.ApiClient;
import com.sunrise.dental.model.LoginResponse;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class LoginFrame extends JFrame {

    // =========================================================
    // COLOURS
    // =========================================================

    private static final Color PRIMARY =
            new Color(31, 122, 140);

    private static final Color PRIMARY_DARK =
            new Color(24, 94, 108);

    private static final Color DARK =
            new Color(35, 55, 65);

    private static final Color LIGHT =
            new Color(245, 250, 252);

    private static final Color WHITE =
            Color.WHITE;

    private static final Color GREY =
            new Color(110, 120, 125);

    private static final Color BORDER =
            new Color(205, 215, 220);

    // =========================================================
    // COMPONENTS
    // =========================================================

    private JTextField usernameField;

    private JPasswordField passwordField;

    private JButton loginButton;

    private JLabel statusLabel;

    private final ApiClient apiClient;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public LoginFrame() {

        apiClient = new ApiClient();

        initializeFrame();

        createInterface();
    }

    // =========================================================
    // FRAME SETTINGS
    // =========================================================

    private void initializeFrame() {

        setTitle(
                "Sunrise Dental Clinic - Login"
        );

        setSize(
                900,
                560
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setResizable(false);
    }

    // =========================================================
    // CREATE INTERFACES
    // =========================================================

    private void createInterface() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                LIGHT
        );

        // =====================================================
        // LEFT CLINIC PANEL
        // =====================================================

        JPanel leftPanel =
                new JPanel();

        leftPanel.setPreferredSize(
                new Dimension(
                        360,
                        560
                )
        );

        leftPanel.setBackground(
                PRIMARY
        );

        leftPanel.setLayout(
                new BoxLayout(
                        leftPanel,
                        BoxLayout.Y_AXIS
                )
        );

        leftPanel.setBorder(
                new EmptyBorder(
                        65,
                        45,
                        45,
                        45
                )
        );

        // Dental symbol

        JLabel icon =
                new JLabel("✚");

        icon.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        65
                )
        );

        icon.setForeground(
                WHITE
        );

        icon.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        leftPanel.add(icon);

        leftPanel.add(
                Box.createVerticalStrut(20)
        );

        // Sunrise

        JLabel clinicName =
                new JLabel(
                        "SUNRISE"
                );

        clinicName.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        32
                )
        );

        clinicName.setForeground(
                WHITE
        );

        clinicName.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        leftPanel.add(clinicName);

        // Dental Clinic

        JLabel clinicType =
                new JLabel(
                        "DENTAL CLINIC"
                );

        clinicType.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        20
                )
        );

        clinicType.setForeground(
                WHITE
        );

        clinicType.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        leftPanel.add(clinicType);

        leftPanel.add(
                Box.createVerticalStrut(35)
        );

        // Description

        JLabel description =
                new JLabel(
                        "<html><center>"
                                + "Appointment & Patient<br>"
                                + "Management System"
                                + "</center></html>"
                );

        description.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        15
                )
        );

        description.setForeground(
                WHITE
        );

        description.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        leftPanel.add(description);

        leftPanel.add(
                Box.createVerticalGlue()
        );

        JLabel location =
                new JLabel(
                        "Colombo, Sri Lanka"
                );

        location.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        location.setForeground(
                WHITE
        );

        location.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        leftPanel.add(location);

        // =====================================================
        // RIGHT LOGIN PANEL
        // =====================================================

        JPanel rightPanel =
                new JPanel(
                        new GridBagLayout()
                );

        rightPanel.setBackground(
                WHITE
        );

        JPanel loginPanel =
                new JPanel();

        loginPanel.setBackground(
                WHITE
        );

        loginPanel.setLayout(
                new BoxLayout(
                        loginPanel,
                        BoxLayout.Y_AXIS
                )
        );

        loginPanel.setBorder(
                new EmptyBorder(
                        30,
                        60,
                        30,
                        60
                )
        );

        // =====================================================
        // TITLE
        // =====================================================

        JLabel title =
                new JLabel(
                        "Welcome Back"
                );

        title.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        30
                )
        );

        title.setForeground(
                DARK
        );

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        loginPanel.add(title);

        loginPanel.add(
                Box.createVerticalStrut(8)
        );

        JLabel subtitle =
                new JLabel(
                        "Sign in to access the clinic system"
                );

        subtitle.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setForeground(
                GREY
        );

        subtitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        loginPanel.add(subtitle);

        loginPanel.add(
                Box.createVerticalStrut(35)
        );

        // =====================================================
        // USERNAME LABEL
        // =====================================================

        JLabel usernameLabel =
                new JLabel(
                        "Username"
                );

        usernameLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        usernameLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        loginPanel.add(usernameLabel);

        loginPanel.add(
                Box.createVerticalStrut(7)
        );

        // =====================================================
        // USERNAME FIELD
        // =====================================================

        usernameField =
                new JTextField();

        usernameField.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        15
                )
        );

        usernameField.setPreferredSize(
                new Dimension(
                        330,
                        42
                )
        );

        usernameField.setMaximumSize(
                new Dimension(
                        330,
                        42
                )
        );

        usernameField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                5,
                                10,
                                5,
                                10
                        )
                )
        );

        loginPanel.add(
                usernameField
        );

        loginPanel.add(
                Box.createVerticalStrut(20)
        );

        // =====================================================
        // PASSWORD LABEL
        // =====================================================

        JLabel passwordLabel =
                new JLabel(
                        "Password"
                );

        passwordLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        14
                )
        );

        passwordLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        loginPanel.add(
                passwordLabel
        );

        loginPanel.add(
                Box.createVerticalStrut(7)
        );

        // =====================================================
        // PASSWORD FIELD
        // =====================================================

        passwordField =
                new JPasswordField();

        passwordField.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        15
                )
        );

        passwordField.setPreferredSize(
                new Dimension(
                        330,
                        42
                )
        );

        passwordField.setMaximumSize(
                new Dimension(
                        330,
                        42
                )
        );

        passwordField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                5,
                                10,
                                5,
                                10
                        )
                )
        );

        loginPanel.add(
                passwordField
        );

        loginPanel.add(
                Box.createVerticalStrut(25)
        );

        // =====================================================
        // LOGIN BUTTON
        // =====================================================

        loginButton =
                new JButton(
                        "LOGIN"
                );

        loginButton.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        15
                )
        );

        loginButton.setForeground(
                WHITE
        );

        loginButton.setBackground(
                PRIMARY
        );

        loginButton.setFocusPainted(
                false
        );

        loginButton.setBorderPainted(
                false
        );

        loginButton.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        loginButton.setPreferredSize(
                new Dimension(
                        330,
                        45
                )
        );

        loginButton.setMaximumSize(
                new Dimension(
                        330,
                        45
                )
        );

        loginPanel.add(
                loginButton
        );

        loginPanel.add(
                Box.createVerticalStrut(15)
        );

        // =====================================================
        // STATUS
        // =====================================================

        statusLabel =
                new JLabel(
                        " "
                );

        statusLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        statusLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        loginPanel.add(
                statusLabel
        );

        loginPanel.add(
                Box.createVerticalStrut(15)
        );

        JLabel securityText =
                new JLabel(
                        "Authorized staff only"
                );

        securityText.setFont(
                new Font(
                        "SansSerif",
                        Font.ITALIC,
                        12
                )
        );

        securityText.setForeground(
                GREY
        );

        securityText.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        loginPanel.add(
                securityText
        );

        rightPanel.add(
                loginPanel
        );

        // =====================================================
        // ADD PANELS
        // =====================================================

        mainPanel.add(
                leftPanel,
                BorderLayout.WEST
        );

        mainPanel.add(
                rightPanel,
                BorderLayout.CENTER
        );

        add(mainPanel);

        // =====================================================
        // EVENTS
        // =====================================================

        loginButton.addActionListener(
                e -> performLogin()
        );

        passwordField.addActionListener(
                e -> performLogin()
        );

        usernameField.addActionListener(
                e -> passwordField.requestFocus()
        );
    }

    // =========================================================
    // PERFORM LOGIN
    // =========================================================

    private void performLogin() {

        String username =
                usernameField
                        .getText()
                        .trim();

        String password =
                new String(
                        passwordField
                                .getPassword()
                );

        // =====================================================
        // VALIDATION
        // =====================================================

        if (username.isEmpty()) {

            showError(
                    "Please enter your username."
            );

            usernameField.requestFocus();

            return;
        }

        if (password.isEmpty()) {

            showError(
                    "Please enter your password."
            );

            passwordField.requestFocus();

            return;
        }

        // =====================================================
        // DISABLE LOGIN
        // =====================================================

        loginButton.setEnabled(
                false
        );

        loginButton.setText(
                "LOGGING IN..."
        );

        statusLabel.setForeground(
                PRIMARY
        );

        statusLabel.setText(
                "Connecting to server..."
        );

        // =====================================================
        // BACKGROUND LOGIN
        // =====================================================

        SwingWorker<LoginResponse, Void>
                worker =
                new SwingWorker<>() {

                    @Override
                    protected LoginResponse
                    doInBackground()
                            throws Exception {

                        return apiClient.login(
                                username,
                                password
                        );
                    }

                    @Override
                    protected void done() {

                        loginButton.setEnabled(
                                true
                        );

                        loginButton.setText(
                                "LOGIN"
                        );

                        try {

                            LoginResponse response =
                                    get();

                            if (response == null) {

                                showError(
                                        "No response received from the server."
                                );

                                return;
                            }

                            // =================================================
                            // LOGIN SUCCESS
                            // =================================================

                            statusLabel.setForeground(
                                    new Color(
                                            30,
                                            130,
                                            75
                                    )
                            );

                            statusLabel.setText(
                                    "Login successful."
                            );

                            System.out.println(
                                    "=================================="
                            );

                            System.out.println(
                                    "LOGIN SUCCESS"
                            );

                            System.out.println(
                                    "User ID: "
                                            + response.getUserId()
                            );

                            System.out.println(
                                    "Username: "
                                            + response.getUsername()
                            );

                            System.out.println(
                                    "Role: "
                                            + response.getRole()
                            );

                            System.out.println(
                                    "=================================="
                            );

                            JOptionPane.showMessageDialog(
                                    LoginFrame.this,

                                    "Welcome, "
                                            + response.getUsername()
                                            + "!\n\n"
                                            + "Role: "
                                            + response.getRole(),

                                    "Login Successful",

                                    JOptionPane.INFORMATION_MESSAGE
                            );

                            openDashboard(
                                    response
                            );

                        }

                        catch (Exception ex) {

                            showError(
                                    getFriendlyErrorMessage(
                                            ex
                                    )
                            );

                            ex.printStackTrace();
                        }
                    }
                };

        worker.execute();
    }

    // =========================================================
    // OPEN DASHBOARD
    // =========================================================

    private void openDashboard(
            LoginResponse response) {

        try {

            System.out.println(
                    "Opening dashboard..."
            );

            DashboardFrame dashboard =
                    new DashboardFrame(
                            response.getUserId(),
                            response.getUsername(),
                            response.getRole()
                    );

            dashboard.setVisible(
                    true
            );

            dispose();

        }

        catch (Exception ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    LoginFrame.this,

                    "Login was successful, but the dashboard "
                            + "could not be opened.\n\n"
                            + ex.getMessage(),

                    "Dashboard Error",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // ERROR MESSAGE
    // =========================================================

    private void showError(
            String message) {

        if (message == null ||
                message.isBlank()) {

            message =
                    "Login failed. Please try again.";
        }

        statusLabel.setForeground(
                new Color(
                        190,
                        50,
                        50
                )
        );

        statusLabel.setText(
                "<html><center>"
                        + message
                        + "</center></html>"
        );

        JOptionPane.showMessageDialog(
                this,

                message,

                "Login Error",

                JOptionPane.ERROR_MESSAGE
        );
    }

    // =========================================================
    // FRIENDLY CONNECTION ERROR
    // =========================================================

    private String getFriendlyErrorMessage(
            Exception ex) {

        Throwable cause =
                ex;

        while (cause.getCause() != null) {

            cause =
                    cause.getCause();
        }

        String message =
                cause.getMessage();

        if (message == null) {

            return "Unable to connect to the server.";
        }

        if (message.toLowerCase().contains(
                "connection refused"
        )) {

            return "Cannot connect to the backend server.\n\n"
                    + "Please make sure Server.java "
                    + "is running on port 8080.";
        }

        if (message.toLowerCase().contains(
                "timed out"
        )) {

            return "The server took too long to respond.";
        }

        if (message.contains("500")) {

            return "The server encountered an internal error.\n"
                    + "Please check the backend console.";
        }

        return message;
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(
            String[] args) {

        SwingUtilities.invokeLater(() -> {

            try {

                UIManager.setLookAndFeel(
                        UIManager
                                .getSystemLookAndFeelClassName()
                );

            }

            catch (Exception ignored) {
            }

            LoginFrame frame =
                    new LoginFrame();

            frame.setVisible(
                    true
            );
        });
    }
}