package Ecom.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionhandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyErrorClass> getException(MethodArgumentNotValidException e, WebRequest req) {

		MyErrorClass e1 = new MyErrorClass();
		e1.setMessage(e.getBindingResult().getFieldError().getDefaultMessage());
		e1.setLocalDateTimes(LocalDateTime.now());
		e1.setDesc(req.getDescription(false));

		return new ResponseEntity<MyErrorClass>(e1, HttpStatus.BAD_GATEWAY);

	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyErrorClass> getException(NoHandlerFoundException e, WebRequest req) {

		MyErrorClass e1 = new MyErrorClass();
		e1.setMessage("handle not found Exception");
		e1.setLocalDateTimes(LocalDateTime.now());
		e1.setDesc(req.getDescription(false));

		return new ResponseEntity<MyErrorClass>(e1, HttpStatus.BAD_GATEWAY);

	}

	@ExceptionHandler(UserException.class)
	public ResponseEntity<MyErrorClass> getException(UserException e, WebRequest req) {

		MyErrorClass e1 = new MyErrorClass();
		e1.setMessage(e.getMessage());
		System.out.println("inside the global orderexception");
		e1.setLocalDateTimes(LocalDateTime.now());
		e1.setDesc(req.getDescription(false));

		return new ResponseEntity<MyErrorClass>(e1, HttpStatus.BAD_GATEWAY);

	}

	@ExceptionHandler(AddressException.class)
	public ResponseEntity<MyErrorClass> getException(AddressException e, WebRequest req) {

		MyErrorClass e1 = new MyErrorClass();
		e1.setMessage(e.getMessage());
		e1.setLocalDateTimes(LocalDateTime.now());
		e1.setDesc(req.getDescription(false));

		return new ResponseEntity<MyErrorClass>(e1, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(CartException.class)
	public ResponseEntity<MyErrorClass> getException(CartException e, WebRequest req) {

		MyErrorClass e1 = new MyErrorClass();
		e1.setMessage(e.getMessage());
		e1.setLocalDateTimes(LocalDateTime.now());
		e1.setDesc(req.getDescription(false));

		return new ResponseEntity<MyErrorClass>(e1, HttpStatus.BAD_GATEWAY);

	}

	@ExceptionHandler(OrdersException.class)
	public ResponseEntity<MyErrorClass> getException(OrdersException e, WebRequest req) {

		MyErrorClass e1 = new MyErrorClass();
		e1.setMessage(e.getMessage());
		e1.setLocalDateTimes(LocalDateTime.now());
		e1.setDesc(req.getDescription(false));

		return new ResponseEntity<MyErrorClass>(e1, HttpStatus.BAD_GATEWAY);

	}

	@ExceptionHandler(PaymentException.class)
	public ResponseEntity<MyErrorClass> getException(PaymentException e, WebRequest req) {

		MyErrorClass e1 = new MyErrorClass();
		e1.setMessage(e.getMessage());
		e1.setLocalDateTimes(LocalDateTime.now());
		e1.setDesc(req.getDescription(false));

		return new ResponseEntity<MyErrorClass>(e1, HttpStatus.BAD_GATEWAY);

	}

	@ExceptionHandler(ProductException.class)
	public ResponseEntity<MyErrorClass> getException(ProductException e, WebRequest req) {

		MyErrorClass e1 = new MyErrorClass();
		e1.setMessage(e.getMessage());
		e1.setLocalDateTimes(LocalDateTime.now());
		e1.setDesc(req.getDescription(false));

		return new ResponseEntity<MyErrorClass>(e1, HttpStatus.BAD_GATEWAY);

	}

	@ExceptionHandler(ReviewException.class)
	public ResponseEntity<MyErrorClass> getException(ReviewException e, WebRequest req) {

		MyErrorClass e1 = new MyErrorClass();
		e1.setMessage(e.getMessage());
		e1.setLocalDateTimes(LocalDateTime.now());
		e1.setDesc(req.getDescription(false));

		return new ResponseEntity<MyErrorClass>(e1, HttpStatus.BAD_GATEWAY);

	}

	@ExceptionHandler(ShippingException.class)
	public ResponseEntity<MyErrorClass> getException(ShippingException e, WebRequest req) {

		MyErrorClass e1 = new MyErrorClass();
		e1.setMessage(e.getMessage());
		e1.setLocalDateTimes(LocalDateTime.now());
		e1.setDesc(req.getDescription(false));

		return new ResponseEntity<MyErrorClass>(e1, HttpStatus.BAD_GATEWAY);

	}

	@ExceptionHandler(ShipperException.class)
	public ResponseEntity<MyErrorClass> getException(ShipperException e, WebRequest req) {

		MyErrorClass e1 = new MyErrorClass();
		e1.setMessage(e.getMessage());
		e1.setLocalDateTimes(LocalDateTime.now());
		e1.setDesc(req.getDescription(false));

		return new ResponseEntity<MyErrorClass>(e1, HttpStatus.BAD_GATEWAY);

	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorClass> getException(Exception e, WebRequest req) {

		MyErrorClass e1 = new MyErrorClass();
		e1.setMessage(e.getMessage());
		e1.setLocalDateTimes(LocalDateTime.now());
		e1.setDesc(req.getDescription(false));

		return new ResponseEntity<MyErrorClass>(e1, HttpStatus.BAD_GATEWAY);

	}

}
