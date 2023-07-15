package Ecom.Service;

import org.springframework.stereotype.Service;

import Ecom.Exception.AddressException;
import Ecom.Model.Address;
import Ecom.ModelDTO.AddressDTO;

@Service
public interface AddressService {
	
	public Address addAddressToUser(Integer userId,Address address) throws AddressException;
	
	public Address updateAddress( Address address) throws AddressException ;
	
	public void   removeAddress(Integer addressId)throws AddressException;

}
