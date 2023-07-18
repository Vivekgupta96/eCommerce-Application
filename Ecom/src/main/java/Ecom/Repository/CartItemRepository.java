package Ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Ecom.Model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
	

}
