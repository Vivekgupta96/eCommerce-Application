package Ecom.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import Ecom.Enum.UserAccountStatus;
import Ecom.Enum.UserRole;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.ToString;


@Data
@Entity
@Table(name = "Users")
public class User {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "email",unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;
    
    @JsonIgnore
    @Column(name = "User_Role")
    @Enumerated(EnumType.STRING)
    private UserRole role;
    
    @Column(name = "User_Reg_Time")
    private LocalDateTime registerTime;
    
    @Column(name = "UserAccountStatus")
    @Enumerated(EnumType.STRING)
    private UserAccountStatus userAccountStatus;

    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "user")
    private Cart cart;
    
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Orders> orders = new ArrayList<>();;
    
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private List<Review> reviews = new ArrayList<>();;
    
    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Address> address = new ArrayList<>();
    
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Payment> payments = new ArrayList<>();
    
    public void updatePassword(String newPassword, PasswordEncoder passwordEncoder) {
        // Hash the new password before setting it
        String hashedPassword = passwordEncoder.encode(newPassword);
        this.setPassword(hashedPassword);
    }
    
      
}

