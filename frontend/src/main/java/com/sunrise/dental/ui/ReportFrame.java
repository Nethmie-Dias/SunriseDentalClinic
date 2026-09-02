package com.sunrise.dental.ui;

import com.sunrise.dental.api.ApiClient;
import com.sunrise.dental.model.Report;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.awt.print.PrinterException;
import java.text.MessageFormat;
import java.util.List;
import java.util.regex.Pattern;

/**
 * =========================================================
 * SUNRISE DENTAL CLINIC
 * REPORT FRAME
 * =========================================================
 *
 * Reports:
 * 1. Appointment Report
 * 2. Patient Report
 * 3. Billing Report
 *
 * Features:
 * - View reports
 * - Search all columns
 * - Clear search
 * - Select a record
 * - Double-click a record to view details
 * - View selected record in popup
 * - Print report
 * - Close report
 *
 * IMPORTANT:
 * This class only works with the existing:
 * - ApiClient
 * - Report model
 *
 * No database/API changes are required.
 * =========================================================
 */
public class ReportFrame extends JFrame {

    // =========================================================
    // USER INFORMATION
    // =========================================================

    private final int userId;
    private final String username;
    private final String role;

    // =========================================================
    // API CLIENT
    // =========================================================

    private final ApiClient apiClient;

    // =========================================================
    // TABLE
    // =========================================================

    private JTable reportTable;

    private DefaultTableModel tableModel;

    /**
     * Used for safe searching/filtering.
     *
     * IMPORTANT:
     * We do NOT hide rows by setting row height to 0.
     * JTable does not allow row height 0.
     */
    private TableRowSorter<DefaultTableModel> tableSorter;

    // =========================================================
    // TITLE
    // =========================================================

    private JLabel reportTitleLabel;

    // =========================================================
    // SEARCH
    // =========================================================

    private JTextField searchField;

    private JLabel resultCountLabel;

    // =========================================================
    // CURRENT REPORT
    // =========================================================

    private String currentReportType = "";

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public ReportFrame(
            int userId,
            String username,
            String role
    ) {

        this.userId = userId;
        this.username = username;
        this.role = role;

        apiClient = new ApiClient();

        setTitle(
                "Sunrise Dental Clinic - Reports"
        );

        setSize(
                1250,
                750
        );

        setMinimumSize(
                new Dimension(
                        1000,
                        650
                )
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
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
                        18,
                        25,
                        18,
                        25
                )
        );

        // =====================================================
        // CLINIC TITLE
        // =====================================================

        JPanel titlePanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        titlePanel.setOpaque(false);

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
                        24
                )
        );

        JLabel subtitleLabel =
                new JLabel(
                        "Clinic Reports & Information"
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
                        14
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
        // CENTER PANEL
        // =====================================================

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                10
                        )
                );

        centerPanel.setOpaque(false);

        centerPanel.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        10,
                        25
                )
        );

        // =====================================================
        // TOP INFORMATION PANEL
        // =====================================================

        JPanel informationPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                12
                        )
                );

        informationPanel.setOpaque(false);

        // =====================================================
        // REPORT TITLE
        // =====================================================

        reportTitleLabel =
                new JLabel(
                        "Select a report to view"
                );

        reportTitleLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        23
                )
        );

        reportTitleLabel.setForeground(
                new Color(
                        25,
                        45,
                        55
                )
        );

        informationPanel.add(
                reportTitleLabel,
                BorderLayout.NORTH
        );

        // =====================================================
        // SEARCH PANEL
        // =====================================================

        JPanel searchContainer =
                new JPanel(
                        new BorderLayout(
                                10,
                                0
                        )
                );

        searchContainer.setBackground(
                Color.WHITE
        );

        searchContainer.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        180,
                                        195,
                                        205
                                ),
                                1
                        ),
                        new EmptyBorder(
                                10,
                                12,
                                10,
                                12
                        )
                )
        );

        // =====================================================
        // SEARCH LABEL
        // =====================================================

        JLabel searchLabel =
                new JLabel(
                        "SEARCH REPORT:"
                );

        searchLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        searchLabel.setForeground(
                new Color(
                        35,
                        55,
                        65
                )
        );

        searchContainer.add(
                searchLabel,
                BorderLayout.WEST
        );

        // =====================================================
        // SEARCH FIELD
        // =====================================================

        searchField =
                new JTextField();

        searchField.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        14
                )
        );

        searchField.setForeground(
                Color.BLACK
        );

        searchField.setBackground(
                Color.WHITE
        );

        searchField.setPreferredSize(
                new Dimension(
                        350,
                        40
                )
        );

        searchField.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        110,
                                        135,
                                        150
                                ),
                                1
                        ),
                        new EmptyBorder(
                                5,
                                12,
                                5,
                                12
                        )
                )
        );

        searchField.setToolTipText(
                "Search by appointment number, patient name, ID, contact, dentist, treatment, date, status, etc."
        );

        searchContainer.add(
                searchField,
                BorderLayout.CENTER
        );

        // =====================================================
        // SEARCH BUTTON
        // =====================================================

        JButton searchButton =
                createButton(
                        "SEARCH"
                );

        searchButton.setPreferredSize(
                new Dimension(
                        105,
                        40
                )
        );

        // =====================================================
        // CLEAR BUTTON
        // =====================================================

        JButton clearSearchButton =
                createButton(
                        "CLEAR"
                );

        clearSearchButton.setPreferredSize(
                new Dimension(
                        90,
                        40
                )
        );

        // =====================================================
        // SEARCH BUTTON PANEL
        // =====================================================

        JPanel searchButtonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                5,
                                0
                        )
                );

        searchButtonPanel.setOpaque(false);

        searchButtonPanel.add(
                searchButton
        );

        searchButtonPanel.add(
                clearSearchButton
        );

        searchContainer.add(
                searchButtonPanel,
                BorderLayout.EAST
        );

        informationPanel.add(
                searchContainer,
                BorderLayout.CENTER
        );

        // =====================================================
        // RESULT COUNT
        // =====================================================

        resultCountLabel =
                new JLabel(
                        "No report selected"
                );

        resultCountLabel.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        resultCountLabel.setForeground(
                new Color(
                        80,
                        95,
                        105
                )
        );

        informationPanel.add(
                resultCountLabel,
                BorderLayout.SOUTH
        );

        centerPanel.add(
                informationPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // TABLE MODEL
        // =====================================================

        tableModel =
                new DefaultTableModel() {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {

                        return false;
                    }
                };

        // =====================================================
        // TABLE
        // =====================================================

        reportTable =
                new JTable(
                        tableModel
                );

        reportTable.setFont(
                new Font(
                        "SansSerif",
                        Font.PLAIN,
                        13
                )
        );

        reportTable.setForeground(
                Color.BLACK
        );

        reportTable.setBackground(
                Color.WHITE
        );

        reportTable.setSelectionBackground(
                new Color(
                        190,
                        220,
                        235
                )
        );

        reportTable.setSelectionForeground(
                Color.BLACK
        );

        reportTable.setRowHeight(
                32
        );

        reportTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_OFF
        );

        reportTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        reportTable.setShowGrid(
                true
        );

        reportTable.setGridColor(
                new Color(
                        210,
                        220,
                        225
                )
        );

        reportTable.setFillsViewportHeight(
                true
        );

        // =====================================================
        // TABLE HEADER
        // =====================================================

        reportTable.getTableHeader()
                .setFont(
                        new Font(
                                "SansSerif",
                                Font.BOLD,
                                13
                        )
                );

        reportTable.getTableHeader()
                .setForeground(
                        Color.BLACK
                );

        reportTable.getTableHeader()
                .setBackground(
                        new Color(
                                220,
                                230,
                                236
                        )
                );

        reportTable.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                35
                        )
                );

        // =====================================================
        // TABLE SORTER
        // =====================================================

        tableSorter =
                new TableRowSorter<>(
                        tableModel
                );

        reportTable.setRowSorter(
                tableSorter
        );

        // =====================================================
        // SCROLL PANE
        // =====================================================

        JScrollPane scrollPane =
                new JScrollPane(
                        reportTable
                );

        scrollPane.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        150,
                                        165,
                                        175
                                ),
                                1
                        ),
                        new EmptyBorder(
                                1,
                                1,
                                1,
                                1
                        )
                )
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
        // BOTTOM BUTTON PANEL
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                12,
                                12
                        )
                );

        buttonPanel.setBackground(
                new Color(
                        230,
                        238,
                        243
                )
        );

        // =====================================================
        // APPOINTMENT REPORT BUTTON
        // =====================================================

        JButton appointmentButton =
                createButton(
                        "APPOINTMENT REPORT"
                );

        appointmentButton.addActionListener(
                e -> loadAppointmentReport()
        );

        // =====================================================
        // PATIENT REPORT BUTTON
        // =====================================================

        JButton patientButton =
                createButton(
                        "PATIENT REPORT"
                );

        patientButton.addActionListener(
                e -> loadPatientReport()
        );

        // =====================================================
        // BILLING REPORT BUTTON
        // =====================================================

        JButton billingButton =
                createButton(
                        "BILLING REPORT"
                );

        billingButton.addActionListener(
                e -> loadBillingReport()
        );

        // =====================================================
        // VIEW SELECTED
        // =====================================================

        JButton viewButton =
                createButton(
                        "VIEW SELECTED"
                );

        viewButton.addActionListener(
                e -> viewSelectedRecord()
        );

        // =====================================================
        // PRINT
        // =====================================================

        JButton printButton =
                createButton(
                        "PRINT REPORT"
                );

        printButton.addActionListener(
                e -> printReport()
        );

        // =====================================================
        // CLOSE
        // =====================================================

        JButton closeButton =
                createButton(
                        "CLOSE"
                );

        closeButton.addActionListener(
                e -> dispose()
        );

        // =====================================================
        // ADD BUTTONS
        // =====================================================

        buttonPanel.add(
                appointmentButton
        );

        buttonPanel.add(
                patientButton
        );

        buttonPanel.add(
                billingButton
        );

        buttonPanel.add(
                viewButton
        );

        buttonPanel.add(
                printButton
        );

        buttonPanel.add(
                closeButton
        );

        mainPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =====================================================
        // SEARCH ACTIONS
        // =====================================================

        searchButton.addActionListener(
                e -> searchReport()
        );

        clearSearchButton.addActionListener(
                e -> clearSearch()
        );

        searchField.addActionListener(
                e -> searchReport()
        );

        // =====================================================
        // DOUBLE CLICK
        // =====================================================

        reportTable.addMouseListener(
                new java.awt.event.MouseAdapter() {

                    @Override
                    public void mouseClicked(
                            java.awt.event.MouseEvent e
                    ) {

                        if (
                                e.getClickCount() == 2
                                        &&
                                        SwingUtilities.isLeftMouseButton(
                                                e
                                        )
                        ) {

                            viewSelectedRecord();
                        }
                    }
                }
        );

        // =====================================================
        // SET CONTENT
        // =====================================================

        setContentPane(
                mainPanel
        );
    }

    // =========================================================
    // CREATE BUTTON
    // =========================================================

    private JButton createButton(
            String text
    ) {

        JButton button =
                new JButton(
                        text
                );

        button.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        13
                )
        );

        button.setForeground(
                Color.BLACK
        );

        button.setBackground(
                Color.WHITE
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
                                        130,
                                        150,
                                        160
                                ),
                                1
                        ),
                        new EmptyBorder(
                                9,
                                14,
                                9,
                                14
                        )
                )
        );

        return button;
    }

    // =========================================================
    // LOAD APPOINTMENT REPORT
    // =========================================================

    private void loadAppointmentReport() {

        currentReportType =
                "APPOINTMENT";

        searchField.setText("");

        clearSearchFilter();

        setLoading(
                "Loading appointment report..."
        );

        SwingWorker<List<Report>, Void> worker =
                new SwingWorker<>() {

                    @Override
                    protected List<Report> doInBackground()
                            throws Exception {

                        return apiClient
                                .getAppointmentReports();
                    }

                    @Override
                    protected void done() {

                        try {

                            List<Report> reports =
                                    get();

                            displayAppointmentReport(
                                    reports
                            );

                        } catch (Exception ex) {

                            showError(
                                    "Unable to load appointment report.",
                                    ex
                            );
                        }
                    }
                };

        worker.execute();
    }

    // =========================================================
    // LOAD PATIENT REPORT
    // =========================================================

    private void loadPatientReport() {

        currentReportType =
                "PATIENT";

        searchField.setText("");

        clearSearchFilter();

        setLoading(
                "Loading patient report..."
        );

        SwingWorker<List<Report>, Void> worker =
                new SwingWorker<>() {

                    @Override
                    protected List<Report> doInBackground()
                            throws Exception {

                        return apiClient
                                .getPatientReports();
                    }

                    @Override
                    protected void done() {

                        try {

                            List<Report> reports =
                                    get();

                            displayPatientReport(
                                    reports
                            );

                        } catch (Exception ex) {

                            showError(
                                    "Unable to load patient report.",
                                    ex
                            );
                        }
                    }
                };

        worker.execute();
    }

    // =========================================================
    // LOAD BILLING REPORT
    // =========================================================

    private void loadBillingReport() {

        currentReportType =
                "BILLING";

        searchField.setText("");

        clearSearchFilter();

        setLoading(
                "Loading billing report..."
        );

        SwingWorker<List<Report>, Void> worker =
                new SwingWorker<>() {

                    @Override
                    protected List<Report> doInBackground()
                            throws Exception {

                        return apiClient
                                .getBillingReports();
                    }

                    @Override
                    protected void done() {

                        try {

                            List<Report> reports =
                                    get();

                            displayBillingReport(
                                    reports
                            );

                        } catch (Exception ex) {

                            showError(
                                    "Unable to load billing report.",
                                    ex
                            );
                        }
                    }
                };

        worker.execute();
    }

    // =========================================================
    // DISPLAY APPOINTMENT REPORT
    // =========================================================

    private void displayAppointmentReport(
            List<Report> reports
    ) {

        reportTitleLabel.setText(
                "Appointment Report"
        );

        clearTable();

        tableModel.setColumnIdentifiers(
                new Object[]{
                        "Appointment No.",
                        "Patient Name",
                        "Contact Number",
                        "Dentist",
                        "Treatment",
                        "Date",
                        "Time",
                        "Status"
                }
        );

        for (
                Report report : reports
        ) {

            tableModel.addRow(
                    new Object[]{
                            report.getAppointmentNumber(),
                            report.getPatientName(),
                            report.getContactNumber(),
                            report.getDentistName(),
                            report.getTreatmentName(),
                            report.getAppointmentDate(),
                            report.getAppointmentTime(),
                            report.getAppointmentStatus()
                    }
            );
        }

        clearSearchFilter();

        finishLoading(
                reports.size()
        );
    }

    // =========================================================
    // DISPLAY PATIENT REPORT
    // =========================================================

    private void displayPatientReport(
            List<Report> reports
    ) {

        reportTitleLabel.setText(
                "Patient Report"
        );

        clearTable();

        tableModel.setColumnIdentifiers(
                new Object[]{
                        "Patient ID",
                        "Patient Name",
                        "Address",
                        "Contact Number"
                }
        );

        for (
                Report report : reports
        ) {

            tableModel.addRow(
                    new Object[]{
                            report.getId(),
                            report.getPatientName(),
                            report.getAddress(),
                            report.getContactNumber()
                    }
            );
        }

        clearSearchFilter();

        finishLoading(
                reports.size()
        );
    }

    // =========================================================
    // DISPLAY BILLING REPORT
    // =========================================================

    private void displayBillingReport(
            List<Report> reports
    ) {

        reportTitleLabel.setText(
                "Billing Report"
        );

        clearTable();

        tableModel.setColumnIdentifiers(
                new Object[]{
                        "Bill ID",
                        "Appointment No.",
                        "Patient Name",
                        "Treatment",
                        "Treatment Cost",
                        "Consultation Fee",
                        "Total Amount",
                        "Payment Status",
                        "Payment Date",
                        "Created At"
                }
        );

        for (
                Report report : reports
        ) {

            tableModel.addRow(
                    new Object[]{
                            report.getId(),
                            report.getAppointmentNumber(),
                            report.getPatientName(),
                            report.getTreatmentName(),
                            String.format(
                                    "Rs. %.2f",
                                    report.getTreatmentCost()
                            ),
                            String.format(
                                    "Rs. %.2f",
                                    report.getConsultationFee()
                            ),
                            String.format(
                                    "Rs. %.2f",
                                    report.getTotalAmount()
                            ),
                            report.getPaymentStatus(),
                            report.getPaymentDate(),
                            report.getCreatedAt()
                    }
            );
        }

        clearSearchFilter();

        finishLoading(
                reports.size()
        );
    }

    // =========================================================
    // SEARCH REPORT
    // =========================================================
    /**
     * Searches ALL visible columns.
     *
     * Examples:
     *
     * Appointment Report:
     * - APT004
     * - Himaya
     * - 0778446717
     * - Fernando
     * - Dental X-Ray
     * - 2026-09-03
     * - SCHEDULED
     *
     * Patient Report:
     * - Himaya
     * - 1
     * - 0778446717
     * - Colombo
     *
     * Billing Report:
     * - Bill ID
     * - Appointment number
     * - Patient
     * - Treatment
     * - Paid
     * - Rs.
     *
     * The search is case-insensitive.
     */
    private void searchReport() {

        // -----------------------------------------------------
        // CHECK WHETHER REPORT EXISTS
        // -----------------------------------------------------

        if (
                reportTable.getColumnCount() == 0
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a report first.",
                    "Search",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // -----------------------------------------------------
        // GET SEARCH TEXT
        // -----------------------------------------------------

        String searchText =
                searchField
                        .getText()
                        .trim();

        // -----------------------------------------------------
        // EMPTY SEARCH
        // -----------------------------------------------------

        if (
                searchText.isEmpty()
        ) {

            clearSearchFilter();

            resultCountLabel.setText(
                    "Showing "
                            + reportTable.getRowCount()
                            + " record(s)"
            );

            return;
        }

        // -----------------------------------------------------
        // SAFE REGEX SEARCH
        // -----------------------------------------------------
        //
        // Pattern.quote() prevents special characters
        // entered by the user from causing regex errors.
        //
        // Example:
        // user searches "Rs. 500"
        //
        // It will be treated as normal text.
        // -----------------------------------------------------

        String searchPattern =
                Pattern.quote(
                        searchText
                );

        tableSorter.setRowFilter(
                RowFilter.regexFilter(
                        "(?i)" + searchPattern
                )
        );

        // -----------------------------------------------------
        // COUNT RESULTS
        // -----------------------------------------------------

        int resultCount =
                reportTable.getRowCount();

        resultCountLabel.setText(
                "Search results: "
                        + resultCount
                        + " record(s)"
        );

        // -----------------------------------------------------
        // NO RESULTS
        // -----------------------------------------------------

        if (
                resultCount == 0
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "No matching records were found.",
                    "Search",
                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        // -----------------------------------------------------
        // SELECT FIRST RESULT
        // -----------------------------------------------------

        reportTable.setRowSelectionInterval(
                0,
                0
        );

        reportTable.scrollRectToVisible(
                reportTable
                        .getCellRect(
                                0,
                                0,
                                true
                        )
        );
    }

    // =========================================================
    // CLEAR SEARCH
    // =========================================================

    private void clearSearch() {

        searchField.setText("");

        clearSearchFilter();

        if (
                reportTable.getColumnCount() > 0
        ) {

            resultCountLabel.setText(
                    "Showing "
                            + reportTable.getRowCount()
                            + " record(s)"
            );
        }
    }

    // =========================================================
    // CLEAR SEARCH FILTER
    // =========================================================

    private void clearSearchFilter() {

        if (
                tableSorter != null
        ) {

            tableSorter.setRowFilter(
                    null
            );
        }
    }

    // =========================================================
    // VIEW SELECTED RECORD
    // =========================================================

    private void viewSelectedRecord() {

        int selectedRow =
                reportTable.getSelectedRow();

        if (
                selectedRow == -1
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a record first.",
                    "View Record",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        showRecordPopup(
                selectedRow
        );
    }

    // =========================================================
    // SHOW RECORD POPUP
    // =========================================================

    private void showRecordPopup(
            int viewRow
    ) {

        // -----------------------------------------------------
        // Convert view row to model row.
        //
        // This is important because searching uses a
        // TableRowSorter.
        // -----------------------------------------------------

        int modelRow =
                reportTable.convertRowIndexToModel(
                        viewRow
                );

        // =====================================================
        // CREATE DIALOG
        // =====================================================

        JDialog dialog =
                new JDialog(
                        this,
                        "Record Details",
                        true
                );

        dialog.setSize(
                650,
                540
        );

        dialog.setMinimumSize(
                new Dimension(
                        550,
                        450
                )
        );

        dialog.setLocationRelativeTo(
                this
        );

        dialog.setLayout(
                new BorderLayout()
        );

        // =====================================================
        // POPUP HEADER
        // =====================================================

        JPanel popupHeader =
                new JPanel(
                        new BorderLayout()
                );

        popupHeader.setBackground(
                new Color(
                        34,
                        93,
                        120
                )
        );

        popupHeader.setBorder(
                new EmptyBorder(
                        16,
                        22,
                        16,
                        22
                )
        );

        JLabel popupTitle =
                new JLabel(
                        getPopupTitle()
                );

        popupTitle.setForeground(
                Color.WHITE
        );

        popupTitle.setFont(
                new Font(
                        "SansSerif",
                        Font.BOLD,
                        20
                )
        );

        popupHeader.add(
                popupTitle,
                BorderLayout.WEST
        );

        dialog.add(
                popupHeader,
                BorderLayout.NORTH
        );

        // =====================================================
        // DETAILS PANEL
        // =====================================================

        JPanel detailsPanel =
                new JPanel();

        detailsPanel.setLayout(
                new BoxLayout(
                        detailsPanel,
                        BoxLayout.Y_AXIS
                )
        );

        detailsPanel.setBackground(
                Color.WHITE
        );

        detailsPanel.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        20,
                        25
                )
        );

        // =====================================================
        // ADD EACH FIELD
        // =====================================================

        for (
                int column = 0;
                column < reportTable.getColumnCount();
                column++
        ) {

            String columnName =
                    reportTable
                            .getColumnName(column);

            Object value =
                    reportTable
                            .getModel()
                            .getValueAt(
                                    modelRow,
                                    column
                            );

            String valueText =
                    value == null
                            ? "N/A"
                            : value.toString();

            // -----------------------------------------------
            // DETAIL ROW
            // -----------------------------------------------

            JPanel detailRow =
                    new JPanel(
                            new BorderLayout(
                                    15,
                                    0
                            )
                    );

            detailRow.setBackground(
                    Color.WHITE
            );

            detailRow.setBorder(
                    new EmptyBorder(
                            9,
                            5,
                            9,
                            5
                    )
            );

            detailRow.setMaximumSize(
                    new Dimension(
                            Integer.MAX_VALUE,
                            50
                    )
            );

            // -----------------------------------------------
            // FIELD LABEL
            // -----------------------------------------------

            JLabel fieldLabel =
                    new JLabel(
                            columnName
                                    + ":"
                    );

            fieldLabel.setFont(
                    new Font(
                            "SansSerif",
                            Font.BOLD,
                            14
                    )
            );

            fieldLabel.setForeground(
                    new Color(
                            45,
                            65,
                            75
                    )
            );

            fieldLabel.setPreferredSize(
                    new Dimension(
                            180,
                            30
                    )
            );

            // -----------------------------------------------
            // VALUE LABEL
            // -----------------------------------------------

            JLabel valueLabel =
                    new JLabel(
                            valueText
                    );

            valueLabel.setFont(
                    new Font(
                            "SansSerif",
                            Font.PLAIN,
                            14
                    )
            );

            valueLabel.setForeground(
                    Color.BLACK
            );

            // -----------------------------------------------
            // ADD LABELS
            // -----------------------------------------------

            detailRow.add(
                    fieldLabel,
                    BorderLayout.WEST
            );

            detailRow.add(
                    valueLabel,
                    BorderLayout.CENTER
            );

            detailsPanel.add(
                    detailRow
            );

            // -----------------------------------------------
            // SEPARATOR
            // -----------------------------------------------

            JSeparator separator =
                    new JSeparator();

            separator.setForeground(
                    new Color(
                            225,
                            230,
                            233
                    )
            );

            detailsPanel.add(
                    separator
            );
        }

        // =====================================================
        // SCROLL DETAILS
        // =====================================================

        JScrollPane detailsScroll =
                new JScrollPane(
                        detailsPanel
                );

        detailsScroll.setBorder(
                BorderFactory.createEmptyBorder()
        );

        detailsScroll.getVerticalScrollBar()
                .setUnitIncrement(
                        16
                );

        dialog.add(
                detailsScroll,
                BorderLayout.CENTER
        );

        // =====================================================
        // POPUP BUTTON PANEL
        // =====================================================

        JPanel popupButtonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.RIGHT,
                                10,
                                10
                        )
                );

        popupButtonPanel.setBackground(
                new Color(
                        235,
                        241,
                        245
                )
        );

        // =====================================================
        // CLOSE POPUP
        // =====================================================

        JButton closeButton =
                createButton(
                        "CLOSE"
                );

        closeButton.setPreferredSize(
                new Dimension(
                        100,
                        38
                )
        );

        closeButton.addActionListener(
                e -> dialog.dispose()
        );

        popupButtonPanel.add(
                closeButton
        );

        dialog.add(
                popupButtonPanel,
                BorderLayout.SOUTH
        );

        // =====================================================
        // SHOW POPUP
        // =====================================================

        dialog.setVisible(
                true
        );
    }

    // =========================================================
    // POPUP TITLE
    // =========================================================

    private String getPopupTitle() {

        switch (
                currentReportType
        ) {

            case "APPOINTMENT":

                return "Appointment Details";

            case "PATIENT":

                return "Patient Details";

            case "BILLING":

                return "Billing Details";

            default:

                return "Record Details";
        }
    }

    // =========================================================
    // CLEAR TABLE
    // =========================================================

    private void clearTable() {

        clearSearchFilter();

        tableModel.setRowCount(
                0
        );

        tableModel.setColumnCount(
                0
        );

        resultCountLabel.setText(
                "Loading..."
        );
    }

    // =========================================================
    // LOADING MESSAGE
    // =========================================================

    private void setLoading(
            String message
    ) {

        reportTitleLabel.setText(
                message
        );

        clearTable();
    }

    // =========================================================
    // FINISH LOADING
    // =========================================================

    private void finishLoading(
            int count
    ) {

        resultCountLabel.setText(
                "Showing "
                        + count
                        + " record(s)"
        );

        if (
                count == 0
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "No records were found for this report.",
                    "Report",
                    JOptionPane.INFORMATION_MESSAGE
            );
        }

        resizeColumns();
    }

    // =========================================================
    // RESIZE TABLE COLUMNS
    // =========================================================

    private void resizeColumns() {

        for (
                int column = 0;
                column < reportTable.getColumnCount();
                column++
        ) {

            int width =
                    100;

            // -------------------------------------------------
            // COLUMN HEADER WIDTH
            // -------------------------------------------------

            String headerText =
                    reportTable
                            .getColumnName(column);

            if (
                    headerText != null
            ) {

                width =
                        Math.max(
                                width,
                                reportTable
                                        .getFontMetrics(
                                                reportTable
                                                        .getFont()
                                        )
                                        .stringWidth(
                                                headerText
                                        )
                                        + 40
                        );
            }

            // -------------------------------------------------
            // DATA WIDTH
            // -------------------------------------------------

            for (
                    int row = 0;
                    row < reportTable.getRowCount();
                    row++
            ) {

                Object value =
                        reportTable.getValueAt(
                                row,
                                column
                        );

                if (
                        value != null
                ) {

                    String text =
                            value.toString();

                    int textWidth =
                            reportTable
                                    .getFontMetrics(
                                            reportTable.getFont()
                                    )
                                    .stringWidth(
                                            text
                                    );

                    width =
                            Math.max(
                                    width,
                                    textWidth + 35
                            );
                }
            }

            // -------------------------------------------------
            // LIMIT WIDTH
            // -------------------------------------------------

            width =
                    Math.min(
                            width,
                            300
                    );

            reportTable
                    .getColumnModel()
                    .getColumn(column)
                    .setPreferredWidth(
                            width
                    );
        }
    }

    // =========================================================
    // PRINT REPORT
    // =========================================================

    private void printReport() {

        if (
                reportTable.getColumnCount() == 0
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a report before printing.",
                    "Print Report",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (
                reportTable.getRowCount() == 0
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "There are no records to print.",
                    "Print Report",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        try {

            MessageFormat header =
                    new MessageFormat(
                            "Sunrise Dental Clinic - "
                                    + currentReportType
                                    + " REPORT"
                    );

            MessageFormat footer =
                    new MessageFormat(
                            "Page {0}"
                    );

            boolean complete =
                    reportTable.print(
                            JTable.PrintMode.FIT_WIDTH,
                            header,
                            footer
                    );

            if (
                    complete
            ) {

                JOptionPane.showMessageDialog(
                        this,
                        "Report printed successfully.",
                        "Print Report",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }

        } catch (
                PrinterException ex
        ) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to print the report.\n\n"
                            + ex.getMessage(),
                    "Print Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // ERROR MESSAGE
    // =========================================================

    private void showError(
            String message,
            Exception ex
    ) {

        ex.printStackTrace();

        String errorMessage =
                ex.getMessage();

        if (
                errorMessage == null
                        ||
                        errorMessage.trim().isEmpty()
        ) {

            errorMessage =
                    "Unknown error occurred.";
        }

        JOptionPane.showMessageDialog(
                this,
                message
                        + "\n\n"
                        + errorMessage,
                "Report Error",
                JOptionPane.ERROR_MESSAGE
        );

        reportTitleLabel.setText(
                "Unable to load report"
        );

        resultCountLabel.setText(
                "An error occurred."
        );
    }
}