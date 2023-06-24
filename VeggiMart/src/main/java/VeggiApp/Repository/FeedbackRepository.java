package VeggiApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import VeggiApp.Model.Feedback;


public interface FeedbackRepository extends JpaRepository<Feedback, Integer>{

}
