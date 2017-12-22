package ChristmasTree;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_MITER;


public class DrawPanel extends JPanel{

    List<XmasShape> shapes = new ArrayList<>();

    DrawPanel() {
        setBackground(new Color(160,160,210));
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Bubble b1 = new Bubble();
        Graphics2D g2 = (Graphics2D)g;
        this.shapes.add(b1);
        Bubble b2 = new Bubble();

        b2.x = 100;
        b2.y = 100;
        b2.scale = 2;


        double s = 1;
        int i = 100;
        Branch branch = new Branch((int) (300 + s*-242),i, s,1);
        shapes.add(branch);
        Branch branch2 = new Branch((int) (300 + s*332),i, -1*s,1);
        shapes.add(branch2);

        this.shapes.add(b1);
        this.shapes.add(b2);
        for(XmasShape shape : shapes){
            shape.draw(g2);
        }
    }
}
