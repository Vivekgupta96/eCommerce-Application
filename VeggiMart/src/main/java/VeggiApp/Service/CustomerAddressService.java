package VeggiApp.Service;

import VeggiApp.Exception.CustomerAddressException;
import VeggiApp.Model.CustomerAddress;

public interface CustomerAddressService {

   public  String addCustomerAddress(Integer customerId,CustomerAddress customerAddress)throws CustomerAddressException;

   public CustomerAddress editCustomerAddress(Integer customerAddressid,CustomerAddress customerAddress)throws CustomerAddressException;

   public String deleteCustomerAddress(Integer customerAddressId)throws CustomerAddressException;
}
