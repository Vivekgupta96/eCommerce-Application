package VeggiApp.Contoller;

import VeggiApp.Model.Feedback;
import VeggiApp.Service.FeedBackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class FeedBackController {
    @Autowired
    private FeedBackService feedbackService;


    @PostMapping("/feedback")
    public ResponseEntity<Feedback> saveFeedback(@RequestBody Feedback feedback) {
        Feedback savedFeedback=feedbackService.saveFeedback(feedback);
        return  new ResponseEntity<>(savedFeedback, HttpStatus.CREATED);
    }

    @GetMapping("/feedback/vegetable/{vegetableId}")
    public ResponseEntity<List<Feedback>> getAllFeedbackByVegetableId(@PathVariable("vegetableId") Integer vegetableId) {
        List<Feedback> feedbackList = feedbackService.getAllFeedbackByVegetableId(vegetableId);
        return ResponseEntity.ok(feedbackList);
    }

    @GetMapping("/feedback/customer/{customerId}")
    public ResponseEntity<List<Feedback>> getAllFeedbackByCustomerId(@PathVariable("customerId") Integer customerId) {
        List<Feedback> feedbackList = feedbackService.getAllFeedbackByCustomerId(customerId);
        return ResponseEntity.ok(feedbackList);
    }

}
