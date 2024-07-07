import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class AddCategoryForm extends JFrame {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/spps";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "kalaivani";

    private JTextField categoryIdField;
    private JTextField categoryNameField;
    private JButton addButton;

    public AddCategoryForm() {
        setTitle("Add Category-SIM PROJECT PAYMENT SYSTEM");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\logo.png"));

        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Initialize components
        categoryIdField = new JTextField(10);
        categoryNameField = new JTextField(20);
        addButton = new JButton("Add");

        // Add components to the frame
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("<html><font color='red'>*</font>Category ID:"));
        panel.add(categoryIdField);
        panel.add(new JLabel("Category Name:"));
        panel.add(categoryNameField);
        panel.add(addButton);

        add(panel, BorderLayout.CENTER);

        // Add action listener for the add button
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String categoryId = categoryIdField.getText().trim();
                String categoryName = categoryNameField.getText().trim();

                try {
                    // Validate category ID
                    if (categoryId.isEmpty()) {
                        throw new IllegalArgumentException("Category ID cannot be empty.");
                    }
                    if (!categoryId.matches("\\d+")) {
                        throw new IllegalArgumentException("Category ID must be a number.");
                    }

                    // Check if category ID already exists
                    if (checkCategoryExists(categoryId)) {
                        throw new IllegalArgumentException("Category ID already exists.");
                    }

                    // Insert category into database
                    addCategory(categoryId, categoryName);
                    JOptionPane.showMessageDialog(null, "Category added successfully.");
                    dispose(); // Close the form
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });

        // Show the frame
        setVisible(true);
    }

    private boolean checkCategoryExists(String categoryId) {
        boolean exists = false;
        try {
            Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "SELECT * FROM Category WHERE category_id=?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, categoryId);
            ResultSet rs = pst.executeQuery();
            exists = rs.next(); // If the result set has at least one row, the category exists
            rs.close();
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return exists;
    }

    private void addCategory(String categoryId, String categoryName) {
        try {
            Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "INSERT INTO Category (category_id, category_name) VALUES (?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, categoryId);
            pst.setString(2, categoryName);
            pst.executeUpdate();
            pst.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
