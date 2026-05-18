import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel {
    private JTextField userIDField, usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;
    private MainFrame mainFrame;

    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setBackground(new Color(30, 30, 30));
        setLayout(new BorderLayout());

        // TITLE at top
        JLabel titleLabel = new JLabel("Smart Campus Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        // FORM in center using GridLayout
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2, 10, 15)); // 3 rows, 2 cols, gaps
        formPanel.setBackground(new Color(30, 30, 30));

        // UserID Label and Field
        JLabel userIDLabel = new JLabel("User ID:");
        userIDLabel.setForeground(Color.WHITE);
        userIDField = new JTextField();
        formPanel.add(userIDLabel);
        formPanel.add(userIDField);

        // Username Label and Field
        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setForeground(Color.WHITE);
        usernameField = new JTextField();
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordField = new JPasswordField();
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        add(formPanel, BorderLayout.CENTER);

        // BUTTON and MESSAGE at bottom
        JPanel bottomPanel = new JPanel();                //bottom panel
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBackground(new Color(30, 30, 30));

        loginButton = new JButton("Login");            //Login button
        loginButton.setBackground(new Color(0, 120, 215));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        bottomPanel.add(loginButton);

        messageLabel = new JLabel("");                  //Login message label
        messageLabel.setForeground(Color.RED);
        bottomPanel.add(messageLabel);

        add(bottomPanel, BorderLayout.SOUTH);

        // Login Button Action
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });
    }

    private void handleLogin() {
        String userID = userIDField.getText().trim();
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (userID.isEmpty() || username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Please fill in all fields.");
            return;
        }

        if (SessionManager.login(username, password)) {
            messageLabel.setForeground(Color.GREEN);
            messageLabel.setText("Welcome, " + SessionManager.getCurrentUser().getUserName());
            mainFrame.showDashboard();
        } else {
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Invalid credentials. Please try again.");
        }
    }
}