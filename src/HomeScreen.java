import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HomeScreen extends JFrame {
    public HomeScreen() {
        setTitle("Home Screen-SIM PROJECT PAYMENT SYSTEM");
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Ishwarya\\Documents\\NetBeansProjects\\sppspro\\src\\logo.png"));

        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create buttons for each category
        JButton categoryButton = new JButton("Category");
        JButton customerButton = new JButton("Customer");
        JButton projectButton = new JButton("Project");
        JButton invoiceButton = new JButton("Invoice");
        JButton reportButton = new JButton("Report");

        // Add action listeners to open respective screens
        categoryButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openCategoryScreen();
            }
        });

        customerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openCustomerScreen();
            }
        });

        projectButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openProjectScreen();
            }
        });

        invoiceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openInvoiceScreen();
            }
        });

        reportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                openReportScreen();
            }
        });

        // Create panel to hold buttons
        JPanel panel = new JPanel(new GridLayout(5, 1, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        panel.add(categoryButton);
        panel.add(customerButton);
        panel.add(projectButton);
        panel.add(invoiceButton);
        panel.add(reportButton);

        // Add panel to the frame
        add(panel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void openCategoryScreen() {
        dispose();
        new MainScreenCategory();
    }

    private void openCustomerScreen() {
        dispose();
        new MainScreenCustomer();
    }

    private void openProjectScreen() {
        // Logic to open the project screen
        dispose();
        new MainScreenProject();
    }

    private void openInvoiceScreen() {
        dispose();
        new MainScreenInvoice();
    }

    private void openReportScreen() {
        // Logic to open the report screen
        dispose();
        new MainScreenReport();
    }

}