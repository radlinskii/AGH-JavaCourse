package CSV;

public class CantCenterEmptyBoundingBox extends Exception{
    CantCenterEmptyBoundingBox(String errorMessage) {
        super(errorMessage);
    }
    CantCenterEmptyBoundingBox(){
        super();
    }
}
