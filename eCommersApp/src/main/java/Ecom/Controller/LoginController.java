package Ecom.Controller;

import Ecom.ModelDTO.UserSignInDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Ecom.Service.UserService;

@RestController
@RequestMapping("/ecom")
public class LoginController {

	@Autowired
	private UserService userService;

	@GetMapping("/signIn")
	public ResponseEntity<UserSignInDetail> getLoggedInCustomerDetailsHandler(Authentication auth) {
		var customer = userService.getUserByEmailId(auth.getName());
		UserSignInDetail signinSuceesData = new UserSignInDetail();
		signinSuceesData.setId(customer.getUserId());
		signinSuceesData.setFirstNAme(customer.getFirstName());
		signinSuceesData.setLastName(customer.getLastName());
		signinSuceesData.setSigninStatus("Success");

		return new ResponseEntity<>(signinSuceesData, HttpStatus.OK);

	}
}
