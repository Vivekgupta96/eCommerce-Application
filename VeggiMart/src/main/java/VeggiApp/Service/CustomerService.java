package VeggiApp.Service;

import java.util.List;

import VeggiApp.ModelDTO.CustomerDTO;
import org.springframework.stereotype.Service;

import VeggiApp.Exception.CustomerException;
import VeggiApp.Model.Customer;

@Service
public interface CustomerService {

	public Customer addCustomer(Customer customer)  throws CustomerException;

	public Customer updateCustomerPassword(Integer customerId, CustomerDTO customer)  throws CustomerException;

	public Customer deactivateCustomer(Integer customerId) throws CustomerException;

	public Customer viewCustomerDetails(Integer customerId)throws CustomerException;

	public List<Customer> viewAllCustomers() throws CustomerException;
}
