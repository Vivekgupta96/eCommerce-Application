package VeggiApp.Contoller;

import VeggiApp.Exception.VegetableManagmentException;
import VeggiApp.Model.Vegetables;
import VeggiApp.Service.VegetableManagmentService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/vegetables")
public class VegetableController {

    private VegetableManagmentService vegetableManagmentService;

    public VegetableController(VegetableManagmentService vegetableManagmentService) {
        this.vegetableManagmentService = vegetableManagmentService;
    }

    @PostMapping
    public ResponseEntity<Vegetables> addVegetable(@RequestBody Vegetables vegetables) {
        try {
            Vegetables addedVegetable = vegetableManagmentService.addVegetable(vegetables);
            return new ResponseEntity<>(addedVegetable, HttpStatus.CREATED);
        } catch (VegetableManagmentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{vegetableId}")
    public ResponseEntity<Vegetables> updateVegetableDetails(
            @PathVariable("vegetableId") Integer vegetableId,
            @RequestBody Vegetables vegetables) {
        try {
            Vegetables updatedVegetable = vegetableManagmentService.updateVegetableDetails(vegetableId, vegetables);
            return new ResponseEntity<>(updatedVegetable, HttpStatus.OK);
        } catch (VegetableManagmentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{vegetableId}")
    public ResponseEntity<String> deleteVegetable(@PathVariable("vegetableId") Integer vegetableId) {
        try {
            String result = vegetableManagmentService.deleteVegetables(vegetableId);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (VegetableManagmentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Vegetables>> viewAllVegetableList() {
        try {
            List<Vegetables> vegetableList = vegetableManagmentService.viewAllVegetableList();
            return new ResponseEntity<>(vegetableList, HttpStatus.OK);
        } catch (VegetableManagmentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{name}")
    public ResponseEntity<List<Vegetables>> viewAllVegetableByName(@PathVariable("name") String name) {
        try {
            List<Vegetables> vegetableList = vegetableManagmentService.viewAllVegetableByName(name);
            return new ResponseEntity<>(vegetableList, HttpStatus.OK);
        } catch (VegetableManagmentException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
