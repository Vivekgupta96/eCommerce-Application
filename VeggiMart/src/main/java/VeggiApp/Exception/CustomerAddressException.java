package VeggiApp.Exception;

public class CustomerAddressException extends RuntimeException{
    public CustomerAddressException(){

    }
    public CustomerAddressException(String msg){
         super(msg);
    }
}
