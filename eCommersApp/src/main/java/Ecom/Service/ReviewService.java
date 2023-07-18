package Ecom.Service;

import java.util.List;

import Ecom.Exception.ReviewException;
import Ecom.Model.Review;

public interface ReviewService {
	
	 public Review addReviewToProduct(Integer productId, Integer UserId,Review review) 
			 throws ReviewException;
	 
	 public Review updateReviewToProduct(Integer reviewId,Review review)
			 throws ReviewException;
	 
	 public void deleteReview(Integer reviewId) throws ReviewException;
	 
	 public List<Review> getAllReviewOfProduct(Integer productId)throws ReviewException;

}
