import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TimeTablePanel extends JPanel {

    private MainFrame mainFrame;

    // Table to show timetable
    private JTable timetableTable;
    private String[] columns = {"Course ID", "Course Name", "Day", "Time", "Classroom", "Credit Hours"};

    // Text area for report
    private JTextArea reportArea;

    // Buttons
    private JButton showTimetableButton, showReportButton;

    public TimeTablePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        // ── TOP: TITLE LABEL ─────────────────────────────
        JLabel titleLabel = new JLabel("Timetable & Reports", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBackground(new Color(50, 50, 50));
        titleLabel.setOpaque(true);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(titleLabel, BorderLayout.NORTH);

        // ── CENTER: SPLIT into timetable table + report area ──
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 1, 5, 5));

        // TOP HALF: Timetable table
        timetableTable = new JTable(getTimetableData(), columns);
        JScrollPane tableScroll = new JScrollPane(timetableTable);
        tableScroll.setBorder(BorderFactory.createTitledBorder("Class Schedule"));
        centerPanel.add(tableScroll);

        // BOTTOM HALF: Report text area
        reportArea = new JTextArea();
        reportArea.setEditable(false);
        reportArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane reportScroll = new JScrollPane(reportArea);
        reportScroll.setBorder(BorderFactory.createTitledBorder("Resource Usage Report"));
        centerPanel.add(reportScroll);

        add(centerPanel, BorderLayout.CENTER);

        // ── SOUTH: BUTTONS ───────────────────────────────
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(50, 50, 50));

        showTimetableButton = new JButton("Refresh Timetable");
        showReportButton = new JButton("Generate Report");

        buttonPanel.add(showTimetableButton);
        buttonPanel.add(showReportButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // ── BUTTON ACTIONS ───────────────────────────────

        // Refresh timetable table
        showTimetableButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timetableTable.setModel(new javax.swing.table.DefaultTableModel(getTimetableData(), columns));
                JOptionPane.showMessageDialog(null, "Timetable refreshed.");
            }
        });

        // Generate a text report in the text area
        showReportButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                generateReport();
            }
        });

        // Load report automatically on open
        generateReport();
    }

    // Pull course data from repo for the timetable table
    private String[][] getTimetableData() {
        int size = mainFrame.courseRepo.getList().size();
        String[][] data = new String[size][6];
        for (int i = 0; i < size; i++) {
            Course c = mainFrame.courseRepo.getList().get(i);
            data[i][0] = c.getCourseID();
            data[i][1] = c.getCourseName();
            data[i][2] = c.getDay();
            data[i][3] = c.getTime();
            data[i][4] = c.getClassroom();
            data[i][5] = c.getCreditHours();
        }
        return data;
    }

    // Build a simple text report
    private void generateReport() {
        StringBuilder sb = new StringBuilder();

        sb.append("=== SYSTEM REPORT ===\n\n");

        // Student summary
        sb.append("--- Students ---\n");
        sb.append("Total Students: " + mainFrame.studentRepo.getList().size() + "\n");
        for (int i = 0; i < mainFrame.studentRepo.getList().size(); i++) {
            Student s = mainFrame.studentRepo.getList().get(i);
            sb.append("  " + s.getUserID() + " | " + s.getUserName() + " | CGPA: " + s.getCgpa() + "\n");
        }

        sb.append("\n--- Courses ---\n");
        sb.append("Total Courses: " + mainFrame.courseRepo.getList().size() + "\n");
        for (int i = 0; i < mainFrame.courseRepo.getList().size(); i++) {
            Course c = mainFrame.courseRepo.getList().get(i);
            sb.append("  " + c.getCourseID() + " | " + c.getCourseName() + " | " + c.getDay() + " " + c.getTime() + "\n");
        }

        sb.append("\n--- Facilities ---\n");
        sb.append("Total Facilities: " + mainFrame.facilityRepo.getList().size() + "\n");
        for (int i = 0; i < mainFrame.facilityRepo.getList().size(); i++) {
            Facility f = mainFrame.facilityRepo.getList().get(i);
            sb.append("  " + f.getEntityID() + " | " + f.getName() + " | Cost: " + f.getMaintenanceCost() + "\n");
        }

        sb.append("\n=== END OF REPORT ===");

        reportArea.setText(sb.toString());
    }
}
