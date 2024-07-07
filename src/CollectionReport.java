import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CollectionReport extends JFrame {
    DefaultTableModel tableModel;
    JTable collectionTable;
    JButton searchButton, refreshButton;
    JTextField yearField;
    JLabel yearLabel;
    Connection conn;
    
    ImageIcon refreshIcon, searchIcon;

    static final String JDBC_URL = "jdbc:mysql://localhost:3306/spps";
    static final String USERNAME = "root";
    static final String PASSWORD = "kalaivani"; // Replace with your MySQL password

    public CollectionReport() {
        setTitle("Collection Report-SIM PROJECT PAYMENT SYSTEM");
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
        collectionTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(collectionTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        refreshButton = new JButton();
        searchButton = new JButton();
        yearField = new JTextField(10);
        yearLabel = new JLabel("Year:");
        refreshIcon = new ImageIcon("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\refresh_icon.png");
        searchIcon = new ImageIcon("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\search_icon.png");
        refreshButton.setIcon(refreshIcon);
        searchButton.setIcon(searchIcon);

        // Set button size
        Dimension buttonSize = new Dimension(40, 40);
        searchButton.setPreferredSize(buttonSize);
        refreshButton.setPreferredSize(buttonSize);

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
        JMenuItem collectionMenuItem = new JMenuItem("Collection Report");
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
        tableModel.addColumn("Customer ID");
        tableModel.addColumn("Jan");
        tableModel.addColumn("Feb");
        tableModel.addColumn("Mar");
        tableModel.addColumn("Apr");
        tableModel.addColumn("May");
        tableModel.addColumn("Jun");
        tableModel.addColumn("Jul");
        tableModel.addColumn("Aug");
        tableModel.addColumn("Sep");
        tableModel.addColumn("Oct");
        tableModel.addColumn("Nov");
        tableModel.addColumn("Dec");

        // Set row height
        collectionTable.setRowHeight(40);

        // Fetch and display collection records based on default year
        fetchCollectionRecords(2023); // Replace with your default year

        // Search by year
        searchButton.addActionListener(e -> {
            String yearText = yearField.getText().trim();
            if (!yearText.isEmpty()) {
                try {
                    int year = Integer.parseInt(yearText);
                    fetchCollectionRecords(year);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Please enter a valid year.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a year.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Refresh button action
        refreshButton.addActionListener(e -> {
            yearField.setText("");
            fetchCollectionRecords(2023); // Replace with your default year
        });

        // Add components to the frame
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(refreshButton);
        buttonPanel.add(yearLabel);
        buttonPanel.add(yearField);
        buttonPanel.add(searchButton);

        add(buttonPanel, BorderLayout.NORTH);
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


 private void fetchCollectionRecords(int year) {
    try {
        conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
        String sql = "SELECT customer_id, " +
                     "SUM(CASE WHEN MONTH(received_date) = 1 THEN amount_received_in_inr ELSE 0 END) AS Jan, " +
                     "SUM(CASE WHEN MONTH(received_date) = 2 THEN amount_received_in_inr ELSE 0 END) AS Feb, " +
                     "SUM(CASE WHEN MONTH(received_date) = 3 THEN amount_received_in_inr ELSE 0 END) AS Mar, " +
                     "SUM(CASE WHEN MONTH(received_date) = 4 THEN amount_received_in_inr ELSE 0 END) AS Apr, " +
                     "SUM(CASE WHEN MONTH(received_date) = 5 THEN amount_received_in_inr ELSE 0 END) AS May, " +
                     "SUM(CASE WHEN MONTH(received_date) = 6 THEN amount_received_in_inr ELSE 0 END) AS Jun, " +
                     "SUM(CASE WHEN MONTH(received_date) = 7 THEN amount_received_in_inr ELSE 0 END) AS Jul, " +
                     "SUM(CASE WHEN MONTH(received_date) = 8 THEN amount_received_in_inr ELSE 0 END) AS Aug, " +
                     "SUM(CASE WHEN MONTH(received_date) = 9 THEN amount_received_in_inr ELSE 0 END) AS Sep, " +
                     "SUM(CASE WHEN MONTH(received_date) = 10 THEN amount_received_in_inr ELSE 0 END) AS Oct, " +
                     "SUM(CASE WHEN MONTH(received_date) = 11 THEN amount_received_in_inr ELSE 0 END) AS Nov, " +
                     "SUM(CASE WHEN MONTH(received_date) = 12 THEN amount_received_in_inr ELSE 0 END) AS `Dec` " +
                     "FROM invoice WHERE YEAR(received_date) = ? GROUP BY customer_id " +
                     "UNION ALL " +
                     "SELECT 'Total' AS customer_id, " +
                     "SUM(CASE WHEN MONTH(received_date) = 1 THEN amount_received_in_inr ELSE 0 END) AS Jan, " +
                     "SUM(CASE WHEN MONTH(received_date) = 2 THEN amount_received_in_inr ELSE 0 END) AS Feb, " +
                     "SUM(CASE WHEN MONTH(received_date) = 3 THEN amount_received_in_inr ELSE 0 END) AS Mar, " +
                     "SUM(CASE WHEN MONTH(received_date) = 4 THEN amount_received_in_inr ELSE 0 END) AS Apr, " +
                     "SUM(CASE WHEN MONTH(received_date) = 5 THEN amount_received_in_inr ELSE 0 END) AS May, " +
                     "SUM(CASE WHEN MONTH(received_date) = 6 THEN amount_received_in_inr ELSE 0 END) AS Jun, " +
                     "SUM(CASE WHEN MONTH(received_date) = 7 THEN amount_received_in_inr ELSE 0 END) AS Jul, " +
                     "SUM(CASE WHEN MONTH(received_date) = 8 THEN amount_received_in_inr ELSE 0 END) AS Aug, " +
                     "SUM(CASE WHEN MONTH(received_date) = 9 THEN amount_received_in_inr ELSE 0 END) AS Sep, " +
                     "SUM(CASE WHEN MONTH(received_date) = 10 THEN amount_received_in_inr ELSE 0 END) AS Oct, " +
                     "SUM(CASE WHEN MONTH(received_date) = 11 THEN amount_received_in_inr ELSE 0 END) AS Nov, " +
                     "SUM(CASE WHEN MONTH(received_date) = 12 THEN amount_received_in_inr ELSE 0 END) AS `Dec` " +
                     "FROM invoice WHERE YEAR(received_date) = ?";
        PreparedStatement pstmt = conn.prepareStatement(sql);
        pstmt.setInt(1, year); // Set year for the first parameter
        pstmt.setInt(2, year); // Set year for the second parameter
        ResultSet rs = pstmt.executeQuery();
        tableModel.setRowCount(0); // Clear existing rows
        while (rs.next()) {
            Object[] rowData = new Object[14]; // Adjust based on columns fetched
            for (int i = 1; i <= 13; i++) {
                rowData[i - 1] = rs.getObject(i);
            }
            tableModel.addRow(rowData);
        }
        rs.close();
        pstmt.close();
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error retrieving data from database.", "Database Error", JOptionPane.ERROR_MESSAGE);
    }
}

}
