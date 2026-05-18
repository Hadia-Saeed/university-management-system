import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class StudentPanel extends JPanel {

    private MainFrame mainFrame;

    // Input fields
    private JTextField idField, nameField, ageField, cgpaField, passwordField;

    // Table to display students
    private JTable studentTable;
    private String[] columns = {"Student ID", "Name", "Age", "CGPA"};

    // Buttons
    private JButton addButton, deleteButton, updateButton, selectButton;

    public StudentPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        // ── TOP: FORM PANEL ──────────────────────────────
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 5, 5));
        formPanel.setBackground(new Color(50, 50, 50));

        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setForeground(Color.WHITE);
        idField = new JTextField();
        formPanel.add(idLabel);
        formPanel.add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        nameField = new JTextField();
        formPanel.add(nameLabel);
        formPanel.add(nameField);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordField = new JTextField();
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);

        JLabel ageLabel = new JLabel("Age:");
        ageLabel.setForeground(Color.WHITE);
        ageField = new JTextField();
        formPanel.add(ageLabel);
        formPanel.add(ageField);

        JLabel cgpaLabel = new JLabel("CGPA:");
        cgpaLabel.setForeground(Color.WHITE);
        cgpaField = new JTextField();
        formPanel.add(cgpaLabel);
        formPanel.add(cgpaField);

        // empty cells for spacing
        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel(""));

        add(formPanel, BorderLayout.NORTH);

        // ── CENTER: TABLE ────────────────────────────────
        studentTable = new JTable(getData(), columns);
        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER);

        // ── SOUTH: BUTTONS ───────────────────────────────
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(50, 50, 50));

        addButton = new JButton("Add Student");
        deleteButton = new JButton("Delete Student");
        updateButton = new JButton("Update Student");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // ── BUTTON ACTIONS ───────────────────────────────

        // ADD
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addStudent();
            }
        });

        // DELETE
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });

        // UPDATE
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });

        // fill fields using select button
        selectButton = new JButton("Select Student");
        buttonPanel.add(selectButton);

        selectButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
        int row = studentTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(null, "Please select a row first.");
            return;
        }
        idField.setText((String) studentTable.getValueAt(row, 0));
        nameField.setText((String) studentTable.getValueAt(row, 1));
        ageField.setText((String) studentTable.getValueAt(row, 2));
        cgpaField.setText((String) studentTable.getValueAt(row, 3));
        }
    });
    }

    // convert repo to 2D array for JTable
    private String[][] getData() {
        int size = mainFrame.studentRepo.getList().size();
        String[][] data = new String[size][4];
        for (int i = 0; i < size; i++) {
            Student s = mainFrame.studentRepo.getList().get(i);
            data[i][0] = s.getUserID();
            data[i][1] = s.getUserName();
            data[i][2] = String.valueOf(s.getAge());
            data[i][3] = String.valueOf(s.getCgpa());
        }
        return data;
    }

    // refresh table after any change
    private void refreshTable() {
        studentTable.setModel(new javax.swing.table.DefaultTableModel(getData(), columns));
    }

    private void addStudent() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String password = passwordField.getText().trim();
        String ageStr = ageField.getText().trim();
        String cgpaStr = cgpaField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || password.isEmpty() || ageStr.isEmpty() || cgpaStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        try {
            int age = Integer.parseInt(ageStr);
            double cgpa = Double.parseDouble(cgpaStr);
            Student s = new Student(age, cgpa, id, name, password);
            mainFrame.studentRepo.additem(s);
            refreshTable();
            clearFields();
            JOptionPane.showMessageDialog(this, "Student added successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Age must be a number and CGPA must be a decimal.");
        }
    }

    private void deleteStudent() {
        int row = studentTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to delete.");
            return;
        }
        mainFrame.studentRepo.removeitem(row);
        refreshTable();
        clearFields();
        JOptionPane.showMessageDialog(this, "Student deleted successfully.");
    }

    private void updateStudent() {
        int row = studentTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student to update.");
            return;
        }

        try {
            Student s = mainFrame.studentRepo.getList().get(row);
            s.setUserName(nameField.getText().trim());
            s.setAge(Integer.parseInt(ageField.getText().trim()));
            s.setCgpa(Double.parseDouble(cgpaField.getText().trim()));
            refreshTable();
            clearFields();
            JOptionPane.showMessageDialog(this, "Student updated successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Age must be a number and CGPA must be a decimal.");
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        passwordField.setText("");
        ageField.setText("");
        cgpaField.setText("");
    }
}