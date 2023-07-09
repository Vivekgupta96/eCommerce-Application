package VeggiApp.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "Admin")
public class Admin extends User {

	@NotNull(message = "Role is mandatory")
	private String role="Admin";

	@Column(name = "AdminName")
	private String adminName;

	@Column(name = "MobileNumber")
	private String mobileNo;

}
