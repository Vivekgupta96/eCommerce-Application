package Ecom.Model;

import java.util.ArrayList;
import java.util.Date;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Orders")
public class Orders {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer orderId;

    @Enumerated(EnumType.STRING)
    private OrderStatus staus;

    @Column(name = "order_date")
    private Date orderDate;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    private double totalAmount;
    
    @OneToMany(mappedBy = "orders", cascade = CascadeType.ALL)
    private List<OrderDetails> orderDetails= new ArrayList<>();;
    
    @OneToOne(mappedBy = "order")
    private Payment payment;

    
    @OneToOne(mappedBy = "orders", cascade = CascadeType.ALL, orphanRemoval = true)
    private ShippingDetails shippingDetails;
    
    
}
