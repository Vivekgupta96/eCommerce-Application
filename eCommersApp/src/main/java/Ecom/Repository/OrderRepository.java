package Ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Ecom.Model.Orders;


@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

	@Query("SELECT o FROM Orders o WHERE o.orderId = :orderId AND o.user.userId = :userId")
	Orders findByIdAndCustomerId(@Param("orderId") Integer orderId, @Param("userId") Integer userId);

}
