import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registerButton;
    private Connection connection;
    private static final int CODE = 0000; // Predefined code

    public LoginFrame() {
        setTitle("Login-SIM PROJECT PAYMENT SYSTEM");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\logo.png"));

        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
        

        // Initialize components
        usernameField = new JTextField(15);
        passwordField = new JPasswordField(15);
        loginButton = new JButton("Login");
        registerButton = new JButton("Register");

        // Set layout
        setLayout(new GridLayout(4, 2));

        // Add components to the frame
        add(new JLabel("Username:"));
        add(usernameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(registerButton);

        // Establish database connection
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/spps", "root", "kalaivani");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Add action listener for the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Check if username or password is empty
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Username and password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Perform authentication
                if (authenticate(username, password)) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login successful!");
                    // Open the main application window or perform other actions
                    dispose(); // Close the login frame
                    new HomeScreen(); // Open the home screen
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid username or password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add action listener for the register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Check if username or password is empty
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Username and password cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Prompt user to enter the code
                String codeStr = JOptionPane.showInputDialog(LoginFrame.this, "Enter the code:");
                if (codeStr != null && !codeStr.isEmpty()) {
                    try {
                        int code = Integer.parseInt(codeStr);
                        if (code == CODE) {
                            // Perform registration
                            if (register(username, password)) {
                                JOptionPane.showMessageDialog(LoginFrame.this, "Registration successful!");
                            } else {
                                JOptionPane.showMessageDialog(LoginFrame.this, "Username already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(LoginFrame.this, "Invalid code!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Invalid code format!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        setVisible(true);
    }

    // Method to authenticate user
    private boolean authenticate(String username, String password) {
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");
            statement.setString(1, username);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to register new user
    private boolean register(String username, String password) {
        try {
            // Check if username already exists
            PreparedStatement checkStatement = connection.prepareStatement("SELECT * FROM users WHERE username=?");
            checkStatement.setString(1, username);
            ResultSet resultSet = checkStatement.executeQuery();
            if (resultSet.next()) {
                // Username already exists
                return false;
            }

            // Insert new user into database
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO users (username, password) VALUES (?, ?)");
            insertStatement.setString(1, username);
            insertStatement.setString(2, password);
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
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
        // Set look and feel to the system's default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        // Create and display the login frame
        SwingUtilities.invokeLater(LoginFrame::new);
    }
}
