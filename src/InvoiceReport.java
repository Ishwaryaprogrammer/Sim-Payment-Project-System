import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InvoiceReport extends JFrame {
    DefaultTableModel tableModel;
    JTable invoiceTable;
    JButton searchButton, refreshButton, projectSearchButton, customerSearchButton, dateSearchButton, categorySearchButton;
    JTextField searchField, projectSearchField, customerSearchField, fromDateField, toDateField, categorySearchField;
    JLabel searchLabel, projectSearchLabel, customerSearchLabel, fromDateLabel, toDateLabel, categorySearchLabel;
    Connection conn;
    ImageIcon refreshIcon, searchIcon;

    static final String JDBC_URL = "jdbc:mysql://localhost:3306/spps";
    static final String USERNAME = "root";
    static final String PASSWORD = "kalaivani"; // Replace with your MySQL password

    public InvoiceReport() {
        setTitle("InvoiceReport-SIM PROJECT PAYMENT SYSTEM");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\logo.png"));

        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        invoiceTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(invoiceTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        refreshButton = new JButton();
        searchButton = new JButton();
        searchField = new JTextField(8);
        searchLabel = new JLabel("Invoice No:");
        projectSearchButton = new JButton();
        projectSearchField = new JTextField(8);
        projectSearchLabel = new JLabel("Project ID:");
        customerSearchButton = new JButton();
        customerSearchField = new JTextField(8);
        customerSearchLabel = new JLabel("Customer ID:");
        fromDateLabel = new JLabel("From Date (yyyy-mm-dd):");
        fromDateField = new JTextField(10);
        toDateLabel = new JLabel("To Date (yyyy-mm-dd):");
        toDateField = new JTextField(10);
        dateSearchButton = new JButton();
        categorySearchButton = new JButton();
        categorySearchField = new JTextField(8);
        categorySearchLabel = new JLabel("Category ID:");
        refreshIcon = new ImageIcon("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\refresh_icon.png");
        searchIcon = new ImageIcon("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\search_icon.png");
        refreshButton.setIcon(refreshIcon);
        searchButton.setIcon(searchIcon);
        projectSearchButton.setIcon(searchIcon);
        customerSearchButton.setIcon(searchIcon);
        dateSearchButton.setIcon(searchIcon);
        categorySearchButton.setIcon(searchIcon);

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create menus
        JMenu homeMenu = new JMenu("Home");
        JMenu categoryMenu = new JMenu("Category");
        JMenu customerMenu = new JMenu("Customer");
        JMenu projectMenu = new JMenu("Project");
        JMenu invoiceMenu = new JMenu("Invoice");
        JMenu reportMenu = new JMenu("Report");

        // Create menu items for category menu
        JMenuItem homeMenuItem = new JMenuItem("Home");
        homeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openhome();
            }
        });
        homeMenu.add(homeMenuItem);

        JMenuItem categoryMenuItem = new JMenuItem("Category");
        categoryMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                opencategory();
            }
        });
        categoryMenu.add(categoryMenuItem);

        JMenuItem customerMenuItem = new JMenuItem("Customer");
        customerMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                opencustomer();
            }
        });
        customerMenu.add(customerMenuItem);

        // Create menu items for project menu
        JMenuItem projectMenuItem = new JMenuItem("Project");
        projectMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openproject();
            }
        });
        JMenuItem projectrepMenuItem = new JMenuItem("Projectreport");
        projectrepMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                projectrep();
            }
        });
        projectMenu.add(projectMenuItem);
        projectMenu.add(projectrepMenuItem);

        // Create menu items for invoice menu
        JMenuItem invoiceMenuItem = new JMenuItem("Invoice");
        invoiceMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openinvoice();
            }
        });
        JMenuItem invoicerepMenuItem = new JMenuItem("Invoicereport");
        invoicerepMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                invoicerep();
            }
        });
        invoiceMenu.add(invoiceMenuItem);
        invoiceMenu.add(invoicerepMenuItem);

        // Create menu items for report menu
        JMenuItem reportMenuItem = new JMenuItem("AnnualReport");
        reportMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openreport();
            }
        });
        JMenuItem collectionMenuItem = new JMenuItem("CollectionReport");
        collectionMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openCollectionReport();
            }
        });
        
        reportMenu.add(reportMenuItem);
        reportMenu.add(collectionMenuItem);
        
        menuBar.add(homeMenu);
        menuBar.add(categoryMenu);
        menuBar.add(customerMenu);
        menuBar.add(projectMenu);
        menuBar.add(invoiceMenu);
        menuBar.add(reportMenu);

        // Set the menu bar
        setJMenuBar(menuBar);

        // Set column headers
        tableModel.addColumn("Invoice No");
        tableModel.addColumn("Customer ID");
        tableModel.addColumn("Project ID");
        tableModel.addColumn("PO No");
        tableModel.addColumn("Invoice Date");
        tableModel.addColumn("Description");
        tableModel.addColumn("Invoice Type");
        tableModel.addColumn("Inv Amount in Euro");
        tableModel.addColumn("Inv Amount in USD");
        tableModel.addColumn("Inv Amount in INR");
        tableModel.addColumn("GST Amount");
        tableModel.addColumn("TDS Deducted");
        tableModel.addColumn("Retention Amount");
        tableModel.addColumn("Amount Received");
        tableModel.addColumn("Amount Received in INR");
        tableModel.addColumn("Received Date");
        tableModel.addColumn("FIRC Details");
        tableModel.addColumn("Delay");

        // Set row height
        invoiceTable.setRowHeight(40);

        // Set preferred column widths
        int[] columnWidths = {100, 100, 100, 100, 100, 500, 100, 150, 150, 150, 100, 100, 100, 150, 150, 100, 200, 100};
        for (int i = 0; i < columnWidths.length; i++) {
            invoiceTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }

        // Set button size
        Dimension buttonSize = new Dimension(40, 40);
        searchButton.setPreferredSize(buttonSize);
        refreshButton.setPreferredSize(buttonSize);
        projectSearchButton.setPreferredSize(buttonSize);
        customerSearchButton.setPreferredSize(buttonSize);
        dateSearchButton.setPreferredSize(buttonSize);
        categorySearchButton.setPreferredSize(buttonSize);

        // Add components to the frame
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(refreshButton);
        buttonPanel.add(searchLabel);
        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);
        buttonPanel.add(projectSearchLabel);
        buttonPanel.add(projectSearchField);
        buttonPanel.add(projectSearchButton);
        buttonPanel.add(customerSearchLabel);
        buttonPanel.add(customerSearchField);
        buttonPanel.add(customerSearchButton);
        buttonPanel.add(fromDateLabel);
        buttonPanel.add(fromDateField);
        buttonPanel.add(toDateLabel);
        buttonPanel.add(toDateField);
        buttonPanel.add(dateSearchButton);
        buttonPanel.add(categorySearchLabel);
        buttonPanel.add(categorySearchField);
        buttonPanel.add(categorySearchButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane);

        // Fetch and display invoice records
        fetchInvoiceRecords();

        // Search by Invoice No
        searchButton.addActionListener(e -> {
            String invoiceNo = searchField.getText().trim();
            if (!invoiceNo.isEmpty()) {
                fetchInvoiceByID(invoiceNo);
            } else {
                fetchInvoiceRecords();
            }
        });

        // Search by Project ID
        projectSearchButton.addActionListener(e -> {
            String projectId = projectSearchField.getText().trim();
            if (!projectId.isEmpty()) {
                fetchInvoiceByProjectID(projectId);
            } else {
                fetchInvoiceRecords();
            }
        });

        // Search by Customer ID
        customerSearchButton.addActionListener(e -> {
            String customerId = customerSearchField.getText().trim();
            if (!customerId.isEmpty()) {
                fetchInvoiceByCustomerID(customerId);
            } else {
                fetchInvoiceRecords();
            }
        });

        // Search by Date Range
        dateSearchButton.addActionListener(e -> {
            String fromDate = fromDateField.getText().trim();
            String toDate = toDateField.getText().trim();
            if (!fromDate.isEmpty() && !toDate.isEmpty()) {
                fetchInvoiceByDateRange(fromDate, toDate);
            } else {
                fetchInvoiceRecords();
            }
        });

        // Search by Category ID
        categorySearchButton.addActionListener(e -> {
            String categoryId = categorySearchField.getText().trim();
            if (!categoryId.isEmpty()) {
                fetchInvoiceByCategoryID(categoryId);
            } else {
                fetchInvoiceRecords();
            }
        });
        
        // Refresh button action
        refreshButton.addActionListener(e -> {
        fetchInvoiceRecords();
        searchField.setText("");
        categorySearchField.setText("");
        customerSearchField.setText("");
        fromDateField.setText("");
        toDateField.setText("");
    });

        invoiceTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Prevent automatic resizing
        invoiceTable.getTableHeader().setResizingAllowed(true); // Allow resizing via table header
        add(scrollPane);

        setVisible(true);
    }

    private void openhome() {
        dispose();
        new HomeScreen();
    }

    private void opencustomer() {
        dispose();
        new MainScreenCustomer();
    }

    private void opencategory() {
        dispose();
        new MainScreenCategory();
    }

    private void openproject() {
        dispose();
        new MainScreenProject();
    }
    private void openCollectionReport() {
        dispose();
        new CollectionReport();
    }

    
    private void projectrep() {
        dispose();
        new ProjectReport();
    }

    private void openinvoice() {
        dispose();
        new MainScreenInvoice();
    }

    
    private void invoicerep() {
        dispose();
        new InvoiceReport();
    }

    private void openreport() {
        dispose();
        new MainScreenReport();
    }
    // Fetch all invoice records
    private void fetchInvoiceRecords() {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM invoice";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            tableModel.setRowCount(0); // Clear previous data

            while (rs.next()) {
                Object[] row = {
                    rs.getString("invoice_no"),
                    rs.getString("customer_id"),
                    rs.getString("ProjectID"),
                    rs.getString("po_no"),
                    rs.getString("invoice_date"),
                    rs.getString("description"),
                    rs.getString("invoice_type"),
                    rs.getDouble("inv_amount_in_euro"),
                    rs.getDouble("inv_amount_in_usd"),
                    rs.getDouble("inv_amount_in_inr"),
                    rs.getDouble("gst_amount"),
                    rs.getDouble("tds_deducted"),
                    rs.getDouble("retention_amount"),
                    rs.getDouble("amount_received"),
                    rs.getDouble("amount_received_in_inr"),
                    rs.getString("received_date"),
                    rs.getString("firc_details"),
                    rs.getInt("delay")
                };
                tableModel.addRow(row);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch invoice records by invoice_no
    private void fetchInvoiceByID(String invoiceNo) {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM invoice WHERE invoice_no LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%" + invoiceNo + "%");
            ResultSet rs = pstmt.executeQuery();

            tableModel.setRowCount(0); // Clear previous data

            while (rs.next()) {
                Object[] row = {
                    rs.getString("invoice_no"),
                    rs.getString("customer_id"),
                    rs.getString("ProjectID"),
                    rs.getString("po_no"),
                    rs.getString("invoice_date"),
                    rs.getString("description"),
                    rs.getString("invoice_type"),
                    rs.getDouble("inv_amount_in_euro"),
                    rs.getDouble("inv_amount_in_usd"),
                    rs.getDouble("inv_amount_in_inr"),
                    rs.getDouble("gst_amount"),
                    rs.getDouble("tds_deducted"),
                    rs.getDouble("retention_amount"),
                    rs.getDouble("amount_received"),
                    rs.getDouble("amount_received_in_inr"),
                    rs.getString("received_date"),
                    rs.getString("firc_details"),
                    rs.getInt("delay")
                };
                tableModel.addRow(row);
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch invoice records by ProjectID
    private void fetchInvoiceByProjectID(String projectId) {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM invoice WHERE ProjectID LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%" + projectId + "%");
            ResultSet rs = pstmt.executeQuery();

            tableModel.setRowCount(0); // Clear previous data

            while (rs.next()) {
                Object[] row = {
                    rs.getString("invoice_no"),
                    rs.getString("customer_id"),
                    rs.getString("ProjectID"),
                    rs.getString("po_no"),
                    rs.getString("invoice_date"),
                    rs.getString("description"),
                    rs.getString("invoice_type"),
                    rs.getDouble("inv_amount_in_euro"),
                    rs.getDouble("inv_amount_in_usd"),
                    rs.getDouble("inv_amount_in_inr"),
                    rs.getDouble("gst_amount"),
                    rs.getDouble("tds_deducted"),
                    rs.getDouble("retention_amount"),
                    rs.getDouble("amount_received"),
                    rs.getDouble("amount_received_in_inr"),
                    rs.getString("received_date"),
                    rs.getString("firc_details"),
                    rs.getInt("delay")
                };
                tableModel.addRow(row);
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch invoice records by CustomerID
    private void fetchInvoiceByCustomerID(String customerId) {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM invoice WHERE customer_id LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%" + customerId + "%");
            ResultSet rs = pstmt.executeQuery();

            tableModel.setRowCount(0); // Clear previous data

            while (rs.next()) {
                Object[] row = {
                    rs.getString("invoice_no"),
                    rs.getString("customer_id"),
                    rs.getString("ProjectID"),
                    rs.getString("po_no"),
                    rs.getString("invoice_date"),
                    rs.getString("description"),
                    rs.getString("invoice_type"),
                    rs.getDouble("inv_amount_in_euro"),
                    rs.getDouble("inv_amount_in_usd"),
                    rs.getDouble("inv_amount_in_inr"),
                    rs.getDouble("gst_amount"),
                    rs.getDouble("tds_deducted"),
                    rs.getDouble("retention_amount"),
                    rs.getDouble("amount_received"),
                    rs.getDouble("amount_received_in_inr"),
                    rs.getString("received_date"),
                    rs.getString("firc_details"),
                    rs.getInt("delay")
                };
                tableModel.addRow(row);
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch invoice records by Date Range
    private void fetchInvoiceByDateRange(String fromDate, String toDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            // Parse dates to ensure they are valid
            sdf.parse(fromDate);
            sdf.parse(toDate);

            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM invoice WHERE invoice_date BETWEEN ? AND ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, fromDate);
            pstmt.setString(2, toDate);
            ResultSet rs = pstmt.executeQuery();

            tableModel.setRowCount(0); // Clear previous data

            while (rs.next()) {
                Object[] row = {
                    rs.getString("invoice_no"),
                    rs.getString("customer_id"),
                    rs.getString("ProjectID"),
                    rs.getString("po_no"),
                    rs.getString("invoice_date"),
                    rs.getString("description"),
                    rs.getString("invoice_type"),
                    rs.getDouble("inv_amount_in_euro"),
                    rs.getDouble("inv_amount_in_usd"),
                    rs.getDouble("inv_amount_in_inr"),
                    rs.getDouble("gst_amount"),
                    rs.getDouble("tds_deducted"),
                    rs.getDouble("retention_amount"),
                    rs.getDouble("amount_received"),
                    rs.getDouble("amount_received_in_inr"),
                    rs.getString("received_date"),
                    rs.getString("firc_details"),
                    rs.getInt("delay")
                };
                tableModel.addRow(row);
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (ParseException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch invoice records by Category ID
    private void fetchInvoiceByCategoryID(String categoryId) {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM invoice WHERE customer_id in (select customer_id from customer where  category_id LIKE ?)";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%" + categoryId + "%");
            ResultSet rs = pstmt.executeQuery();

            tableModel.setRowCount(0); // Clear previous data

            while (rs.next()) {
                Object[] row = {
                    rs.getString("invoice_no"),
                    rs.getString("customer_id"),
                    rs.getString("ProjectID"),
                    rs.getString("po_no"),
                    rs.getString("invoice_date"),
                    rs.getString("description"),
                    rs.getString("invoice_type"),
                    rs.getDouble("inv_amount_in_euro"),
                    rs.getDouble("inv_amount_in_usd"),
                    rs.getDouble("inv_amount_in_inr"),
                    rs.getDouble("gst_amount"),
                    rs.getDouble("tds_deducted"),
                    rs.getDouble("retention_amount"),
                    rs.getDouble("amount_received"),
                    rs.getDouble("amount_received_in_inr"),
                    rs.getString("received_date"),
                    rs.getString("firc_details"),
                    rs.getInt("delay")
                };
                tableModel.addRow(row);
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

/*import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class InvoiceReport extends JFrame {
    DefaultTableModel tableModel;
    JTable invoiceTable;
    JButton searchButton, refreshButton, projectSearchButton, customerSearchButton;
    JTextField searchField, projectSearchField, customerSearchField;
    JLabel searchLabel, projectSearchLabel, customerSearchLabel;
    Connection conn;
    ImageIcon refreshIcon, searchIcon;
    
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/spps";
    static final String USERNAME = "root";
    static final String PASSWORD = "kalaivani"; // Replace with your MySQL password

    public InvoiceReport() {
        setTitle("InvoiceReport");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        invoiceTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(invoiceTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        refreshButton = new JButton();
        searchButton = new JButton();
        searchField = new JTextField(12);
        searchLabel = new JLabel("Invoice No:");
        projectSearchButton = new JButton();
        projectSearchField = new JTextField(12);
        projectSearchLabel = new JLabel("Project ID:");
        customerSearchButton = new JButton();
        customerSearchField = new JTextField(12);
        customerSearchLabel = new JLabel("Customer ID:");
        refreshIcon = new ImageIcon("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\refresh_icon.png");
        searchIcon = new ImageIcon("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\search_icon.png");
        refreshButton.setIcon(refreshIcon);
        searchButton.setIcon(searchIcon);
        projectSearchButton.setIcon(searchIcon);
        customerSearchButton.setIcon(searchIcon);

        // Create menu bar
        JMenuBar menuBar = new JMenuBar();

        // Create menus
        JMenu homeMenu = new JMenu("Home");
        JMenu categoryMenu = new JMenu("Category");
        JMenu customerMenu = new JMenu("Customer");
        JMenu projectMenu = new JMenu("Project");
        JMenu invoiceMenu = new JMenu("Invoice");
        JMenu reportMenu = new JMenu("Report");

        // Create menu items for category menu
        JMenuItem homeMenuItem = new JMenuItem("Home");
        homeMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openhome();
            }
        });
        homeMenu.add(homeMenuItem);

        JMenuItem categoryMenuItem = new JMenuItem("Category");
        categoryMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                opencategory();
            }
        });
        categoryMenu.add(categoryMenuItem);

        JMenuItem customerMenuItem = new JMenuItem("Customer");
        customerMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                opencustomer();
            }
        });
        customerMenu.add(customerMenuItem);

        // Create menu items for project menu
        JMenuItem projectMenuItem = new JMenuItem("Project");
        projectMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openproject();
            }
        });
        JMenuItem projectrepMenuItem = new JMenuItem("Projectreport");
        projectrepMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                projectrep();
            }
        });
        projectMenu.add(projectMenuItem);
        projectMenu.add(projectrepMenuItem);

        // Create menu items for invoice menu
        JMenuItem invoiceMenuItem = new JMenuItem("Invoice");
        invoiceMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openinvoice();
            }
        });
        JMenuItem invoicerepMenuItem = new JMenuItem("Invoicereport");
        invoicerepMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                invoicerep();
            }
        });
        invoiceMenu.add(invoiceMenuItem);
        invoiceMenu.add(invoicerepMenuItem);

        // Create menu items for report menu
        JMenuItem reportMenuItem = new JMenuItem("Report");
        reportMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openreport();
            }
        });
        reportMenu.add(reportMenuItem);

        menuBar.add(homeMenu);
        menuBar.add(categoryMenu);
        menuBar.add(customerMenu);
        menuBar.add(projectMenu);
        menuBar.add(invoiceMenu);
        menuBar.add(reportMenu);

        // Set the menu bar
        setJMenuBar(menuBar);

        // Set column headers
        tableModel.addColumn("Invoice No");
        tableModel.addColumn("Customer ID");
        tableModel.addColumn("Project ID");
        tableModel.addColumn("PO No");
        tableModel.addColumn("Invoice Date");
        tableModel.addColumn("Description");
        tableModel.addColumn("Invoice Type");
        tableModel.addColumn("Inv Amount in Euro");
        tableModel.addColumn("Inv Amount in USD");
        tableModel.addColumn("Inv Amount in INR");
        tableModel.addColumn("GST Amount");
        tableModel.addColumn("TDS Deducted");
        tableModel.addColumn("Retention Amount");
        tableModel.addColumn("Amount Received");
        tableModel.addColumn("Amount Received in INR");
        tableModel.addColumn("Received Date");
        tableModel.addColumn("FIRC Details");
        tableModel.addColumn("Delay");

        // Set row height
        invoiceTable.setRowHeight(40);

        // Set preferred column widths
        int[] columnWidths = {100, 100, 100, 100, 100, 500, 100, 150, 150, 150, 100, 100, 100, 150, 150, 100, 200, 100};
        for (int i = 0; i < columnWidths.length; i++) {
            invoiceTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }

        // Set button size
        Dimension buttonSize = new Dimension(40, 40);
        searchButton.setPreferredSize(buttonSize);
        refreshButton.setPreferredSize(buttonSize);
        projectSearchButton.setPreferredSize(buttonSize);
        customerSearchButton.setPreferredSize(buttonSize);

        // Add components to the frame
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(refreshButton);
        buttonPanel.add(searchLabel);
        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);
        buttonPanel.add(projectSearchLabel);
        buttonPanel.add(projectSearchField);
        buttonPanel.add(projectSearchButton);
        buttonPanel.add(customerSearchLabel);
        buttonPanel.add(customerSearchField);
        buttonPanel.add(customerSearchButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane);

        // Fetch and display invoice records
        fetchInvoiceRecords();

        // Search by Invoice No
        searchButton.addActionListener(e -> {
            String invoiceNo = searchField.getText().trim();
            if (!invoiceNo.isEmpty()) {
                fetchInvoiceByID(invoiceNo);
            } else {
                fetchInvoiceRecords();
            }
        });

        // Search by Project ID
        projectSearchButton.addActionListener(e -> {
            String projectId = projectSearchField.getText().trim();
            if (!projectId.isEmpty()) {
                fetchInvoiceByProjectID(projectId);
            } else {
                fetchInvoiceRecords();
            }
        });

        // Search by Customer ID
        customerSearchButton.addActionListener(e -> {
            String customerId = customerSearchField.getText().trim();
            if (!customerId.isEmpty()) {
                fetchInvoiceByCustomerID(customerId);
            } else {
                fetchInvoiceRecords();
            }
        });

        // Refresh button action
        refreshButton.addActionListener(e -> {
            fetchInvoiceRecords();
            searchField.setText("");
            projectSearchField.setText("");
            customerSearchField.setText("");
        });

        invoiceTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Prevent automatic resizing
        invoiceTable.getTableHeader().setResizingAllowed(true); // Allow resizing via table header
        add(scrollPane);

        // Show the frame
        setVisible(true);
    }

    private void openhome() {
        dispose();
        new HomeScreen();
    }

    private void opencustomer() {
        dispose();
        new MainScreenCustomer();
    }

    private void opencategory() {
        dispose();
        new MainScreenCategory();
    }

    private void openproject() {
        dispose();
        new MainScreenProject();
    }

    private void projectrep() {
        dispose();
        new ProjectReport();
    }

    private void openinvoice() {
        dispose();
        new MainScreenInvoice();
    }

    private void invoicerep() {
        dispose();
        new InvoiceReport();
    }

    private void openreport() {
        dispose();
        new MainScreenReport();
    }

    // Fetch invoice records from database
    private void fetchInvoiceRecords() {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM invoice";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            tableModel.setRowCount(0); // Clear previous data

            while (rs.next()) {
                Object[] row = {
                    rs.getString("invoice_no"),
                    rs.getString("customer_id"),
                    rs.getString("ProjectID"),
                    rs.getString("po_no"),
                    rs.getString("invoice_date"),
                    rs.getString("description"),
                    rs.getString("invoice_type"),
                    rs.getDouble("inv_amount_in_euro"),
                    rs.getDouble("inv_amount_in_usd"),
                    rs.getDouble("inv_amount_in_inr"),
                    rs.getDouble("gst_amount"),
                    rs.getDouble("tds_deducted"),
                    rs.getDouble("retention_amount"),
                    rs.getDouble("amount_received"),
                    rs.getDouble("amount_received_in_inr"),
                    rs.getString("received_date"),
                    rs.getString("firc_details"),
                    rs.getInt("delay")
                };
                tableModel.addRow(row);
            }

            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch invoice records by invoice_no
    private void fetchInvoiceByID(String invoiceNo) {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM invoice WHERE invoice_no LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%" + invoiceNo + "%");
            ResultSet rs = pstmt.executeQuery();

            tableModel.setRowCount(0); // Clear previous data

            while (rs.next()) {
                Object[] row = {
                    rs.getString("invoice_no"),
                    rs.getString("customer_id"),
                    rs.getString("ProjectID"),
                    rs.getString("po_no"),
                    rs.getString("invoice_date"),
                    rs.getString("description"),
                    rs.getString("invoice_type"),
                    rs.getDouble("inv_amount_in_euro"),
                    rs.getDouble("inv_amount_in_usd"),
                    rs.getDouble("inv_amount_in_inr"),
                    rs.getDouble("gst_amount"),
                    rs.getDouble("tds_deducted"),
                    rs.getDouble("retention_amount"),
                    rs.getDouble("amount_received"),
                    rs.getDouble("amount_received_in_inr"),
                    rs.getString("received_date"),
                    rs.getString("firc_details"),
                    rs.getInt("delay")
                };
                tableModel.addRow(row);
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch invoice records by ProjectID
    private void fetchInvoiceByProjectID(String projectId) {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM invoice WHERE ProjectID LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%" + projectId + "%");
            ResultSet rs = pstmt.executeQuery();

            tableModel.setRowCount(0); // Clear previous data

            while (rs.next()) {
                Object[] row = {
                    rs.getString("invoice_no"),
                    rs.getString("customer_id"),
                    rs.getString("ProjectID"),
                    rs.getString("po_no"),
                    rs.getString("invoice_date"),
                    rs.getString("description"),
                    rs.getString("invoice_type"),
                    rs.getDouble("inv_amount_in_euro"),
                    rs.getDouble("inv_amount_in_usd"),
                    rs.getDouble("inv_amount_in_inr"),
                    rs.getDouble("gst_amount"),
                    rs.getDouble("tds_deducted"),
                    rs.getDouble("retention_amount"),
                    rs.getDouble("amount_received"),
                    rs.getDouble("amount_received_in_inr"),
                    rs.getString("received_date"),
                    rs.getString("firc_details"),
                    rs.getInt("delay")
                };
                tableModel.addRow(row);
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch invoice records by CustomerID
    private void fetchInvoiceByCustomerID(String customerId) {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM invoice WHERE customer_id LIKE ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "%" + customerId + "%");
            ResultSet rs = pstmt.executeQuery();

            tableModel.setRowCount(0); // Clear previous data

            while (rs.next()) {
                Object[] row = {
                    rs.getString("invoice_no"),
                    rs.getString("customer_id"),
                    rs.getString("ProjectID"),
                    rs.getString("po_no"),
                    rs.getString("invoice_date"),
                    rs.getString("description"),
                    rs.getString("invoice_type"),
                    rs.getDouble("inv_amount_in_euro"),
                    rs.getDouble("inv_amount_in_usd"),
                    rs.getDouble("inv_amount_in_inr"),
                    rs.getDouble("gst_amount"),
                    rs.getDouble("tds_deducted"),
                    rs.getDouble("retention_amount"),
                    rs.getDouble("amount_received"),
                    rs.getDouble("amount_received_in_inr"),
                    rs.getString("received_date"),
                    rs.getString("firc_details"),
                    rs.getInt("delay")
                };
                tableModel.addRow(row);
            }

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new InvoiceReport();
    }
}
*/