package Ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Ecom.Model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {


}
