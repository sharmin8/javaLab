import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Lab_4_SimpleCalculator extends JFrame implements ActionListener {
    JTextField display;
    JLabel historyLabel;
    JButton[] numberButtons = new JButton[10];
    JButton[] functionButtons = new JButton[6];
    JButton addBtn, subBtn, mulBtn, divBtn, eqBtn, clrBtn;

    double num1 = 0, num2 = 0, result = 0;
    char operator;
    boolean startNewNumber = true;

    Lab_4_SimpleCalculator() {
        setTitle("GUI Calculator");
        setSize(360, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Display Panel
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBorder(new EmptyBorder(15, 15, 5, 15));
        displayPanel.setBackground(new Color(248, 249, 250));

        historyLabel = new JLabel(" ", SwingConstants.RIGHT);
        historyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        historyLabel.setForeground(Color.GRAY);
        historyLabel.setPreferredSize(new Dimension(300, 20));

        display = new JTextField();
        display.setFont(new Font("Segoe UI", Font.BOLD, 24));
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBackground(Color.WHITE);
        display.setPreferredSize(new Dimension(300, 45));
        display.setBorder(new CompoundBorder(
            new LineBorder(new Color(200, 200, 200), 1),
            new EmptyBorder(5, 10, 5, 10)
        ));

        displayPanel.add(historyLabel, BorderLayout.NORTH);
        displayPanel.add(display, BorderLayout.CENTER);

        // Buttons Grid
        JPanel gridPanel = new JPanel(new GridLayout(4, 4, 10, 10));
        gridPanel.setBorder(new EmptyBorder(10, 15, 15, 15));
        gridPanel.setBackground(new Color(248, 249, 250));

        addBtn = new JButton("+");
        subBtn = new JButton("-");
        mulBtn = new JButton("*");
        divBtn = new JButton("/");
        eqBtn = new JButton("=");
        clrBtn = new JButton("C");

        functionButtons[0] = addBtn;
        functionButtons[1] = subBtn;
        functionButtons[2] = mulBtn;
        functionButtons[3] = divBtn;
        functionButtons[4] = eqBtn;
        functionButtons[5] = clrBtn;

        Font btnFont = new Font("Segoe UI", Font.BOLD, 16);

        for (JButton btn : functionButtons) {
            btn.setFont(btnFont);
            btn.setForeground(Color.BLACK);
            btn.setFocusPainted(false);
            btn.addActionListener(this);
        }

        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].setFont(btnFont);
            numberButtons[i].setForeground(Color.BLACK);
            numberButtons[i].setFocusPainted(false);
            numberButtons[i].addActionListener(this);
        }

        // Button Layout
        gridPanel.add(numberButtons[7]);
        gridPanel.add(numberButtons[8]);
        gridPanel.add(numberButtons[9]);
        gridPanel.add(divBtn);

        gridPanel.add(numberButtons[4]);
        gridPanel.add(numberButtons[5]);
        gridPanel.add(numberButtons[6]);
        gridPanel.add(mulBtn);

        gridPanel.add(numberButtons[1]);
        gridPanel.add(numberButtons[2]);
        gridPanel.add(numberButtons[3]);
        gridPanel.add(subBtn);

        gridPanel.add(clrBtn);
        gridPanel.add(numberButtons[0]);
        gridPanel.add(eqBtn);
        gridPanel.add(addBtn);

        add(displayPanel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Number Input
        for (int i = 0; i < 10; i++) {
            if (e.getSource() == numberButtons[i]) {
                if (startNewNumber || display.getText().equals("Error")) {
                    display.setText(String.valueOf(i));
                    startNewNumber = false;
                } else {
                    display.setText(display.getText() + i);
                }
                return;
            }
        }

        // Clear All
        if (e.getSource() == clrBtn) {
            display.setText("");
            historyLabel.setText(" ");
            num1 = 0;
            num2 = 0;
            result = 0;
            operator = '\0';
            startNewNumber = true;
            return;
        }

        // Operators
        if (e.getSource() == addBtn || e.getSource() == subBtn ||
            e.getSource() == mulBtn || e.getSource() == divBtn) {

            if (!display.getText().isEmpty() &&
                !display.getText().equals("Error")) {
                num1 = Double.parseDouble(display.getText());

                if (e.getSource() == addBtn) operator = '+';
                else if (e.getSource() == subBtn) operator = '-';
                else if (e.getSource() == mulBtn) operator = '*';
                else operator = '/';

                historyLabel.setText(formatNumber(num1) + " " + operator);
                display.setText("");
                startNewNumber = true;
            }
            return;
        }

        // Equals
        if (e.getSource() == eqBtn) {
            if (display.getText().isEmpty() ||
                display.getText().equals("Error") || operator == '\0') {
                return;
            }

            num2 = Double.parseDouble(display.getText());

            switch (operator) {
                case '+': result = num1 + num2; break;
                case '-': result = num1 - num2; break;
                case '*': result = num1 * num2; break;
                case '/':
                    if (num2 == 0) {
                        historyLabel.setText(formatNumber(num1) + " / 0");
                        display.setText("Error");
                        startNewNumber = true;
                        operator = '\0';
                        return;
                    }
                    result = num1 / num2;
                    break;
                default:
                    return;
            }

            historyLabel.setText(
                formatNumber(num1) + " " + operator + " " +
                formatNumber(num2) + " ="
            );
            display.setText(formatNumber(result));
            operator = '\0';
            startNewNumber = true;
        }
    }

    private String formatNumber(double number) {
        if (number == (long) number) {
            return String.format("%d", (long) number);
        }
        return String.valueOf(number);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(
                UIManager.getSystemLookAndFeelClassName()
            );
        } catch (Exception ignored) {}

        new Lab_4_SimpleCalculator();
    }
}