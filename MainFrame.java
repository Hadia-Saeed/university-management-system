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
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setLayout(new BorderLayout());

        // SHOW LOGIN FIRST
        loginPanel = new LoginPanel(this);
        add(loginPanel, BorderLayout.CENTER);

        // SAVE ON CLOSE
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                DataManager.saveAll(studentRepo, courseRepo, facilityRepo, userList);
                System.out.println("Data saved. Goodbye!");
                System.exit(0);
            }
        });

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

        // ADD TABS
        tabbedPane.addTab("Student Management", new StudentPanel(this));
        tabbedPane.addTab("Course Management", new CoursePanel(this));
        tabbedPane.addTab("Facility Management", new FacilityPanel(this));
        tabbedPane.addTab("Timetable & Reports", new TimetablePanel(this));
        tabbedPane.addTab("Campus Map", new CampusMapPanel(this));

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
                SessionManager.logout();
                getContentPane().removeAll();
                loginPanel = new LoginPanel(MainFrame.this);
                add(loginPanel, BorderLayout.CENTER);
                revalidate();
                repaint();
            }
        });
        topPanel.add(logoutButton);

        add(topPanel, BorderLayout.NORTH);

        revalidate();
        repaint();
    }

    public static void main(String[] args) {
        new MainFrame();
    }
}