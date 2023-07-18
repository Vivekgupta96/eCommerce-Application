package Ecom.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ecom.Exception.UserException;
import Ecom.Model.User;
import Ecom.ModelDTO.UserDTO;
import Ecom.Repository.UserRepository;
import Ecom.Service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public User getUserByEmailId(String emailId) throws UserException {
		return userRepository.findByEmail(emailId).orElseThrow(() -> new UserException("User not found"));
		
	}
	@Override
	public User addUser(User user) throws UserException {
		if (user == null)
			throw new UserException("User Can not be Null");
		return userRepository.save(user);
	}

	@Override
	public User updateUserPassword(Integer userId, UserDTO userdto) throws UserException {
		User existingUser = userRepository.findById(userId).orElseThrow(() -> new UserException("User not found"));

		if (existingUser.getPassword().equals(userdto.getOldPassword())) {
			existingUser.setPassword(userdto.getNewPassword());
		}else {
			throw new UserException("Old password Mismatch");
		}

		// Save the updated user in the repository
		 User updatedUser = userRepository.save(existingUser);
		return updatedUser;
	}

	@Override
	public String deactivateUser(Integer userId) throws UserException {
		User existingUser = userRepository.findById(userId).orElseThrow(() -> new UserException("User not found"));
		existingUser.setActive(false);
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
		if(existingAllUser.isEmpty()) {
			new UserException("User list is Empty");
		}
		return existingAllUser;
	}

	

}
