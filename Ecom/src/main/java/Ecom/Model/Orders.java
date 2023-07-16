package Ecom.Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Ecom.Enum.OrderStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Orders")
public class Orders {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Enumerated(EnumType.STRING)
    private OrderStatus staus;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private String billingAddress;
    
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems= new ArrayList<>();;
    
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Payment> payments= new ArrayList<>();;
    
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<Shipping> shipping= new ArrayList<>();;
    
    
    
    
   
    // Other order-related fields, getters, and setters
}
