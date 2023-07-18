package Ecom.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Address")
public class Address {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AddressID")
    private Integer addressID;
    
    //@NotNull
   // @NotBlank(message = "flatNo Name Mandatory")
    private String flatNo;
    
    //@NotNull
    //@NotBlank(message = "Area Name Mandatory")
    private String Street;
    
    //@NotNull
    //@NotBlank(message = "City Name Mandatory")
    private String city;
    
   // @NotNull
    //@NotBlank(message = "zipCode Name Mandatory")
    private String ZipCode;
    
    //@NotNull
    //@NotBlank(message = "City Name Mandatory")
    private String state;
    
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name="userid")
    private User user;
   
 
}
