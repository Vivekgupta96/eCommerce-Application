package Ecom.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Ecom.Exception.ReviewException;
import Ecom.Model.Review;
import Ecom.Service.ReviewService;

@RestController
@RequestMapping("/product/reviews")
public class ReviewController {

	private final ReviewService reviewService;

	@Autowired
	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@PostMapping("/{productId}/{userId}")
	public ResponseEntity<Review> addReviewToProduct(@PathVariable Integer productId, @PathVariable Integer userId,
			@RequestBody Review review) {
		try {
			Review addedReview = reviewService.addReviewToProduct(productId, userId, review);
			return ResponseEntity.ok(addedReview);
		} catch (ReviewException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PutMapping("/{reviewId}")
	public ResponseEntity<Review> updateReviewToProduct(@PathVariable Integer reviewId, @RequestBody Review review) {
		try {
			Review updatedReview = reviewService.updateReviewToProduct(reviewId, review);
			return ResponseEntity.ok(updatedReview);
		} catch (ReviewException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@DeleteMapping("/{reviewId}")
	public ResponseEntity<Void> deleteReview(@PathVariable Integer reviewId) {
		try {
			reviewService.deleteReview(reviewId);
			return ResponseEntity.noContent().build();
		} catch (ReviewException e) {
			return ResponseEntity.notFound().build();
		}
	}
	
	@GetMapping("/{productId}")
    public ResponseEntity<List<Review>> getAllReviewOfProduct(@PathVariable Integer productId) {
        try {
            List<Review> allReviews = reviewService.getAllReviewOfProduct(productId);
            return new ResponseEntity<>(allReviews, HttpStatus.OK);
        } catch (ReviewException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
