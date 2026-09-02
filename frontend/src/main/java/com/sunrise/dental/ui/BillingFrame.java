package com.sunrise.dental.ui;

import com.sunrise.dental.api.ApiClient;
import com.sunrise.dental.model.Appointment;
import com.sunrise.dental.model.Bill;

import javax.swing.*;
import java.awt.*;
import java.awt.print.PrinterException;
import java.text.MessageFormat;

/**
 * =========================================================
 * BILLING FRAME
 * =========================================================
 *
 * Frontend screen for:
 *
 * 1. Search appointment
 * 2. Calculate bill
 * 3. Save bill
 * 4. Print bill
 * 5. Clear form
 *
 * This class communicates with the existing backend
 * through ApiClient.
 */
public class BillingFrame extends JFrame {

    // =========================================================
    // API CLIENT
    // =========================================================

    private final ApiClient apiClient;

    // =========================================================
    // CURRENT DATA
    // =========================================================

    private Appointment currentAppointment;
    private Bill currentBill;

    // =========================================================
    // FORM COMPONENTS
    // =========================================================

    private JTextField appointmentNumberField;

    private JTextField patientNameField;
    private JTextField dentistNameField;
    private JTextField treatmentNameField;

    private JTextField treatmentCostField;
    private JTextField consultationFeeField;
    private JTextField totalAmountField;

    // =========================================================
    // BUTTONS
    // =========================================================

    private JButton calculateButton;
    private JButton saveButton;
    private JButton printButton;
    private JButton clearButton;
    private JButton closeButton;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public BillingFrame() {

        apiClient = new ApiClient();

        initializeFrame();
        initializeComponents();
    }

    // =========================================================
    // FRAME
    // =========================================================

    private void initializeFrame() {

        setTitle("Sunrise Dental Clinic - Billing");

        setSize(
                750,
                600
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

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        20,
                        20
                )
        );

        mainPanel.setBackground(
                Color.WHITE
        );

        // =====================================================
        // TITLE
        // =====================================================

        JLabel titleLabel =
                new JLabel(
                        "SUNRISE DENTAL CLINIC - BILLING",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        titleLabel.setForeground(
                Color.BLACK
        );

        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );

        // =====================================================
        // FORM PANEL
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
                        8,
                        8,
                        8,
                        8
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // =====================================================
        // APPOINTMENT NUMBER
        // =====================================================

        JLabel appointmentNumberLabel =
                new JLabel(
                        "Appointment Number:"
                );

        appointmentNumberLabel.setForeground(
                Color.BLACK
        );

        appointmentNumberField =
                new JTextField(
                        20
                );

        calculateButton =
                new JButton(
                        "Calculate Bill"
                );

        styleButton(
                calculateButton
        );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;

        formPanel.add(
                appointmentNumberLabel,
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        formPanel.add(
                appointmentNumberField,
                gbc
        );

        gbc.gridx = 2;
        gbc.weightx = 0;

        formPanel.add(
                calculateButton,
                gbc
        );

        // =====================================================
        // PATIENT NAME
        // =====================================================

        JLabel patientLabel =
                new JLabel(
                        "Patient Name:"
                );

        patientLabel.setForeground(
                Color.BLACK
        );

        patientNameField =
                createReadOnlyField();

        addRow(
                formPanel,
                patientLabel,
                patientNameField,
                1
        );

        // =====================================================
        // DENTIST NAME
        // =====================================================

        JLabel dentistLabel =
                new JLabel(
                        "Dentist:"
                );

        dentistLabel.setForeground(
                Color.BLACK
        );

        dentistNameField =
                createReadOnlyField();

        addRow(
                formPanel,
                dentistLabel,
                dentistNameField,
                2
        );

        // =====================================================
        // TREATMENT NAME
        // =====================================================

        JLabel treatmentLabel =
                new JLabel(
                        "Treatment:"
                );

        treatmentLabel.setForeground(
                Color.BLACK
        );

        treatmentNameField =
                createReadOnlyField();

        addRow(
                formPanel,
                treatmentLabel,
                treatmentNameField,
                3
        );

        // =====================================================
        // TREATMENT COST
        // =====================================================

        JLabel treatmentCostLabel =
                new JLabel(
                        "Treatment Cost:"
                );

        treatmentCostLabel.setForeground(
                Color.BLACK
        );

        treatmentCostField =
                createReadOnlyField();

        addRow(
                formPanel,
                treatmentCostLabel,
                treatmentCostField,
                4
        );

        // =====================================================
        // CONSULTATION FEE
        // =====================================================

        JLabel consultationLabel =
                new JLabel(
                        "Consultation Fee:"
                );

        consultationLabel.setForeground(
                Color.BLACK
        );

        consultationFeeField =
                createReadOnlyField();

        addRow(
                formPanel,
                consultationLabel,
                consultationFeeField,
                5
        );

        // =====================================================
        // TOTAL
        // =====================================================

        JLabel totalLabel =
                new JLabel(
                        "TOTAL AMOUNT:"
                );

        totalLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        totalLabel.setForeground(
                Color.BLACK
        );

        totalAmountField =
                createReadOnlyField();

        totalAmountField.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        addRow(
                formPanel,
                totalLabel,
                totalAmountField,
                6
        );

        mainPanel.add(
                formPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // BUTTON PANEL
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                10
                        )
                );

        buttonPanel.setBackground(
                Color.WHITE
        );

        saveButton =
                new JButton(
                        "Save Bill"
                );

        printButton =
                new JButton(
                        "Print Bill"
                );

        clearButton =
                new JButton(
                        "Clear"
                );

        closeButton =
                new JButton(
                        "Close"
                );

        styleButton(saveButton);
        styleButton(printButton);
        styleButton(clearButton);
        styleButton(closeButton);

        saveButton.setEnabled(false);
        printButton.setEnabled(false);

        buttonPanel.add(
                saveButton
        );

        buttonPanel.add(
                printButton
        );

        buttonPanel.add(
                clearButton
        );

        buttonPanel.add(
                closeButton
        );

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        calculateButton.addActionListener(
                e -> calculateBill()
        );

        saveButton.addActionListener(
                e -> saveBill()
        );

        printButton.addActionListener(
                e -> printBill()
        );

        clearButton.addActionListener(
                e -> clearForm()
        );

        closeButton.addActionListener(
                e -> dispose()
        );

        setContentPane(
                mainPanel
        );
    }

    // =========================================================
    // CREATE READ-ONLY FIELD
    // =========================================================

    private JTextField createReadOnlyField() {

        JTextField field =
                new JTextField(
                        20
                );

        field.setEditable(
                false
        );

        field.setBackground(
                new Color(
                        245,
                        245,
                        245
                )
        );

        field.setForeground(
                Color.BLACK
        );

        return field;
    }

    // =========================================================
    // ADD FORM ROW
    // =========================================================

    private void addRow(
            JPanel panel,
            JLabel label,
            JTextField field,
            int row
    ) {

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

        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0;

        panel.add(
                label,
                gbc
        );

        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1;

        panel.add(
                field,
                gbc
        );
    }

    // =========================================================
    // BUTTON STYLE
    // =========================================================

    private void styleButton(
            JButton button
    ) {

        button.setBackground(
                new Color(
                        30,
                        136,
                        229
                )
        );

        button.setForeground(
                Color.BLACK
        );

        button.setFont(
                new Font(
                        "Arial",
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
    }

    // =========================================================
    // CALCULATE BILL
    // =========================================================

    private void calculateBill() {

        String appointmentNumber =
                appointmentNumberField
                        .getText()
                        .trim();

        if (
                appointmentNumber.isEmpty()
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter an appointment number.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            appointmentNumberField.requestFocus();

            return;
        }

        calculateButton.setEnabled(
                false
        );

        saveButton.setEnabled(
                true
        );

        printButton.setEnabled(
                true
        );

        SwingWorker<Bill, Void> worker =
                new SwingWorker<>() {

                    @Override
                    protected Bill doInBackground()
                            throws Exception {

                        currentAppointment =
                                apiClient.getAppointmentByNumber(
                                        appointmentNumber
                                );

                        if (
                                currentAppointment == null
                        ) {

                            throw new Exception(
                                    "Appointment not found."
                            );
                        }

                        return apiClient.calculateBill(
                                appointmentNumber
                        );
                    }

                    @Override
                    protected void done() {

                        calculateButton.setEnabled(
                                true
                        );

                        try {

                            currentBill =
                                    get();

                            displayBill();

                            saveButton.setEnabled(
                                    true
                            );

                            printButton.setEnabled(
                                    true
                            );

                            JOptionPane.showMessageDialog(
                                    BillingFrame.this,
                                    "Bill calculated successfully.",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE
                            );

                        } catch (Exception ex) {

                            currentAppointment =
                                    null;

                            currentBill =
                                    null;

                            JOptionPane.showMessageDialog(
                                    BillingFrame.this,
                                    getErrorMessage(ex),
                                    "Billing Error",
                                    JOptionPane.ERROR_MESSAGE
                            );
                        }
                    }
                };

        worker.execute();
    }

    // =========================================================
    // DISPLAY BILL
    // =========================================================

    private void displayBill() {

        if (
                currentAppointment == null
                        ||
                        currentBill == null
        ) {

            return;
        }

        patientNameField.setText(
                safe(
                        currentAppointment
                                .getPatientName()
                )
        );

        dentistNameField.setText(
                safe(
                        currentAppointment
                                .getDentistName()
                )
        );

        treatmentNameField.setText(
                safe(
                        currentAppointment
                                .getTreatmentName()
                )
        );

        treatmentCostField.setText(
                String.format(
                        "Rs. %.2f",
                        currentBill
                                .getTreatmentCost()
                )
        );

        consultationFeeField.setText(
                String.format(
                        "Rs. %.2f",
                        currentBill
                                .getConsultationFee()
                )
        );

        totalAmountField.setText(
                String.format(
                        "Rs. %.2f",
                        currentBill
                                .getTotalAmount()
                )
        );
    }

    // =========================================================
    // SAVE BILL
    // =========================================================

    private void saveBill() {

        if (
                currentBill == null
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please calculate the bill first.",
                    "Billing",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        saveButton.setEnabled(
                false
        );

        SwingWorker<Integer, Void> worker =
                new SwingWorker<>() {

                    @Override
                    protected Integer doInBackground()
                            throws Exception {

                        return apiClient.saveBill(
                                currentBill
                        );
                    }

                    @Override
                    protected void done() {

                        try {

                            int billId =
                                    get();

                            if (
                                    billId > 0
                            ) {

                                JOptionPane.showMessageDialog(
                                        BillingFrame.this,
                                        "Bill saved successfully.\n\n"
                                                + "Bill ID: "
                                                + billId,
                                        "Success",
                                        JOptionPane.INFORMATION_MESSAGE
                                );

                            } else {

                                throw new Exception(
                                        "Bill could not be saved."
                                );
                            }

                        } catch (Exception ex) {

                            JOptionPane.showMessageDialog(
                                    BillingFrame.this,
                                    getErrorMessage(ex),
                                    "Save Error",
                                    JOptionPane.ERROR_MESSAGE
                            );

                            saveButton.setEnabled(
                                    true
                            );
                        }
                    }
                };

        worker.execute();
    }

    // =========================================================
    // PRINT BILL
    // =========================================================

    private void printBill() {

        if (
                currentBill == null
                        ||
                        currentAppointment == null
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please calculate the bill first.",
                    "Billing",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String billText =
                buildBillText();

        JTextArea printArea =
                new JTextArea(
                        billText
                );

        printArea.setFont(
                new Font(
                        "Monospaced",
                        Font.PLAIN,
                        12
                )
        );

        printArea.setEditable(
                false
        );

        try {

            boolean complete =
                    printArea.print(
                            new MessageFormat(
                                    "Sunrise Dental Clinic - Bill"
                            ),
                            new MessageFormat(
                                    "Page {0}"
                            )
                    );

            if (
                    complete
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Bill printed successfully.",
                        "Print",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (PrinterException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to print bill.\n\n"
                            + ex.getMessage(),
                    "Print Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // BUILD BILL TEXT
    // =========================================================

    private String buildBillText() {

        StringBuilder bill =
                new StringBuilder();

        bill.append(
                "========================================\n"
        );

        bill.append(
                "          SUNRISE DENTAL CLINIC\n"
        );

        bill.append(
                "                 BILL\n"
        );

        bill.append(
                "========================================\n"
        );

        bill.append(
                "Appointment No: "
        );

        bill.append(
                safe(
                        currentAppointment
                                .getAppointmentNumber()
                )
        );

        bill.append(
                "\n"
        );

        bill.append(
                "Patient Name:    "
        );

        bill.append(
                safe(
                        currentAppointment
                                .getPatientName()
                )
        );

        bill.append(
                "\n"
        );

        bill.append(
                "Dentist:         "
        );

        bill.append(
                safe(
                        currentAppointment
                                .getDentistName()
                )
        );

        bill.append(
                "\n"
        );

        bill.append(
                "Treatment:       "
        );

        bill.append(
                safe(
                        currentAppointment
                                .getTreatmentName()
                )
        );

        bill.append(
                "\n"
        );

        bill.append(
                "----------------------------------------\n"
        );

        bill.append(
                String.format(
                        "Treatment Cost:      Rs. %.2f%n",
                        currentBill
                                .getTreatmentCost()
                )
        );

        bill.append(
                String.format(
                        "Consultation Fee:    Rs. %.2f%n",
                        currentBill
                                .getConsultationFee()
                )
        );

        bill.append(
                "----------------------------------------\n"
        );

        bill.append(
                String.format(
                        "TOTAL:               Rs. %.2f%n",
                        currentBill
                                .getTotalAmount()
                )
        );

        bill.append(
                "========================================\n"
        );

        bill.append(
                "          Thank you for visiting!\n"
        );

        bill.append(
                "========================================\n"
        );

        return bill.toString();
    }

    // =========================================================
    // CLEAR FORM
    // =========================================================

    private void clearForm() {

        appointmentNumberField.setText("");

        patientNameField.setText("");

        dentistNameField.setText("");

        treatmentNameField.setText("");

        treatmentCostField.setText("");

        consultationFeeField.setText("");

        totalAmountField.setText("");

        currentAppointment =
                null;

        currentBill =
                null;

        saveButton.setEnabled(
                false
        );

        printButton.setEnabled(
                false
        );

        appointmentNumberField.requestFocus();
    }

    // =========================================================
    // SAFE STRING
    // =========================================================

    private String safe(
            String value
    ) {

        if (
                value == null
        ) {

            return "";
        }

        return value;
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
    // TEST MAIN
    // =========================================================

    public static void main(
            String[] args
    ) {

        SwingUtilities.invokeLater(
                () -> {

                    BillingFrame frame =
                            new BillingFrame();

                    frame.setVisible(
                            true
                    );
                }
        );
    }
}
