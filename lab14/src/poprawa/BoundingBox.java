package poprawa;

public class BoundingBox {
    Double xmin;
    Double ymin;
    Double xmax;
    Double ymax;

    /**
     * Powiększa BB tak, aby zawierał punkt (x,y)
     * @param x - współrzędna x
     * @param y - współrzędna y
     */
    void addPoint(double x, double y){
        if(xmin != null){
            if(xmin > x)
                xmin = x;
        } else {
            xmin = x;
        }
        if(xmax != null){
            if(xmax < x)
                xmax = x;
        } else {
            xmax = x;
        }
        if(ymin != null){
            if(ymin > y)
                ymin = y;
        } else {
            ymin = y;
        }
        if(ymax != null){
            if(ymax < y)
                ymax = y;
        } else {
            ymax = y;
        }

    }

    /**
     * Sprawdza, czy BB zawiera punkt (x,y)
     * @param x
     * @param y
     * @return
     */
    boolean contains(double x, double y){
        return ((x<=xmax) && (x >= xmin) && (ymax >= y) && (ymin <= y));
    }

    /**
     * Sprawdza czy dany BB zawiera bb
     * @param bb
     * @return
     */
    boolean contains(BoundingBox bb) {
        return ((bb.xmax <= xmax) && (bb.xmin >= xmin) && (ymax >= bb.ymax) && (ymin <= bb.ymin));
    }

    /**
     * Sprawdza, czy dany BB przecina się z bb
     * @param bb
     * @return
     */
    boolean intersects(BoundingBox bb){
        return (contains(bb.xmax, bb.ymax) || contains(bb.xmin, bb.ymin)
                || contains(bb.xmax, bb.ymin) || contains(bb.xmin, bb.ymax))
                && (!contains(bb.xmax, bb.ymax) || !contains(bb.xmin, bb.ymin)
                || !contains(bb.xmax, bb.ymin) || !contains(bb.xmin, bb.ymax));
    }
    /**
     * Powiększa rozmiary tak, aby zawierał bb oraz poprzednią wersję this
     * @param bb
     * @return
     */
    BoundingBox add(BoundingBox bb){
        if(!bb.isEmpty()) {
            this.addPoint(bb.xmax, bb.ymax);
            this.addPoint(bb.xmax, bb.ymin);
            this.addPoint(bb.xmin, bb.ymax);
            this.addPoint(bb.xmin, bb.ymin);
        }
        return this;
    }
    /**
     * Sprawdza czy BB jest pusty
     * @return
     */
    boolean isEmpty(){
        return xmax == null || xmin == null || ymax == null || ymin == null;
    }

    /**
     * Oblicza i zwraca współrzędną x środka
     * @return if !isEmpty() współrzędna x środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterX() throws CantCenterEmptyBoundingBox{
        if(!isEmpty()){
            return xmax-((xmax - xmin)/2);
        } else
            throw new CantCenterEmptyBoundingBox("Empty on Xcenter");
    }
    /**
     * Oblicza i zwraca współrzędną y środka
     * @return if !isEmpty() współrzędna y środka else wyrzuca wyjątek
     * (sam dobierz typ)
     */
    double getCenterY() throws CantCenterEmptyBoundingBox {
        if(!isEmpty()){
            return ymax-((ymax - ymin)/2);
        } else
            throw new CantCenterEmptyBoundingBox("Empty on Ycenter");
    }

    /**
     * Oblicza odległość pomiędzy środkami this bounding box oraz bbx
     * @param bbx prostokąt, do którego liczona jest odległość
     * @return if !isEmpty odległość, else wyrzuca wyjątek lub zwraca maksymalną możliwą wartość double
     * Ze względu na to, że są to współrzędne geograficzne, zamiast odległosci euklidesowej możesz użyć wzoru haversine
     * (ang. haversine formula)
     */
    double distanceTo(BoundingBox bbx) throws CantCenterEmptyBoundingBox {
        return Math.sqrt(Math.pow((getCenterX()-bbx.getCenterX()),2)
                + Math.pow((getCenterY()-bbx.getCenterY()),2))*111;
    }

    @Override
    public String toString(){
        return "\n" + " xmax: " + xmax.toString() + " ymax: " + ymax.toString() + " xmin: " + xmin.toString() + " ymin: " + ymin.toString();
    }

    String toLINESTRING(){
        return "LINESTRING(" +
                xmax + " " + ymax + ", " +
                xmax + " " + ymin + ", " +
                xmin + " " + ymin + ", " +
                xmin + " " + ymax + ", " +
                xmax + " " + ymax + ")";

    }

}
