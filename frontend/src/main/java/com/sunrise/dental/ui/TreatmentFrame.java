package com.sunrise.dental.ui;

import com.sunrise.dental.api.ApiClient;
import com.sunrise.dental.model.Treatment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class TreatmentFrame extends JFrame {

    // =========================================================
    // API
    // =========================================================

    private final ApiClient apiClient;

    // =========================================================
    // FORM FIELDS
    // =========================================================

    private JTextField treatmentNameField;
    private JTextField descriptionField;
    private JTextField costField;
    private JTextField consultationFeeField;

    // =========================================================
    // TABLE
    // =========================================================

    private JTable treatmentTable;

    private DefaultTableModel tableModel;

    // =========================================================
    // BUTTONS
    // =========================================================

    private JButton addButton;
    private JButton refreshButton;
    private JButton clearButton;
    private JButton closeButton;

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public TreatmentFrame() {

        apiClient = new ApiClient();

        initializeWindow();

        initializeComponents();

        createLayout();

        loadTreatments();
    }

    // =========================================================
    // WINDOW
    // =========================================================

    private void initializeWindow() {

        setTitle(
                "Sunrise Dental Clinic - Treatment Management"
        );

        setSize(
                1050,
                700
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

        treatmentNameField =
                new JTextField();

        descriptionField =
                new JTextField();

        costField =
                new JTextField();

        consultationFeeField =
                new JTextField();

        // =====================================================
        // BUTTONS
        // =====================================================

        addButton =
                new JButton(
                        "Add Treatment"
                );

        refreshButton =
                new JButton(
                        "Refresh"
                );

        clearButton =
                new JButton(
                        "Clear"
                );

        closeButton =
                new JButton(
                        "Close"
                );

        // =====================================================
        // TABLE
        // =====================================================

        tableModel =
                new DefaultTableModel(
                        new Object[]{
                                "ID",
                                "Treatment Name",
                                "Description",
                                "Treatment Cost",
                                "Consultation Fee",
                                "Active"
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

        treatmentTable =
                new JTable(
                        tableModel
                );

        treatmentTable.setRowHeight(
                30
        );

        treatmentTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        // =====================================================
        // BUTTON ACTIONS
        // =====================================================

        addButton.addActionListener(
                e -> addTreatment()
        );

        refreshButton.addActionListener(
                e -> loadTreatments()
        );

        clearButton.addActionListener(
                e -> clearForm()
        );

        closeButton.addActionListener(
                e -> dispose()
        );

        // =====================================================
        // TABLE SELECTION
        // =====================================================

        treatmentTable
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                int row =
                                        treatmentTable
                                                .getSelectedRow();

                                if (row >= 0) {

                                    treatmentNameField.setText(
                                            getTableValue(
                                                    row,
                                                    1
                                            )
                                    );

                                    descriptionField.setText(
                                            getTableValue(
                                                    row,
                                                    2
                                            )
                                    );

                                    costField.setText(
                                            getTableValue(
                                                    row,
                                                    3
                                            )
                                    );

                                    consultationFeeField.setText(
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
                        "Treatment Management"
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
                        "Manage Sunrise Dental Clinic treatments"
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
                        "Treatment Information"
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

        // =====================================================
        // NAME
        // =====================================================

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;

        formPanel.add(
                new JLabel(
                        "Treatment Name:"
                ),
                gbc
        );

        gbc.gridx = 1;
        gbc.gridwidth = 3;

        formPanel.add(
                treatmentNameField,
                gbc
        );

        // =====================================================
        // DESCRIPTION
        // =====================================================

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;

        formPanel.add(
                new JLabel(
                        "Description:"
                ),
                gbc
        );

        gbc.gridx = 1;
        gbc.gridwidth = 3;

        formPanel.add(
                descriptionField,
                gbc
        );

        // =====================================================
        // TREATMENT COST
        // =====================================================

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;

        formPanel.add(
                new JLabel(
                        "Treatment Cost:"
                ),
                gbc
        );

        gbc.gridx = 1;
        gbc.gridwidth = 1;

        formPanel.add(
                costField,
                gbc
        );

        // =====================================================
        // CONSULTATION FEE
        // =====================================================

        gbc.gridx = 2;
        gbc.gridwidth = 1;

        formPanel.add(
                new JLabel(
                        "Consultation Fee:"
                ),
                gbc
        );

        gbc.gridx = 3;
        gbc.gridwidth = 1;

        formPanel.add(
                consultationFeeField,
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
        // TABLE PANEL
        // =====================================================

        JPanel tablePanel =
                new JPanel(
                        new BorderLayout()
                );

        tablePanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Available Treatments"
                )
        );

        tablePanel.setBackground(
                Color.WHITE
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        treatmentTable
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
        // BUTTON PANEL
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
    // ADD TREATMENT
    // =========================================================

    private void addTreatment() {

        String name =
                treatmentNameField
                        .getText()
                        .trim();

        String description =
                descriptionField
                        .getText()
                        .trim();

        String costText =
                costField
                        .getText()
                        .trim();

        String consultationFeeText =
                consultationFeeField
                        .getText()
                        .trim();

        // =====================================================
        // NAME VALIDATION
        // =====================================================

        if (name.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the treatment name.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            treatmentNameField.requestFocus();

            return;
        }

        // =====================================================
        // DESCRIPTION VALIDATION
        // =====================================================

        if (description.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the treatment description.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            descriptionField.requestFocus();

            return;
        }

        // =====================================================
        // COST VALIDATION
        // =====================================================

        if (costText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the treatment cost.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            costField.requestFocus();

            return;
        }

        double cost;

        try {

            cost =
                    Double.parseDouble(
                            costText
                    );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Treatment cost must be a valid number.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            costField.requestFocus();

            return;
        }

        if (cost < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Treatment cost cannot be negative.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            costField.requestFocus();

            return;
        }

        // =====================================================
        // CONSULTATION FEE VALIDATION
        // =====================================================

        if (consultationFeeText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the consultation fee.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            consultationFeeField.requestFocus();

            return;
        }

        double consultationFee;

        try {

            consultationFee =
                    Double.parseDouble(
                            consultationFeeText
                    );

        } catch (NumberFormatException e) {

            JOptionPane.showMessageDialog(
                    this,
                    "Consultation fee must be a valid number.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            consultationFeeField.requestFocus();

            return;
        }

        if (consultationFee < 0) {

            JOptionPane.showMessageDialog(
                    this,
                    "Consultation fee cannot be negative.",
                    "Validation Error",
                    JOptionPane.WARNING_MESSAGE
            );

            consultationFeeField.requestFocus();

            return;
        }

        // =====================================================
        // CREATE TREATMENT OBJECT
        // =====================================================

        Treatment treatment =
                new Treatment(
                        name,
                        description,
                        cost,
                        consultationFee,
                        true
                );

        // =====================================================
        // DISABLE BUTTON
        // =====================================================

        addButton.setEnabled(
                false
        );

        // =====================================================
        // SEND TO BACKEND
        // =====================================================

        SwingWorker<Treatment, Void>
                worker =
                new SwingWorker<>() {

                    @Override
                    protected Treatment
                    doInBackground()
                            throws Exception {

                        return apiClient.addTreatment(
                                treatment
                        );
                    }

                    @Override
                    protected void done() {

                        addButton.setEnabled(
                                true
                        );

                        try {

                            Treatment created =
                                    get();

                            JOptionPane.showMessageDialog(
                                    TreatmentFrame.this,
                                    "Treatment added successfully!",
                                    "Success",
                                    JOptionPane.INFORMATION_MESSAGE
                            );

                            clearForm();

                            loadTreatments();

                        } catch (Exception ex) {

                            JOptionPane.showMessageDialog(
                                    TreatmentFrame.this,
                                    "Unable to add treatment.\n\n"
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
    // LOAD TREATMENTS
    // =========================================================

    private void loadTreatments() {

        refreshButton.setEnabled(
                false
        );

        SwingWorker<List<Treatment>, Void>
                worker =
                new SwingWorker<>() {

                    @Override
                    protected List<Treatment>
                    doInBackground()
                            throws Exception {

                        return apiClient.getTreatments();
                    }

                    @Override
                    protected void done() {

                        refreshButton.setEnabled(
                                true
                        );

                        try {

                            List<Treatment> treatments =
                                    get();

                            updateTable(
                                    treatments
                            );

                        } catch (Exception ex) {

                            JOptionPane.showMessageDialog(
                                    TreatmentFrame.this,
                                    "Unable to load treatments.\n\n"
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
            List<Treatment> treatments
    ) {

        tableModel.setRowCount(
                0
        );

        if (treatments == null) {

            return;
        }

        for (
                Treatment treatment :
                treatments
        ) {

            tableModel.addRow(
                    new Object[]{
                            treatment.getTreatmentId(),

                            treatment.getTreatmentName(),

                            treatment.getDescription(),

                            String.format(
                                    "Rs. %.2f",
                                    treatment.getTreatmentCost()
                            ),

                            String.format(
                                    "Rs. %.2f",
                                    treatment.getConsultationFee()
                            ),

                            treatment.isActive()
                                    ? "Yes"
                                    : "No"
                    }
            );
        }
    }

    // =========================================================
    // CLEAR FORM
    // =========================================================

    private void clearForm() {

        treatmentNameField.setText(
                ""
        );

        descriptionField.setText(
                ""
        );

        costField.setText(
                ""
        );

        consultationFeeField.setText(
                ""
        );

        treatmentTable.clearSelection();

        treatmentNameField.requestFocus();
    }

    // =========================================================
    // TABLE VALUE
    // =========================================================

    private String getTableValue(
            int row,
            int column
    ) {

        Object value =
                treatmentTable.getValueAt(
                        row,
                        column
                );

        if (value == null) {

            return "";
        }

        String text =
                value.toString();

        text =
                text.replace(
                        "Rs. ",
                        ""
                );

        return text;
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

                    TreatmentFrame frame =
                            new TreatmentFrame();

                    frame.setVisible(
                            true
                    );
                }
        );
    }
}

