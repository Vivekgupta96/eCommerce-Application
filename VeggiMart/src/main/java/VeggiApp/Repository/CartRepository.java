package VeggiApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import VeggiApp.Model.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {

    @Query("SELECT c FROM Cart c WHERE c.userid = :userId")
    Cart findByUserId(@Param("userId") Integer userId);

    @Query("SELECT c FROM Cart c WHERE c.vegetableId = :vegetableId")
    Cart findByVegId(@Param("vegetableId") Integer vegetableId);

}
