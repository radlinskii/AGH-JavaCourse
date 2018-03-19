package elevator;

public class Elevator {
    static ElevatorCar car = new ElevatorCar();
    static ExternalPanelsAgent externalPanelAgent = new ExternalPanelsAgent(car);
    static InternalPanelAgent internalPanelAgent = new InternalPanelAgent(car);

    static void makeExternalCall(int atFloor,boolean directionUp){
        try {
            externalPanelAgent.input.put(new ExternalPanelsAgent.ExternalCall(atFloor,directionUp));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void makeInternalCall(int toFloor){
        try {
            internalPanelAgent.input.put(new InternalPanelAgent.InternalCall(toFloor));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void init(){
        car.start();
        externalPanelAgent.start();
        internalPanelAgent.start();
    }

    public static void main(String[] args) throws InterruptedException {
        init();

        makeInternalCall(2);
        makeExternalCall(6,false);
        Thread.currentThread().sleep(2000);
        makeInternalCall(1);

    }
}
