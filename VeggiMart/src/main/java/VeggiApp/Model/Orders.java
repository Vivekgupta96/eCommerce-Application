package VeggiApp.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import VeggiApp.ModelEnum.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Orders")
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	
	
	private double totalAmount;
	
	@Column(name = "OrderDate")
	private LocalDateTime orderDate;
	
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	
	//************
	@ManyToOne
	@JoinColumn(name="CustomerID")
	private Customer customer;
	
	//*************
	@OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "orders")
	private List<BillingDetails> billingDetails=new ArrayList<>();

	//*************
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "orders")
	private List< Vegetables > vegetables=new ArrayList<>();
	
}
