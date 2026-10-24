import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Lab_3_StudentForm extends JFrame implements ActionListener {
    JTextField tfName, tfRoll, tfEmail;
    JRadioButton rMale, rFemale;
    ButtonGroup bgGender;
    JComboBox<String> cbDept;
    JButton btnSubmit, btnReset;

    Lab_3_StudentForm() {
        setTitle("Student Registration Form");
        setSize(450, 520);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header Panel
        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setBackground(new Color(41, 128, 185));
        headerPanel.setPreferredSize(new Dimension(450, 65));

        JLabel title = new JLabel("STUDENT REGISTRATION", SwingConstants.CENTER);
        title.setFont(new Font("SansSerif", Font.BOLD, 20));
        title.setForeground(Color.WHITE);
        headerPanel.add(title);

        // Form Input Panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(new Color(248, 249, 250));
        formPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Font inputFont = new Font("Segoe UI", Font.PLAIN, 14);
        Color textColor = new Color(44, 62, 80);

        // Row 1: Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.3;
        JLabel lblName = new JLabel("Full Name:");
        lblName.setFont(labelFont);
        lblName.setForeground(textColor);
        formPanel.add(lblName, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        tfName = new JTextField();
        tfName.setFont(inputFont);
        tfName.setPreferredSize(new Dimension(200, 30));
        formPanel.add(tfName, gbc);

        // Row 2: Roll No
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.3;
        JLabel lblRoll = new JLabel("Roll No:");
        lblRoll.setFont(labelFont);
        lblRoll.setForeground(textColor);
        formPanel.add(lblRoll, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        tfRoll = new JTextField();
        tfRoll.setFont(inputFont);
        tfRoll.setPreferredSize(new Dimension(200, 30));
        formPanel.add(tfRoll, gbc);

        // Row 3: Gender
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.3;
        JLabel lblGender = new JLabel("Gender:");
        lblGender.setFont(labelFont);
        lblGender.setForeground(textColor);
        formPanel.add(lblGender, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        rMale = new JRadioButton("Male");
        rFemale = new JRadioButton("Female");
        rMale.setFont(inputFont);
        rFemale.setFont(inputFont);
        rMale.setOpaque(false);
        rFemale.setOpaque(false);

        bgGender = new ButtonGroup();
        bgGender.add(rMale);
        bgGender.add(rFemale);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        genderPanel.setOpaque(false);
        genderPanel.add(rMale);
        genderPanel.add(Box.createHorizontalStrut(15));
        genderPanel.add(rFemale);
        formPanel.add(genderPanel, gbc);

        // Row 4: Department
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0.3;
        JLabel lblDept = new JLabel("Department:");
        lblDept.setFont(labelFont);
        lblDept.setForeground(textColor);
        formPanel.add(lblDept, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        String[] departments = {"ICE", "CSE", "EEE", "ECE"};
        cbDept = new JComboBox<>(departments);
        cbDept.setFont(inputFont);
        cbDept.setPreferredSize(new Dimension(200, 30));
        formPanel.add(cbDept, gbc);

        // Row 5: Email
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0.3;
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(labelFont);
        lblEmail.setForeground(textColor);
        formPanel.add(lblEmail, gbc);

        gbc.gridx = 1;
        gbc.weightx = 0.7;
        tfEmail = new JTextField();
        tfEmail.setFont(inputFont);
        tfEmail.setPreferredSize(new Dimension(200, 30));
        formPanel.add(tfEmail, gbc);

        // Footer Buttons
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
        footerPanel.setBackground(Color.WHITE);
        footerPanel.setBorder(new MatteBorder(1, 0, 0, 0, new Color(220, 220, 220)));

        btnSubmit = new JButton("Submit");
        btnSubmit.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnSubmit.setForeground(Color.BLACK);
        btnSubmit.setFocusPainted(false);
        btnSubmit.setPreferredSize(new Dimension(120, 38));
        btnSubmit.addActionListener(this);

        btnReset = new JButton("Reset");
        btnReset.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnReset.setForeground(Color.BLACK);
        btnReset.setFocusPainted(false);
        btnReset.setPreferredSize(new Dimension(100, 38));
        btnReset.addActionListener(e -> resetForm());

        footerPanel.add(btnSubmit);
        footerPanel.add(btnReset);

        add(headerPanel, BorderLayout.NORTH);
        add(formPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
        setVisible(true);
    }

    private void resetForm() {
        tfName.setText("");
        tfRoll.setText("");
        tfEmail.setText("");
        bgGender.clearSelection();
        cbDept.setSelectedIndex(0);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String name = tfName.getText().trim();
        String roll = tfRoll.getText().trim();
        String email = tfEmail.getText().trim();
        String gender = rMale.isSelected() ? "Male" :
                        (rFemale.isSelected() ? "Female" : "");
        String dept = (String) cbDept.getSelectedItem();

        if (name.isEmpty() || roll.isEmpty() ||
            gender.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(
                this,
                "Please fill in all the fields!",
                "Incomplete Form",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String info = "--- Student Details Registered ---\n\n"
            + "- Name: " + name + "\n"
            + "- Roll No: " + roll + "\n"
            + "- Gender: " + gender + "\n"
            + "- Department: " + dept + "\n"
            + "- Email: " + email;

        JOptionPane.showMessageDialog(
            this,
            info,
            "Submission Successful",
            JOptionPane.INFORMATION_MESSAGE
        );
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception ignored) {}

        new Lab_3_StudentForm();
    }
}