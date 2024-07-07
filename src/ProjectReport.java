
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class ProjectReport extends JFrame {
    DefaultTableModel tableModel;
    JTable projectTable;
    JButton searchButton, refreshButton, categorySearchButton, customerSearchButton, countrySearchButton;
    JTextField searchField, categorySearchField, customerSearchField, countrySearchField;
    JLabel searchLabel, categorySearchLabel, customerSearchLabel, countrySearchLabel;
    Connection conn;
    ImageIcon refreshIcon, searchIcon;

    static final String JDBC_URL = "jdbc:mysql://localhost:3306/spps";
    static final String USERNAME = "root";
    static final String PASSWORD = "kalaivani"; // Replace with your MySQL password

    public ProjectReport() {
        setTitle("ProjectReport-SIM PROJECT PAYMENT SYSTEM");
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
        projectTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(projectTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        refreshButton = new JButton();
        searchButton = new JButton();
        searchField = new JTextField(12);
        searchLabel = new JLabel("Project ID:");
        categorySearchButton = new JButton();
        categorySearchField = new JTextField(12);
        categorySearchLabel = new JLabel("Category ID:");
        customerSearchButton = new JButton();
        customerSearchField = new JTextField(12);
        customerSearchLabel = new JLabel("Customer ID:");
        countrySearchButton = new JButton();
        countrySearchField = new JTextField(12);
        countrySearchLabel = new JLabel("End Client Country:");
        refreshIcon = new ImageIcon("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\refresh_icon.png");
        searchIcon = new ImageIcon("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\search_icon.png");
        refreshButton.setIcon(refreshIcon);
        searchButton.setIcon(searchIcon);
        categorySearchButton.setIcon(searchIcon);
        customerSearchButton.setIcon(searchIcon);
        countrySearchButton.setIcon(searchIcon);

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
                // Add action here
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

        setJMenuBar(menuBar);

        // Set column headers
        tableModel.addColumn("Project ID");
        tableModel.addColumn("Description");
        tableModel.addColumn("Customer ID");
        tableModel.addColumn("End Client Detail");
        tableModel.addColumn("End Client Country");
        tableModel.addColumn("PO No");
        tableModel.addColumn("PO Date");
        tableModel.addColumn("PO Initial Value");
        tableModel.addColumn("GST Percent");
        tableModel.addColumn("PO Rev1 Value");
        tableModel.addColumn("PO Rev1 Date");
        tableModel.addColumn("PO Rev2 Value");
        tableModel.addColumn("PO Rev2 Date");
        tableModel.addColumn("PO Rev3 Value");
        tableModel.addColumn("PO Rev3 Date");
        tableModel.addColumn("PO Rev4 Value");
        tableModel.addColumn("PO Rev4 Date");
        tableModel.addColumn("Currency");
        tableModel.addColumn("Payment Terms");
        tableModel.addColumn("Project Lead");
        tableModel.addColumn("Expected Completion Date");
        tableModel.addColumn("Actual Completion Date");
        tableModel.addColumn("Completion Percent");

        // Set row height
        projectTable.setRowHeight(40);

        // Set preferred column widths
        int[] columnWidths = {100, 500, 100, 200, 150, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 200, 150, 150, 150};
        for (int i = 0; i < columnWidths.length; i++) {
            projectTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }

        // Set button size
        Dimension buttonSize = new Dimension(40, 40);
        searchButton.setPreferredSize(buttonSize);
        refreshButton.setPreferredSize(buttonSize);
        categorySearchButton.setPreferredSize(buttonSize);
        customerSearchButton.setPreferredSize(buttonSize);
        countrySearchButton.setPreferredSize(buttonSize);

        // Add components to the frame
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(refreshButton);
        buttonPanel.add(searchLabel);
        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);
        buttonPanel.add(categorySearchLabel);
        buttonPanel.add(categorySearchField);
        buttonPanel.add(categorySearchButton);
        buttonPanel.add(customerSearchLabel);
        buttonPanel.add(customerSearchField);
        buttonPanel.add(customerSearchButton);
        buttonPanel.add(countrySearchLabel);
        buttonPanel.add(countrySearchField);
        buttonPanel.add(countrySearchButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane);

        // Fetch and display project records
        fetchProjectRecords();

        // Search by Project ID
        searchButton.addActionListener(e -> {
            String projectId = searchField.getText().trim();
            if (!projectId.isEmpty()) {
                fetchProjectByID(projectId);
            } else {
                fetchProjectRecords();
            }
        });

        // Search by Category ID
        categorySearchButton.addActionListener(e -> {
            String categoryId = categorySearchField.getText().trim();
            if (!categoryId.isEmpty()) {
                fetchProjectByCategoryID(categoryId);
            } else {
                fetchProjectRecords();
            }
        });

        // Search by Customer ID
        customerSearchButton.addActionListener(e -> {
            String customerId = customerSearchField.getText().trim();
            if (!customerId.isEmpty()) {
                fetchProjectByCustomerID(customerId);
            } else {
                fetchProjectRecords();
            }
        });

        // Search by End Client Country
        countrySearchButton.addActionListener(e -> {
            String country = countrySearchField.getText().trim();
            if (!country.isEmpty()) {
                fetchProjectByCountry(country);
            } else {
                fetchProjectRecords();
            }
        });

        // Refresh button action
        refreshButton.addActionListener(e -> {
            fetchProjectRecords();
            searchField.setText("");
            categorySearchField.setText("");
            customerSearchField.setText("");
            countrySearchField.setText("");
        });

        projectTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Prevent automatic resizing
        projectTable.getTableHeader().setResizingAllowed(true); // Allow resizing via table header
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

    private void openinvoice() {
        dispose();
        new MainScreenInvoice();
    }

    private void openreport() {
        dispose();
        new MainScreenReport();
    }

    private void projectrep() {
        dispose();
        new ProjectReport();
    }
    private void openCollectionReport() {
        dispose();
        new CollectionReport();
    }
    
    
    private void invoicerep() {
        dispose();
        new InvoiceReport();
    }


    private void fetchProjectRecords() {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM project";
            ResultSet rs = stmt.executeQuery(sql);
            tableModel.setRowCount(0); // Clear existing data

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getString("ProjectID"),
                    rs.getString("Description"),
                    rs.getString("customer_id"),
                    rs.getString("EndClientDetail"),
                    rs.getString("EndClientCountry"),
                    rs.getString("PONo"),
                    rs.getString("PODate"),
                    rs.getString("POInitialValue"),
                    rs.getString("GSTPercent"),
                    rs.getString("PORev1Value"),
                    rs.getString("PORev1Date"),
                    rs.getString("PORev2Value"),
                    rs.getString("PORev2Date"),
                    rs.getString("PORev3Value"),
                    rs.getString("PORev3Date"),
                    rs.getString("PORev4Value"),
                    rs.getString("PORev4Date"),
                    rs.getString("Currency"),
                    rs.getString("PaymentTerms"),
                    rs.getString("ProjectLead"),
                    rs.getString("ExpectedCompletionDate"),
                    rs.getString("ActualCompletionDate"),
                    rs.getString("CompletionPercent")
                });
            }
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fetchProjectByID(String projectId) {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM project WHERE ProjectID = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, projectId);
            ResultSet rs = pstmt.executeQuery();
            tableModel.setRowCount(0); // Clear existing data

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getString("ProjectID"),
                    rs.getString("Description"),
                    rs.getString("customer_id"),
                    rs.getString("EndClientDetail"),
                    rs.getString("EndClientCountry"),
                    rs.getString("PONo"),
                    rs.getString("PODate"),
                    rs.getString("POInitialValue"),
                    rs.getString("GSTPercent"),
                    rs.getString("PORev1Value"),
                    rs.getString("PORev1Date"),
                    rs.getString("PORev2Value"),
                    rs.getString("PORev2Date"),
                    rs.getString("PORev3Value"),
                    rs.getString("PORev3Date"),
                    rs.getString("PORev4Value"),
                    rs.getString("PORev4Date"),
                    rs.getString("Currency"),
                    rs.getString("PaymentTerms"),
                    rs.getString("ProjectLead"),
                    rs.getString("ExpectedCompletionDate"),
                    rs.getString("ActualCompletionDate"),
                    rs.getString("CompletionPercent")
                });
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fetchProjectByCategoryID(String categoryId) {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM project where customer_id in (select customer_id from customer WHERE category_id=?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, categoryId);
            ResultSet rs = pstmt.executeQuery();
            tableModel.setRowCount(0); // Clear existing data

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getString("ProjectID"),
                    rs.getString("Description"),
                    rs.getString("customer_id"),
                    rs.getString("EndClientDetail"),
                    rs.getString("EndClientCountry"),
                    rs.getString("PONo"),
                    rs.getString("PODate"),
                    rs.getString("POInitialValue"),
                    rs.getString("GSTPercent"),
                    rs.getString("PORev1Value"),
                    rs.getString("PORev1Date"),
                    rs.getString("PORev2Value"),
                    rs.getString("PORev2Date"),
                    rs.getString("PORev3Value"),
                    rs.getString("PORev3Date"),
                    rs.getString("PORev4Value"),
                    rs.getString("PORev4Date"),
                    rs.getString("Currency"),
                    rs.getString("PaymentTerms"),
                    rs.getString("ProjectLead"),
                    rs.getString("ExpectedCompletionDate"),
                    rs.getString("ActualCompletionDate"),
                    rs.getString("CompletionPercent")
                });
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fetchProjectByCustomerID(String customerId) {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM project WHERE customer_id = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, customerId);
            ResultSet rs = pstmt.executeQuery();
            tableModel.setRowCount(0); // Clear existing data

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getString("ProjectID"),
                    rs.getString("Description"),
                    rs.getString("CustomerID"),
                    rs.getString("EndClientDetail"),
                    rs.getString("EndClientCountry"),
                    rs.getString("PONo"),
                    rs.getString("PODate"),
                    rs.getString("POInitialValue"),
                    rs.getString("GSTPercent"),
                    rs.getString("PORev1Value"),
                    rs.getString("PORev1Date"),
                    rs.getString("PORev2Value"),
                    rs.getString("PORev2Date"),
                    rs.getString("PORev3Value"),
                    rs.getString("PORev3Date"),
                    rs.getString("PORev4Value"),
                    rs.getString("PORev4Date"),
                    rs.getString("Currency"),
                    rs.getString("PaymentTerms"),
                    rs.getString("ProjectLead"),
                    rs.getString("ExpectedCompletionDate"),
                    rs.getString("ActualCompletionDate"),
                    rs.getString("CompletionPercent")
                });
            }
            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fetchProjectByCountry(String country) {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String sql = "SELECT * FROM project WHERE EndClientCountry = ?";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, country);
            ResultSet rs = pstmt.executeQuery();
            tableModel.setRowCount(0); // Clear existing data

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getString("ProjectID"),
                    rs.getString("Description"),
                    rs.getString("customer_id"),
                    rs.getString("EndClientDetail"),
                    rs.getString("EndClientCountry"),
                    rs.getString("PONo"),
                    rs.getString("PODate"),
                    rs.getString("POInitialValue"),
                    rs.getString("GSTPercent"),
                    rs.getString("PORev1Value"),
                    rs.getString("PORev1Date"),
                    rs.getString("PORev2Value"),
                    rs.getString("PORev2Date"),
                    rs.getString("PORev3Value"),
                    rs.getString("PORev3Date"),
                    rs.getString("PORev4Value"),
                    rs.getString("PORev4Date"),
                    rs.getString("Currency"),
                    rs.getString("PaymentTerms"),
                    rs.getString("ProjectLead"),
                    rs.getString("ExpectedCompletionDate"),
                    rs.getString("ActualCompletionDate"),
                    rs.getString("CompletionPercent")
                });
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

public class ProjectReport extends JFrame {
    DefaultTableModel tableModel;
    JTable projectTable;
    JButton searchButton, refreshButton, categorySearchButton, customerSearchButton;
    JTextField searchField, categorySearchField, customerSearchField;
    JLabel searchLabel, categorySearchLabel, customerSearchLabel;
    Connection conn;
    ImageIcon refreshIcon, searchIcon;
    
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/spps";
    static final String USERNAME = "root";
    static final String PASSWORD = "kalaivani"; // Replace with your MySQL password

    public ProjectReport() {
        setTitle("ProjectReport");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        projectTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(projectTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        refreshButton = new JButton();
        searchButton = new JButton();
        searchField = new JTextField(12);
        searchLabel = new JLabel("Project ID:");
        categorySearchButton = new JButton();
        categorySearchField = new JTextField(12);
        categorySearchLabel = new JLabel("Category ID:");
        customerSearchButton = new JButton();
        customerSearchField = new JTextField(12);
        customerSearchLabel = new JLabel("Customer ID:");
        refreshIcon = new ImageIcon("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\refresh_icon.png");
        searchIcon = new ImageIcon("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\search_icon.png");
        refreshButton.setIcon(refreshIcon);
        searchButton.setIcon(searchIcon);
        categorySearchButton.setIcon(searchIcon);
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
        invoiceMenu.add(invoiceMenuItem);

        // Create menu items for report menu
        JMenuItem reportMenuItem = new JMenuItem("Report");
        reportMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Add action here
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
        tableModel.addColumn("Project ID");
        tableModel.addColumn("Description");
        tableModel.addColumn("Customer ID");
        tableModel.addColumn("End Client Detail");
        tableModel.addColumn("End Client Country");
        tableModel.addColumn("PO No");
        tableModel.addColumn("PO Date");
        tableModel.addColumn("PO Initial Value");
        tableModel.addColumn("GST Percent");
        tableModel.addColumn("PO Rev1 Value");
        tableModel.addColumn("PO Rev1 Date");
        tableModel.addColumn("PO Rev2 Value");
        tableModel.addColumn("PO Rev2 Date");
        tableModel.addColumn("PO Rev3 Value");
        tableModel.addColumn("PO Rev3 Date");
        tableModel.addColumn("PO Rev4 Value");
        tableModel.addColumn("PO Rev4 Date");
        tableModel.addColumn("Currency");
        tableModel.addColumn("Payment Terms");
        tableModel.addColumn("Project Lead");
        tableModel.addColumn("Expected Completion Date");
        tableModel.addColumn("Actual Completion Date");
        tableModel.addColumn("Completion Percent");

        // Set row height
        projectTable.setRowHeight(40);

        // Set preferred column widths
        int[] columnWidths = {100, 500, 100, 200, 150, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 200, 150, 150, 150};
        for (int i = 0; i < columnWidths.length; i++) {
            projectTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }

        // Set button size
        Dimension buttonSize = new Dimension(40, 40);
        searchButton.setPreferredSize(buttonSize);
        refreshButton.setPreferredSize(buttonSize);
        categorySearchButton.setPreferredSize(buttonSize);
        customerSearchButton.setPreferredSize(buttonSize);

        // Add components to the frame
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(refreshButton);
        buttonPanel.add(searchLabel);
        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);
        buttonPanel.add(categorySearchLabel);
        buttonPanel.add(categorySearchField);
        buttonPanel.add(categorySearchButton);
        buttonPanel.add(customerSearchLabel);
        buttonPanel.add(customerSearchField);
        buttonPanel.add(customerSearchButton);

        add(buttonPanel, BorderLayout.NORTH);
        add(scrollPane);

        // Fetch and display project records
        fetchProjectRecords();

        // Search by Project ID
        searchButton.addActionListener(e -> {
            String projectId = searchField.getText().trim();
            if (!projectId.isEmpty()) {
                fetchProjectByID(projectId);
            } else {
                fetchProjectRecords();
            }
        });

        // Search by Category ID
        categorySearchButton.addActionListener(e -> {
            String categoryId = categorySearchField.getText().trim();
            if (!categoryId.isEmpty()) {
                fetchProjectByCategoryID(categoryId);
            } else {
                fetchProjectRecords();
            }
        });

        // Search by Customer ID
        customerSearchButton.addActionListener(e -> {
            String customerId = customerSearchField.getText().trim();
            if (!customerId.isEmpty()) {
                fetchProjectByCustomerID(customerId);
            } else {
                fetchProjectRecords();
            }
        });

        // Refresh button action
        refreshButton.addActionListener(e -> {
            fetchProjectRecords();
            searchField.setText("");
            categorySearchField.setText("");
            customerSearchField.setText("");
        });
        
        
        projectTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Prevent automatic resizing
        projectTable.getTableHeader().setResizingAllowed(true); // Allow resizing via table header
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
    private void openreport() {
        dispose();
        new MainScreenReport();
    }


    // Fetch project records from database
    private void fetchProjectRecords() {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Project");

            // Clear existing table data
            tableModel.setRowCount(0);

            // Populate the table with fetched project records
            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getString("ProjectID"),
                        resultSet.getString("Description"),
                        resultSet.getString("customer_id"),
                        resultSet.getString("EndClientDetail"),
                        resultSet.getString("EndClientCountry"),
                        resultSet.getString("PONo"),
                        resultSet.getString("PODate"),
                        resultSet.getString("POInitialValue"),
                        resultSet.getString("GSTPercent"),
                        resultSet.getString("PORev1Value"),
                        resultSet.getString("PORev1Date"),
                        resultSet.getString("PORev2Value"),
                        resultSet.getString("PORev2Date"),
                        resultSet.getString("PORev3Value"),
                        resultSet.getString("PORev3Date"),
                        resultSet.getString("PORev4Value"),
                        resultSet.getString("PORev4Date"),
                        resultSet.getString("Currency"),
                        resultSet.getString("PaymentTerms"),
                        resultSet.getString("ProjectLead"),
                        resultSet.getString("ExpectedCompletionDate"),
                        resultSet.getString("ActualCompletionDate"),
                        resultSet.getString("CompletionPercent")
                };
                tableModel.addRow(rowData);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Fetch project records by Project ID
    private void fetchProjectByID(String projectId) {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM Project WHERE ProjectID=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, projectId);
            ResultSet rs = pst.executeQuery();

            // Clear existing table data
            tableModel.setRowCount(0);

            // Populate the table with fetched project records
            while (rs.next()) {
                Object[] rowData = {
                        rs.getString("ProjectID"),
                        rs.getString("Description"),
                        rs.getString("customer_id"),
                        rs.getString("EndClientDetail"),
                        rs.getString("EndClientCountry"),
                        rs.getString("PONo"),
                        rs.getString("PODate"),
                        rs.getString("POInitialValue"),
                        rs.getString("GSTPercent"),
                        rs.getString("PORev1Value"),
                        rs.getString("PORev1Date"),
                        rs.getString("PORev2Value"),
                        rs.getString("PORev2Date"),
                        rs.getString("PORev3Value"),
                        rs.getString("PORev3Date"),
                        rs.getString("PORev4Value"),
                        rs.getString("PORev4Date"),
                        rs.getString("Currency"),
                        rs.getString("PaymentTerms"),
                        rs.getString("ProjectLead"),
                        rs.getString("ExpectedCompletionDate"),
                        rs.getString("ActualCompletionDate"),
                        rs.getString("CompletionPercent")
                };
                tableModel.addRow(rowData);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Fetch project records by Category ID
    private void fetchProjectByCategoryID(String categoryId) {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM Project where customer_id in (select customer_id from customer WHERE category_id=?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, categoryId);
            ResultSet rs = pst.executeQuery();

            // Clear existing table data
            tableModel.setRowCount(0);

            // Populate the table with fetched project records
            while (rs.next()) {
                Object[] rowData = {
                        rs.getString("ProjectID"),
                        rs.getString("Description"),
                        rs.getString("customer_id"),
                        rs.getString("EndClientDetail"),
                        rs.getString("EndClientCountry"),
                        rs.getString("PONo"),
                        rs.getString("PODate"),
                        rs.getString("POInitialValue"),
                        rs.getString("GSTPercent"),
                        rs.getString("PORev1Value"),
                        rs.getString("PORev1Date"),
                        rs.getString("PORev2Value"),
                        rs.getString("PORev2Date"),
                        rs.getString("PORev3Value"),
                        rs.getString("PORev3Date"),
                        rs.getString("PORev4Value"),
                        rs.getString("PORev4Date"),
                        rs.getString("Currency"),
                        rs.getString("PaymentTerms"),
                        rs.getString("ProjectLead"),
                        rs.getString("ExpectedCompletionDate"),
                        rs.getString("ActualCompletionDate"),
                        rs.getString("CompletionPercent")
                };
                tableModel.addRow(rowData);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    // Fetch project records by Customer ID
    private void fetchProjectByCustomerID(String customerId) {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM Project WHERE customer_id=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, customerId);
            ResultSet rs = pst.executeQuery();

            // Clear existing table data
            tableModel.setRowCount(0);

            // Populate the table with fetched project records
            while (rs.next()) {
                Object[] rowData = {
                        rs.getString("ProjectID"),
                        rs.getString("Description"),
                        rs.getString("customer_id"),
                        rs.getString("EndClientDetail"),
                        rs.getString("EndClientCountry"),
                        rs.getString("PONo"),
                        rs.getString("PODate"),
                        rs.getString("POInitialValue"),
                        rs.getString("GSTPercent"),
                        rs.getString("PORev1Value"),
                        rs.getString("PORev1Date"),
                        rs.getString("PORev2Value"),
                        rs.getString("PORev2Date"),
                        rs.getString("PORev3Value"),
                        rs.getString("PORev3Date"),
                        rs.getString("PORev4Value"),
                        rs.getString("PORev4Date"),
                        rs.getString("Currency"),
                        rs.getString("PaymentTerms"),
                        rs.getString("ProjectLead"),
                        rs.getString("ExpectedCompletionDate"),
                        rs.getString("ActualCompletionDate"),
                        rs.getString("CompletionPercent")
                };
                tableModel.addRow(rowData);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ProjectReport::new);
    }
}
*/