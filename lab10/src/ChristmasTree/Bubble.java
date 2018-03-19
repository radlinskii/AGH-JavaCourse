package ChristmasTree;

import java.awt.*;

public class Bubble implements XmasShape {
    public void setScale(double scale) {
        this.scale = scale;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLineColor(Color lineColor) {
        this.lineColor = lineColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    int x;
    int y;
    double scale;
    Color lineColor;
    Color fillColor;

    Bubble(){
        x = 0;
        y = 0;
        scale = 1;
        lineColor = Color.MAGENTA;
        fillColor = Color.CYAN;
    }


    @Override
    public void render(Graphics2D g2d) {
        g2d.setColor(fillColor);
        g2d.fillOval(0,0,100,100);
        g2d.setColor(lineColor);
        g2d.drawOval(0,0,100,100);
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(scale,scale);
    }
}