package VeggiApp.Exception;

public class CustomerException  extends  RuntimeException{
    public CustomerException(){}
    public CustomerException(String mesg) {
        super(mesg);
    }
}
