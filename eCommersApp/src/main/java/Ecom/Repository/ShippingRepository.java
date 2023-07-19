package Ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Ecom.Model.ShippingDetails;

public interface ShippingRepository extends JpaRepository<ShippingDetails, Integer> {

}
