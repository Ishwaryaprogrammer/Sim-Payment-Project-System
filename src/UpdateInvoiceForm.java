import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import static java.lang.Double.parseDouble;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UpdateInvoiceForm extends JFrame implements ActionListener {
    // JDBC URL, username, and password
    static final String JDBC_URL = "jdbc:mysql://localhost:3306/spps";
    static final String USERNAME = "root";
    static final String PASSWORD = "kalaivani";

    // Swing components
    JLabel lblInvoiceNo, lblCustomerID, lblProjectID, lblPONo, lblInvoiceDate, lblDescription,
            lblInvoiceType, lblAmountInEuro, lblAmountInUSD, lblAmountInINR, lblGSTAmount,
            lblTDSDeducted, lblRetentionAmount, lblAmountReceived, lblAmountReceivedINR, lblReceivedDate,
            lblFIRCDetails;
    JTextField txtInvoiceNo, txtCustomerID, txtProjectID, txtPONo,  txtDescription,
            txtAmountInEuro, txtAmountInUSD, txtAmountInINR, txtGSTAmount,
            txtTDSDeducted, txtRetentionAmount, txtAmountReceived, txtAmountReceivedINR,
            txtFIRCDetails;
    JComboBox<String> cbInvoiceType;
    JFormattedTextField txtInvoiceDate,txtReceivedDate;
    JButton updateButton, fetchButton;

    public UpdateInvoiceForm(String invoiceNo) {
        setTitle("Update Invoice-SIM PROJECT PAYMENT SYSTEM");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\logo.png"));

        setSize(1200, 800);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Set default font size and style
        Font defaultFont = new Font("Times New Roman", Font.PLAIN, 20);
        setUIFont(new javax.swing.plaf.FontUIResource(defaultFont));

        // Initialize Swing components
        lblInvoiceNo = new JLabel("<html><font color='red'>*</font>Invoice No:");
        lblCustomerID = new JLabel("Customer ID:");
        lblProjectID = new JLabel("Project ID:");
        lblPONo = new JLabel("PO No:");
        lblInvoiceDate = new JLabel("Invoice Date:");
        lblDescription = new JLabel("Description:");
        lblInvoiceType = new JLabel("Invoice Type:");
        lblAmountInEuro = new JLabel("Amount in Euro:");
        lblAmountInUSD = new JLabel("Amount in USD:");
        lblAmountInINR = new JLabel("Amount in INR:");
        lblGSTAmount = new JLabel("GST Amount:");
        lblTDSDeducted = new JLabel("TDS Deducted:");
        lblRetentionAmount = new JLabel("Retention Amount:");
        lblAmountReceived = new JLabel("Amount Received:");
        lblAmountReceivedINR = new JLabel("Amount Received (INR):");
        lblReceivedDate = new JLabel("Received Date:");
        lblFIRCDetails = new JLabel("FIRC Details:");
        

        // Initialize text fields
        txtInvoiceNo = new JTextField(20);
        txtCustomerID = new JTextField(20);
        txtProjectID = new JTextField(20);
        txtPONo = new JTextField(20);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        txtInvoiceDate = new JFormattedTextField(dateFormat);
        txtDescription = new JTextField(50);
        txtAmountInEuro = new JTextField(20);
        txtAmountInUSD = new JTextField(20);
        txtAmountInINR = new JTextField(20);
        txtGSTAmount = new JTextField(20);
        txtTDSDeducted = new JTextField(20);
        txtRetentionAmount = new JTextField(20);
        txtAmountReceived = new JTextField(20);
        txtAmountReceivedINR = new JTextField(20);
        txtReceivedDate = new JFormattedTextField(dateFormat);
        txtFIRCDetails = new JTextField(50);
        
        // Initialize combo box
        cbInvoiceType = new JComboBox<>(new String[]{"Milestone", "Expense"});

        
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
        add(lblInvoiceNo, gbc);

        gbc.gridy = 1;
        add(lblCustomerID, gbc);

        gbc.gridy = 2;
        add(lblProjectID, gbc);

        gbc.gridy = 3;
        add(lblPONo, gbc);

        gbc.gridy = 4;
        add(lblInvoiceDate, gbc);

        gbc.gridy = 5;
        add(lblDescription, gbc);

        gbc.gridy = 6;
        add(lblInvoiceType, gbc);

        gbc.gridy = 7;
        add(lblAmountInEuro, gbc);

        gbc.gridy = 8;
        add(lblAmountInUSD, gbc);

        gbc.gridy = 9;
        add(lblAmountInINR, gbc);

        gbc.gridy = 10;
        add(lblGSTAmount, gbc);

        gbc.gridy = 11;
        add(lblTDSDeducted, gbc);

        gbc.gridy = 12;
        add(lblRetentionAmount, gbc);

        gbc.gridy = 13;
        add(lblAmountReceived, gbc);

        gbc.gridy = 14;
        add(lblAmountReceivedINR, gbc);

        gbc.gridy = 15;
        add(lblReceivedDate, gbc);

        gbc.gridy = 16;
        add(lblFIRCDetails, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Increase width to 2 grids
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(txtInvoiceNo, gbc);

        gbc.gridy = 1;
        add(txtCustomerID, gbc);

        gbc.gridy = 2;
        add(txtProjectID, gbc);

        gbc.gridy = 3;
        add(txtPONo, gbc);

        gbc.gridy = 4;
        add(txtInvoiceDate, gbc);

        gbc.gridy = 5;
        add(txtDescription, gbc);

        gbc.gridy = 6;
        add(cbInvoiceType, gbc);
        
        gbc.gridy = 7;
        add(txtAmountInEuro, gbc);

        gbc.gridy = 8;
        add(txtAmountInUSD, gbc);

        gbc.gridy = 9;
        add(txtAmountInINR, gbc);

        gbc.gridy = 10;
        add(txtGSTAmount, gbc);

        gbc.gridy = 11;
        add(txtTDSDeducted, gbc);

        gbc.gridy = 12;
        add(txtRetentionAmount, gbc);

        gbc.gridy = 13;
        add(txtAmountReceived, gbc);

        gbc.gridy = 14;
        add(txtAmountReceivedINR, gbc);

        gbc.gridy = 15;
        add(txtReceivedDate, gbc);

        gbc.gridy = 16;
        add(txtFIRCDetails, gbc);

        gbc.gridx = 2;
        gbc.gridy = 17;
        gbc.gridwidth = 1; // Reset grid width
        gbc.fill = GridBagConstraints.NONE;
        add(updateButton, gbc);

        gbc.gridx = 3;
        gbc.gridy = 0;
        add(fetchButton, gbc);

        // If invoice ID is provided, fetch invoice details
        if (invoiceNo != null && !invoiceNo.isEmpty()) {
        txtInvoiceNo.setText(invoiceNo);
        fetchInvoiceDetails(invoiceNo);
        }
        
        
        JScrollPane scrollPane = new JScrollPane(this.getContentPane());
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        // Set the content pane of the JFrame to the scroll pane
        setContentPane(scrollPane);
        

            setVisible(true);
}

private void fetchInvoiceDetails(String invoiceNo) {
    try {
        // Establish connection to MySQL
        Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

        // Prepare SQL statement to fetch invoice details
        String sql = "SELECT * FROM invoice WHERE invoice_no = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, invoiceNo);

        // Execute the statement
        ResultSet resultSet = statement.executeQuery();

        // Populate fields with fetched invoice details
        if (resultSet.next()) {
            
            txtInvoiceNo.setText(resultSet.getString("invoice_no"));
            txtCustomerID.setText(resultSet.getString("customer_id"));
            txtProjectID.setText(resultSet.getString("ProjectID"));
            txtPONo.setText(resultSet.getString("po_no"));
            txtInvoiceDate.setValue(resultSet.getDate("invoice_date"));
            txtDescription.setText(resultSet.getString("description"));
            cbInvoiceType.setSelectedItem(resultSet.getString("invoice_type"));
            txtAmountInEuro.setText(String.valueOf(resultSet.getFloat("inv_amount_in_euro")));
            txtAmountInUSD.setText(String.valueOf((float)resultSet.getFloat("inv_amount_in_usd")));
            txtAmountInINR.setText(String.valueOf(resultSet.getFloat("inv_amount_in_inr")));
            txtGSTAmount.setText(String.valueOf(resultSet.getFloat("gst_amount")));
            txtTDSDeducted.setText(String.valueOf(resultSet.getFloat("tds_deducted")));
            txtRetentionAmount.setText(String.valueOf(resultSet.getFloat("retention_amount")));
            txtAmountReceived.setText(String.valueOf(resultSet.getFloat("amount_received")));
            txtAmountReceivedINR.setText(String.valueOf(resultSet.getFloat("amount_received_in_inr")));
            txtReceivedDate.setValue(resultSet.getDate("received_date"));
            txtFIRCDetails.setText(resultSet.getString("firc_details"));
            
        
        } else {
            JOptionPane.showMessageDialog(this, "Invoice with  " + invoiceNo + " not found!");
        }

        // Close connections
        resultSet.close();
        statement.close();
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error fetching invoice details: " + ex.getMessage());
    }
}
private void updateInvoice() {
    String invoiceNo = txtInvoiceNo.getText().trim();
    if (invoiceNo.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Please enter a Invoice No!");
        return;
    }

    try {
        // Establish connection to MySQL
        Connection conn = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);

        // Check if the invoice exists
        String checkSql = "SELECT COUNT(*) FROM invoice WHERE invoice_no = ?";
        PreparedStatement checkStatement = conn.prepareStatement(checkSql);
        checkStatement.setString(1, invoiceNo);
        ResultSet rs = checkStatement.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        rs.close();
        checkStatement.close();

        if (count == 0) {
            JOptionPane.showMessageDialog(this, "Invoice No does not exist!");
            conn.close();
            return;
        }

        // Proceed with update if invoice exists
        String sql = "UPDATE invoice SET customer_id=?, ProjectID=?, po_no=?, invoice_date=?, description=?, " +
                "invoice_type=?, inv_amount_in_euro=?, inv_amount_in_usd=?, inv_amount_in_inr=?, gst_amount=?, " +
                "tds_deducted=?, retention_amount=?, amount_received=?, amount_received_in_inr=?, received_date=?, " +
                "firc_details=? WHERE invoice_no=?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, txtCustomerID.getText());
        statement.setString(2, txtProjectID.getText());
        statement.setString(3, txtPONo.getText());
        if (!txtInvoiceDate.getText().isEmpty()) {
            statement.setString(4, txtInvoiceDate.getText());
        } else {
            statement.setNull(4, Types.DATE); // Set NULL if the field is empty
        }
        statement.setString(5, txtDescription.getText());
        statement.setString(6, cbInvoiceType.getSelectedItem().toString());
        statement.setDouble(7, parseDouble(txtAmountInEuro.getText()));
        statement.setDouble(8, parseDouble(txtAmountInUSD.getText()));
        statement.setDouble(9, parseDouble(txtAmountInINR.getText()));
        statement.setDouble(10, parseDouble(txtGSTAmount.getText()));
        statement.setDouble(11, parseDouble(txtTDSDeducted.getText()));
        statement.setDouble(12, parseDouble(txtRetentionAmount.getText()));
        statement.setDouble(13, parseDouble(txtAmountReceived.getText()));
        statement.setDouble(14, parseDouble(txtAmountReceivedINR.getText()));
        
        if (!txtReceivedDate.getText().isEmpty()) {
            statement.setString(15, txtReceivedDate.getText());
        } else {
            statement.setNull(15, Types.DATE); // Set NULL if the field is empty
        }
        statement.setString(16, txtFIRCDetails.getText());
        statement.setString(17, txtInvoiceNo.getText()); // InvoiceNo as WHERE condition

        // Execute the statement
        int rowsUpdated = statement.executeUpdate();

        if (rowsUpdated > 0) {
            JOptionPane.showMessageDialog(this, "Invoice details updated successfully!");
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update invoice details!");
        }
        // Close connections
        statement.close();
        conn.close();
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error updating  details: " + ex.getMessage());
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            updateInvoice();
        } else if (e.getSource() == fetchButton) {
            String invoiceNo = txtInvoiceNo.getText();
            if (invoiceNo != null && !invoiceNo.isEmpty()) {
                fetchInvoiceDetails(invoiceNo);
            } else {
                JOptionPane.showMessageDialog(this, "Please enter a Invoice No!");
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
