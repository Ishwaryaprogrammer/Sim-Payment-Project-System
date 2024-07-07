import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class MainScreenProject extends JFrame {
    DefaultTableModel tableModel;
    JTable projectTable;
    JButton addButton, updateButton, deleteButton, searchButton, refreshButton;
    JTextField searchField;
    JLabel searchLabel;
    ImageIcon addIcon, updateIcon, deleteIcon, refreshIcon, searchIcon;
    Connection conn;
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/spps";
    static final String USERNAME = "root";
    static final String PASSWORD = "kalaivani";

    public MainScreenProject() {
        setTitle("Project-SIM PROJECT PAYMENT SYSTEM");
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

        addButton = new JButton();
        updateButton = new JButton();
        deleteButton = new JButton();
        searchButton = new JButton();
        searchField = new JTextField(20);
        searchLabel = new JLabel("Project ID:");
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
        JMenuItem reportMenuItem = new JMenuItem("Annual Report");
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
        int[] columnWidths = {100, 500, 100, 200, 150, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 200, 150, 150, 150, 100};
        for (int i = 0; i < columnWidths.length; i++) {
            projectTable.getColumnModel().getColumn(i).setPreferredWidth(columnWidths[i]);
        }
        //int[] columnWidths = {100, 150, 100, 200, 150, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 200, 150, 150, 150, 100};
        //for (int i = 0; i < columnWidths.length; i++) {
            //projectTable.getColumnModel().getColumn(i);
        //    projectTable.getColumnModel().getColumn(i).setMinWidth(columnWidths[i]);
        //    projectTable.getColumnModel().getColumn(i).setMaxWidth(columnWidths[i]);
        //    projectTable.getColumnModel().getColumn(i).setWidth(columnWidths[i]);
        //}


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

        

            //scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
            // Enable vertical scrolling
            //scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        add(buttonPanel, BorderLayout.NORTH);

        // Fetch and display project records
        fetchProjectRecords();

        // Add action listeners
        addButton.addActionListener((ActionEvent e) -> {
            // Open AddProjectForm
            new AddProjectForm();
        });

        updateButton.addActionListener(new UpdateButtonAction());

        deleteButton.addActionListener(new DeleteButtonAction());

        searchButton.addActionListener(e -> {
            String projectId = searchField.getText().trim();
            if (!projectId.isEmpty()) {
                fetchProjectByID(projectId);
            } else {
                fetchProjectRecords();
            }
        });

        refreshButton.addActionListener(e -> {
            fetchProjectRecords(); // Refresh project records
            searchField.setText("");
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
    private void openCollectionReport() {
        dispose();
        new CollectionReport();
    }
    
    private void invoicerep() {
        dispose();
        new InvoiceReport();
    }

    
    private void openinvoice() {
        dispose();
        new MainScreenInvoice();
    }
    private void openreport() {
        dispose();
        new MainScreenReport();
    }
    
    

    // Action listener for "Update" button
    class UpdateButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Get the selected row
            int selectedRow = projectTable.getSelectedRow();

            // Ensure a row is selected
            if (selectedRow != -1) {
                // Fetch project ID from the selected row
                String projectId = (String) tableModel.getValueAt(selectedRow, 0);
                // Open the UpdateProjectForm with the project ID
                new UpdateProjectForm(projectId);
            } else {
                // Open the UpdateProjectForm without project ID
                new UpdateProjectForm("");
            }
        }
    }

    // Action listener for "Delete" button
    class DeleteButtonAction implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // Get the selected row
            int selectedRow = projectTable.getSelectedRow();

            // Check if any row is selected
            if (selectedRow != -1) {
                // Fetch project ID from the selected row
                String projectId = (String) tableModel.getValueAt(selectedRow, 0);
                // Confirm deletion directly without asking for project ID
                int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirmation", JOptionPane.YES_NO_OPTION);
                if (confirmDelete == JOptionPane.YES_OPTION) {
                    // Delete project
                    deleteProject(projectId);
                }
            } else {
                // No row selected, ask for the project ID
                String projectId = JOptionPane.showInputDialog(null, "Please enter the Project ID:");
                if (projectId != null && !projectId.isEmpty()) {
                    // Confirm deletion after entering project ID
                    int confirmDelete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this record?", "Confirmation", JOptionPane.YES_NO_OPTION);
                    if (confirmDelete == JOptionPane.YES_OPTION) {
                        // Delete project
                        deleteProject(projectId);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please enter a Project ID!");
                }
            }
        }
    }

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
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

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

    private void deleteProject(String projectId) {
        try {
            String query = "DELETE FROM Project WHERE ProjectID=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, projectId);
            int rowsDeleted = pst.executeUpdate();
            if (rowsDeleted > 0) {
                JOptionPane.showMessageDialog(null, "Project deleted successfully.");
                fetchProjectRecords(); // Refresh table
            } else {
                JOptionPane.showMessageDialog(null, "Failed to delete project.");
            }
            pst.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
