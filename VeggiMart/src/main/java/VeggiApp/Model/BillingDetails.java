package VeggiApp.Model;

import java.time.LocalDateTime;

import org.hibernate.resource.transaction.spi.TransactionStatus;

import VeggiApp.ModelEnum.TransactionMode;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Billing_Detail")
public class BillingDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer billingId;
	
	@ManyToOne
	@JoinColumn(name="order_id")
	private Orders orders;
	
	@Enumerated(EnumType.STRING)
	private TransactionMode transactionMode;
	
	@Enumerated(EnumType.STRING)
	private TransactionStatus transactionStatus;
	
	private LocalDateTime localDateTime;
	
	@OneToOne(mappedBy = "billingDetails", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
	private BillingAddress billingAddress;
	


}
