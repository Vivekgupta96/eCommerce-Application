package Ecom.Model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Shipper")
public class Shipper {
    @Id
    @Column(name = "shipperid")
    private Integer shipperId;

    @Column(name = "name")
    private String name;

    @Column(name = "phoneNumber")
    private String phoneNumber;
    
    @OneToMany(mappedBy = "shipper")
    private List<ShippingDetails> shippingDetails= new ArrayList<>();;

  
}

