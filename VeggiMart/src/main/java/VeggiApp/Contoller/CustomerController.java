package VeggiApp.Contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import VeggiApp.Exception.CustomerException;
import VeggiApp.Model.Customer;
import VeggiApp.Service.CustomerService;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/customers/register")
	public ResponseEntity<Customer> addCustomerHandler(@Valid @RequestBody Customer cus){
		Customer addCustomer = customerService.addCustomer(cus);
		return new ResponseEntity<Customer>(addCustomer,HttpStatus.CREATED);
		
	}
	@GetMapping("/hello")
	public String getHello(){
		return "Hello";
		
	}

	@PutMapping("/customers/update/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable Integer customerId, @RequestBody Customer customerDto) {
		try {
			Customer updatedCustomer = customerService.updateCustomer(customerId, customerDto);
			return ResponseEntity.ok(updatedCustomer);
		} catch (CustomerException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@DeleteMapping ("/customers//deactivate/{customerId}")
	public ResponseEntity<Customer> deactivateCustomer(@PathVariable Integer customerId) {
		try {
			Customer deactivatedCustomer = customerService.deactivateCustomer(customerId);
			return ResponseEntity.ok(deactivatedCustomer);
		} catch (CustomerException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@GetMapping("/customers/details/{customerId}")
	public ResponseEntity<Customer> getCustomerDetails(@PathVariable Integer customerId) {
		try {
			Customer customer = customerService.viewCustomerDetails(customerId);
			return ResponseEntity.ok(customer);
		} catch (CustomerException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
	@GetMapping("/customers/getAllCustomer")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		try {
			List<Customer> customers = customerService.viewAllCustomers();
			return ResponseEntity.ok(customers);
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}
}
