package VeggiApp.ServiceImpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import VeggiApp.Exception.CustomerException;
import VeggiApp.Model.Customer;
import VeggiApp.Repository.CustomerRepository;
import VeggiApp.Service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {

	Optional<Customer> getCustByEmail	=customerRepository.findByEmail(customer.getEmail());
	if(getCustByEmail.isPresent()){
		throw new CustomerException("Email Id Alredy Register");
	}
		return customerRepository.save(customer);
	}

	@Override
	public Customer updateCustomer(Integer customerId,Customer customerDto) throws CustomerException{
		Optional<Customer> existingCustomer = customerRepository.findById(customerId);

		if (existingCustomer.isEmpty()) {
			throw new CustomerException("Customer not found");
		}
		// Update the customer fields
		Customer updatedCustomer = existingCustomer.get();
		updatedCustomer.setCustomerName(customerDto.getCustomerName());
		updatedCustomer.setEmail(customerDto.getEmail());
		// Update other fields as needed
		return customerRepository.save(updatedCustomer);
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
