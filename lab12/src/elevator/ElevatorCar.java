package elevator;

public class ElevatorCar extends Thread{
    private int floor=0;

    public int getFloor() {
        return floor;
    }

    enum Tour {UP, DOWN};
    Tour tour = Tour.UP;
    enum Movement {STOP,MOVING};
    Movement movementState = Movement.STOP;

    public void run(){
        for(;;){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(movementState == Movement.STOP && tour == Tour.DOWN){
                if(!ElevatorStops.get().hasStopBelow(floor))tour = Tour.UP;
                else movementState = Movement.MOVING;
                continue;
            }
            if(movementState == Movement.STOP && tour == Tour.UP){
                if(!ElevatorStops.get().hasStopAbove(floor))tour = Tour.DOWN;
                else movementState = Movement.MOVING;
                continue;
            }
            if(movementState == Movement.MOVING && tour == Tour.DOWN){
                if(floor> ElevatorStops.get().getMinSetFloor()){
                    floor--;
                    System.out.println("Floor"+floor);
                }else{
                    movementState = Movement.STOP;
                    tour = Tour.UP;
                }
                if(ElevatorStops.get().whileMovingDownShouldStopAt(floor)|| floor==ElevatorStops.get().getMinSetFloor()){
                    movementState=Movement.STOP;
                    ElevatorStops.get().clearStopDown(floor);
                    ElevatorStops.get().clearStopUp(floor);
                    System.out.println("STOP");
                }
                continue;
            }
            if(movementState == Movement.MOVING && tour == Tour.UP){
                if(floor< ElevatorStops.get().getMaxSetFloor()){
                    floor++;
                    System.out.println("Floor"+floor);
                }else{
                    movementState = Movement.STOP;
                    tour = Tour.DOWN;
                }
                if(ElevatorStops.get().whileMovingUpShouldStopAt(floor)|| floor==ElevatorStops.get().getMaxSetFloor()){
                    movementState=Movement.STOP;
                    ElevatorStops.get().clearStopUp(floor);
                    ElevatorStops.get().clearStopDown(floor);
                    System.out.println("STOP");
                }
                continue;
            }

        }
    }
}
