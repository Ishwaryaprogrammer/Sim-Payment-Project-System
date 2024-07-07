
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class UpdateCategoryForm extends JFrame implements ActionListener {
    // JDBC URL, username, and password
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/spps";
    static final String USERNAME = "root";
    static final String PASSWORD = "kalaivani";

    // Swing components
    JLabel idLabel, nameLabel;
    JTextField idField, nameField;
    JButton updateButton, fetchButton;

    public UpdateCategoryForm(String categoryId) {
        setTitle("Update Category-SIM PROJECT PAYMENT SYSTEM");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\logo.png"));

        setSize(500, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        

        // Set default font size and style
        Font defaultFont = new Font("Times New Roman", Font.PLAIN, 20);
        setUIFont(new javax.swing.plaf.FontUIResource(defaultFont));

        // Initialize Swing components
        idLabel = new JLabel("<html><font color='red'>*</font>Category ID:");
        nameLabel = new JLabel("Category Name:");

        idField = new JTextField();
        idField.setEditable(true); // Disable editing
        nameField = new JTextField();

        updateButton = new JButton("Update");
        updateButton.addActionListener(this);

        fetchButton = new JButton("Fetch");
        fetchButton.addActionListener(this);

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

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Increase width to 2 grids
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(idField, gbc);

        gbc.gridy = 1;
        add(nameField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1; // Reset grid width
        gbc.fill = GridBagConstraints.NONE;
        add(updateButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        add(fetchButton, gbc);

        // If category ID is provided, fetch category details
        if (categoryId != null && !categoryId.isEmpty()) {
            idField.setText(categoryId);
            fetchCategoryDetails(categoryId);
        }

        setVisible(true);
    }

    private void fetchCategoryDetails(String categoryId) {
        try {
            // Establish connection to MySQL
            Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Prepare SQL statement to fetch category details
            String sql = "SELECT * FROM Category WHERE category_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, categoryId);

            // Execute the statement
            ResultSet resultSet = statement.executeQuery();

            // Populate fields with fetched category details
            if (resultSet.next()) {
                nameField.setText(resultSet.getString("category_name"));
            } else {
                JOptionPane.showMessageDialog(this, "Category with ID " + categoryId + " not found!");
            }

            // Close connections
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching category details: " + ex.getMessage());
        }
    }

    private void updateCategory() {
        String categoryId = idField.getText();
        if (categoryId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Category ID!");
            return;
        }

        String name = nameField.getText();

        try {
            // Establish connection to MySQL
            Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Prepare SQL statement to update category details
            String sql = "UPDATE Category SET category_name=? WHERE category_id=?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, name);
            statement.setString(2, categoryId);

            // Execute the statement
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Category details updated successfully!");
            } else {
                JOptionPane.showMessageDialog(this, "Failed to update category details!");
            }

            // Close connections
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error updating category details: " + ex.getMessage());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            updateCategory();
        } else if (e.getSource() == fetchButton) {
            String categoryId = idField.getText();
            if (categoryId != null && !categoryId.isEmpty()) {
                fetchCategoryDetails(categoryId);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a Category ID!");
            }
        }
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
