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
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "Address")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AddressID")
	private Integer addressID;

	@NotNull(message = "flatNo Name Mandatory")
	@NotBlank(message = "flatNo Name Mandatory")
	private String flatNo;

	@NotNull(message = "Street Name Mandatory")
	@NotBlank(message = "Street Name Mandatory")
	private String Street;

	@Size(max = 10)
	@NotNull(message = "City Name Mandatory")
	@NotBlank(message = "City Name Mandatory")
	private String city;

	@NotNull(message = "zipCode should not Blank")
	@NotBlank(message = "zipCode is Mandatory")
	private String ZipCode;

	@Size(max = 10)
	@NotNull(message = "State should not Blank")
	@NotBlank(message = "State Name Mandatory")
	private String state;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "userid")
	private User user;

}
