package Ecom.Controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("/ecom/customer-addresses")
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/{userId}")
    public ResponseEntity<Address> addAddressToUser(
            @PathVariable Integer userId, @Valid @RequestBody Address address) {
        Address addedAddress = addressService.addAddressToUser(userId, address);
        return new ResponseEntity<>(addedAddress, HttpStatus.CREATED);
    }

    @PutMapping("/update/{addressId}")
    public ResponseEntity<Address> updateAddress( @Valid @RequestBody Address address,@PathVariable Integer addressId) {
        Address updatedAddress = addressService.updateAddress(address,addressId);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/delete/{addressId}")
    public ResponseEntity<String> removeAddress(@PathVariable Integer addressId) {
        addressService.removeAddress(addressId);
        return ResponseEntity.ok("Address removed successfully");
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Address>> getAllUserAddress(@PathVariable Integer userId) {
        List<Address> allUserAddress = addressService.getAllUserAddress(userId);
        return ResponseEntity.ok(allUserAddress);
    }

}
