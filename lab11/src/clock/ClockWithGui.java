package clock;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.time.LocalTime;

public class ClockWithGui extends JPanel {

    LocalTime time = LocalTime.now();

    public ClockWithGui(){
        new ClockThread().start();
        setOpaque(false);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clock");
        frame.setContentPane(new ClockWithGui());
        frame.setSize(700, 700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d=(Graphics2D)g;
        g2d.translate(getWidth()/2,getHeight()/2);

        for(int i=1;i<13;i++){
            AffineTransform at = new AffineTransform();
            at.rotate(2*Math.PI/12*i);
            Point2D src = new Point2D.Float(0,-120);
            Point2D trg = new Point2D.Float();
            at.transform(src,trg);
            g2d.drawString(Integer.toString(i),(int)trg.getX()-6,(int)trg.getY()+3);
        }

        for(int i=1;i<61;i++){
            AffineTransform at = new AffineTransform();
            at.rotate(2*Math.PI/60*i);
            Point2D src = new Point2D.Float(0,-120);
            Point2D trg = new Point2D.Float();
            at.transform(src,trg);
            if(i%5==0){
                g2d.setStroke(new BasicStroke(4));
            } else {
                g2d.setStroke(new BasicStroke(2));
            }

            g2d.drawLine(((int)((trg.getX())*0.9)),(int)((trg.getY())*0.9 ),((int)((trg.getX())*0.8)),((int)((trg.getY())*0.8)));
            //g2d.drawString(Integer.toString(i),(int)trg.getX(),(int)trg.getY());
        }

        AffineTransform saveAT = g2d.getTransform();
        g2d.rotate(time.getSecond()%60*2*Math.PI/60);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(0,0,0,-85);
        g2d.setTransform(saveAT);


        g2d.rotate(time.getMinute()%60*2*Math.PI/60);
        g2d.setStroke(new BasicStroke(3));
        g2d.drawLine(0,0,0,-75);
        g2d.setTransform(saveAT);

        g2d.rotate(time.getHour()%12*2*Math.PI/12);
        g2d.setStroke(new BasicStroke(4));
        g2d.drawLine(0,0,0,-65);
        g2d.setTransform(saveAT);



    }


    class ClockThread extends Thread{
        @Override
        public void run() {
            for(;;){
                time = LocalTime.now();

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }
    }
}