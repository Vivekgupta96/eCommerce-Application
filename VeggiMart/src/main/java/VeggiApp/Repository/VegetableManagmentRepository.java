package VeggiApp.Repository;


import VeggiApp.Model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import VeggiApp.Model.Vegetables;

import java.util.List;

@Repository
public interface VegetableManagmentRepository extends JpaRepository<Vegetables, Integer> {

    @Query(value = "SELECT * FROM vegetables WHERE vegetable_name like :name", nativeQuery = true)
    List<Vegetables> getAllVegitableByName(@Param("name") String name);

}
