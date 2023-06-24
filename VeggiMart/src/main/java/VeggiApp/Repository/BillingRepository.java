package VeggiApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import VeggiApp.Model.BillingDetails;

@Repository
public interface BillingRepository extends JpaRepository<BillingDetails,Integer> {


}
