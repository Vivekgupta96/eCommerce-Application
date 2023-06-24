package VeggiApp.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class BillingAddress extends Address {

	@OneToOne
	@JoinColumn(name = "CustomerID")
	private BillingDetails billingDetails;

}
