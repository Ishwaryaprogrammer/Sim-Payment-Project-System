import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.Double.parseDouble;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.plaf.FontUIResource;

public class UpdateProjectForm extends JFrame implements ActionListener {
    // JDBC URL, username, and password
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/spps";
    static final String USERNAME = "root";
    static final String PASSWORD = "kalaivani";

    // Swing components
    JLabel idLabel, descriptionLabel, customerIdLabel, endClientDetailLabel, endClientCountryLabel, poNoLabel, poDateLabel,
            poInitialValueLabel, gstPercentLabel, poRev1ValueLabel, poRev1DateLabel, poRev2ValueLabel, poRev2DateLabel,
            poRev3ValueLabel, poRev3DateLabel, poRev4ValueLabel, poRev4DateLabel, currencyLabel, paymentTermsLabel,
            projectLeadLabel, expectedCompletionDateLabel, actualCompletionDateLabel, completionPercentLabel;
    JTextField idField, descriptionField, endClientDetailField, endClientCountryField, poNoField, poInitialValueField,
            gstPercentField, poRev1ValueField, poRev2ValueField, poRev3ValueField, poRev4ValueField, currencyField,
            paymentTermsField, projectLeadField, completionPercentField;
    JComboBox<String> customerIdDropdown;
    JFormattedTextField poDateField, poRev1DateField, poRev2DateField, poRev3DateField, poRev4DateField, expectedCompletionDateField, actualCompletionDateField;
    JButton updateButton, fetchButton;

    public UpdateProjectForm(String projectId) {
        setTitle("Update Project-SIM PROJECT PAYMENT SYSTEM");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\logo.png"));

        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set default font size and style
        Font defaultFont = new Font("Times New Roman", Font.PLAIN, 20);
        setUIFont(new javax.swing.plaf.FontUIResource(defaultFont));

        // Initialize Swing components
        idLabel = new JLabel("<html><font color='red'>*</font>Project ID:");
        descriptionLabel = new JLabel("Description:");
        customerIdLabel = new JLabel("Customer ID:");
        endClientDetailLabel = new JLabel("End Client Detail:");
        endClientCountryLabel = new JLabel("End Client Country:");
        poNoLabel = new JLabel("PO No:");
        poDateLabel = new JLabel("PO Date:");
        poInitialValueLabel = new JLabel("PO Initial Value:");
        gstPercentLabel = new JLabel("GST Percent:");
        poRev1ValueLabel = new JLabel("PO Rev1 Value:");
        poRev1DateLabel = new JLabel("PO Rev1 Date:");
        poRev2ValueLabel = new JLabel("PO Rev2 Value:");
        poRev2DateLabel = new JLabel("PO Rev2 Date:");
        poRev3ValueLabel = new JLabel("PO Rev3 Value:");
        poRev3DateLabel = new JLabel("PO Rev3 Date:");
        poRev4ValueLabel = new JLabel("PO Rev4 Value:");
        poRev4DateLabel = new JLabel("PO Rev4 Date:");
        currencyLabel = new JLabel("Currency:");
        paymentTermsLabel = new JLabel("Payment Terms:");
        projectLeadLabel = new JLabel("Project Lead:");
        expectedCompletionDateLabel = new JLabel("Expected Completion Date:");
        actualCompletionDateLabel = new JLabel("Actual Completion Date:");
        completionPercentLabel = new JLabel("Completion Percent:");

        idField = new JTextField();
        descriptionField = new JTextField();
        endClientDetailField = new JTextField();
        endClientCountryField = new JTextField();
        poNoField = new JTextField();
        poInitialValueField = new JTextField();
        gstPercentField = new JTextField();
        poRev1ValueField = new JTextField();
        poRev2ValueField = new JTextField();
        poRev3ValueField = new JTextField();
        poRev4ValueField = new JTextField();
        currencyField = new JTextField();
        paymentTermsField = new JTextField();
        projectLeadField = new JTextField();
        completionPercentField = new JTextField();

        customerIdDropdown = new JComboBox<>();
        fetchCustomerIds(); // Fetch customer IDs dynamically from the database

        // Date format for date fields
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        poDateField = new JFormattedTextField(dateFormat);
        poRev1DateField = new JFormattedTextField(dateFormat);
        poRev2DateField = new JFormattedTextField(dateFormat);
        poRev3DateField = new JFormattedTextField(dateFormat);
        poRev4DateField = new JFormattedTextField(dateFormat);
        expectedCompletionDateField = new JFormattedTextField(dateFormat);
        actualCompletionDateField = new JFormattedTextField(dateFormat);

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
        add(descriptionLabel, gbc);

        gbc.gridy = 2;
        add(customerIdLabel, gbc);

        gbc.gridy = 3;
        add(endClientDetailLabel, gbc);

        gbc.gridy = 4;
        add(endClientCountryLabel, gbc);

        gbc.gridy = 5;
        add(poNoLabel, gbc);

        gbc.gridy = 6;
        add(poDateLabel, gbc);

        gbc.gridy = 7;
        add(poInitialValueLabel, gbc);

        gbc.gridy = 8;
        add(gstPercentLabel, gbc);

        gbc.gridy = 9;
        add(poRev1ValueLabel, gbc);

        gbc.gridy = 10;
        add(poRev1DateLabel, gbc);

        gbc.gridy = 11;
        add(poRev2ValueLabel, gbc);

        gbc.gridy = 12;
        add(poRev2DateLabel, gbc);

        gbc.gridy = 13;
        add(poRev3ValueLabel, gbc);

        gbc.gridy = 14;
        add(poRev3DateLabel, gbc);

        gbc.gridy = 15;
        add(poRev4ValueLabel, gbc);

        gbc.gridy = 16;
        add(poRev4DateLabel, gbc);

        gbc.gridy = 17;
        add(currencyLabel, gbc);

        gbc.gridy = 18;
        add(paymentTermsLabel, gbc);

        gbc.gridy = 19;
        add(projectLeadLabel, gbc);

        gbc.gridy = 20;
        add(expectedCompletionDateLabel, gbc);

        gbc.gridy = 21;
        add(actualCompletionDateLabel, gbc);

        gbc.gridy = 22;
        add(completionPercentLabel, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Increase width to 2 grids
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(idField, gbc);

        gbc.gridy = 1;
        add(descriptionField, gbc);

        gbc.gridy = 2;
        add(customerIdDropdown, gbc);

        gbc.gridy = 3;
        add(endClientDetailField, gbc);

        gbc.gridy = 4;
        add(endClientCountryField, gbc);

        gbc.gridy = 5;
        add(poNoField, gbc);

        gbc.gridy = 6;
        add(poDateField, gbc);

        gbc.gridy = 7;
        add(poInitialValueField, gbc);

        gbc.gridy = 8;
        add(gstPercentField, gbc);

        gbc.gridy = 9;
        add(poRev1ValueField, gbc);

        gbc.gridy = 10;
        add(poRev1DateField, gbc);

        gbc.gridy = 11;
        add(poRev2ValueField, gbc);

        gbc.gridy = 12;
        add(poRev2DateField, gbc);

        gbc.gridy = 13;
        add(poRev3ValueField, gbc);

        gbc.gridy = 14;
        add(poRev3DateField, gbc);

        gbc.gridy = 15;
        add(poRev4ValueField, gbc);

        gbc.gridy = 16;
        add(poRev4DateField, gbc);

        gbc.gridy = 17;
        add(currencyField, gbc);

        gbc.gridy = 18;
        add(paymentTermsField, gbc);

        gbc.gridy = 19;
        add(projectLeadField, gbc);

        gbc.gridy = 20;
        add(expectedCompletionDateField, gbc);

        gbc.gridy = 21;
        add(actualCompletionDateField, gbc);

        gbc.gridy = 22;
        add(completionPercentField, gbc);

        gbc.gridx = 2;
        gbc.gridy = 23;
        gbc.gridwidth = 1; // Reset grid width
        gbc.fill = GridBagConstraints.NONE;
        add(updateButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        add(fetchButton, gbc);

        // If project ID is provided, fetch project details
        if (projectId != null && !projectId.isEmpty()) {
        idField.setText(projectId);
        fetchProjectDetails(projectId);
        }
        
        
        JScrollPane scrollPane = new JScrollPane(this.getContentPane());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Set the content pane of the JFrame to the scroll pane
        setContentPane(scrollPane);
        

            setVisible(true);
}

private void fetchProjectDetails(String projectId) {
    try {
        // Establish connection to MySQL
        Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

        // Prepare SQL statement to fetch project details
        String sql = "SELECT * FROM project WHERE ProjectID = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, projectId);

        // Execute the statement
        ResultSet resultSet = statement.executeQuery();

        // Populate fields with fetched project details
        if (resultSet.next()) {
            descriptionField.setText(resultSet.getString("Description"));
            customerIdDropdown.setSelectedItem(resultSet.getString("customer_id"));
            endClientDetailField.setText(resultSet.getString("EndClientDetail"));
            endClientCountryField.setText(resultSet.getString("EndClientCountry"));
            poNoField.setText(resultSet.getString("PONo"));
            poDateField.setValue(resultSet.getDate("PODate"));
            poInitialValueField.setText(String.valueOf(resultSet.getFloat("POInitialValue")));
            gstPercentField.setText(String.valueOf(resultSet.getFloat("GSTPercent")));
            poRev1ValueField.setText(String.valueOf(resultSet.getFloat("PORev1Value")));
            poRev1DateField.setValue(resultSet.getDate("PORev1Date"));
            poRev2ValueField.setText(String.valueOf(resultSet.getFloat("PORev2Value")));
            poRev2DateField.setValue(resultSet.getDate("PORev2Date"));
            poRev3ValueField.setText(String.valueOf(resultSet.getFloat("PORev3Value")));
            poRev3DateField.setValue(resultSet.getDate("PORev3Date"));
            poRev4ValueField.setText(String.valueOf(resultSet.getFloat("PORev4Value")));
            poRev4DateField.setValue(resultSet.getDate("PORev4Date"));
            currencyField.setText(resultSet.getString("Currency"));
            paymentTermsField.setText(resultSet.getString("PaymentTerms"));
            projectLeadField.setText(resultSet.getString("ProjectLead"));
            expectedCompletionDateField.setValue(resultSet.getDate("ExpectedCompletionDate"));
            actualCompletionDateField.setValue(resultSet.getDate("ActualCompletionDate"));
            completionPercentField.setText(String.valueOf(resultSet.getFloat("CompletionPercent")));
        } else {
            JOptionPane.showMessageDialog(this, "Project with ID " + projectId + " not found!");
        }

        // Close connections
        resultSet.close();
        statement.close();
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error fetching project details: " + ex.getMessage());
    }
}
private void updateProject() {
    String projectId = idField.getText().trim();
    if (projectId.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a Project ID!");
        return;
    }

    try {
        // Establish connection to MySQL
        Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

        // Check if the project exists
        String checkSql = "SELECT COUNT(*) FROM project WHERE ProjectID = ?";
        PreparedStatement checkStatement = conn.prepareStatement(checkSql);
        checkStatement.setString(1, projectId);
        ResultSet rs = checkStatement.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        rs.close();
        checkStatement.close();

        if (count == 0) {
            JOptionPane.showMessageDialog(this, "Project ID does not exist!");
            conn.close();
            return;
        }

        // Proceed with update if project exists
        String sql = "UPDATE project SET Description=?, customer_id=?, EndClientDetail=?, EndClientCountry=?, PONo=?, "
                + "PODate=?, POInitialValue=?, GSTPercent=?, PORev1Value=?, PORev1Date=?, PORev2Value=?, PORev2Date=?, "
                + "PORev3Value=?, PORev3Date=?, PORev4Value=?, PORev4Date=?, Currency=?, PaymentTerms=?, ProjectLead=?, "
                + "ExpectedCompletionDate=?, ActualCompletionDate=?, CompletionPercent=? WHERE ProjectID=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, descriptionField.getText());
        statement.setString(2, customerIdDropdown.getSelectedItem().toString());
        statement.setString(3, endClientDetailField.getText());
        statement.setString(4, endClientCountryField.getText());
        statement.setString(5, poNoField.getText());

        // Handling date fields
        if (!poDateField.getText().isEmpty()) {
            statement.setString(6, poDateField.getText());
        } else {
            statement.setNull(6, Types.DATE); // Set NULL if the field is empty
        }

        // Handling numeric fields
        statement.setDouble(7, parseDouble(poInitialValueField.getText()));
        statement.setDouble(8, parseDouble(gstPercentField.getText()));
        statement.setDouble(9, parseDouble(poRev1ValueField.getText()));
        statement.setDouble(11, parseDouble(poRev2ValueField.getText()));
        statement.setDouble(13, parseDouble(poRev3ValueField.getText()));
        statement.setDouble(15, parseDouble(poRev4ValueField.getText()));
        statement.setDouble(22, parseDouble(completionPercentField.getText()));

        if (!poRev1DateField.getText().isEmpty()) {
            statement.setString(10, poRev1DateField.getText());
        } else {
            statement.setNull(10, Types.DATE); // Set NULL if the field is empty
        }

        if (!poRev2DateField.getText().isEmpty()) {
            statement.setString(12, poRev2DateField.getText());
        } else {
            statement.setNull(12, Types.DATE); // Set NULL if the field is empty
        }

        if (!poRev3DateField.getText().isEmpty()) {
            statement.setString(14, poRev3DateField.getText());
        } else {
            statement.setNull(14, Types.DATE); // Set NULL if the field is empty
        }

        if (!poRev4DateField.getText().isEmpty()) {
            statement.setString(16, poRev4DateField.getText());
        } else {
            statement.setNull(16, Types.DATE); // Set NULL if the field is empty
        }

        // Handling other date fields
        if (!expectedCompletionDateField.getText().isEmpty()) {
            statement.setString(20, expectedCompletionDateField.getText());
        } else {
            statement.setNull(20, Types.DATE); // Set NULL if the field is empty
        }

        if (!actualCompletionDateField.getText().isEmpty()) {
            statement.setString(21, actualCompletionDateField.getText());
        } else {
            statement.setNull(21, Types.DATE); // Set NULL if the field is empty
        }

        // Other fields
        statement.setString(17, currencyField.getText());
        statement.setString(18, paymentTermsField.getText());
        statement.setString(19, projectLeadField.getText());
        statement.setString(23, idField.getText()); // ProjectID as WHERE condition

        // Execute the statement
        int rowsUpdated = statement.executeUpdate();

        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(this, "Project details updated successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update project details!");
        }

        // Close connections
        statement.close();
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error updating project details: " + ex.getMessage());
    }
}

// Method to safely parse double values
private double parseDouble(String text) {
    if (text == null || text.trim().isEmpty()) {
        return 0.0;
    }
    try {
        return Double.parseDouble(text.trim());
    } catch (NumberFormatException e) {
        return 0.0;
    }
}


    private void fetchCustomerIds() {
        try {
            // Establish connection to MySQL
            Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

            // Prepare SQL statement to fetch customer IDs
            String sql = "SELECT customer_id FROM Customer";
            PreparedStatement statement = conn.prepareStatement(sql);

            // Execute the statement
            ResultSet resultSet = statement.executeQuery();

            // Populate the customer ID dropdown
            while (resultSet.next()) {
                String customerId = resultSet.getString("customer_id");
                customerIdDropdown.addItem(customerId);
            }

            // Close connections
            resultSet.close();
            statement.close();
            conn.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching customer IDs: " + ex.getMessage());
        }
        
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new UpdateProjectForm(""));
}


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            updateProject();
        } else if (e.getSource() == fetchButton) {
            String projectId = idField.getText();
            if (projectId != null && !projectId.isEmpty()) {
                fetchProjectDetails(projectId);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a Project ID!");
            }
        }
    }
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
