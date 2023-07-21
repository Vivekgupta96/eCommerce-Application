package Ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Ecom.Model.Shipper;

@Repository
public interface ShipperRepository extends JpaRepository<Shipper, Integer> {

}
