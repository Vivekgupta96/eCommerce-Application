package Ecom.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Ecom.Exception.ShipperException;
import Ecom.Model.Shipper;
import Ecom.Service.ShipperService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ecom/order-shippers")
public class ShipperController {

	private final ShipperService shipperService;

	@Autowired
	public ShipperController(ShipperService shipperService) {
		this.shipperService = shipperService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<Shipper> getShipperById(@PathVariable Integer id) {
		try {
			Shipper shipper = shipperService.getShipperById(id);
			return ResponseEntity.ok(shipper);
		} catch (ShipperException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@GetMapping
	public ResponseEntity<List<Shipper>> getAllShippers() {
		try {
			List<Shipper> shippers = shipperService.getAllShippers();
			return ResponseEntity.ok(shippers);
		} catch (ShipperException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<Shipper> saveShipper(@Valid @RequestBody Shipper shipper) {
		try {
			Shipper savedShipper = shipperService.saveShipper(shipper);
			return ResponseEntity.status(HttpStatus.CREATED).body(savedShipper);
		} catch (ShipperException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteShipperById(@PathVariable Integer id) {
		try {
			shipperService.deleteShipperById(id);
			return ResponseEntity.ok("Shipper with ID " + id + " successfully deleted.");
		} catch (ShipperException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Shipper with ID " + id + " not found.");
		}
	}
}
