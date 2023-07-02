package VeggiApp.ServiceImpl;

import VeggiApp.Exception.FeedBackException;
import VeggiApp.Model.Feedback;
import VeggiApp.Repository.FeedbackRepository;
import VeggiApp.Service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedBackServiceImpl implements FeedBackService {

    @Autowired
    private  FeedbackRepository feedbackRepository;

    @Override
    public Feedback saveFeedback(Feedback feedback) throws FeedBackException {

        if(feedback==null){
            throw new FeedBackException("Feedback Can't be empty");
        }
        return feedbackRepository.save(feedback);
    }

    public List<Feedback> getAllFeedbackByVegetableId(Integer vegetableId) {

         List<Feedback> feedbackList=feedbackRepository.getAllFeedBackByVegId(vegetableId);
         if(feedbackList.isEmpty()){
             throw new FeedBackException("FeedBack Not given Yet");
         }
         return feedbackList;
        // return null;
    }

    @Override
    public List<Feedback> getAllFeedbackByCustomerId(Integer customerId) throws FeedBackException {
        List<Feedback> feedbackList=feedbackRepository.getAllFeedBackByCustId(customerId);
        if(feedbackList.isEmpty()){
            throw new FeedBackException("FeedBack Not given Yet");
        }
        return feedbackList;
    }


}
