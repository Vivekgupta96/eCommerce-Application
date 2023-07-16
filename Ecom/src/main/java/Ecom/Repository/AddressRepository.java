package Ecom.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Ecom.Model.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {
	
	 @Query("SELECT a FROM Address a JOIN a.user u WHERE u.userId = :userId")
	 List<Address> getUserAddressList(@Param("userId") Integer userId);

}
