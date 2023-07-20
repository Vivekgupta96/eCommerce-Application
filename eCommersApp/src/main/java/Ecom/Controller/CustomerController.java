package Ecom.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Ecom.Exception.UserException;
import Ecom.Model.User;
import Ecom.ModelDTO.CustomerDTO;
import Ecom.ModelDTO.UserDTO;
import Ecom.Service.UserService;

@RestController
@RequestMapping("/veggi/customers")
public class CustomerController {

    private final UserService userService;

    @Autowired
    public CustomerController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> addUser(@RequestBody CustomerDTO user) {
        try {
            User addedUser = userService.addUser(user);
            return ResponseEntity.ok(addedUser);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/updatepassword/{customerId}")
    public ResponseEntity<User> updateUserPassword(@PathVariable("customerid") Integer customerId, @RequestBody UserDTO userdto) {
        try {
            User updatedUser = userService.updateUserPassword(customerId, userdto);
            return ResponseEntity.ok(updatedUser);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/deactivate/{customerId}")
    public ResponseEntity<String> deactivateUser(@PathVariable("customerid") Integer customerId) {
        try {
            String message = userService.deactivateUser(customerId);
            return ResponseEntity.ok(message);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{customerid}")
    public ResponseEntity<User> getUserDetails(@PathVariable("customerid") Integer customerId) {
        try {
            User user = userService.getUserDetails(customerId);
            return ResponseEntity.ok(user);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/get-all-customer")
    public ResponseEntity<List<User>> getAllUserDetails() {
        try {
            List<User> users = userService.getAllUserDetails();
            return ResponseEntity.ok(users);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}

