package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserDashBoard extends JFrame {
    public UserDashBoard() {
        setTitle("RapidPay - User Dashboard");
        setSize(800, 600); // Increased size for better layout
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Dashboard panel with light theme
        JPanel dashboardPanel = new JPanel(null);
        dashboardPanel.setBackground(new Color(240, 248, 255)); // Light theme background

        // Add header
        JLabel header = new JLabel(" RapidPay ", SwingConstants.CENTER);
        header.setFont(new Font("Arial", Font.BOLD, 28));
        header.setForeground(new Color(20, 60, 120));
        header.setBounds(0, 10, 800, 50);
        dashboardPanel.add(header);

        // Add balance overview
        JLabel balanceLabel = new JLabel("Your Balance:");
        balanceLabel.setFont(new Font("Arial", Font.BOLD, 20));
        balanceLabel.setBounds(30, 80, 200, 30);
        dashboardPanel.add(balanceLabel);

        JLabel balanceAmount = new JLabel(" $ 12,345.67", SwingConstants.LEFT);
        balanceAmount.setFont(new Font("Arial", Font.PLAIN, 24));
        balanceAmount.setForeground(new Color(34, 139, 34)); // Green for balance
        balanceAmount.setBounds(30, 120, 200, 30);
        dashboardPanel.add(balanceAmount);

        // Add buttons for actions (Send Money, Add Money, Pay Bill)
        JButton sendMoneyButton = new JButton("Send Money");
        styleButton(sendMoneyButton);
        sendMoneyButton.setBounds(30, 180, 150, 40);
        sendMoneyButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Send Money feature coming soon!"));
        addHoverEffect(sendMoneyButton);
        dashboardPanel.add(sendMoneyButton);

        JButton addMoneyButton = new JButton("Add Money");
        styleButton(addMoneyButton);
        addMoneyButton.setBounds(200, 180, 150, 40);
        addMoneyButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Add Money feature coming soon!"));
        addHoverEffect(addMoneyButton);
        dashboardPanel.add(addMoneyButton);

        JButton payBillButton = new JButton("Pay Bill");
        styleButton(payBillButton);
        payBillButton.setBounds(370, 180, 150, 40);
        payBillButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Pay Bill feature coming soon!"));
        addHoverEffect(payBillButton);
        dashboardPanel.add(payBillButton);

        // Add a container panel for transaction and notifications side by side
        JPanel sideBySidePanel = new JPanel(new GridLayout(1, 2, 10, 0)); // 1 row, 2 columns, with 10px spacing
        sideBySidePanel.setBounds(30, 250, 740, 120); // Positioned below the balance section

        // Recent transactions section
        JPanel transactionPanel = new JPanel();
        transactionPanel.setLayout(new BorderLayout());
        JTextArea transactionArea = new JTextArea(
                "1. Received 500 from John\n" +
                        "2. Sent 300 to Grocery Store\n" +
                        "3. Paid 100 for Electricity Bill\n"
        );
        transactionArea.setFont(new Font("Arial", Font.PLAIN, 16));
        transactionArea.setEditable(false);
        transactionArea.setBackground(new Color(245, 245, 245)); // Light gray
        transactionArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        transactionPanel.add(new JScrollPane(transactionArea), BorderLayout.CENTER);
        transactionPanel.setBorder(BorderFactory.createTitledBorder("Recent Transactions")); // Border for separation

        // Notifications section
        JPanel notificationsPanel = new JPanel();
        notificationsPanel.setLayout(new BorderLayout());
        JTextArea notificationsArea = new JTextArea(
                "1. New feature: Scheduled Payments\n" +
                        "2. Update: Increased security measures\n"
        );
        notificationsArea.setFont(new Font("Arial", Font.PLAIN, 16));
        notificationsArea.setEditable(false);
        notificationsArea.setBackground(new Color(245, 245, 245)); // Light gray
        notificationsArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        notificationsPanel.add(new JScrollPane(notificationsArea), BorderLayout.CENTER);
        notificationsPanel.setBorder(BorderFactory.createTitledBorder("Notifications")); // Border for separation

        // Add both panels to the sideBySidePanel
        sideBySidePanel.add(transactionPanel);
        sideBySidePanel.add(notificationsPanel);

        // Add the sideBySidePanel to the main dashboardPanel
        dashboardPanel.add(sideBySidePanel);

        // Add a footer button for log out
        JButton logoutButton = new JButton("Log Out");
        styleButton(logoutButton);
        logoutButton.setBounds(670, 520, 100, 30); // Adjusted position for visibility
        logoutButton.addActionListener((ActionEvent e) -> {
            // Show confirmation dialog
            int response = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to log out?",
                    "Log Out Confirmation",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);

            if (response == JOptionPane.YES_OPTION) {

                System.exit(0); // Close application
            }
        });
        addHoverEffect(logoutButton); // Ensure hover effect is applied before adding it to the panel
        dashboardPanel.add(logoutButton); // Add button to the panel

        // Add the panel to the frame
        add(dashboardPanel);

        // Make sure to update the layout correctly
        revalidate();
        repaint();
    }

    // Button styling helper method
    private void styleButton(JButton button) {
        button.setFocusPainted(false);
        button.setBackground(new Color(20, 60, 120));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
    }

    // Add hover effect to buttons
    private void addHoverEffect(JButton button) {
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(30, 80, 150)); // Lighter shade on hover
            }

            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(20, 60, 120)); // Original shade when not hovered
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(UserDashBoard::new);
    }
}
