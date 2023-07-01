package VeggiApp.ServiceImpl;

import VeggiApp.Exception.CustomerAddressException;
import VeggiApp.Model.Customer;
import VeggiApp.Model.CustomerAddress;
import VeggiApp.Repository.CustomerAddressRepository;
import VeggiApp.Repository.CustomerRepository;
import VeggiApp.Service.CustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerAddressServiceImpl implements CustomerAddressService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerAddressRepository customerAddressRepository;


    @Override
    public String addCustomerAddress(Integer customerId, CustomerAddress customerAddress) throws CustomerAddressException {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);

        if (optionalCustomer.isEmpty()) {
            throw new CustomerAddressException("Customer not found with ID: " + customerId);
        }

        if (customerAddress == null) {
            throw new CustomerAddressException("Customer address cannot be null");
        }

        Customer customer = optionalCustomer.get();
        customer.getCustomerAddresses().add(customerAddress);

        customerRepository.save(customer);

        return "Address added successfully";
    }


    @Override
    public CustomerAddress editCustomerAddress(Integer customerAddressId, CustomerAddress customerAddress) throws CustomerAddressException {
        Optional<CustomerAddress> optionalAddress = customerAddressRepository.findById(customerAddressId);

        if (optionalAddress.isPresent()) {
            CustomerAddress existingAddress = optionalAddress.get();

            // Update the existing address with the new values
            existingAddress.setFlatNo(customerAddress.getFlatNo());
            existingAddress.setStreet(customerAddress.getStreet());
            existingAddress.setCity(customerAddress.getCity());
            existingAddress.setState(customerAddress.getState());
            existingAddress.setZipCode(customerAddress.getZipCode());

            // Save the updated address
            CustomerAddress updatedAddress = customerAddressRepository.save(existingAddress);

            return updatedAddress;
        } else {
            throw new CustomerAddressException("Customer address not found with ID: " + customerAddressId);
        }
    }


    public String deleteCustomerAddress(Integer customerAddressId) throws CustomerAddressException {
        Optional<CustomerAddress> optionalAddress = customerAddressRepository.findById(customerAddressId);

        if (optionalAddress.isPresent()) {
            CustomerAddress customerAddress = optionalAddress.get();
            customerAddressRepository.delete(customerAddress);
            return "Address deleted successfully";
        } else {
            throw new CustomerAddressException("Customer address not found with ID: " + customerAddressId);
        }
    }

}
