package ChristmasTree;

import java.awt.*;
import java.awt.geom.AffineTransform;

public interface XmasShape {

    void transform(Graphics2D g2d);


    void render(Graphics2D g2d);


    default void draw(Graphics2D g2d){
        // Get the current transform
        AffineTransform saveAT = g2d.getTransform();
        // Perform transformation
        transform(g2d);
        // Render
        render(g2d);
        // Restore original transform
        g2d.setTransform(saveAT);
    }
}
