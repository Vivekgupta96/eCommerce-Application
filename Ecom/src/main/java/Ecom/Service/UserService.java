package Ecom.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import Ecom.Exception.UserException;
import Ecom.Model.User;
import Ecom.ModelDTO.UserDTO;


@Service
public interface UserService {
	
	public User getUserByEmailId(String emailId)throws UserException;

	public User addUser(User customer)  throws UserException;

	public User updateUserPassword(Integer userId, UserDTO customer)  throws UserException;

	public String deactivateUser(Integer userId) throws UserException;

	public User getUserDetails(Integer userId)throws UserException;

	public List<User> getAllUserDetails() throws UserException;
}
