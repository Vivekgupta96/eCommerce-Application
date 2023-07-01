package VeggiApp.Contoller;


import VeggiApp.Exception.CustomerAddressException;
import VeggiApp.Model.CustomerAddress;
import VeggiApp.Service.CustomerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customer-address")
public class CustomerAddressController {
    @Autowired
    private CustomerAddressService customerAddressService;

    @PostMapping("/{customerId}")
    public ResponseEntity<String> addCustomerAddress(
            @PathVariable Integer customerId,
            @RequestBody CustomerAddress customerAddress
    ) {
        try {
            String result = customerAddressService.addCustomerAddress(customerId, customerAddress);
            return ResponseEntity.ok(result);
        } catch (CustomerAddressException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{customerAddressId}")
    public ResponseEntity<CustomerAddress> editCustomerAddress(
            @PathVariable Integer customerAddressId,
            @RequestBody CustomerAddress customerAddress
    ) {
        try {
            CustomerAddress updatedAddress = customerAddressService.editCustomerAddress(customerAddressId, customerAddress);
            return ResponseEntity.ok(updatedAddress);
        } catch (CustomerAddressException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @DeleteMapping("/{customerAddressId}")
    public ResponseEntity<String> deleteCustomerAddress(
            @PathVariable Integer customerAddressId
    ) {
        try {
            String result = customerAddressService.deleteCustomerAddress(customerAddressId);
            return ResponseEntity.ok(result);
        } catch (CustomerAddressException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }
}

