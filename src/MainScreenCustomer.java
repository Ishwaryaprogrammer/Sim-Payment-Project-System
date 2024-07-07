import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MainScreenCustomer extends JFrame {
    DefaultTableModel tableModel;
    JTable customerTable;
    JButton addButton, updateButton, deleteButton, searchButton, refreshButton;
    JTextField searchField;
    JLabel searchLabel;
    ImageIcon addIcon, updateIcon, deleteIcon,refreshIcon,searchIcon;
    Connection conn;
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/spps";
    static final String USERNAME = "root";
    static final String PASSWORD = "kalaivani";

    public MainScreenCustomer() {
        setTitle("Customer-SIM PROJECT PAYMENT SYSTEM");
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
        customerTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(customerTable);
        
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        addButton = new JButton();
        updateButton = new JButton();
        deleteButton = new JButton();
        searchButton = new JButton();
        searchField = new JTextField(20);
        searchLabel = new JLabel("Customer ID:");
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

        // Set the menu bar
        setJMenuBar(menuBar);

        
        // Set column headers
        tableModel.addColumn("Customer ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Category");
        tableModel.addColumn("Address");
        tableModel.addColumn("Country");
        tableModel.addColumn("Type");
        tableModel.addColumn("Contact Details");

        // Set row height
        customerTable.setRowHeight(30);

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

        // Fetch and display customer records
        fetchCustomerRecords();

        // Add action listeners
        addButton.addActionListener((ActionEvent e) -> {
            // Open AddCustomerForm
            new CustomerForm();
        });

        updateButton.addActionListener(new UpdateButtonAction());

        deleteButton.addActionListener(new DeleteButtonAction());

        searchButton.addActionListener(e -> {
            String customerId = searchField.getText().trim();
            if (!customerId.isEmpty()) {
                fetchCustomerByID(customerId);
            } else {
                fetchCustomerRecords();
                
            }
        });


        refreshButton.addActionListener(e -> {
            fetchCustomerRecords(); // Refresh customer records
            searchField.setText("");
        });

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
    
    private void invoicerep() {
        dispose();
        new InvoiceReport();
    }
    private void openinvoice() {
        dispose();
        new MainScreenInvoice();
        //
    }
    private void openreport() {
        dispose();
        new MainScreenReport();
        //
    }
    private void openCollectionReport() {
        dispose();
        new CollectionReport();
    }
    
    
    private void projectrep() {
        dispose();
        new ProjectReport();
    }

    // Action listener for "Update" button
    class UpdateButtonAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get the selected row
            int selectedRow = customerTable.getSelectedRow();

            // Ensure a row is selected
            if (selectedRow != -1) {
                // Fetch customer ID from the selected row
                String customerId = (String) tableModel.getValueAt(selectedRow, 0);
                // Open the UpdateCustomerForm with the customer ID
                new UpdateCustomerForm(customerId);
            } else {
                // Open the UpdateCustomerForm without customer ID
                new UpdateCustomerForm("");
            }
        }
    }

// Action listener for "Delete" button
class DeleteButtonAction implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        // Get the selected row
        int selectedRow = customerTable.getSelectedRow();

        // Check if any row is selected
        if (selectedRow != -1) {
            // Fetch customer ID from the selected row
            String customerId = (String) tableModel.getValueAt(selectedRow, 0);
            // Confirm deletion directly without asking for customer ID
            int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmDelete == JOptionPane.YES_OPTION) {
                // Delete customer
                deleteCustomer(customerId);
            }
        } else {
            // No row selected, ask for the customer ID
            String customerId = JOptionPane.showInputDialog(null, "Please enter the Customer ID:");
            if (customerId != null && !customerId.isEmpty()) {
                try {
                    // Validate customer ID
                    
                    // Check if customer ID exists
                    boolean customerExists = checkCustomerExists(customerId);
                    if (customerExists) {
                        // Confirm deletion after entering customer ID
                        int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (confirmDelete == JOptionPane.YES_OPTION) {
                            // Delete customer
                            deleteCustomer(customerId);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Customer with ID " + customerId + " does not exist.");
                    }
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a Customer ID!");
            }
        }
    }

    // Method to check if a customer with the given ID exists
    private boolean checkCustomerExists(String customerId) {
        boolean exists = false;
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM Customer WHERE customer_id=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, customerId);
            ResultSet rs = pst.executeQuery();
            exists = rs.next(); // If the result set has at least one row, the customer exists
            rs.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return exists;
    }
}



    private void fetchCustomerRecords() {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Customer");

            // Clear existing table data
            tableModel.setRowCount(0);

            // Populate the table with fetched customer records
            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getString("customer_id"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("category_id"),
                        resultSet.getString("address"),
                        resultSet.getString("country"),
                        resultSet.getString("type"),
                        resultSet.getString("contact_details")
                };
                tableModel.addRow(rowData);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void fetchCustomerByID(String customerId) {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM Customer WHERE customer_id=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, customerId);
            ResultSet rs = pst.executeQuery();

            // Clear existing table data
            tableModel.setRowCount(0);

            // Populate the table with fetched customer records
            while (rs.next()) {
                Object[] rowData = {
                        rs.getString("customer_id"),
                        rs.getString("customer_name"),
                        rs.getString("category_id"),
                        rs.getString("address"),
                        rs.getString("country"),
                        rs.getString("type"),
                        rs.getString("contact_details")
                };
                tableModel.addRow(rowData);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteCustomer(String customerId) {
        try {
            String query = "DELETE FROM Customer WHERE customer_id=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, customerId);
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Customer deleted successfully.");
                fetchCustomerRecords(); // Refresh table
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete customer.");
            }
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}





/*enhanced the delte function
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MainScreenCustomer extends JFrame {
    DefaultTableModel tableModel;
    JTable customerTable;
    JButton addButton, updateButton, deleteButton, searchButton, submitButton;
    JTextField searchField;
    ImageIcon addIcon, updateIcon, deleteIcon;
    Connection conn;
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/spps";
    static final String USERNAME = "root";
    static final String PASSWORD = "kalaivani";

    public MainScreenCustomer() {
        setTitle("Customer Records");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        customerTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(customerTable);
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        searchButton = new JButton("Search");
        submitButton = new JButton("Submit");
        searchField = new JTextField(15);
        addIcon = new ImageIcon("add_icon.png"); // Replace with the actual path to your icon
        updateIcon = new ImageIcon("update_icon.png"); // Replace with the actual path to your icon
        deleteIcon = new ImageIcon("delete_icon.png"); // Replace with the actual path to your icon
        addButton.setIcon(addIcon);
        updateButton.setIcon(updateIcon);
        deleteButton.setIcon(deleteIcon);

        // Set column headers
        tableModel.addColumn("Customer ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Category");
        tableModel.addColumn("Address");
        tableModel.addColumn("Country");
        tableModel.addColumn("Type");
        tableModel.addColumn("Contact Details");

        // Set row height
        customerTable.setRowHeight(30);

        // Set button size
        Dimension buttonSize = new Dimension(100, 30);
        addButton.setPreferredSize(buttonSize);
        updateButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);
        searchButton.setPreferredSize(buttonSize);
        submitButton.setPreferredSize(buttonSize);

        // Add components to the frame
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);
        buttonPanel.add(submitButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.NORTH);

        // Fetch and display customer records
        fetchCustomerRecords();

        // Add action listeners
        addButton.addActionListener(e -> {
            // Open AddCustomerForm
            new CustomerForm();
        });

        updateButton.addActionListener(new UpdateButtonAction());

        deleteButton.addActionListener(new DeleteButtonAction());

        searchButton.addActionListener(e -> {
            String customerId = searchField.getText().trim();
            if (!customerId.isEmpty()) {
                fetchCustomerByID(customerId);
            } else {
                fetchCustomerRecords();
            }
        });

        submitButton.addActionListener(e -> {
            // Fetch and display customer records
            fetchCustomerRecords();
        });

        // Show the frame
        setVisible(true);
    }

    // Action listener for "Update" button
    class UpdateButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Get the selected row
            int selectedRow = customerTable.getSelectedRow();

            // Ensure a row is selected
            if (selectedRow != -1) {
                // Fetch customer ID from the selected row
                String customerId = (String) tableModel.getValueAt(selectedRow, 0);
                // Open the UpdateCustomerForm with the customer ID
                new UpdateCustomerForm(customerId);
            } else {
                // Open the UpdateCustomerForm without customer ID
                new UpdateCustomerForm("");
            }
        }
    }

// Action listener for "Delete" button
class DeleteButtonAction implements ActionListener {
    public void actionPerformed(ActionEvent e) {
        // Get the selected row
        int selectedRow = customerTable.getSelectedRow();

        // Check if any row is selected
        if (selectedRow != -1) {
            // Fetch customer ID from the selected row
            String customerId = (String) tableModel.getValueAt(selectedRow, 0);
            // Confirm deletion directly without asking for customer ID
            int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (confirmDelete == JOptionPane.YES_OPTION) {
                // Delete customer
                deleteCustomer(customerId);
            }
        } else {
            // No row selected, ask for the customer ID
            String customerId = JOptionPane.showInputDialog(null, "Please enter the Customer ID:");
            if (customerId != null && !customerId.isEmpty()) {
                // Confirm deletion after entering customer ID
                int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirmDelete == JOptionPane.YES_OPTION) {
                    // Delete customer
                    deleteCustomer(customerId);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please enter a Customer ID!");
            }
        }
    }
}

    private void fetchCustomerRecords() {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Customer");

            // Clear existing table data
            tableModel.setRowCount(0);

            // Populate the table with fetched customer records
            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getString("customer_id"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("category_id"),
                        resultSet.getString("address"),
                        resultSet.getString("country"),
                        resultSet.getString("type"),
                        resultSet.getString("contact_details")
                };
                tableModel.addRow(rowData);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void fetchCustomerByID(String customerId) {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM Customer WHERE customer_id=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, customerId);
            ResultSet rs = pst.executeQuery();

            // Clear existing table data
            tableModel.setRowCount(0);

            // Populate the table with fetched customer records
            while (rs.next()) {
                Object[] rowData = {
                        rs.getString("customer_id"),
                        rs.getString("customer_name"),
                        rs.getString("category_id"),
                        rs.getString("address"),
                        rs.getString("country"),
                        rs.getString("type"),
                        rs.getString("contact_details")
                };
                tableModel.addRow(rowData);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteCustomer(String customerId) {
        try {
            String query = "DELETE FROM Customer WHERE customer_id=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, customerId);
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Customer deleted successfully.");
                fetchCustomerRecords(); // Refresh table
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete customer.");
            }
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainScreenCustomer::new);
    }
}
*/
/*import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MainScreenCustomer extends JFrame {
    DefaultTableModel tableModel;
    JTable customerTable;
    JButton addButton, updateButton, deleteButton, searchButton, submitButton;
    JTextField searchField;
    ImageIcon addIcon, updateIcon, deleteIcon;
    Connection conn;
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/spps";
    static final String USERNAME = "root";
    static final String PASSWORD = "kalaivani";

    public MainScreenCustomer() {
        setTitle("Customer Records");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize components
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        customerTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(customerTable);
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        searchButton = new JButton("Search");
        submitButton = new JButton("Submit");
        searchField = new JTextField(15);
        addIcon = new ImageIcon("add_icon.png"); // Replace with the actual path to your icon
        updateIcon = new ImageIcon("update_icon.png"); // Replace with the actual path to your icon
        deleteIcon = new ImageIcon("delete_icon.png"); // Replace with the actual path to your icon
        addButton.setIcon(addIcon);
        updateButton.setIcon(updateIcon);
        deleteButton.setIcon(deleteIcon);

        // Set column headers
        tableModel.addColumn("Customer ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Category");
        tableModel.addColumn("Address");
        tableModel.addColumn("Country");
        tableModel.addColumn("Type");
        tableModel.addColumn("Contact Details");

        // Set row height
        customerTable.setRowHeight(30);

        // Set button size
        Dimension buttonSize = new Dimension(100, 30);
        addButton.setPreferredSize(buttonSize);
        updateButton.setPreferredSize(buttonSize);
        deleteButton.setPreferredSize(buttonSize);
        searchButton.setPreferredSize(buttonSize);
        submitButton.setPreferredSize(buttonSize);

        // Add components to the frame
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchField);
        buttonPanel.add(searchButton);
        buttonPanel.add(submitButton);

        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.NORTH);

        // Fetch and display customer records
        fetchCustomerRecords();

        // Add action listeners
        addButton.addActionListener(e -> {
            // Open AddCustomerForm
            new CustomerForm();
        });

        updateButton.addActionListener(new UpdateButtonAction());

        deleteButton.addActionListener(new DeleteButtonAction());

        searchButton.addActionListener(e -> {
            String customerId = searchField.getText().trim();
            if (!customerId.isEmpty()) {
                fetchCustomerByID(customerId);
            } else {
                fetchCustomerRecords();
            }
        });

        submitButton.addActionListener(e -> {
            // Fetch and display customer records
            fetchCustomerRecords();
        });

        // Show the frame
        setVisible(true);
    }

    // Action listener for "Update" button
    class UpdateButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Get the selected row
            int selectedRow = customerTable.getSelectedRow();

            // Ensure a row is selected
            if (selectedRow != -1) {
                // Fetch customer ID from the selected row
                String customerId = (String) tableModel.getValueAt(selectedRow, 0);
                // Open the UpdateCustomerForm with the customer ID
                new UpdateCustomerForm(customerId);
            } else {
                JOptionPane.showMessageDialog(null, "Please select a record to update.");
            }
        }
    }

    // Action listener for "Delete" button
    class DeleteButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Get the selected row
            int selectedRow = customerTable.getSelectedRow();

            // Ensure a row is selected
            if (selectedRow != -1) {
                // Fetch customer ID from the selected row
                String customerId = (String) tableModel.getValueAt(selectedRow, 0);
                // Confirm deletion
                int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirmDelete == JOptionPane.YES_OPTION) {
                    // Delete customer
                    deleteCustomer(customerId);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Please select a record to delete.");
            }
        }
    }

    private void fetchCustomerRecords() {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Customer");

            // Clear existing table data
            tableModel.setRowCount(0);

            // Populate the table with fetched customer records
            while (resultSet.next()) {
                Object[] rowData = {
                        resultSet.getString("customer_id"),
                        resultSet.getString("customer_name"),
                        resultSet.getString("category_id"),
                        resultSet.getString("address"),
                        resultSet.getString("country"),
                        resultSet.getString("type"),
                        resultSet.getString("contact_details")
                };
                tableModel.addRow(rowData);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void fetchCustomerByID(String customerId) {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM Customer WHERE customer_id=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, customerId);
            ResultSet rs = pst.executeQuery();

            // Clear existing table data
            tableModel.setRowCount(0);

            // Populate the table with fetched customer records
            while (rs.next()) {
                Object[] rowData = {
                        rs.getString("customer_id"),
                        rs.getString("customer_name"),
                        rs.getString("category_id"),
                        rs.getString("address"),
                        rs.getString("country"),
                        rs.getString("type"),
                        rs.getString("contact_details")
                };
                tableModel.addRow(rowData);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void deleteCustomer(String customerId) {
        try {
            String query = "DELETE FROM Customer WHERE customer_id=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, customerId);
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Customer deleted successfully.");
                fetchCustomerRecords(); // Refresh table
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete customer.");
            }
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainScreenCustomer::new);
    }
}
*/