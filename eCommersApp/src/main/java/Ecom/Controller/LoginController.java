package Ecom.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Ecom.Service.UserService;

@RestController
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping("/signIn")
	public ResponseEntity<String> getLoggedInCustomerDetailsHandler(Authentication auth) {

		System.out.println(auth); // this Authentication object having Principle object details

		var customer = userService.getUserByEmailId(auth.getName());

		return new ResponseEntity<>(customer.getFirstName() + " " + customer.getLastName() + "  Logged In Successfully",
				HttpStatus.ACCEPTED);
	}

}
