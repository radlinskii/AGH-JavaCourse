package elevator;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ExternalPanelsAgent extends Thread{
    private final ElevatorCar elevatorCar;

    static class ExternalCall{
        private final int atFloor;
        private final boolean directionUp;

        ExternalCall(int atFloor,boolean directionUp){
            this.atFloor = atFloor;
            this.directionUp = directionUp;
        }
    }
    BlockingQueue<ExternalCall> input = new ArrayBlockingQueue<ExternalCall>(129);
    ExternalPanelsAgent(ElevatorCar elevatorCar){
        this.elevatorCar = elevatorCar;
    }

    public void run(){
        for(;;){
            ExternalCall ec = null;
            try {
                ec = input.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(ec.atFloor==elevatorCar.getFloor())continue;
            if(ec.directionUp){
                ElevatorStops.get().setLiftStopUp(ec.atFloor);
            }else{
                ElevatorStops.get().setLiftStopDown(ec.atFloor);
            }
        }
    }
}
