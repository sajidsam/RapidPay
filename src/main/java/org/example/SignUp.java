package org.example;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SignUp {

    public JPanel createSignUpPage(JPanel mainPanel, CardLayout cardLayout) {
        JPanel panel = new JPanel(null);

        // Background image
        JLabel background = new JLabel(new ImageIcon("C:\\propic\\propic.png"));
        background.setBounds(0, 0, 800, 480);

        JPanel signUpPanel = new JPanel();
        signUpPanel.setLayout(null);
        signUpPanel.setOpaque(false);
        signUpPanel.setBounds(350, 100, 400, 300);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setForeground(Color.WHITE);
        userLabel.setFont(new Font("Arial", Font.BOLD, 16));
        userLabel.setBounds(20, 20, 100, 30);
        signUpPanel.add(userLabel);

        JTextField userTextField = new JTextField();
        userTextField.setBounds(130, 20, 200, 30);
        signUpPanel.add(userTextField);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setFont(new Font("Arial", Font.BOLD, 16));
        emailLabel.setBounds(20, 70, 100, 30);
        signUpPanel.add(emailLabel);

        JTextField emailTextField = new JTextField();
        emailTextField.setBounds(130, 70, 200, 30);
        signUpPanel.add(emailTextField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setForeground(Color.WHITE);
        passLabel.setFont(new Font("Arial", Font.BOLD, 16));
        passLabel.setBounds(20, 120, 100, 30);
        signUpPanel.add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(130, 120, 200, 30);
        signUpPanel.add(passField);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setBounds(130, 170, 100, 30);
        signUpButton.addActionListener(e -> {
            String username = userTextField.getText().trim();
            String email = emailTextField.getText().trim();
            String password = new String(passField.getPassword()).trim();

            // Validate user input
            if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "All fields are required.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try (Connection connection = DatabaseUtil.getConnection()) {
                // Check for duplicate email
                String checkEmailQuery = "SELECT * FROM users WHERE email = ?";
                try (PreparedStatement checkEmailStmt = connection.prepareStatement(checkEmailQuery)) {
                    checkEmailStmt.setString(1, email);
                    try (ResultSet resultSet = checkEmailStmt.executeQuery()) {
                        if (resultSet.next()) {
                            JOptionPane.showMessageDialog(panel, "Email already exists. Please use a different email.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                }

                // Insert new user
                String query = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, email);
                    preparedStatement.setString(3, password); // You should hash passwords in production

                    int rowsInserted = preparedStatement.executeUpdate();

                    if (rowsInserted > 0) {
                        JOptionPane.showMessageDialog(panel, "Account Created Successfully!");

                        // Send a welcome email
                        EmailSender.sendEmail(
                                email,
                                "Welcome to RapidPay!",
                                "Dear " + username + ",\n\nThank you for signing up for RapidPay. We're excited to have you on board!\n\nBest Regards,\nRapidPay Team"
                        );
                    } else {
                        JOptionPane.showMessageDialog(panel, "Failed to create account. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        signUpPanel.add(signUpButton);

        JButton backButton = new JButton("Back to Login");
        backButton.setBounds(240, 170, 100, 30);
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "LoginPage"));
        signUpPanel.add(backButton);

        background.setLayout(null);
        background.add(signUpPanel);

        panel.setLayout(null);
        panel.add(background);

        return panel;
    }
}
