package VeggiApp.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import VeggiApp.Model.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	public Optional<Customer> findByEmail(String email);

}
