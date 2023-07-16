package Ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Ecom.Model.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

}
