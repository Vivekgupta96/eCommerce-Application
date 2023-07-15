package Ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Ecom.Model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

}
