package Ecom.Model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "Products")
public class Product {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Integer productId;

    @Column(name = "name")
    private String name;
    
    @Column(name = "imageUrl")
    private String imageUrl;
    
    @Column(name = "isAvailable")
    private boolean isAvailable;
    

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;
 
    @Column(name = "category_name")
    private String category; 
    
//    @ManyToOne
//    @JoinColumn(name="category_id")
//    private Category category;


    @JsonIgnore
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;
    
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems= new ArrayList<>();;
    
    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<Review> reviews= new ArrayList<>();;
    
   
}

