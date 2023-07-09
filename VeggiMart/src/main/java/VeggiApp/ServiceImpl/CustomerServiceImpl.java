package VeggiApp.ServiceImpl;


import java.util.List;
import java.util.Optional;

import VeggiApp.Model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import VeggiApp.Exception.CustomerException;
import VeggiApp.Model.Customer;
import VeggiApp.ModelDTO.CustomerDTO;
import VeggiApp.Repository.CustomerRepository;
import VeggiApp.Service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {

		Optional<Customer> getCustByEmail = customerRepository.findByEmail(customer.getEmail());
		if (getCustByEmail.isPresent()) {
			throw new CustomerException("Email Id Alredy Register");
		}

		if (customer.getCart() == null) {
			Cart customerCartAllotted = new Cart();
			customer.setCart(customerCartAllotted);
			customerCartAllotted.setCustomer(customer);
		}
		return customerRepository.save(customer);
	}
	@Override
	public Customer updateCustomerPassword(Integer customerId,CustomerDTO customerDto) throws CustomerException{
		Optional<Customer> existingCustomer = customerRepository.findById(customerId);

		if (existingCustomer.isEmpty()) {
			throw new CustomerException("Customer not found");
		}
        Customer updatedCustomer = existingCustomer.get();
        if(updatedCustomer.getPassword().equals(customerDto.getOldpassword())){
            updatedCustomer.setPassword(customerDto.getPassword());
            // Update the customer fields

            // Update other fields as needed
            return customerRepository.save(updatedCustomer);
        }else{
            throw new CustomerException("Old Password Not Matched");
        }

	}

	@Override
	public Customer deactivateCustomer(Integer customerId) throws CustomerException {
		Optional<Customer> existingCustomer = customerRepository.findById(customerId);

		if (existingCustomer.isEmpty()) {
			throw new CustomerException("Customer not found");
		}

		Customer customer = existingCustomer.get();
		customer.setActive(false); // Set the customer's active status to false

		return customerRepository.save(customer);
	}

	@Override
	public Customer viewCustomerDetails(Integer customerId)throws CustomerException {
		Optional<Customer> existingCustomer = customerRepository.findById(customerId);

		if (existingCustomer.isEmpty()) {
			throw new CustomerException("Customer not found");
		}
		return existingCustomer.get();
	}




	@Override
	public List<Customer> viewAllCustomers() throws CustomerException {
		List<Customer> customers = customerRepository.findAll();

		if (customers.isEmpty()) {
			throw new CustomerException("No customers found");
		}

		return customers;
	}

}
