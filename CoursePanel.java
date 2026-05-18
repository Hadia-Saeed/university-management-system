import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CoursePanel extends JPanel {

    private MainFrame mainFrame;

    // Input fields
    private JTextField idField, nameField, creditHoursField, dayField, timeField, classroomField;

    // Table
    private JTable courseTable;
    private String[] columns = {"Course ID", "Name", "Credit Hours", "Day", "Time", "Classroom"};

    // Buttons
    private JButton addButton, deleteButton, updateButton, selectButton;

    public CoursePanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        // ── TOP: FORM PANEL ──────────────────────────────
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(7, 2, 5, 5));
        formPanel.setBackground(new Color(50, 50, 50));

        JLabel idLabel = new JLabel("Course ID:");
        idLabel.setForeground(Color.WHITE);
        idField = new JTextField();
        formPanel.add(idLabel);
        formPanel.add(idField);

        JLabel nameLabel = new JLabel("Course Name:");
        nameLabel.setForeground(Color.WHITE);
        nameField = new JTextField();
        formPanel.add(nameLabel);
        formPanel.add(nameField);

        JLabel creditLabel = new JLabel("Credit Hours:");
        creditLabel.setForeground(Color.WHITE);
        creditHoursField = new JTextField();
        formPanel.add(creditLabel);
        formPanel.add(creditHoursField);

        JLabel dayLabel = new JLabel("Day:");
        dayLabel.setForeground(Color.WHITE);
        dayField = new JTextField();
        formPanel.add(dayLabel);
        formPanel.add(dayField);

        JLabel timeLabel = new JLabel("Time:");
        timeLabel.setForeground(Color.WHITE);
        timeField = new JTextField();
        formPanel.add(timeLabel);
        formPanel.add(timeField);

        JLabel classroomLabel = new JLabel("Classroom ID:");
        classroomLabel.setForeground(Color.WHITE);
        classroomField = new JTextField();
        formPanel.add(classroomLabel);
        formPanel.add(classroomField);

        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel(""));

        add(formPanel, BorderLayout.NORTH);

        // ── CENTER: TABLE ────────────────────────────────
        courseTable = new JTable(getData(), columns);
        JScrollPane scrollPane = new JScrollPane(courseTable);
        add(scrollPane, BorderLayout.CENTER);

        // ── SOUTH: BUTTONS ───────────────────────────────
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(50, 50, 50));

        addButton = new JButton("Add Course");
        deleteButton = new JButton("Delete Course");
        updateButton = new JButton("Update Course");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // ── BUTTON ACTIONS ───────────────────────────────

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addCourse();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteCourse();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateCourse();
            }
        });

        // fill fields using select button
        selectButton = new JButton("Select Course");
        buttonPanel.add(selectButton);

        selectButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int row = courseTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(null, "Please select a row first.");
                return;
            }
        idField.setText((String) courseTable.getValueAt(row, 0));
        nameField.setText((String) courseTable.getValueAt(row, 1));
        creditHoursField.setText((String) courseTable.getValueAt(row, 2));
        dayField.setText((String) courseTable.getValueAt(row, 3));
        timeField.setText((String) courseTable.getValueAt(row, 4));
        classroomField.setText((String) courseTable.getValueAt(row, 5));
    }
});
    }

    private String[][] getData() {
        int size = mainFrame.courseRepo.getList().size();
        String[][] data = new String[size][6];
        for (int i = 0; i < size; i++) {
            Course c = mainFrame.courseRepo.getList().get(i);
            data[i][0] = c.getCourseID();
            data[i][1] = c.getCourseName();
            data[i][2] = c.getCreditHours();
            data[i][3] = c.getDay();
            data[i][4] = c.getTime();
            data[i][5] = c.getClassroom();
        }
        return data;
    }

    private void refreshTable() {
        courseTable.setModel(new javax.swing.table.DefaultTableModel(getData(), columns));
    }

    private void addCourse() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String credits = creditHoursField.getText().trim();
        String day = dayField.getText().trim();
        String time = timeField.getText().trim();
        String classroom = classroomField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || credits.isEmpty() || day.isEmpty() || time.isEmpty() || classroom.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        Course c = new Course(classroom, name, id, credits, day, time);
        mainFrame.courseRepo.additem(c);
        refreshTable();
        clearFields();
        JOptionPane.showMessageDialog(this, "Course added successfully.");
    }

    private void deleteCourse() {
        int row = courseTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course to delete.");
            return;
        }
        mainFrame.courseRepo.removeitem(row);
        refreshTable();
        clearFields();
        JOptionPane.showMessageDialog(this, "Course deleted successfully.");
    }

    private void updateCourse() {
        int row = courseTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a course to update.");
            return;
        }
        Course c = mainFrame.courseRepo.getList().get(row);
        c.setCourseName(nameField.getText().trim());
        c.setCreditHours(creditHoursField.getText().trim());
        c.setDay(dayField.getText().trim());
        c.setTime(timeField.getText().trim());
        c.setClassroom(classroomField.getText().trim());
        refreshTable();
        clearFields();
        JOptionPane.showMessageDialog(this, "Course updated successfully.");
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        creditHoursField.setText("");
        dayField.setText("");
        timeField.setText("");
        classroomField.setText("");
    }
}