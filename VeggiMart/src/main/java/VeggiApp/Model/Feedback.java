package VeggiApp.Model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Feedback {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer feedbackId;

	@Column(name = "Title")
	private String title;

	@Column(name = "comment")
	private String comment;

	@Column(name = "Rating")
	private double rating;

	@Column(name = "Timestamp")
	private LocalDateTime timestamp;

	// *****************
	@ManyToOne
	@JoinColumn(name = "CustomerId")
	private Customer customer;

//*************
	@ManyToOne
	@JoinColumn(name = "vegetable_id")
	private Vegetables vegetables;

}
