package ChristmasTree;


import java.awt.*;


public class Branch implements XmasShape{

    int x;
    int y;
    double sx = 1;
    double sy = 1;
    Color lineColor = Color.GREEN;
    Color fillColor = Color.GREEN;

    public Branch () {}

    public Branch (int x, int y, double sx, double sy) {
        this.x = x;
        this.y = y;
        this.sx = sx;
        this.sy = sy;
    }

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x,y);
        g2d.scale(sx,sy);
    }

    @Override
    public void render(Graphics2D g2d) {
        GradientPaint grad = new GradientPaint(0,0,new Color(0,230,0),0,100, new Color(5,60,5));
        g2d.setPaint(grad);
        int x[]={286,286,223,200,148,119,69,45,0};
        int y[]={0,131,89,108,79,95,66,80,56};
        g2d.fillPolygon(x,y,x.length);
    }
}
