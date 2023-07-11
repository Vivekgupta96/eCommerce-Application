package VeggiApp.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Vegetables {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer vegetableId;
	
	@Column(name = "VegetableName")
	private String vegName;

	@Column(name = "categoryName")
	private String category;

	@Column(name = "Price")
	private double price;

	
	@Column(name = "Quantity")
	private int Quantity=1;
	
	
	// *************
	@JsonIgnore
	@ManyToMany
	private List<Orders> orders = new ArrayList<>();

	// *************
	@JsonIgnore
	@OneToMany(mappedBy = "vegetables")
    private List<Feedback> feedbacks;
	
	// *************
	@JsonIgnore
	@ManyToMany(mappedBy = "vegetables")
    private Set<Cart> carts = new HashSet<>();
}
