package Ecom.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Ecom.Enum.OrderStatus;
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
    private OrderStatus status;

    @Column(name = "order_date")
    private LocalDateTime orderDate;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double totalAmount;

    @OneToMany( cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private List<OrderItem> orderItem= new ArrayList<>();

    @OneToOne
    @JoinColumn(name="Payment_id")
    private Payment payment;


    @OneToOne
    @JoinColumn(name="ShippingDetails_id")
    private ShippingDetails shippingDetails;


}