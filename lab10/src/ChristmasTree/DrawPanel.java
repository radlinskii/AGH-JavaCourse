package ChristmasTree;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.awt.BasicStroke.CAP_ROUND;
import static java.awt.BasicStroke.JOIN_MITER;


public class DrawPanel extends JPanel{

    List<XmasShape> shapes = new ArrayList<>();

    DrawPanel() {
        setBackground(new Color(160,160,210));
    }

    void addBranches(){
        double s = 0.8;
        int i = 360;
        for(;i > 61; i -= 60, s /= 1.1) {
            Branch branch = new Branch((int)(285*0.8 + 20 - s*285), i, s, 3*s);
            shapes.add(branch);
            Branch branch2 = new Branch((int)(285*0.8  + 20 + s*286), i, -1 * s, 3*s);
            shapes.add(branch2);
        }
        Branch branch = new Branch((int)(285*0.8 + 20 - s*0.8*285), i-10, 0.8*s, 3.2*s);
        shapes.add(branch);
        Branch branch2 = new Branch((int)(285*0.8  + 20 + s*0.8*286), i-10, -1*0.8*s, 3.2*s);
        shapes.add(branch2);
    }

    void addBubbles(){
        for(int row = 0; row < 6; row ++){
            Bubble bubble = new Bubble();
            Random random = new Random();
            bubble.x = (int)((285*0.8 - 10)+ random.nextInt()%(80 + 20*row));
            bubble.y = (int)(100 + row *131*(0.7));
            bubble.scale = 0.5;
            shapes.add(bubble);
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        addBranches();
        addBubbles();
        for(XmasShape shape : shapes){
            shape.draw(g2);
        }
    }
}
