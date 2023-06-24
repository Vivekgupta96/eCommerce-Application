package VeggiApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import VeggiApp.Model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

//
//    @Query("SELECT c FROM Cart c WHERE c.customer.customerId = :customerId")
//    Cart getCartByCustomerId(@Param("customerId") Integer customerId);

}
