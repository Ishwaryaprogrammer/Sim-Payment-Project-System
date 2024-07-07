import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Calendar;
import javax.swing.table.TableColumnModel;

public class MainScreenReport extends JFrame {
    DefaultTableModel tableModel;
    JTable annualTable;
    Connection conn;

    static final String JDBC_URL = "jdbc:mysql://localhost:3306/spps";
    static final String USERNAME = "root";
    static final String PASSWORD = "kalaivani"; // Replace with your MySQL password

    public MainScreenReport() {
        setTitle("Annual Report-SIM PROJECT PAYMENT SYSTEM");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\logo.png"));

        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
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

        // Initialize components
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        annualTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(annualTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        // Set column headers
        tableModel.addColumn("Month");
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        for (int year = 2005; year <= currentYear; year++) {
            tableModel.addColumn(String.valueOf(year));
        }

        // Set row height
        annualTable.setRowHeight(40);

        // Fetch and display annual records from 2005 to current year
        fetchAnnualRecords(currentYear);
        annualTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Prevent automatic resizing
        TableColumnModel columnModel = annualTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(150); // Month column
        for (int i = 1; i < columnModel.getColumnCount(); i++) {
            columnModel.getColumn(i).setPreferredWidth(100); // Set preferred width for other columns
        }

        // Add components to the frame
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

    private void fetchAnnualRecords(int currentYear) {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String sql = "SELECT MONTHNAME(received_date) AS Month, " +
             "YEAR(received_date) AS Year, " +
             "SUM(amount_received_in_inr) AS Amount " +
             "FROM invoice " +
             "WHERE YEAR(received_date) BETWEEN 2005 AND ? " +
             "GROUP BY MONTH(received_date), YEAR(received_date), MONTHNAME(received_date) " +
             "ORDER BY YEAR(received_date), MONTH(received_date)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, currentYear);
            ResultSet rs = pstmt.executeQuery();

            // Clear existing rows
            tableModel.setRowCount(0);

            // Initialize array to hold monthly totals
            double[] yearlyTotals = new double[currentYear - 2004]; // 2005 to current year

            while (rs.next()) {
                String month = rs.getString("Month");
                int year = rs.getInt("Year");
                double amount = rs.getDouble("Amount");
                
                // Find the column index for the current year
                int columnIndex = year - 2005 + 1; // Adding 1 to account for Month column

                // Update yearly totals
                yearlyTotals[columnIndex - 1] += amount;

                // Check if month row exists, if not, add a new row
                boolean rowExists = false;
                for (int i = 0; i < tableModel.getRowCount(); i++) {
                    if (tableModel.getValueAt(i, 0).equals(month)) {
                        tableModel.setValueAt(amount, i, columnIndex);
                        rowExists = true;
                        break;
                    }
                }
                if (!rowExists) {
                    Object[] rowData = new Object[currentYear - 2004 + 1];
                    rowData[0] = month;
                    rowData[columnIndex] = amount;
                    tableModel.addRow(rowData);
                }
            }

            // Add totals row
            Object[] totalsRow = new Object[currentYear - 2004 + 1];
            totalsRow[0] = "Total";
            for (int i = 1; i <= currentYear - 2004; i++) {
                totalsRow[i] = yearlyTotals[i - 1];
            }
            tableModel.addRow(totalsRow);

            rs.close();
            pstmt.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error retrieving data from database.", "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
