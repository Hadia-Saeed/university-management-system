import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class FacilityPanel extends JPanel {

    private MainFrame mainFrame;

    // Input fields
    private JTextField idField, nameField, locationField, costField, hoursField, capacityField;

    // ComboBox for facility type
    private JComboBox<String> typeCombo;

    // Table to display facilities
    private JTable facilityTable;
    private String[] columns = {"ID", "Name", "Location", "Type", "Maint. Cost", "Hours", "Capacity"};

    // Buttons
    private JButton addButton, deleteButton, updateButton;

    public FacilityPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());

        // ── TOP: FORM PANEL ──────────────────────────────
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(8, 2, 5, 5));
        formPanel.setBackground(new Color(50, 50, 50));

        JLabel idLabel = new JLabel("Facility ID:");
        idLabel.setForeground(Color.WHITE);
        idField = new JTextField();
        formPanel.add(idLabel);
        formPanel.add(idField);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        nameField = new JTextField();
        formPanel.add(nameLabel);
        formPanel.add(nameField);

        JLabel locationLabel = new JLabel("Location:");
        locationLabel.setForeground(Color.WHITE);
        locationField = new JTextField();
        formPanel.add(locationLabel);
        formPanel.add(locationField);

        JLabel typeLabel = new JLabel("Type:");
        typeLabel.setForeground(Color.WHITE);
        typeCombo = new JComboBox<>(new String[]{"Library", "Cafeteria", "Hostel"});
        formPanel.add(typeLabel);
        formPanel.add(typeCombo);

        JLabel costLabel = new JLabel("Maintenance Cost:");
        costLabel.setForeground(Color.WHITE);
        costField = new JTextField();
        formPanel.add(costLabel);
        formPanel.add(costField);

        JLabel hoursLabel = new JLabel("Operating Hours:");
        hoursLabel.setForeground(Color.WHITE);
        hoursField = new JTextField();
        formPanel.add(hoursLabel);
        formPanel.add(hoursField);

        JLabel capacityLabel = new JLabel("Capacity:");
        capacityLabel.setForeground(Color.WHITE);
        capacityField = new JTextField();
        formPanel.add(capacityLabel);
        formPanel.add(capacityField);

        // spacing
        formPanel.add(new JLabel(""));
        formPanel.add(new JLabel(""));

        add(formPanel, BorderLayout.NORTH);

        // ── CENTER: TABLE ────────────────────────────────
        facilityTable = new JTable(getData(), columns);
        JScrollPane scrollPane = new JScrollPane(facilityTable);
        add(scrollPane, BorderLayout.CENTER);

        // ── SOUTH: BUTTONS ───────────────────────────────
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setBackground(new Color(50, 50, 50));

        addButton = new JButton("Add Facility");
        deleteButton = new JButton("Delete Facility");
        updateButton = new JButton("Update Facility");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(updateButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // ── BUTTON ACTIONS ───────────────────────────────

        // ADD
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                addFacility();
            }
        });

        // DELETE
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteFacility();
            }
        });

        // UPDATE
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateFacility();
            }
        });

        // CLICK ON TABLE ROW → fill fields
        facilityTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                int row = facilityTable.getSelectedRow();
                idField.setText((String) facilityTable.getValueAt(row, 0));
                nameField.setText((String) facilityTable.getValueAt(row, 1));
                locationField.setText((String) facilityTable.getValueAt(row, 2));
                typeCombo.setSelectedItem((String) facilityTable.getValueAt(row, 3));
                costField.setText((String) facilityTable.getValueAt(row, 4));
                hoursField.setText((String) facilityTable.getValueAt(row, 5));
                capacityField.setText((String) facilityTable.getValueAt(row, 6));
            }
        });
    }

    // convert repo to 2D array for JTable
    private String[][] getData() {
        int size = mainFrame.facilityRepo.getList().size();
        String[][] data = new String[size][7];
        for (int i = 0; i < size; i++) {
            Facility f = mainFrame.facilityRepo.getList().get(i);
            data[i][0] = f.getEntityID();
            data[i][1] = f.getName();
            data[i][2] = f.getLocation();
            // determine type
            if (f instanceof Library) {
                data[i][3] = "Library";
            } else if (f instanceof Cafeteria) {
                data[i][3] = "Cafeteria";
            } else if (f instanceof Hostel) {
                data[i][3] = "Hostel";
            } else {
                data[i][3] = "Facility";
            }
            data[i][4] = String.valueOf(f.getMaintenanceCost());
            data[i][5] = String.valueOf(f.getOperatingHours());
            data[i][6] = String.valueOf(f.getCapacity());
        }
        return data;
    }

    // refresh table after any change
    private void refreshTable() {
        facilityTable.setModel(new javax.swing.table.DefaultTableModel(getData(), columns));
    }

    private void addFacility() {
        String id = idField.getText().trim();
        String name = nameField.getText().trim();
        String location = locationField.getText().trim();
        String type = (String) typeCombo.getSelectedItem();
        String costStr = costField.getText().trim();
        String hoursStr = hoursField.getText().trim();
        String capStr = capacityField.getText().trim();

        if (id.isEmpty() || name.isEmpty() || location.isEmpty() || costStr.isEmpty() || hoursStr.isEmpty() || capStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
            return;
        }

        try {
            double cost = Double.parseDouble(costStr);
            int hours = Integer.parseInt(hoursStr);
            int capacity = Integer.parseInt(capStr);

            Facility f;
            if (type.equals("Library")) {
                f = new Library(id, location, name, cost, hours, capacity);
            } else if (type.equals("Cafeteria")) {
                f = new Cafeteria(id, location, name, cost, hours, capacity, 0, 0);
            } else {
                f = new Hostel(id, location, name, cost, hours, capacity, "N/A", 0, 0);
            }

            mainFrame.facilityRepo.additem(f);
            refreshTable();
            clearFields();
            JOptionPane.showMessageDialog(this, "Facility added successfully.");

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Cost must be a decimal. Hours and Capacity must be whole numbers.");
        }
    }

    private void deleteFacility() {
        int row = facilityTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a facility to delete.");
            return;
        }
        mainFrame.facilityRepo.removeitem(row);
        refreshTable();
        clearFields();
        JOptionPane.showMessageDialog(this, "Facility deleted successfully.");
    }

    private void updateFacility() {
        int row = facilityTable.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a facility to update.");
            return;
        }
        try {
            Facility f = mainFrame.facilityRepo.getList().get(row);
            f.setName(nameField.getText().trim());
            f.setLocation(locationField.getText().trim());
            f.setMaintenanceCost(Double.parseDouble(costField.getText().trim()));
            f.setOperatingHours(Integer.parseInt(hoursField.getText().trim()));
            f.setCapacity(Integer.parseInt(capacityField.getText().trim()));
            refreshTable();
            clearFields();
            JOptionPane.showMessageDialog(this, "Facility updated successfully.");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Cost must be a decimal. Hours and Capacity must be whole numbers.");
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        locationField.setText("");
        costField.setText("");
        hoursField.setText("");
        capacityField.setText("");
        typeCombo.setSelectedIndex(0);
    }
}