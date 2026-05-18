import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CampusMapPanel extends JPanel {

    private MainFrame mainFrame;

    // Labels that act as "buildings" on the map
    private JLabel libraryLabel;
    private JLabel cafeteriaLabel;
    private JLabel hostelLabel;
    private JLabel adminLabel;
    private JLabel deptLabel;
    private JLabel securityLabel;

    public CampusMapPanel(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(new BorderLayout());
        setBackground(new Color(50, 50, 50));

        // ── TOP: TITLE ────────────────────────────────────
        JLabel titleLabel = new JLabel("Campus Map - Building Status", JLabel.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBackground(new Color(50, 50, 50));
        titleLabel.setOpaque(true);
        add(titleLabel, BorderLayout.NORTH);

        // ── CENTER: BUILDINGS GRID ───────────────────────
        JPanel mapPanel = new JPanel();
        mapPanel.setLayout(new GridLayout(2, 3, 10, 10));
        mapPanel.setBackground(new Color(50, 50, 50));

        libraryLabel   = makeBuilding("Library",    Color.GREEN);
        cafeteriaLabel = makeBuilding("Cafeteria",  Color.ORANGE);
        hostelLabel    = makeBuilding("Hostel",     Color.GREEN);
        adminLabel     = makeBuilding("Admin Block", Color.GREEN);
        deptLabel      = makeBuilding("Dept Block", Color.GREEN);
        securityLabel  = makeBuilding("Security",   Color.RED);

        mapPanel.add(libraryLabel);
        mapPanel.add(cafeteriaLabel);
        mapPanel.add(hostelLabel);
        mapPanel.add(adminLabel);
        mapPanel.add(deptLabel);
        mapPanel.add(securityLabel);

        add(mapPanel, BorderLayout.CENTER);

        // ── SOUTH: LEGEND + REFRESH BUTTON ───────────────
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.setBackground(new Color(50, 50, 50));

        JLabel greenBox = new JLabel("  Active  ");
        greenBox.setOpaque(true);
        greenBox.setBackground(Color.GREEN);
        greenBox.setForeground(Color.BLACK);
        bottomPanel.add(greenBox);

        JLabel orangeBox = new JLabel("  Busy  ");
        orangeBox.setOpaque(true);
        orangeBox.setBackground(Color.ORANGE);
        orangeBox.setForeground(Color.BLACK);
        bottomPanel.add(orangeBox);

        JLabel redBox = new JLabel("  Closed  ");
        redBox.setOpaque(true);
        redBox.setBackground(Color.RED);
        redBox.setForeground(Color.WHITE);
        bottomPanel.add(redBox);

        JButton refreshButton = new JButton("Refresh Map");
        refreshButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                refreshMap();
            }
        });
        bottomPanel.add(refreshButton);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    // Helper to create a building label
    private JLabel makeBuilding(String name, Color color) {
        JLabel label = new JLabel(name, JLabel.CENTER);
        label.setOpaque(true);
        label.setBackground(color);
        label.setForeground(Color.BLACK);
        label.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        return label;
    }

    // Update colors based on facility repo data
    private void refreshMap() {
        // Reset to defaults first
        libraryLabel.setBackground(Color.GREEN);
        cafeteriaLabel.setBackground(Color.ORANGE);
        hostelLabel.setBackground(Color.GREEN);

        // Update from actual facility data if any exist
        for (int i = 0; i < mainFrame.facilityRepo.getList().size(); i++) {
            Facility f = mainFrame.facilityRepo.getList().get(i);
            int hours = f.getOperatingHours();

            Color status;
            if (hours == 0) {
                status = Color.RED;       // Closed
            } else if (hours > 8) {
                status = Color.ORANGE;    // Busy
            } else {
                status = Color.GREEN;     // Active
            }

            // Match by type
            if (f instanceof Library) {
                libraryLabel.setText(f.getName());
                libraryLabel.setBackground(status);
            } else if (f instanceof Cafeteria) {
                cafeteriaLabel.setText(f.getName());
                cafeteriaLabel.setBackground(status);
            } else if (f instanceof Hostel) {
                hostelLabel.setText(f.getName());
                hostelLabel.setBackground(status);
            }
        }

        JOptionPane.showMessageDialog(this, "Map refreshed.");
    }
}