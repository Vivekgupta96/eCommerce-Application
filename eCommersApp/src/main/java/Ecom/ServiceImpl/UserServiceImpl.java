package Ecom.ServiceImpl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import Ecom.Enum.UserAccountStatus;
import Ecom.Enum.UserRole;
import Ecom.Exception.UserException;
import Ecom.Model.User;
import Ecom.ModelDTO.AdminDTO;
import Ecom.ModelDTO.CustomerDTO;
import Ecom.ModelDTO.UserDTO;
import Ecom.Repository.UserRepository;
import Ecom.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User getUserByEmailId(String emailId) throws UserException {
		return userRepository.findByEmail(emailId).orElseThrow(() -> new UserException("User not found"));

	}

	@Override
	public User addUser(CustomerDTO customer) throws UserException {
		if (customer == null)
			throw new UserException("customer Can not be Null");
		Optional<User> findByEmail = userRepository.findByEmail(customer.getEmail());
		if (findByEmail.isPresent()) {
			System.out.println("inside add user method");
			throw new RuntimeException("Email alredy Register");
		}

		User newCustomer = new User();
		newCustomer.setEmail(customer.getEmail());
		newCustomer.setPassword(customer.getPassword());
		newCustomer.setFirstName(customer.getFirstName());
		newCustomer.setLastName(customer.getLastName());
		newCustomer.setPhoneNumber(customer.getPhoneNumber());
		newCustomer.setRole(UserRole.ROLE_USER);
		newCustomer.setRegisterTime(LocalDateTime.now());
		newCustomer.setUserAccountStatus(UserAccountStatus.ACTIVE);

		return userRepository.save(newCustomer);
	}

	@Override
	public User addUserAdmin(AdminDTO customer) throws UserException {
		if (customer == null)
			throw new UserException("admin Can not be Null");
		Optional<User> findByEmail = userRepository.findByEmail(customer.getEmail());
		if (findByEmail.isPresent()) {
			System.out.println("inside add user method");
			throw new RuntimeException("Email alredy Register");
		}
		User newAdmin = new User();
		newAdmin.setEmail(customer.getEmail());
		newAdmin.setPassword(customer.getPassword());
		newAdmin.setFirstName(customer.getFirstName());
		newAdmin.setLastName(customer.getLastName());
		newAdmin.setPhoneNumber(customer.getPhoneNumber());
		newAdmin.setRole(UserRole.ROLE_ADMIN);
		newAdmin.setRegisterTime(LocalDateTime.now());
		newAdmin.setUserAccountStatus(UserAccountStatus.ACTIVE);

		return userRepository.save(newAdmin);
	}

	public User changePassword(Integer userId, UserDTO customer) throws UserException {
		User user = userRepository.findById(userId).orElseThrow(() -> new UserException("User not found"));
		if (customer.getNewPassword().length() >= 5 && customer.getNewPassword().length() <= 10) {
			user.updatePassword(customer.getNewPassword(), passwordEncoder);
			return userRepository.save(user);
		} else {
			throw new UserException("provide valid  password");
		}

	}

	
	@Override
	public String deactivateUser(Integer userId) throws UserException {
		User existingUser = userRepository.findById(userId).orElseThrow(() -> new UserException("User not found"));
		existingUser.setUserAccountStatus(UserAccountStatus.DEACTIVETE);
		userRepository.save(existingUser);
		return "Account deactivet Succesfully";
	}

	@Override
	public User getUserDetails(Integer userId) throws UserException {
		User existingUser = userRepository.findById(userId).orElseThrow(() -> new UserException("User not found"));
		return existingUser;
	}

	@Override
	public List<User> getAllUserDetails() throws UserException {

		List<User> existingAllUser = userRepository.findAll();
		if (existingAllUser.isEmpty()) {
			new UserException("User list is Empty");
		}
		return existingAllUser;
	}

	

}
