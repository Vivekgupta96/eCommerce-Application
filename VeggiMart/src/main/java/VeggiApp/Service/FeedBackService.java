package VeggiApp.Service;

import VeggiApp.Exception.FeedBackException;
import VeggiApp.Model.Feedback;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public interface FeedBackService {

    public Feedback saveFeedback(Feedback feedback) throws FeedBackException;

    public List<Feedback> getAllFeedbackByVegetableId(Integer vegetableId) throws FeedBackException;

    public List<Feedback> getAllFeedbackByCustomerId(Integer customerId)throws FeedBackException;
}