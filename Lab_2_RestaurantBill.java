import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

public class Lab_2_RestaurantBill extends JFrame implements ActionListener {
JCheckBox cb1, cb2, cb3;
JSpinner sp1, sp2, sp3;
JButton btnOrder, btnReset;

Lab_2_RestaurantBill() {
setTitle("Restaurant Billing System");
setSize(450, 520);
setDefaultCloseOperation(EXIT_ON_CLOSE);
setLocationRelativeTo(null);
setLayout(new BorderLayout());

// Header Banner
JPanel headerPanel = new JPanel(new GridLayout(2, 1));
headerPanel.setBackground(new Color(231, 76, 60));
headerPanel.setPreferredSize(new Dimension(450, 70));

JLabel title = new JLabel("FOODIE'S HAVEN", SwingConstants.CENTER);
title.setFont(new Font("SansSerif", Font.BOLD, 22));
title.setForeground(Color.WHITE);

JLabel subtitle = new JLabel("Digital Billing System", SwingConstants.CENTER);
subtitle.setFont(new Font("SansSerif", Font.PLAIN, 12));
subtitle.setForeground(new Color(245, 230, 230));

headerPanel.add(title);
headerPanel.add(subtitle);

// Menu Panel
JPanel menuPanel = new JPanel(new GridLayout(3, 1, 10, 10));
menuPanel.setBackground(new Color(248, 249, 250));
menuPanel.setBorder(new EmptyBorder(20, 25, 20, 25));

Font itemFont = new Font("Segoe UI", Font.BOLD, 14);
Color textColor = new Color(44, 62, 80);

cb1 = new JCheckBox(" Classic Beef Burger - 150 BDT");
cb1.setFont(itemFont);
cb1.setForeground(textColor);
cb1.setOpaque(false);
sp1 = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
menuPanel.add(createItemRow(cb1, sp1));

cb2 = new JCheckBox(" Cheese Burst Pizza - 300 BDT");
cb2.setFont(itemFont);
cb2.setForeground(textColor);
cb2.setOpaque(false);
sp2 = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
menuPanel.add(createItemRow(cb2, sp2));

cb3 = new JCheckBox(" Cold Coffee / Espresso - 80 BDT");
cb3.setFont(itemFont);
cb3.setForeground(textColor);
cb3.setOpaque(false);
sp3 = new JSpinner(new SpinnerNumberModel(1, 1, 10, 1));
menuPanel.add(createItemRow(cb3, sp3));

// Footer Buttons
JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));
footerPanel.setBackground(Color.WHITE);
footerPanel.setBorder(new MatteBorder(1, 0, 0, 0, new Color(220, 220, 220)));

btnOrder = new JButton("Generate Bill");
btnOrder.setFont(new Font("Segoe UI", Font.BOLD, 14));
btnOrder.setForeground(Color.BLACK);
btnOrder.setFocusPainted(false);
btnOrder.setPreferredSize(new Dimension(160, 40));
btnOrder.addActionListener(this);

btnReset = new JButton("Reset");
btnReset.setFont(new Font("Segoe UI", Font.BOLD, 14));
btnReset.setForeground(Color.BLACK);
btnReset.setFocusPainted(false);
btnReset.setPreferredSize(new Dimension(100, 40));
btnReset.addActionListener(e -> resetForm());

footerPanel.add(btnOrder);
footerPanel.add(btnReset);

add(headerPanel, BorderLayout.NORTH);
add(menuPanel, BorderLayout.CENTER);
add(footerPanel, BorderLayout.SOUTH);
setVisible(true);
}

private JPanel createItemRow(JCheckBox cb, JSpinner sp) {
JPanel row = new JPanel(new BorderLayout());
row.setBackground(Color.WHITE);
row.setBorder(BorderFactory.createCompoundBorder(
new LineBorder(new Color(230, 230, 230), 1, true),
new EmptyBorder(10, 15, 10, 15)
));

JLabel qtyLabel = new JLabel("Qty: ");
qtyLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));

JPanel qtyPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 2, 0));
qtyPanel.setOpaque(false);
qtyPanel.add(qtyLabel);
qtyPanel.add(sp);

row.add(cb, BorderLayout.WEST);
row.add(qtyPanel, BorderLayout.EAST);
return row;
}

private void resetForm() {
cb1.setSelected(false);
cb2.setSelected(false);
cb3.setSelected(false);
sp1.setValue(1);
sp2.setValue(1);
sp3.setValue(1);
}

@Override
public void actionPerformed(ActionEvent e) {
double subtotal = 0;
StringBuilder receipt = new StringBuilder();

receipt.append(" RESTAURANT BILL\n");
receipt.append("---------------------------------------\n");
receipt.append(String.format("%-20s %-6s %-10s%n", "Item", "Qty", "Cost"));

if (cb1.isSelected()) {
int q = (Integer) sp1.getValue();
double cost = q * 150;
subtotal += cost;
receipt.append(String.format("%-20s %-6d %-8.2f BDT%n", "Beef Burger", q, cost));
}

if (cb2.isSelected()) {
int q = (Integer) sp2.getValue();
double cost = q * 300;
subtotal += cost;
receipt.append(String.format("%-20s %-6d %-8.2f BDT%n", "Cheese Pizza", q, cost));
}

if (cb3.isSelected()) {
int q = (Integer) sp3.getValue();
double cost = q * 80;
subtotal += cost;
receipt.append(String.format("%-20s %-6d %-8.2f BDT%n", "Cold Coffee", q, cost));
}

if (subtotal == 0) {
JOptionPane.showMessageDialog(
this, "Please select at least one item!",
"No Selection", JOptionPane.WARNING_MESSAGE
);
return;
}

double vat = subtotal * 0.05;
double grandTotal = subtotal + vat;

receipt.append("---------------------------------------\n");
receipt.append(String.format("%-27s %8.2f BDT%n", "Subtotal:", subtotal));
receipt.append(String.format("%-27s %8.2f BDT%n", "VAT (5%):", vat));
receipt.append("=======================================\n");
receipt.append(String.format("%-27s %8.2f BDT%n", "GRAND TOTAL:", grandTotal));
receipt.append("=======================================\n");
receipt.append("\nThank You For Dining With Us!\n");

JTextArea area = new JTextArea(receipt.toString(), 14, 38);
area.setFont(new Font("Monospaced", Font.PLAIN, 12));
area.setEditable(false);
area.setCaretPosition(0);

JScrollPane scrollPane = new JScrollPane(area);
scrollPane.setPreferredSize(new Dimension(350, 300));

JOptionPane.showMessageDialog(
this, scrollPane, "Customer Bill Receipt",
JOptionPane.PLAIN_MESSAGE
);
}

public static void main(String[] args) {
try {
UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
} catch (Exception ignored) {
}
new Lab_2_RestaurantBill();
}
}
