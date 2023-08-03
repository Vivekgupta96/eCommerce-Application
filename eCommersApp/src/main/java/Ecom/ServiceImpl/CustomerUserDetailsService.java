package Ecom.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import Ecom.Repository.UserRepository;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;

	public CustomerUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<Ecom.Model.User> opt = userRepository.findByEmail(username);
		 
		if (opt.isPresent()) {
			Ecom.Model.User customer = opt.get();

			List<GrantedAuthority> authorities = new ArrayList<>();
			
			SimpleGrantedAuthority sga = new SimpleGrantedAuthority(customer.getRole().toString());
			authorities.add(sga);
		
			return new org.springframework.security.core.userdetails.User(customer.getEmail(), customer.getPassword(),
					authorities);
		} else
			throw new BadCredentialsException("User Details not found with this username: " + username);
	}

}
