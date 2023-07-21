package Ecom.Exception;

public class OrdersException extends RuntimeException{
	

	public OrdersException(String msg) {
		super(msg);
		System.out.println("inside the orderexception");
	}
}
