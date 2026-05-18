import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainFrame extends JFrame {

    // Repositories - shared across all panels
    CampusRepository<Student> studentRepo;
    CampusRepository<Course> courseRepo;
    CampusRepository<Facility> facilityRepo;
    ArrayList<User> userList;

    // Panels
    LoginPanel loginPanel;
    JTabbedPane tabbedPane;

    public MainFrame() {
        // LOAD DATA ON STARTUP
        studentRepo = DataManager.loadStudents();
        courseRepo = DataManager.loadCourses();
        facilityRepo = DataManager.loadFacilities();
        userList = DataManager.loadUsers();

        // load users into session manager
        SessionManager.loadUsers(userList);

        // FRAME SETUP
        setTitle("Smart Campus Management System");
        setSize(900, 650);
        setLocationRelativeTo(null);    //centers the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // SHOW LOGIN FIRST
        loginPanel = new LoginPanel(this);
        add(loginPanel, BorderLayout.CENTER);

        // AUTO SAVE EVERY 5 MINUTES
        javax.swing.Timer autoSave = new javax.swing.Timer(300000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DataManager.saveAll(studentRepo, courseRepo, facilityRepo, userList);
                System.out.println("Auto-saved.");
            }
        });
        autoSave.start();

        setVisible(true);
    }

    // Called by LoginPanel after successful login
    public void showDashboard() {
        remove(loginPanel); // remove login panel

        tabbedPane = new JTabbedPane();

        // Role Based Access 
       String role = SessionManager.getRole();
       if (role.equalsIgnoreCase("Admin")) {
        tabbedPane.addTab("Student Management", new StudentPanel(this));
        tabbedPane.addTab("Course Management", new CoursePanel(this));
        tabbedPane.addTab("Facility Management", new FacilityPanel(this));
        tabbedPane.addTab("Timetable & Reports", new TimeTablePanel(this));
        tabbedPane.addTab("Campus Map", new CampusMapPanel(this));
    } else if (role.equalsIgnoreCase("Teacher")) {
        tabbedPane.addTab("Course Management", new CoursePanel(this));
        tabbedPane.addTab("Student Management", new StudentPanel(this));
        tabbedPane.addTab("Timetable & Reports", new TimeTablePanel(this));
        tabbedPane.addTab("Campus Map", new CampusMapPanel(this));
    } else { // Student
        tabbedPane.addTab("Timetable & Reports", new TimeTablePanel(this));
        tabbedPane.addTab("Campus Map", new CampusMapPanel(this));
    }

        add(tabbedPane, BorderLayout.CENTER);

        // ADD LOGOUT BUTTON AT TOP
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        topPanel.setBackground(new Color(50, 50, 50));

        JLabel welcomeLabel = new JLabel("Logged in as: " + SessionManager.getCurrentUser().getUserName() + " (" + SessionManager.getCurrentUser().getRole() + ")");
        welcomeLabel.setForeground(Color.WHITE);
        topPanel.add(welcomeLabel);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBackground(new Color(200, 50, 50));
        logoutButton.setForeground(Color.WHITE);
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DataManager.saveAll(studentRepo, courseRepo, facilityRepo, userList);  //automatically save all data if user logouts
                SessionManager.logout();
                getContentPane().removeAll();
                loginPanel = new LoginPanel(MainFrame.this);
                add(loginPanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });
        topPanel.add(logoutButton);

        // SAVE AND EXIT BUTTON
        JButton exitButton = new JButton("Save & Exit");
        exitButton.setBackground(new Color(150, 50, 50));
        exitButton.setForeground(Color.WHITE);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DataManager.saveAll(studentRepo, courseRepo, facilityRepo, userList);
                JOptionPane.showMessageDialog(null, "Data saved. Goodbye!");
                System.exit(0);
            }
        });
        topPanel.add(exitButton);

        add(topPanel, BorderLayout.NORTH);

        revalidate();  //tells Java to recalculate the layout after you add/remove panels dynamically.
        repaint(); //tells Java to redraw the screen so the changes actually show up visually.
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}