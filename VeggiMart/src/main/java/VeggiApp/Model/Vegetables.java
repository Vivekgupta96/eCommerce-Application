package VeggiApp.Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

	@Column(name = "Price")
	private double price;

	
	@Column(name = "AvailableQuantity")
	private int availableQuantity;
	
	
	// *************
	@ManyToMany
	private List<Orders> orders = new ArrayList<>();

	// *************
	@OneToMany(mappedBy = "vegetables")
    private List<Feedback> feedbacks;
	
	// *************
	@ManyToMany(mappedBy = "vegetables")
    private Set<Cart> carts = new HashSet<>();
}
