package VeggiApp.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import VeggiApp.Model.Vegetables;

@Repository
public interface VegetableManagmentRepository extends JpaRepository<Vegetables, Integer> {

}
