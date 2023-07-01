package VeggiApp.Repository;

import VeggiApp.Model.Customer;
import VeggiApp.Model.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Integer> {

}
