package testy;

public class DivisionByZeroException extends Exception {
    public DivisionByZeroException(String reason, String statement){
        super(reason + ": " + statement);
    }

}
