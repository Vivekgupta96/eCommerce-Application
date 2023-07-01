package VeggiApp.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CustomerAddress extends Address {

	@Column(name = "AddressType")
	private String addressType;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "CustomerId")
	private Customer customer;

}
