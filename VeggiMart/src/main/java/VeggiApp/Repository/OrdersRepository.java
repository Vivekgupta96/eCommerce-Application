package VeggiApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import VeggiApp.Model.Orders;



@Repository
public interface OrdersRepository extends JpaRepository<Orders, Integer> {
	
   
}
