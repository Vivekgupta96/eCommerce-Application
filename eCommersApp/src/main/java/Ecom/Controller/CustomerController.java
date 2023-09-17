package Ecom.Controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import Ecom.Model.User;
import Ecom.ModelDTO.CustomerDTO;
import Ecom.ModelDTO.UserDTO;
import Ecom.Service.UserService;
import jakarta.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ecom/customers")
public class CustomerController {

    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<User> addUser(@Valid @RequestBody CustomerDTO user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User addedUser = userService.addUser(user);
        return ResponseEntity.ok(addedUser);
    }

    @PutMapping("/update-password/{customerId}")
    public ResponseEntity<User> updateUserPassword(@PathVariable("customerId") Integer customerId,
                                                  @Valid @RequestBody UserDTO userdto) {
        User updatedUser = userService.changePassword(customerId, userdto);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/deactivate/{customerid}")
    public ResponseEntity<String> deactivateUser(@PathVariable("customerid") Integer customerId) {
        System.out.println("inside the deactivate method");
        String message = userService.deactivateUser(customerId);
        return ResponseEntity.ok(message);
    }

    @GetMapping("/{customerid}")
    public ResponseEntity<User> getUserDetails(@PathVariable("customerid") Integer customerId) {
        User user = userService.getUserDetails(customerId);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/get-all-customer")
    public ResponseEntity<List<User>> getAllUserDetails() {
        List<User> users = userService.getAllUserDetails();
        return ResponseEntity.ok(users);
    }

}
