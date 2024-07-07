import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class MainScreenCategory extends JFrame {
    DefaultTableModel tableModel;
    JTable categoryTable;
    JButton addButton, updateButton, deleteButton, refreshButton, searchButton;
    JTextField searchField;
    JLabel searchLabel;
    ImageIcon addIcon, updateIcon, deleteIcon, refreshIcon, searchIcon;
    Connection conn;
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/spps";
    static final String USERNAME = "root";
    static final String PASSWORD = "kalaivani";

    public MainScreenCategory() {
        setTitle("Category-SIM PROJECT PAYMENT SYSTEM");
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
        categoryTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(categoryTable);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        addButton = new JButton();
        updateButton = new JButton();
        deleteButton = new JButton();
        refreshButton = new JButton();
        searchButton = new JButton();
        searchField = new JTextField(20);
        searchLabel = new JLabel("Category ID:");
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
        tableModel.addColumn("Category ID");
        tableModel.addColumn("Category Name");

        // Set row height
        categoryTable.setRowHeight(30);

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

        // Fetch and display category records
        fetchCategoryRecords();

        // Add action listeners
        addButton.addActionListener(e -> {
            // Open AddCategoryForm
            new AddCategoryForm();
        });
        searchButton.addActionListener(e -> {
            String categoryId = searchField.getText().trim();
            if (!categoryId.isEmpty()) {
                fetchCategoryByID(categoryId);
            } else {
                fetchCategoryRecords();
            }
        });

        updateButton.addActionListener(new UpdateButtonAction());

        deleteButton.addActionListener(new DeleteButtonAction(categoryTable, tableModel));

        refreshButton.addActionListener(e -> {
            fetchCategoryRecords(); // Refresh category records
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
        //
    }
    private void openinvoice() {
        dispose();
        new MainScreenInvoice();
        //
    }
    
    
    private void invoicerep() {
        dispose();
        new InvoiceReport();
    }

    private void projectrep() {
        dispose();
        new ProjectReport();
    }
    private void openreport() {
        dispose();
        new MainScreenReport();
    }
    private void openCollectionReport() {
        dispose();
        new CollectionReport();
    }

    // Action listener for "Update" button
    class UpdateButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Get the selected row
            int selectedRow = categoryTable.getSelectedRow();

            // Ensure a row is selected
            if (selectedRow != -1) {
                // Fetch category ID from the selected row
                String categoryId = (String) tableModel.getValueAt(selectedRow, 0);
                // Open the UpdateCategoryForm with the category ID
                new UpdateCategoryForm(categoryId);
            } else {
                // Open the UpdateCategoryForm without category ID
                new UpdateCategoryForm("");
            }
        }
    }

    // Action listener for "Delete" button
    public class DeleteButtonAction implements ActionListener {
        private JTable categoryTable;
        private DefaultTableModel tableModel;

        public DeleteButtonAction(JTable categoryTable, DefaultTableModel tableModel) {
            this.categoryTable = categoryTable;
            this.tableModel = tableModel;
        }

        public void actionPerformed(ActionEvent e) {
            // Get the selected row
            int selectedRow = categoryTable.getSelectedRow();

            // Check if any row is selected
            if (selectedRow != -1) {
                // Fetch category ID from the selected row
                String categoryId = (String) tableModel.getValueAt(selectedRow, 0);
                // Confirm deletion directly without asking for category ID
                int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirmDelete == JOptionPane.YES_OPTION) {
                    // Delete category
                    deleteCategory(categoryId);
                }
            } else {
                // No row selected, ask for the category ID
                String categoryId = JOptionPane.showInputDialog(null, "Please enter the Category ID:");
                if (categoryId != null && !categoryId.isEmpty()) {
                    try {
                        // Validate category ID
                        if (!categoryId.matches("\\d+")) {
                            throw new IllegalArgumentException("Category ID must be a number.");
                        }

                        // Check if category ID exists
                        boolean categoryExists = checkCategoryExists(categoryId);
                        if (categoryExists) {
                            // Confirm deletion after entering category ID
                            int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirmation", JOptionPane.YES_NO_OPTION);
                            if (confirmDelete == JOptionPane.YES_OPTION) {
                                // Delete category
                                deleteCategory(categoryId);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Category with ID " + categoryId + " does not exist.");
                        }
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a Category ID!");
                }
            }
        }

        private boolean checkCategoryExists(String categoryId) {
            try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                 PreparedStatement checkStatement = conn.prepareStatement("SELECT * FROM Category WHERE category_id = ?")) {
                checkStatement.setString(1, categoryId);
                try (ResultSet resultSet = checkStatement.executeQuery()) {
                    return resultSet.next();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error checking category: " + ex.getMessage());
                return false;
            }
        }

        private void deleteCategory(String categoryId) {
            try (Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
                 PreparedStatement checkStatement = conn.prepareStatement("SELECT * FROM Customer WHERE category_id = ?")) {
                checkStatement.setString(1, categoryId);
                try (ResultSet resultSet = checkStatement.executeQuery()) {
                    if (resultSet.next()) {
                        JOptionPane.showMessageDialog(null, "Cannot delete category because it is referenced by Customer records!");
                    } else {
                        try (PreparedStatement deleteStatement = conn.prepareStatement("DELETE FROM Category WHERE category_id = ?")) {
                            deleteStatement.setString(1, categoryId);
                            int rowsDeleted = deleteStatement.executeUpdate();
                            if (rowsDeleted > 0) {
                                JOptionPane.showMessageDialog(null, "Category deleted successfully!");
                            } else {
                                JOptionPane.showMessageDialog(null, "Failed to delete category!");
                            }
                        }
                    }
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error deleting category: " + ex.getMessage());
            }
        }
    }

    private void fetchCategoryByID(String categoryId) {
        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM Category WHERE category_id=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, categoryId);
            ResultSet rs = pst.executeQuery();

            // Clear existing table data
            tableModel.setRowCount(0);

            // Populate the table with fetched customer records
            while (rs.next()) {
                Object[] rowData = {
                        rs.getString("category_id"),
                        rs.getString("category_name")
                };
                tableModel.addRow(rowData);
            }

            rs.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void fetchCategoryRecords() {
        // Clear table
        tableModel.setRowCount(0);

        try {
            conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM Category";
            PreparedStatement pst = conn.prepareStatement(query);
            ResultSet rs = pst.executeQuery();
            // Populate table with fetched records
            while (rs.next()) {
                String categoryId = rs.getString("category_id");
                String categoryName = rs.getString("category_name");
                tableModel.addRow(new Object[]{categoryId, categoryName});
            }
            rs.close();
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

}
