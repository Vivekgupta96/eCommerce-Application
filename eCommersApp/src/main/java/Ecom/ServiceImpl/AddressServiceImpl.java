package Ecom.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ecom.Exception.AddressException;
import Ecom.Exception.UserException;
import Ecom.Model.Address;
import Ecom.Model.User;
import Ecom.Repository.AddressRepository;
import Ecom.Repository.UserRepository;
import Ecom.Service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository,UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository=userRepository;
    }

    @Override
    public Address addAddressToUser(Integer userId, Address address) throws AddressException {
      User existingUser=userRepository.findById(userId).orElseThrow(()-> new UserException("User Not Fouund"));
      
      Address saveaAddress=addressRepository.save(address);
      saveaAddress.setUser(existingUser);
      
      existingUser.getAddress().add(saveaAddress);
      userRepository.save(existingUser);
      return saveaAddress;
    }

    @Override
    public Address updateAddress( Address address) throws AddressException {
        // Find the existing address by ID
        Address existingAddress = addressRepository.findById(address.getAddressID())
                .orElseThrow(() -> new AddressException("Address not found"));
        
        // Update the address properties from the DTO
        existingAddress.setFlatNo(address.getFlatNo());
        existingAddress.setZipCode(address.getZipCode());
        existingAddress.setStreet(address.getStreet());
        existingAddress.setCity(address.getCity());
        existingAddress.setState(address.getState());
        // Save the updated address in the repository
        return addressRepository.save(existingAddress);
    }

    @Override
    public void removeAddress(Integer addressId) throws AddressException {
        // Find the existing address by ID
        Address existingAddress = addressRepository.findById(addressId)
                .orElseThrow(() -> new AddressException("Address not found"));

        // Delete the address from the repository
        addressRepository.delete(existingAddress);
    }

	@Override
	public List<Address> getAllUserAddress(Integer userId) throws AddressException {
		
		List<Address> userAddressList = addressRepository.getUserAddressList(userId);
		if(userAddressList.isEmpty()) throw new AddressException("User do not hava AnyAddress");
		return userAddressList;
	}
}