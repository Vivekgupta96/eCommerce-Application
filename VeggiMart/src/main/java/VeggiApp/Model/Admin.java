package VeggiApp.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "Admin")
public class Admin extends User {

	@Column(name = "AdminName")
	private String adminName;

	@Column(name = "MobileNumber")
	private String mobileNo;

}
