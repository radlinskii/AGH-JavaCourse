package ChristmasTree;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Christmas Tree");
        frame.setContentPane(new DrawPanel());
        frame.setSize((int)(2*(285*0.8 + 20)), 800);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }
}
