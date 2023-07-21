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

import Ecom.Exception.AddressException;
import Ecom.Exception.UserException;
import Ecom.Model.Address;
import Ecom.Service.AddressService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ecom/addresses")
public class AddressController {

    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Address> addAddressToUser(@Valid @PathVariable Integer userId, @RequestBody Address address) {
        try {
            Address addedAddress = addressService.addAddressToUser(userId, address);
            return ResponseEntity.ok(addedAddress);
        } catch (AddressException | UserException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<Address> updateAddress(@RequestBody Address address) {
        try {
            Address updatedAddress = addressService.updateAddress(address);
            return ResponseEntity.ok(updatedAddress);
        } catch (AddressException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<String> removeAddress(@PathVariable Integer addressId) {
        try {
            addressService.removeAddress(addressId);
            return ResponseEntity.ok("Address removed successfully");
        } catch (AddressException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
    
    @GetMapping("/{userId}")
    public ResponseEntity<List<Address>> getAllUserAddress(@PathVariable Integer userId) {
        try {
            List<Address> allUserAddress = addressService.getAllUserAddress(userId);
            return ResponseEntity.ok(allUserAddress);
        } catch (AddressException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

}

