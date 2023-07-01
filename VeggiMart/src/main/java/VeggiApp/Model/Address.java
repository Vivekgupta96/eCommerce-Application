package VeggiApp.Model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
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
   

   
}