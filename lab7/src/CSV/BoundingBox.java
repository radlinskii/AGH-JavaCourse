package CSV;

public class BoundingBox {
    double xmin;
    double ymin;
    double xmax;
    double ymax;


    void addPoint(double x, double y){

    }

    boolean contains(double x, double y){
        return false;
    }

    boolean contains(BoundingBox bb){
        return false;
    }

    boolean intersects(BoundingBox bb){
        return false;
    }

    BoundingBox add(BoundingBox bb){
        return this;
    }

    boolean isEmpty(){
        return true;
    }

    double getCenterX(){
        throw new RuntimeException("Not implemented");
    }

    double getCenterY(){
        throw new RuntimeException("Not implemented");
    }

    double distanceTo(BoundingBox bbx){
        throw new RuntimeException("Not implemented");
    }

}
