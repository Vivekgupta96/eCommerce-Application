package VeggiApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import VeggiApp.Model.Feedback;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{

    @Query(value = "SELECT * FROM feedback WHERE vegetable_id = :vegetableId", nativeQuery = true)
    List<Feedback> getAllFeedBackByVegId(@Param("vegetableId") Integer vegetableId);

    @Query(value ="SELECT * FROM Feedback  WHERE customer_id = :customerId", nativeQuery = true)
    List<Feedback> getAllFeedBackByCustId(@Param("customerId") Integer customerId);

}
