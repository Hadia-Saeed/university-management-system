import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPanel extends JPanel {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel messageLabel;
    private MainFrame mainFrame;

    public LoginPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setBackground(new Color(30, 30, 30));
        setLayout(new BorderLayout());

        // TITLE at top
        JLabel titleLabel = new JLabel("Campus Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        add(titleLabel, BorderLayout.NORTH);

        // FORM in center using GridLayout
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(2, 2, 10, 15)); // 2 rows, 2 cols, gaps
        formPanel.setBackground(Color.DARK_GRAY);

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

        // Center the form panel in the middle of the login panel
        JPanel middlePanel = new JPanel();
        middlePanel.setLayout(new FlowLayout(FlowLayout.CENTER,180,180));
        middlePanel.setBackground(Color.LIGHT_GRAY);
        formPanel.setPreferredSize(new Dimension(300, 120)); // adjust width/height as needed
        middlePanel.add(formPanel);
        add(middlePanel, BorderLayout.CENTER);

        // BUTTON and MESSAGE at bottom
        JPanel bottomPanel = new JPanel();                //bottom panel
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBackground(Color.BLUE);

        loginButton = new JButton("Login");            //Login button
        loginButton.setBackground(new Color(0, 120, 215));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        bottomPanel.add(loginButton);

        messageLabel = new JLabel("");                  //Login message label
        messageLabel.setBackground(Color.RED);
        messageLabel.setOpaque(true);
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
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            messageLabel.setText("Please fill in all fields.");
            return;
        }

        if (SessionManager.login(username, password)) {
            messageLabel.setBackground(Color.GREEN);
            messageLabel.setText("Welcome, " + SessionManager.getCurrentUser().getUserName());
            mainFrame.showDashboard();
        } else {
            messageLabel.setBackground(Color.RED);
            messageLabel.setText("Invalid credentials. Please try again.");
        }
    }
}