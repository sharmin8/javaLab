import javax.swing.*;

public class Lab_1_DisplayImage {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Display Image");
        frame.setSize(500, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("image.jpg"); // Replace with actual image path
        JLabel label = new JLabel(icon);

        frame.add(label);
        frame.setVisible(true);
    }
}