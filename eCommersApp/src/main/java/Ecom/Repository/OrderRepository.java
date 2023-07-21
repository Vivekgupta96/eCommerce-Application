package Ecom.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import Ecom.Model.Orders;

@Repository
public interface OrderRepository extends JpaRepository<Orders, Integer> {

	@Query("SELECT o FROM Orders o WHERE o.orderId = :orderId AND o.user.userId = :userId")
	Orders findByIdAndCustomerId(@Param("orderId") Integer orderId, @Param("userId") Integer userId);

	@Query("SELECT o FROM Orders o WHERE o.orderDate >= :date")
	List<Orders> findByOrderDateGreaterThanEqual(Date date);

	@Query("SELECT o FROM Orders o WHERE  o.user.userId = :userId")
	List<Orders> getAllOrderByUserId(@Param("userId") Integer userId);

}
