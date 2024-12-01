package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.Border;

public class Login {

    public JPanel createLoginPage(JPanel mainPanel, CardLayout cardLayout) {
        JPanel panel = new JPanel(null);

        // Background image
        JLabel background = new JLabel(new ImageIcon("C:\\propic\\propic.png")); // Update with the correct image path
        background.setBounds(0, 0, 800, 480);

        // White background for the login form (no transparency)
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(null);
        loginPanel.setOpaque(true);
        loginPanel.setBackground(Color.WHITE); // Solid white background
        loginPanel.setBounds(370, 100, 350, 310);


// Apply rounded borders to the login panel
        Border roundedBorder = createRoundedBorder(50, Color.LIGHT_GRAY, 2); // Rounded border with a 10px radius
        loginPanel.setBorder(roundedBorder);



        // Role selection label and combo box
        String[] roles = {"User", "Admin"};
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        roleComboBox.setBounds(130, 20, 200, 30);
        roleComboBox.setFont(new Font("Arial", Font.PLAIN, 16));
        roleComboBox.setBackground(new Color(240, 240, 240));
        roleComboBox.setBorder(createRoundedBorder(10, Color.GRAY, 1)); // Rounded border
        loginPanel.add(roleComboBox);

        // Username label and text field with placeholder
        JTextField userTextField = new JTextField("Username"); // Placeholder text
        userTextField.setBounds(130, 70, 200, 40); // Adjusted position and size
        userTextField.setForeground(Color.GRAY); // Set color for placeholder text
        userTextField.setFont(new Font("Arial", Font.PLAIN, 16));
        userTextField.setBackground(new Color(255, 255, 255)); // Light background color
        userTextField.setCaretColor(Color.BLACK); // Set caret color
        userTextField.setBorder(createRoundedBorder(10, Color.GRAY, 1)); // Rounded border
        userTextField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (userTextField.getText().equals("Username")) {
                    userTextField.setText("");
                    userTextField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (userTextField.getText().isEmpty()) {
                    userTextField.setText("Username");
                    userTextField.setForeground(Color.GRAY);
                }
            }
        });
        loginPanel.add(userTextField);

        // Password label and password field with placeholder
        JPasswordField passField = new JPasswordField("Password"); // Placeholder text
        passField.setBounds(130, 120, 200, 40); // Adjusted position
        passField.setEchoChar((char) 0); // Make the password visible (for placeholder)
        passField.setForeground(Color.GRAY); // Set color for placeholder text
        passField.setFont(new Font("Arial", Font.PLAIN, 16));
        passField.setBackground(new Color(255, 255, 255)); // Light background color
        passField.setCaretColor(Color.BLACK); // Set caret color
        passField.setBorder(createRoundedBorder(10, Color.GRAY, 1)); // Rounded border
        passField.addFocusListener(new FocusAdapter() {
            public void focusGained(FocusEvent e) {
                if (new String(passField.getPassword()).equals("Password")) {
                    passField.setText("");
                    passField.setForeground(Color.BLACK);
                    passField.setEchoChar('⋅'); // Change placeholder dot
                }
            }

            public void focusLost(FocusEvent e) {
                if (new String(passField.getPassword()).isEmpty()) {
                    passField.setText("Password");
                    passField.setForeground(Color.GRAY);
                    passField.setEchoChar((char) 0); // Make the password visible again (for placeholder)
                }
            }
        });
        loginPanel.add(passField);

        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.setBounds(130, 190, 100, 40);
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(new Color(0, 123, 255)); // Blue background
        loginButton.setForeground(Color.WHITE);
        loginButton.setBorder(createRoundedBorder(20, new Color(0, 123, 255), 2)); // Rounded border
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(0, 105, 217)); // Darker blue on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(new Color(0, 123, 255)); // Normal blue on exit
            }
        });
        loginPanel.add(loginButton);

        // Sign-up button
        JButton signUpButton = new Button();
        signUpButton.setText("Sign Up");
        signUpButton.setBounds(240, 190, 100, 40);
        signUpButton.setFont(new Font("Arial", Font.BOLD, 16));
        signUpButton.setBackground(new Color(34, 193, 195)); // Gradient background
        signUpButton.setForeground(Color.WHITE);
        signUpButton.setFocusPainted(false);
        signUpButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        signUpButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                signUpButton.setBackground(new Color(28, 170, 171)); // Darker gradient on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                signUpButton.setBackground(new Color(34, 193, 195)); // Normal gradient on exit
            }
        });
        signUpButton.addActionListener(e -> cardLayout.show(mainPanel, "SignUpPage"));
        loginPanel.add(signUpButton);

        // Forgot password button
        JButton forgotPasswordButton = new Button();
        forgotPasswordButton.setText("Forgot Password");
        forgotPasswordButton.setBounds(130, 240, 200, 40); // Adjusted position
        forgotPasswordButton.setFont(new Font("Arial", Font.PLAIN, 14));
        forgotPasswordButton.setForeground(Color.WHITE);
        forgotPasswordButton.setBackground(new Color(255, 87, 34)); // Red background
        forgotPasswordButton.setFocusPainted(false);
        forgotPasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        forgotPasswordButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                forgotPasswordButton.setBackground(new Color(229, 57, 53)); // Darker red on hover
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                forgotPasswordButton.setBackground(new Color(255, 87, 34)); // Normal red on exit
            }
        });
        forgotPasswordButton.addActionListener(e -> cardLayout.show(mainPanel, "ForgotPasswordPage"));
        loginPanel.add(forgotPasswordButton);

        // Add action listener for the login button
        loginButton.addActionListener((ActionEvent e) -> {
            String role = (String) roleComboBox.getSelectedItem();
            String username = userTextField.getText();
            String password = new String(passField.getPassword());

            if (username.isEmpty() && password.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Username and Password required !", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (username.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Username Required !", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(panel, "Password Required !", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Check for default admin credentials
            if ("Admin".equals(role) && "admin".equals(username) && "admin".equals(password)) {
                // If the credentials are "admin" for Admin role, directly go to Admin Dashboard
                SwingUtilities.invokeLater(() -> {
                    AdminDashBoard adminDashBoard = new AdminDashBoard();
                    adminDashBoard.setVisible(true);
                });

                // Close the current login window
                Window loginWindow = SwingUtilities.getWindowAncestor(panel);
                if (loginWindow != null) {
                    loginWindow.dispose();
                }

                return; // Exit the method to avoid the database check for Admin
            }

            // Proceed with database check for other roles
            try (Connection connection = DatabaseUtil.getConnection()) {
                String query;
                if ("Admin".equals(role)) {
                    query = "SELECT * FROM admins WHERE username = ? AND password = ?";
                } else {
                    query = "SELECT * FROM users WHERE username = ? AND password = ?";
                }

                try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, password);

                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        if (resultSet.next()) {
                            // Open the appropriate dashboard based on the role
                            SwingUtilities.invokeLater(() -> {
                                if ("Admin".equals(role)) {
                                    AdminDashBoard adminDashBoard = new AdminDashBoard();
                                    adminDashBoard.setVisible(true);
                                } else {
                                    UserDashBoard userDashboard = new UserDashBoard();
                                    userDashboard.setVisible(true);
                                }
                            });

                            // Close the current login window
                            Window loginWindow = SwingUtilities.getWindowAncestor(panel);
                            if (loginWindow != null) {
                                loginWindow.dispose();
                            }
                        } else {
                            JOptionPane.showMessageDialog(panel, "Invalid Username or Password", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panel, "Error connecting to the database. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        background.setLayout(null);
        background.add(loginPanel);

        panel.setLayout(null);
        panel.add(background);

        return panel;
    }

    // Helper method to create rounded borders
    private Border createRoundedBorder(int radius, Color color, int thickness) {
        return new javax.swing.border.LineBorder(color, thickness) {
            @Override
            public Insets getBorderInsets(Component c) {
                return new Insets(5, 5, 5, 5); // Padding inside the border
            }

            @Override
            public boolean isBorderOpaque() {
                return true;
            }

            @Override
            public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
                Graphics2D g2 = (Graphics2D) g;
                // Enable anti-aliasing for smoother edges
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2.setColor(getLineColor());
                g2.setStroke(new BasicStroke(thickness));
                g2.draw(new RoundRectangle2D.Float(x, y, width - 1, height - 1, radius, radius)); // Rounded rectangle
            }
        };
    }

}
