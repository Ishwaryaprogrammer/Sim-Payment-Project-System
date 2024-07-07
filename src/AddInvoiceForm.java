import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.SimpleDateFormat;

public class AddInvoiceForm extends JFrame implements ActionListener {
    // JDBC URL, username, and password
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/spps";
    static final String USERNAME = "root";
    static final String PASSWORD = "kalaivani";

    // Swing components
    JLabel customerIDLabel, projectIDLabel, poNoLabel, invoiceNoLabel, invoiceDateLabel, descriptionLabel, 
           invoiceTypeLabel, invAmountInEuroLabel, invAmountInUsdLabel, invAmountInInrLabel, gstAmountLabel, 
           tdsDeductedLabel, retentionAmountLabel, amountReceivedLabel, amountReceivedInInrLabel, 
           receivedDateLabel, fircDetailsLabel;

    JTextField customerIDField, projectIDField, poNoField, invoiceNoField, descriptionField, 
               invAmountInEuroField, invAmountInUsdField, invAmountInInrField, gstAmountField, 
               tdsDeductedField, retentionAmountField, amountReceivedField, amountReceivedInInrField, 
               fircDetailsField;

    JComboBox<String> invoiceTypeComboBox;
    JFormattedTextField invoiceDateField, receivedDateField;

    JButton addButton;

    public AddInvoiceForm() {
        setTitle("Add Invoice-SIM PROJECT PAYMENT SYSTEM");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\logo.png"));

        setSize(1000, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set default font size and style
        Font defaultFont = new Font("Times New Roman", Font.PLAIN, 20);
        setUIFont(new javax.swing.plaf.FontUIResource(defaultFont));

        // Initialize Swing components
        customerIDLabel = new JLabel("Customer ID:");
        projectIDLabel = new JLabel("Project ID:");
        poNoLabel = new JLabel("PO No:");
        invoiceNoLabel = new JLabel("Invoice No:");
        invoiceDateLabel = new JLabel("Invoice Date:");
        descriptionLabel = new JLabel("Description:");
        invoiceTypeLabel = new JLabel("Invoice Type:");
        invAmountInEuroLabel = new JLabel("Invoice Amount in Euro:");
        invAmountInUsdLabel = new JLabel("Invoice Amount in USD:");
        invAmountInInrLabel = new JLabel("Invoice Amount in INR:");
        gstAmountLabel = new JLabel("GST Amount:");
        tdsDeductedLabel = new JLabel("TDS Deducted:");
        retentionAmountLabel = new JLabel("Retention Amount:");
        amountReceivedLabel = new JLabel("Amount Received:");
        amountReceivedInInrLabel = new JLabel("Amount Received in INR:");
        receivedDateLabel = new JLabel("Received Date:");
        fircDetailsLabel = new JLabel("FIRC Details:");
        

        customerIDField = new JTextField();
        projectIDField = new JTextField();
        poNoField = new JTextField();
        invoiceNoField = new JTextField();
        descriptionField = new JTextField();
        invAmountInEuroField = new JTextField();
        invAmountInUsdField = new JTextField();
        invAmountInInrField = new JTextField();
        gstAmountField = new JTextField();
        tdsDeductedField = new JTextField();
        retentionAmountField = new JTextField();
        amountReceivedField = new JTextField();
        amountReceivedInInrField = new JTextField();
        fircDetailsField = new JTextField();
    

        invoiceTypeComboBox = new JComboBox<>(new String[]{"Milestone", "Expense"});

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        invoiceDateField = new JFormattedTextField(dateFormat);
        receivedDateField = new JFormattedTextField(dateFormat);

        addButton = new JButton("Add Invoice");
        addButton.addActionListener(this);

        // Set layout manager
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Add components to the frame using GridBagLayout
        int y = 0;
        addComponent(customerIDLabel, 0, y, 1, 1, gbc);
        addComponent(customerIDField, 1, y++, 2, 1, gbc);

        addComponent(projectIDLabel, 0, y, 1, 1, gbc);
        addComponent(projectIDField, 1, y++, 2, 1, gbc);

        addComponent(poNoLabel, 0, y, 1, 1, gbc);
        addComponent(poNoField, 1, y++, 2, 1, gbc);

        addComponent(invoiceNoLabel, 0, y, 1, 1, gbc);
        addComponent(invoiceNoField, 1, y++, 2, 1, gbc);

        addComponent(invoiceDateLabel, 0, y, 1, 1, gbc);
        addComponent(invoiceDateField, 1, y++, 2, 1, gbc);

        addComponent(descriptionLabel, 0, y, 1, 1, gbc);
        addComponent(descriptionField, 1, y++, 2, 1, gbc);

        addComponent(invoiceTypeLabel, 0, y, 1, 1, gbc);
        addComponent(invoiceTypeComboBox, 1, y++, 2, 1, gbc);

        addComponent(invAmountInEuroLabel, 0, y, 1, 1, gbc);
        addComponent(invAmountInEuroField, 1, y++, 2, 1, gbc);

        addComponent(invAmountInUsdLabel, 0, y, 1, 1, gbc);
        addComponent(invAmountInUsdField, 1, y++, 2, 1, gbc);

        addComponent(invAmountInInrLabel, 0, y, 1, 1, gbc);
        addComponent(invAmountInInrField, 1, y++, 2, 1, gbc);

        addComponent(gstAmountLabel, 0, y, 1, 1, gbc);
        addComponent(gstAmountField, 1, y++, 2, 1, gbc);

        addComponent(tdsDeductedLabel, 0, y, 1, 1, gbc);
        addComponent(tdsDeductedField, 1, y++, 2, 1, gbc);

        addComponent(retentionAmountLabel, 0, y, 1, 1, gbc);
        addComponent(retentionAmountField, 1, y++, 2, 1, gbc);

        addComponent(amountReceivedLabel, 0, y, 1, 1, gbc);
        addComponent(amountReceivedField, 1, y++, 2, 1, gbc);

        addComponent(amountReceivedInInrLabel, 0, y, 1, 1, gbc);
        addComponent(amountReceivedInInrField, 1, y++, 2, 1, gbc);

        addComponent(receivedDateLabel, 0, y, 1, 1, gbc);
        addComponent(receivedDateField, 1, y++, 2, 1, gbc);

        addComponent(fircDetailsLabel, 0, y, 1, 1, gbc);
        addComponent(fircDetailsField, 1, y++, 2, 1, gbc);

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

  private void addInvoice() {
    String customerID = customerIDField.getText();
    String projectID = projectIDField.getText();
    String poNo = poNoField.getText();
    String invoiceNo = invoiceNoField.getText();
    String invoiceDate = invoiceDateField.getText();
    String description = descriptionField.getText();
    String invoiceType = (String) invoiceTypeComboBox.getSelectedItem();
    String invAmountInEuro = invAmountInEuroField.getText();
    String invAmountInUsd = invAmountInUsdField.getText();
    String invAmountInInr = invAmountInInrField.getText();
    String gstAmount = gstAmountField.getText();
    String tdsDeducted = tdsDeductedField.getText();
    String retentionAmount = retentionAmountField.getText();
    String amountReceived = amountReceivedField.getText();
    String amountReceivedInInr = amountReceivedInInrField.getText();
    String receivedDate = receivedDateField.getText();
    String fircDetails = fircDetailsField.getText();

    try {
        // Establish connection to MySQL
        Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

        // Prepare SQL statement to insert invoice details
        String sql = "INSERT INTO invoice (customer_id, projectID, po_no, invoice_no, invoice_date, description, invoice_type, inv_amount_in_euro, inv_amount_in_usd, inv_amount_in_inr, gst_amount, tds_deducted, retention_amount, amount_received, amount_received_in_inr, received_date, firc_details) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, customerID);
        statement.setString(2, projectID);
        
        if (!poNo.isEmpty()) {
            statement.setString(3, poNo);
        } else {
            statement.setNull(3, Types.VARCHAR);
        }
        
        statement.setString(4, invoiceNo);
        
        if (!invoiceDate.isEmpty()) {
            statement.setString(5, invoiceDate);
        } else {
            statement.setNull(5, Types.DATE);
        }
        
        statement.setString(6, description);
        statement.setString(7, invoiceType);
        
        if (!invAmountInEuro.isEmpty()) {
            statement.setDouble(8, Double.parseDouble(invAmountInEuro));
        } else {
            statement.setNull(8, Types.DOUBLE);
        }
        
        if (!invAmountInUsd.isEmpty()) {
            statement.setDouble(9, Double.parseDouble(invAmountInUsd));
        } else {
            statement.setNull(9, Types.DOUBLE);
        }
        
        if (!invAmountInInr.isEmpty()) {
            statement.setDouble(10, Double.parseDouble(invAmountInInr));
        } else {
            statement.setNull(10, Types.DOUBLE);
        }
        
        if (!gstAmount.isEmpty()) {
            statement.setDouble(11, Double.parseDouble(gstAmount));
        } else {
            statement.setNull(11, Types.DOUBLE);
        }
        
        if (!tdsDeducted.isEmpty()) {
            statement.setDouble(12, Double.parseDouble(tdsDeducted));
        } else {
            statement.setNull(12, Types.DOUBLE);
        }
        
        if (!retentionAmount.isEmpty()) {
            statement.setDouble(13, Double.parseDouble(retentionAmount));
        } else {
            statement.setNull(13, Types.DOUBLE);
        }
        
        if (!amountReceived.isEmpty()) {
            statement.setDouble(14, Double.parseDouble(amountReceived));
        } else {
            statement.setNull(14, Types.DOUBLE);
        }
        
        if (!amountReceivedInInr.isEmpty()) {
            statement.setDouble(15, Double.parseDouble(amountReceivedInInr));
        } else {
            statement.setNull(15, Types.DOUBLE);
        }
        
        if (!receivedDate.isEmpty()) {
            statement.setString(16, receivedDate);
        } else {
            statement.setNull(16, Types.DATE);
        }
        
        statement.setString(17, fircDetails);
        

        // Execute the statement
        int rowsInserted = statement.executeUpdate();

        if (rowsInserted > 0) {
            JOptionPane.showMessageDialog(this, "Invoice added successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to add invoice!");
        }

        // Close connections
        statement.close();
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error adding invoice: " + ex.getMessage());
    } catch (NumberFormatException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Please enter valid numeric values for amounts and percentages.");
    }
}

    private boolean isValidDateFormat(String dateStr) {
        if (dateStr == null || dateStr.isEmpty()) {
            return false;
        }

        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setLenient(false);
            sdf.parse(dateStr);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void clearFields() {
        customerIDField.setText("");
        projectIDField.setText("");
        poNoField.setText("");
        invoiceNoField.setText("");
        invoiceDateField.setText("");
        descriptionField.setText("");
        invoiceTypeComboBox.setSelectedIndex(0);
        invAmountInEuroField.setText("");
        invAmountInUsdField.setText("");
        invAmountInInrField.setText("");
        gstAmountField.setText("");
        tdsDeductedField.setText("");
        retentionAmountField.setText("");
        amountReceivedField.setText("");
        amountReceivedInInrField.setText("");
        receivedDateField.setText("");
        fircDetailsField.setText("");
        
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addButton) {
            addInvoice();
        }
    }

    private static void setUIFont(javax.swing.plaf.FontUIResource f) {
        java.util.Enumeration keys = UIManager.getDefaults().keys();
        while (keys.hasMoreElements()) {
            Object key = keys.nextElement();
            Object value = UIManager.get(key);
            if (value instanceof javax.swing.plaf.FontUIResource) {
                UIManager.put(key, f);
            }
        }
    }

    
}


/*import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AddInvoiceForm extends JFrame implements ActionListener {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/spps";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "kalaivani";

    private JTextField customerIdField, projectIdField, poNoField, invoiceNoField, invoiceDateField,
            invAmountEuroField, invAmountUsdField, invAmountInrField, gstAmountField, tdsDeductedField,
            retentionAmountField, amountReceivedField, amountReceivedInrField, receivedDateField, fircDetailsField;

    private JComboBox<String> invoiceTypeComboBox;

    private JTextArea descriptionArea;

    private JButton addButton;

    public AddInvoiceForm() {
        setTitle("Add Invoice");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Initialize components
        customerIdField = new JTextField();
        projectIdField = new JTextField();
        poNoField = new JTextField();
        invoiceNoField = new JTextField();
        invoiceDateField = new JTextField();
        descriptionArea = new JTextArea(5, 20);
        invoiceTypeComboBox = new JComboBox<>(new String[]{"Milestone", "Expense"});
        invAmountEuroField = new JTextField();
        invAmountUsdField = new JTextField();
        invAmountInrField = new JTextField();
        gstAmountField = new JTextField();
        tdsDeductedField = new JTextField();
        retentionAmountField = new JTextField();
        amountReceivedField = new JTextField();
        amountReceivedInrField = new JTextField();
        receivedDateField = new JTextField();
        fircDetailsField = new JTextField();
        addButton = new JButton("Add");

        // Set layout manager
        setLayout(new GridLayout(0, 2, 10, 10)); // 2 columns, gap of 10 between rows and columns

        // Add components to the frame
        add(new JLabel("<html><font color='red'>*</font>Customer ID:"));
        add(customerIdField);
        add(new JLabel("<html><font color='red'>*</font>Project ID:"));
        add(projectIdField);
        add(new JLabel("PO No:"));
        add(poNoField);
        add(new JLabel("<html><font color='red'>*</font>Invoice No:"));
        add(invoiceNoField);
        add(new JLabel("<html><font color='red'>*</font>Invoice Date (YYYY-MM-DD):"));
        add(invoiceDateField);
        add(new JLabel("Description:"));
        add(new JScrollPane(descriptionArea));
        add(new JLabel("<html><font color='red'>*</font>Invoice Type:"));
        add(invoiceTypeComboBox);
        add(new JLabel("Inv Amount in Euro:"));
        add(invAmountEuroField);
        add(new JLabel("Inv Amount in USD:"));
        add(invAmountUsdField);
        add(new JLabel("Inv Amount in INR:"));
        add(invAmountInrField);
        add(new JLabel("GST Amount:"));
        add(gstAmountField);
        add(new JLabel("TDS Deducted:"));
        add(tdsDeductedField);
        add(new JLabel("Retention Amount:"));
        add(retentionAmountField);
        add(new JLabel("Amount Received:"));
        add(amountReceivedField);
        add(new JLabel("Amount Received in INR:"));
        add(amountReceivedInrField);
        add(new JLabel("Received Date (YYYY-MM-DD):"));
        add(receivedDateField);
        add(new JLabel("FIRC Details:"));
        add(fircDetailsField);
        add(addButton);

        // Add action listener for the add button
        addButton.addActionListener(this);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle button click event
        if (e.getSource() == addButton) {
            addInvoice();
        }
    }

    private void addInvoice() {
        String customerId = customerIdField.getText().trim();
        String projectId = projectIdField.getText().trim();
        String poNo = poNoField.getText().trim();
        String invoiceNo = invoiceNoField.getText().trim();
        String invoiceDate = invoiceDateField.getText().trim();
        String description = descriptionArea.getText().trim();
        String invoiceType = (String) invoiceTypeComboBox.getSelectedItem();
        String invAmountEuro = invAmountEuroField.getText().trim();
        String invAmountUsd = invAmountUsdField.getText().trim();
        String invAmountInr = invAmountInrField.getText().trim();
        String gstAmount = gstAmountField.getText().trim();
        String tdsDeducted = tdsDeductedField.getText().trim();
        String retentionAmount = retentionAmountField.getText().trim();
        String amountReceived = amountReceivedField.getText().trim();
        String amountReceivedInr = amountReceivedInrField.getText().trim();
        String receivedDate = receivedDateField.getText().trim();
        String fircDetails = fircDetailsField.getText().trim();

        try {
            // Validate inputs (you can customize these validations as per your requirements)
            if (customerId.isEmpty() || projectId.isEmpty() || invoiceNo.isEmpty() || invoiceDate.isEmpty() ||
                      invoiceType.isEmpty()) {
                throw new IllegalArgumentException("Required fields cannot be empty.");
            }
            // Additional validation logic can be added here

            // Insert invoice into database
            Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
            String query = "INSERT INTO Invoice (customer_id, ProjectID, po_no, invoice_no, invoice_date, " +
                    "description, invoice_type, inv_amount_in_euro, inv_amount_in_usd, inv_amount_in_inr, " +
                    "gst_amount, tds_deducted, retention_amount, amount_received, amount_received_in_inr, " +
                    "received_date, firc_details) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, customerId);
            pst.setString(2, projectId);
            pst.setString(3, poNo);
            pst.setString(4, invoiceNo);
            pst.setDate(5, java.sql.Date.valueOf(invoiceDate));
            pst.setString(6, description);
            pst.setString(7, invoiceType);
            pst.setString(8, invAmountEuro);
            pst.setString(9, invAmountUsd);
            pst.setString(10, invAmountInr);
            pst.setString(11, gstAmount);
            pst.setString(12, tdsDeducted);
            pst.setString(13, retentionAmount);
            pst.setString(14, amountReceived);
            pst.setString(15, amountReceivedInr);
            pst.setDate(16, java.sql.Date.valueOf(receivedDate));
            pst.setString(17, fircDetails);

            pst.executeUpdate();
            pst.close();
            conn.close();

            JOptionPane.showMessageDialog(this, "Invoice added successfully.");
            dispose(); // Close the form after successful addition
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: Failed to add invoice.");
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AddInvoiceForm());
    }
}
*/