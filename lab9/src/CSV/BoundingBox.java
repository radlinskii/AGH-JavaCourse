package CSV;

public class BoundingBox {
    Double xmin;
    Double ymin;
    Double xmax;
    Double ymax;


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


    boolean contains(double x, double y){
        return ((x<=xmax) && (x >= xmin) && (ymax >= y) && (ymin <= y));
    }

    boolean contains(BoundingBox bb) {
        return ((bb.xmax <= xmax) && (bb.xmin >= xmin) && (ymax >= bb.ymax) && (ymin <= bb.ymin));
    }

    boolean intersects(BoundingBox bb){
        return (contains(bb.xmax, bb.ymax) || contains(bb.xmin, bb.ymin)
                || contains(bb.xmax, bb.ymin) || contains(bb.xmin, bb.ymax))
                && (!contains(bb.xmax, bb.ymax) || !contains(bb.xmin, bb.ymin)
                || !contains(bb.xmax, bb.ymin) || !contains(bb.xmin, bb.ymax));
    }

    BoundingBox add(BoundingBox bb){
        if(!bb.isEmpty()) {
            this.addPoint(bb.xmax, bb.ymax);
            this.addPoint(bb.xmax, bb.ymin);
            this.addPoint(bb.xmin, bb.ymax);
            this.addPoint(bb.xmin, bb.ymin);
        }
        return this;
    }

    boolean isEmpty(){
        return xmax == null || xmin == null || ymax == null || ymin == null;
    }

    double getCenterX() throws CantCenterEmptyBoundingBox{
        if(!isEmpty()){
            return xmax-((xmax - xmin)/2);
        } else
            throw new CantCenterEmptyBoundingBox("Empty on Xcenter");
    }

    double getCenterY() throws CantCenterEmptyBoundingBox {
        if(!isEmpty()){
            return ymax-((ymax - ymin)/2);
        } else
            throw new CantCenterEmptyBoundingBox("Empty on Ycenter");
    }

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
