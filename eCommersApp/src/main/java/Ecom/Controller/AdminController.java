package Ecom.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Ecom.Exception.UserException;
import Ecom.Model.User;
import Ecom.ModelDTO.AdminDTO;
import Ecom.ModelDTO.UserDTO;
import Ecom.Service.UserService;

@RestController
@RequestMapping("/veggi/admin")
public class AdminController {
	
	private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> addUser(@RequestBody AdminDTO user) {
        try {
            User addedUser = userService.addUserAdmin(user);
            return ResponseEntity.ok(addedUser);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/updatepassword/{adminId}")
    public ResponseEntity<User> updateUserPassword(@PathVariable("adminId") Integer customerId, @RequestBody UserDTO userdto) {
        try {
            User updatedUser = userService.updateUserPassword(customerId, userdto);
            return ResponseEntity.ok(updatedUser);
        } catch (UserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }


}
