import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class CustomerForm extends JFrame implements ActionListener {
    // JDBC URL, username, and password
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/spps";
    static final String USERNAME = "root";
    static final String PASSWORD = "kalaivani";

    // Swing components
    JLabel idLabel, nameLabel, categoryLabel, addressLabel, countryLabel, typeLabel, contactLabel;
    JTextField idField, nameField, countryField;
    JComboBox<String> categoryDropdown, typeDropdown;
    JTextArea addressField, contactField;
    JButton addButton;

    public CustomerForm() {
        setTitle("Add Customer-SIM PROJECT PAYMENT SYSTEM");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\logo.png"));

        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set default font size and style
        Font defaultFont = new Font("Times New Roman", Font.PLAIN, 20);
        setUIFont(new javax.swing.plaf.FontUIResource(defaultFont));

        // Initialize Swing components
        idLabel = new JLabel("<html><font color='red'>*</font>Customer ID:");
        nameLabel = new JLabel("Name:");
        categoryLabel = new JLabel("Category ID:");
        addressLabel = new JLabel("Address:");
        countryLabel = new JLabel("Country:");
        typeLabel = new JLabel("Type:");
        contactLabel = new JLabel("Contact Details:");

        idField = new JTextField();
        nameField = new JTextField();
        countryField = new JTextField();

        categoryDropdown = new JComboBox<>();
        fetchCategories(); // Fetch categories dynamically from the database

        typeDropdown = new JComboBox<>(new String[]{"Customer", "Partner", "Agent"});

        addressField = new JTextArea(6, 30); // Increase height and width
        addressField.setFont(new Font("Times New Roman", Font.PLAIN, 20)); // Set font style
        addressField.setLineWrap(true);
        addressField.setWrapStyleWord(true);

        contactField = new JTextArea(6, 30); // Increase height and width
        contactField.setFont(new Font("Times New Roman", Font.PLAIN, 20)); // Set font style
        contactField.setLineWrap(true);
        contactField.setWrapStyleWord(true);

        addButton = new JButton("Add");
        addButton.addActionListener(this);

        // Set layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Add components to the frame using GridBagLayout
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(idLabel, gbc);

        gbc.gridy = 1;
        add(nameLabel, gbc);

        gbc.gridy = 2;
        add(categoryLabel, gbc);

        gbc.gridy = 3;
        add(addressLabel, gbc);

        gbc.gridy = 4;
        add(countryLabel, gbc);

        gbc.gridy = 5;
        add(typeLabel, gbc);

        gbc.gridy = 6;
        add(contactLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Increase width to 3 grids
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(idField, gbc);

        gbc.gridy = 1;
        add(nameField, gbc);

        gbc.gridy = 2;
        add(categoryDropdown, gbc);

        gbc.gridy = 3;
        add(new JScrollPane(addressField), gbc);

        gbc.gridy = 4;
        add(countryField, gbc);

        gbc.gridy = 5;
        add(typeDropdown, gbc);

        gbc.gridy = 6;
        add(new JScrollPane(contactField), gbc);

        gbc.gridx = 2;
        gbc.gridy = 7;
        gbc.gridwidth = 3; // Reset grid width
        gbc.fill = GridBagConstraints.NONE;
        add(addButton, gbc);

        setVisible(true);
    }

    private void fetchCategories() {
        try {
            // Establish connection to MySQL
            Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Prepare SQL statement to fetch categories
            String sql = "SELECT category_id FROM Category";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Execute the statement
            ResultSet resultSet = statement.executeQuery();

            // Populate the category dropdown
            while (resultSet.next()) {
                String categoryID = resultSet.getString("category_id");
                categoryDropdown.addItem(categoryID);
            }

            // Close connections
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching categories: " + ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addCustomer();
        }
    }
      private void clearFields() {
        idField.setText("");
        nameField.setText("");
        categoryDropdown.setSelectedIndex(0);
        addressField.setText("");
        countryField.setText("");
        typeDropdown.setSelectedIndex(0);
        contactField.setText("");
    }
  private void addCustomer() {
        // Validate customer ID
        String customerId = idField.getText();
        if (customerId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Customer ID cannot be empty!");
            return;
        }
        
        try {
            // Establish connection to MySQL
            Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Check if customer ID is unique
            if (!isCustomerIdUnique(conn, customerId)) {
                JOptionPane.showMessageDialog(this, "Customer ID must be unique!");
                conn.close();
                return;
            }

            // Prepare SQL statement to insert customer data
            String sql = "INSERT INTO Customer (customer_id, customer_name, category_id, address, country, type, contact_details) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, customerId);
            statement.setString(2, nameField.getText());
            statement.setString(3, (String) categoryDropdown.getSelectedItem());
            statement.setString(4, addressField.getText());
            statement.setString(5, countryField.getText());
            statement.setString(6, (String) typeDropdown.getSelectedItem());
            statement.setString(7, contactField.getText());

            // Execute the statement
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(this, "Customer added successfully!");
                clearFields();
            }

            // Close connections
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error adding customer: " + ex.getMessage());
        }
    }

    private boolean isCustomerIdUnique(Connection conn, String customerId) throws SQLException {
        String sql = "SELECT * FROM Customer WHERE customer_id = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, customerId);
        ResultSet resultSet = statement.executeQuery();
        boolean isUnique = !resultSet.next();
        resultSet.close();
        statement.close();
        return isUnique;
    }

    // Method to set the default font size and style
    public static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value != null && value instanceof javax.swing.plaf.FontUIResource)
                UIManager.put(key, f);
        }
    }

}
