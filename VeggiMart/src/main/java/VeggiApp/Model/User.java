package VeggiApp.Model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer userId;
	
	@Column(unique = true)
	@Email
	private String email;
	
	@NotNull(message = "Password is mandatory")
	private String password;
	
	@NotNull(message = "Role is mandatory")
	private String role;


	private boolean isActive=true;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User( @Email String email, @NotNull(message = "Password is mandatory") String password,
			@NotNull(message = "Role is mandatory") String role) {
		super();
		this.email = email;
		this.password = password;
		this.role = role;
	}
}
