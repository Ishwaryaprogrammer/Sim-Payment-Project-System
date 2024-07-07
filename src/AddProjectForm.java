
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import javax.swing.plaf.FontUIResource;

public class AddProjectForm extends JFrame implements ActionListener {
    // JDBC URL, username, and password
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/spps";
    static final String USERNAME = "root";
    static final String PASSWORD = "kalaivani";

    // Swing components
    JLabel projectIDLabel, descriptionLabel, customerIDLabel, endClientDetailLabel, endClientCountryLabel, 
           poNoLabel, poDateLabel, poInitialValueLabel, gstPercentLabel, poRev1ValueLabel, poRev1DateLabel, 
           poRev2ValueLabel, poRev2DateLabel, poRev3ValueLabel, poRev3DateLabel, poRev4ValueLabel, 
           poRev4DateLabel, currencyLabel, paymentTermsLabel, projectLeadLabel, expectedCompletionDateLabel, 
           actualCompletionDateLabel, completionPercentLabel;

    JTextField projectIDField, descriptionField, customerIDField, endClientDetailField, endClientCountryField, 
               poNoField, poInitialValueField, gstPercentField, poRev1ValueField, poRev2ValueField, 
               poRev3ValueField, poRev4ValueField, currencyField, projectLeadField, completionPercentField;

    JTextArea paymentTermsField;
    JFormattedTextField poDateField, poRev1DateField, poRev2DateField, poRev3DateField, poRev4DateField, 
                        expectedCompletionDateField, actualCompletionDateField;

    JButton addButton;

    public AddProjectForm() {
        setTitle("Add Project-SIM PROJECT PAYMENT SYSTEM");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\logo.png"));

        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set default font size and style
        Font defaultFont = new Font("Times New Roman", Font.PLAIN, 20);
        setUIFont(new javax.swing.plaf.FontUIResource(defaultFont));

        // Initialize Swing components
        projectIDLabel = new JLabel("Project ID:");
        descriptionLabel = new JLabel("Description:");
        customerIDLabel = new JLabel("Customer ID:");
        endClientDetailLabel = new JLabel("End Client Detail:");
        endClientCountryLabel = new JLabel("End Client Country:");
        poNoLabel = new JLabel("PO No:");
        poDateLabel = new JLabel("PO Date:");
        poInitialValueLabel = new JLabel("PO Initial Value:");
        gstPercentLabel = new JLabel("GST Percent:");
        poRev1ValueLabel = new JLabel("PO Rev 1 Value:");
        poRev1DateLabel = new JLabel("PO Rev 1 Date:");
        poRev2ValueLabel = new JLabel("PO Rev 2 Value:");
        poRev2DateLabel = new JLabel("PO Rev 2 Date:");
        poRev3ValueLabel = new JLabel("PO Rev 3 Value:");
        poRev3DateLabel = new JLabel("PO Rev 3 Date:");
        poRev4ValueLabel = new JLabel("PO Rev 4 Value:");
        poRev4DateLabel = new JLabel("PO Rev 4 Date:");
        currencyLabel = new JLabel("Currency:");
        paymentTermsLabel = new JLabel("Payment Terms:");
        projectLeadLabel = new JLabel("Project Lead:");
        expectedCompletionDateLabel = new JLabel("Expected Completion Date:");
        actualCompletionDateLabel = new JLabel("Actual Completion Date:");
        completionPercentLabel = new JLabel("Completion Percent:");

        projectIDField = new JTextField();
        descriptionField = new JTextField();
        customerIDField = new JTextField();
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
        projectLeadField = new JTextField();
        completionPercentField = new JTextField();

        paymentTermsField = new JTextArea(3, 30);
        paymentTermsField.setLineWrap(true);
        paymentTermsField.setWrapStyleWord(true);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        poDateField = new JFormattedTextField(dateFormat);
        poRev1DateField = new JFormattedTextField(dateFormat);
        poRev2DateField = new JFormattedTextField(dateFormat);
        poRev3DateField = new JFormattedTextField(dateFormat);
        poRev4DateField = new JFormattedTextField(dateFormat);
        expectedCompletionDateField = new JFormattedTextField(dateFormat);
        actualCompletionDateField = new JFormattedTextField(dateFormat);

        addButton = new JButton("Add Project");
        addButton.addActionListener(this);

        // Set layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Add components to the frame using GridBagLayout
        int y = 0;
        addComponent(projectIDLabel, 0, y, 1, 1, gbc);
        addComponent(projectIDField, 1, y++, 2, 1, gbc);

        addComponent(descriptionLabel, 0, y, 1, 1, gbc);
        addComponent(descriptionField, 1, y++, 2, 1, gbc);

        addComponent(customerIDLabel, 0, y, 1, 1, gbc);
        addComponent(customerIDField, 1, y++, 2, 1, gbc);

        addComponent(endClientDetailLabel, 0, y, 1, 1, gbc);
        addComponent(endClientDetailField, 1, y++, 2, 1, gbc);

        addComponent(endClientCountryLabel, 0, y, 1, 1, gbc);
        addComponent(endClientCountryField, 1, y++, 2, 1, gbc);

        addComponent(poNoLabel, 0, y, 1, 1, gbc);
        addComponent(poNoField, 1, y++, 2, 1, gbc);

        addComponent(poDateLabel, 0, y, 1, 1, gbc);
        addComponent(poDateField, 1, y++, 2, 1, gbc);

        addComponent(poInitialValueLabel, 0, y, 1, 1, gbc);
        addComponent(poInitialValueField, 1, y++, 2, 1, gbc);

        addComponent(gstPercentLabel, 0, y, 1, 1, gbc);
        addComponent(gstPercentField, 1, y++, 2, 1, gbc);
        
        addComponent(poRev1ValueLabel, 0, y, 1, 1, gbc);
        addComponent(poRev1ValueField, 1, y++, 2, 1, gbc);

        addComponent(poRev1DateLabel, 0, y, 1, 1, gbc);
        addComponent(poRev1DateField, 1, y++, 2, 1, gbc);

        addComponent(poRev2ValueLabel, 0, y, 1, 1, gbc);
        addComponent(poRev2ValueField, 1, y++, 2, 1, gbc);

        addComponent(poRev2DateLabel, 0, y, 1, 1, gbc);
        addComponent(poRev2DateField, 1, y++, 2, 1, gbc);

        addComponent(poRev3ValueLabel, 0, y, 1, 1, gbc);
        addComponent(poRev3ValueField, 1, y++, 2, 1, gbc);

        addComponent(poRev3DateLabel, 0, y, 1, 1, gbc);
        addComponent(poRev3DateField, 1, y++, 2, 1, gbc);

        addComponent(poRev4ValueLabel, 0, y, 1, 1, gbc);
        addComponent(poRev4ValueField, 1, y++, 2, 1, gbc);

        addComponent(poRev4DateLabel, 0, y, 1, 1, gbc);
        addComponent(poRev4DateField, 1, y++, 2, 1, gbc);

        addComponent(currencyLabel, 0, y, 1, 1, gbc);
        addComponent(currencyField, 1, y++, 2, 1, gbc);

        addComponent(paymentTermsLabel, 0, y, 1, 1, gbc);
        addComponent(new JScrollPane(paymentTermsField), 1, y++, 2, 1, gbc);

        addComponent(projectLeadLabel, 0, y, 1, 1, gbc);
        addComponent(projectLeadField, 1, y++, 2, 1, gbc);

        addComponent(expectedCompletionDateLabel, 0, y, 1, 1, gbc);
        addComponent(expectedCompletionDateField, 1, y++, 2, 1, gbc);
        
        addComponent(actualCompletionDateLabel, 0, y, 1, 1, gbc);
        addComponent(actualCompletionDateField, 1, y++, 2, 1, gbc);

        addComponent(completionPercentLabel, 0, y, 1, 1, gbc);
        addComponent(completionPercentField, 1, y++, 2, 1, gbc);

        gbc.gridx = 1;
        gbc.gridy = y;
        gbc.gridwidth = 1; // Reset grid width
        gbc.fill = GridBagConstraints.NONE;
        add(addButton, gbc);
        
        JScrollPane scrollPane = new JScrollPane(this.getContentPane());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Set the content pane of the JFrame to the scroll pane
        setContentPane(scrollPane);

        setVisible(true);
    }

    private void addComponent(Component component, int x, int y, int width, int height, GridBagConstraints gbc) {
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.gridwidth = width;
        gbc.gridheight = height;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(component, gbc);
    }
    
    private void addProject() {
    String projectID = projectIDField.getText();
    String description = descriptionField.getText();
    String customerID = customerIDField.getText();
    String endClientDetail = endClientDetailField.getText();
    String endClientCountry = endClientCountryField.getText();
    String poNo = poNoField.getText();
    String poDate = poDateField.getText();
    String poInitialValue = poInitialValueField.getText();
    String gstPercent = gstPercentField.getText();
    String poRev1Value = poRev1ValueField.getText();
    String poRev1Date = poRev1DateField.getText();
    String poRev2Value = poRev2ValueField.getText();
    String poRev2Date = poRev2DateField.getText();
    String poRev3Value = poRev3ValueField.getText();
    String poRev3Date = poRev3DateField.getText();
    String poRev4Value = poRev4ValueField.getText();
    String poRev4Date = poRev4DateField.getText();
    String currency = currencyField.getText();
    String paymentTerms = paymentTermsField.getText();
    String projectLead = projectLeadField.getText();
    String expectedCompletionDate = expectedCompletionDateField.getText();
    String actualCompletionDate = actualCompletionDateField.getText();
    String completionPercent = completionPercentField.getText();

    try {
        // Establish connection to MySQL
        Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

        // Prepare SQL statement to insert project details
        String sql = "INSERT INTO Project (ProjectID, Description, customer_id, EndClientDetail, EndClientCountry, PONo, PODate, " +
                "POInitialValue, GSTPercent, PORev1Value, PORev1Date, PORev2Value, PORev2Date, PORev3Value, PORev3Date, PORev4Value, PORev4Date, " +
                "Currency, PaymentTerms, ProjectLead, ExpectedCompletionDate, ActualCompletionDate, CompletionPercent) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, projectID);
        statement.setString(2, description);
        statement.setString(3, customerID);
        statement.setString(4, endClientDetail);
        statement.setString(5, endClientCountry);
        if (!poNo.isEmpty()) {
            statement.setString(6, poDate);
            }else{
            statement.setNull(6, Types.VARCHAR); // Set poNo as NULL in the database
        }
        if (!poDate.isEmpty()) {
        statement.setString(7, poDate); // Assuming poDate is a String in 'YYYY-MM-DD' format
        } else {
        statement.setNull(7, Types.DATE); // Set PODate as NULL in the database
        }
    
        if (!poInitialValue.isEmpty()) {
            statement.setDouble(8, Double.parseDouble(poInitialValue));
        } else {
            statement.setNull(8, Types.DOUBLE);
        }
        
        if (!gstPercent.isEmpty()) {
            statement.setDouble(9, Double.parseDouble(gstPercent));
        } else {
            statement.setNull(9, Types.DOUBLE);
        }
        
        if (!poRev1Value.isEmpty()) {
            statement.setDouble(10, Double.parseDouble(poRev1Value));
        } else {
            statement.setNull(10, Types.DOUBLE);
        }
        if (!poRev1Date.isEmpty()) {
        statement.setString(11, poRev1Date); // Assuming poDate is a String in 'YYYY-MM-DD' format
        } else {
        statement.setNull(11, Types.DATE); // Set PODate as NULL in the database
        }
        if (!poRev2Value.isEmpty()) {
            statement.setDouble(12, Double.parseDouble(poRev2Value));
        } else {
            statement.setNull(12, Types.DOUBLE);
        }
        if (!poRev2Date.isEmpty()) {
        statement.setString(13, poRev2Date); // Assuming poDate is a String in 'YYYY-MM-DD' format
        } else {
        statement.setNull(13, Types.DATE); // Set PODate as NULL in the database
        }
        if (!poRev3Value.isEmpty()) {
            statement.setDouble(14, Double.parseDouble(poRev3Value));
        } else {
            statement.setNull(14, Types.DOUBLE);
        }
        if (!poRev3Date.isEmpty()) {
        statement.setString(15, poRev3Date); // Assuming poDate is a String in 'YYYY-MM-DD' format
        } else {
        statement.setNull(15, Types.DATE); // Set PODate as NULL in the database
        }
        
        if (!poRev4Value.isEmpty()) {
            statement.setDouble(16, Double.parseDouble(poRev4Value));
        } else {
            statement.setNull(16, Types.DOUBLE);
        }
        if (!poRev4Date.isEmpty()) {
        statement.setString(17, poRev4Date); // Assuming poDate is a String in 'YYYY-MM-DD' format
        } else {
        statement.setNull(17, Types.DATE); // Set PODate as NULL in the database
        }
        
        
        statement.setString(18, currency);
        statement.setString(19, paymentTerms);
        statement.setString(20, projectLead);
        if (!expectedCompletionDate.isEmpty()) {
        statement.setString(21, expectedCompletionDate); // Assuming poDate is a String in 'YYYY-MM-DD' format
        } else {
        statement.setNull(21, Types.DATE); // Set PODate as NULL in the database
        }
        if (!actualCompletionDate.isEmpty()) {
        statement.setString(22, actualCompletionDate); // Assuming poDate is a String in 'YYYY-MM-DD' format
        } else {
        statement.setNull(22, Types.DATE); // Set PODate as NULL in the database
        }
        
        if (!completionPercent.isEmpty()) {
            statement.setDouble(23, Double.parseDouble(completionPercent));
        } else {
            statement.setNull(23, Types.DOUBLE);
        }

        // Execute the statement
        int rowsInserted = statement.executeUpdate();

        if (rowsInserted > 0) {
            JOptionPane.showMessageDialog(this, "Project added successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add project!");
        }

        // Close connections
        statement.close();
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error adding project: " + ex.getMessage());
    } catch (NumberFormatException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Please enter valid numeric values for amounts and percentages.");
    }
}
    


  

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addProject();
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

public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddProjectForm());
}

}
