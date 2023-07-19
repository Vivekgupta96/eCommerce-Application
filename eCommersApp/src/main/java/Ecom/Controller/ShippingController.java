package Ecom.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Ecom.Exception.ShippingException;
import Ecom.Model.ShippingDetails;
import Ecom.ModelDTO.ShippingDTO;
import Ecom.Service.ShippingService;

@RestController
@RequestMapping("/shipping")
public class ShippingController {

    private final ShippingService shippingService;

    @Autowired
    public ShippingController(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    @PostMapping("/{orderId}")
    public ResponseEntity<ShippingDetails> setShippingDetails(@PathVariable Integer orderId,
                                                              @RequestBody ShippingDetails shippingDetails) {
        try {
            ShippingDetails savedShippingDetails = shippingService.setShippingDetails(orderId, shippingDetails);
            return new ResponseEntity<>(savedShippingDetails, HttpStatus.CREATED);
        } catch (ShippingException ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{shippingId}")
    public ResponseEntity<ShippingDetails> updateShippingAddress(@PathVariable Integer shippingId,
                                                                 @RequestBody ShippingDTO shippingDTO) {
        try {
            ShippingDetails updatedShippingDetails = shippingService.updateShippingAddress(shippingId, shippingDTO);
            return new ResponseEntity<>(updatedShippingDetails, HttpStatus.OK);
        } catch (ShippingException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{shippingId}")
    public ResponseEntity<Void> deleteShippingDetails(@PathVariable Integer shippingId) {
        try {
            shippingService.deleteShippingDetails(shippingId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ShippingException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

