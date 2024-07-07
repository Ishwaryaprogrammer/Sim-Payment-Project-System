import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MainScreenInvoice extends JFrame {
    DefaultTableModel tableModel;
    JTable invoiceTable;
    JButton addButton, updateButton, deleteButton, searchButton, refreshButton;
    JTextField searchField;
    JLabel searchLabel;
    ImageIcon addIcon, updateIcon, deleteIcon, refreshIcon, searchIcon;
    Connection conn;
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/spps";
    static final String USERNAME = "root";
    static final String PASSWORD = "kalaivani";

    public MainScreenInvoice() {
        setTitle("Invoice-SIM PROJECT PAYMENT SYSTEM");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\logo.png"));

        setSize(1000, 600);
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

        addButton = new JButton();
        updateButton = new JButton();
        deleteButton = new JButton();
        searchButton = new JButton();
        searchField = new JTextField(20);
        searchLabel = new JLabel("Invoice No:");
        refreshButton = new JButton("");
        addIcon = new ImageIcon("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\add_icon.png"); // Replace with the actual path to your icon
        updateIcon = new ImageIcon("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\update_icon.png"); // Replace with the actual path to your icon
        deleteIcon = new ImageIcon("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\delete_icon.png"); // Replace with the actual path to your icon
        refreshIcon = new ImageIcon("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\refresh_icon.png");
        searchIcon = new ImageIcon("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\search_icon.png");
        addButton.setIcon(addIcon);
        updateButton.setIcon(updateIcon);
        deleteButton.setIcon(deleteIcon);
        refreshButton.setIcon(refreshIcon);
        searchButton.setIcon(searchIcon);

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
                openHome();
            }
        });
        homeMenu.add(homeMenuItem);

        JMenuItem categoryMenuItem = new JMenuItem("Category");
        categoryMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openCategory();
            }
        });
        categoryMenu.add(categoryMenuItem);

        JMenuItem customerMenuItem = new JMenuItem("Customer");
        customerMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openCustomer();
            }
        });
        customerMenu.add(customerMenuItem);

        JMenuItem projectMenuItem = new JMenuItem("Project");
        projectMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openProject();
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
        

        JMenuItem invoiceMenuItem = new JMenuItem("Invoice");
        invoiceMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openInvoice();
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


        JMenuItem reportMenuItem = new JMenuItem("Annual Report");
        reportMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openReport();
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
        tableModel.addColumn("Project ID");
        tableModel.addColumn("PO No");
        tableModel.addColumn("Invoice No");
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
        int[] columnWidths = {100, 100, 100, 100, 100, 200, 100, 150, 150, 150, 150, 150, 150, 150, 150, 150, 200, 100};
        for (int i = 0; i < columnWidths.length; i++) {
            invoiceTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }
        
        // Set button size
        Dimension buttonSize = new Dimension(40, 40);
        addButton.setPreferredSize(buttonSize);
        updateButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);
        searchButton.setPreferredSize(buttonSize);
        refreshButton.setPreferredSize(buttonSize);

        // Add components to the frame
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(searchLabel);
        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.NORTH);

        // Fetch and display invoice records
        fetchInvoiceRecords();

        // Add action listeners
        addButton.addActionListener(e -> new AddInvoiceForm());
        updateButton.addActionListener(new UpdateButtonAction());
        deleteButton.addActionListener(new DeleteButtonAction());
        searchButton.addActionListener(e -> {
            String invoiceNo = searchField.getText().trim();
            if (!invoiceNo.isEmpty()) {
                fetchInvoiceByID(invoiceNo);
            } else {
                fetchInvoiceRecords();
            }
        });
        refreshButton.addActionListener(e -> {
            fetchInvoiceRecords(); // Refresh invoice records
            searchField.setText("");
        });
        
        invoiceTable.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Prevent automatic resizing
        invoiceTable.getTableHeader().setResizingAllowed(true); // Allow resizing via table header
        add(scrollPane);

        setVisible(true);
    }

    private void openHome() {
        dispose();
        new HomeScreen();
    }

    private void openCustomer() {
        dispose();
        new MainScreenCustomer();
    }

    private void openCategory() {
        dispose();
        new MainScreenCategory();
    }

    private void openProject() {
        dispose();
        new MainScreenProject();
    }

    private void openInvoice() {
        dispose();
        new MainScreenInvoice();
    }
    
    private void invoicerep() {
        dispose();
        new InvoiceReport();
    }


    private void openReport() {
        // Add logic for opening the report screen
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
    

    // Action listener for "Update" button
    class UpdateButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedRow = invoiceTable.getSelectedRow();
            if (selectedRow != -1) {
                String invoiceNo = (String) tableModel.getValueAt(selectedRow, 3); // Invoice No is at column 3
                new UpdateInvoiceForm(invoiceNo);
            } else {
                new UpdateInvoiceForm("");
            }
        }
    }

    // Action listener for "Delete" button
    class DeleteButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            int selectedRow = invoiceTable.getSelectedRow();
            if (selectedRow != -1) {
                String invoiceNo = (String) tableModel.getValueAt(selectedRow, 3); // Invoice No is at column 3
                int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirmDelete == JOptionPane.YES_OPTION) {
                    deleteInvoice(invoiceNo);
                }
            } else {
                String invoiceNo = JOptionPane.showInputDialog(null, "Please enter the Invoice No:");
                if (invoiceNo != null && !invoiceNo.isEmpty()) {
                    int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (confirmDelete == JOptionPane.YES_OPTION) {
                        deleteInvoice(invoiceNo);
                    }
                }
            }
        }
    }

    private void fetchInvoiceRecords() {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            Statement stmt = conn.createStatement();
            String sql = "SELECT * FROM Invoice";
            ResultSet rs = stmt.executeQuery(sql);
            tableModel.setRowCount(0); // Clear existing rows

            while (rs.next()) {
                Object[] row = new Object[18];
                row[0] = rs.getString("customer_id");
                row[1] = rs.getString("projectID");
                row[2] = rs.getString("po_no");
                row[3] = rs.getString("invoice_no");
                row[4] = rs.getDate("invoice_date");
                row[5] = rs.getString("description");
                row[6] = rs.getString("invoice_type");
                row[7] = rs.getBigDecimal("inv_amount_in_euro");
                row[8] = rs.getBigDecimal("inv_amount_in_usd");
                row[9] = rs.getBigDecimal("inv_amount_in_inr");
                row[10] = rs.getBigDecimal("gst_amount");
                row[11] = rs.getBigDecimal("tds_deducted");
                row[12] = rs.getBigDecimal("retention_amount");
                row[13] = rs.getBigDecimal("amount_received");
                row[14] = rs.getBigDecimal("amount_received_in_inr");
                row[15] = rs.getDate("received_date");
                row[16] = rs.getString("firc_details");
                row[17] = rs.getInt("delay");
                tableModel.addRow(row);
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fetchInvoiceByID(String invoiceNo) {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM Invoice WHERE invoice_no = ?");
            pstmt.setString(1, invoiceNo);
            ResultSet rs = pstmt.executeQuery();
            tableModel.setRowCount(0); // Clear existing rows

            while (rs.next()) {
                Object[] row = new Object[18];
                row[0] = rs.getString("customer_id");
                row[1] = rs.getString("projectID");
                row[2] = rs.getString("po_no");
                row[3] = rs.getString("invoice_no");
                row[4] = rs.getDate("invoice_date");
                row[5] = rs.getString("description");
                row[6] = rs.getString("invoice_type");
                row[7] = rs.getBigDecimal("inv_amount_in_euro");
                row[8] = rs.getBigDecimal("inv_amount_in_usd");
                row[9] = rs.getBigDecimal("inv_amount_in_inr");
                row[10] = rs.getBigDecimal("gst_amount");
                row[11] = rs.getBigDecimal("tds_deducted");
                row[12] = rs.getBigDecimal("retention_amount");
                row[13] = rs.getBigDecimal("amount_received");
                row[14] = rs.getBigDecimal("amount_received_in_inr");
                row[15] = rs.getDate("received_date");
                row[16] = rs.getString("firc_details");
                row[17] = rs.getInt("delay");
                tableModel.addRow(row);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void deleteInvoice(String invoiceNo) {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Invoice WHERE invoice_no = ?");
            pstmt.setString(1, invoiceNo);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Record deleted successfully");
                fetchInvoiceRecords(); // Refresh table after deletion
            } else {
                JOptionPane.showMessageDialog(null, "Invoice No not found");
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
