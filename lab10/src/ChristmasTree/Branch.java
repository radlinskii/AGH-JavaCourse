package ChristmasTree;

import com.sun.prism.paint.Gradient;

import java.awt.*;


public class Branch implements XmasShape{

    //int x []={286,286,223,200,148,119,69,45,0};
    //int y []={0,131,89,108,79,95,66,80,56};
    int x0 = 300;
    int y0 = 300;
    double scale = 1;
    //GradientPaint grad = new GradientPaint(0,0,new Color(0,255,0),0,100, new Color(0,10,0));

    @Override
    public void transform(Graphics2D g2d) {
        g2d.translate(x0,y0);
        g2d.scale(scale,scale);
    }

    @Override
    public void render(Graphics2D g2d) {
        GradientPaint grad = new GradientPaint(0,0,new Color(0,255,0),0,100, new Color(0,10,0));
        g2d.setPaint(grad);
        g2d.translate(0,50);
        g2d.scale(0.7,0.5);
        int x[]={286,286,223,200,148,119,69,45,0};
        int y[]={0,131,89,108,79,95,66,80,56};
        g2d.fillPolygon(x,y,x.length);
        g2d.translate(670,0);
        g2d.scale(-1,1);
        g2d.fillPolygon(x,y,x.length);

    }

    @Override
    public void draw(Graphics2D g2d) {

    }
}
