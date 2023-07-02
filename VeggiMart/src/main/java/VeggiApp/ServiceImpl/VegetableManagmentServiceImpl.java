package VeggiApp.ServiceImpl;

import VeggiApp.Exception.VegetableManagmentException;
import VeggiApp.Model.Vegetables;
import VeggiApp.Repository.VegetableManagmentRepository;
import VeggiApp.Service.VegetableManagmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VegetableManagmentServiceImpl implements VegetableManagmentService {

    @Autowired
    private VegetableManagmentRepository vegetableRepository;

    @Override
    public Vegetables addVegetable(Vegetables vegetables) throws VegetableManagmentException {
        if(vegetables==null){
            throw new VegetableManagmentException("vegetable Can't be empty");
        }
        return vegetableRepository.save(vegetables);
    }

    @Override
    public Vegetables updateVegetableDetails(Integer vegetableId, Vegetables vegetables) throws VegetableManagmentException {
        Optional<Vegetables> exisVegetable = vegetableRepository.findById(vegetableId);

        if (exisVegetable.isEmpty()) {
            throw new VegetableManagmentException("Vegetable not found");
        }
         Vegetables existingVegetable=exisVegetable.get();
        existingVegetable.setVegName(vegetables.getVegName());
        existingVegetable.setPrice(vegetables.getPrice());
        existingVegetable.setAvailableQuantity(vegetables.getAvailableQuantity());
        // Update any other fields as needed
        return vegetableRepository.save(existingVegetable);
    }

    @Override
    public String deleteVegetables(Integer vegetableId) throws VegetableManagmentException {
        Optional<Vegetables> exisVegetable = vegetableRepository.findById(vegetableId);

        if (exisVegetable.isEmpty()) {
            throw new VegetableManagmentException("Vegetable not found");
        }
        Vegetables existingVeg = exisVegetable.get();
        vegetableRepository.delete(existingVeg);
        return "Removed Vegetable Successfully";
    }
    @Override
    public List<Vegetables> viewAllVegetableList() throws VegetableManagmentException {

       List<Vegetables> allVegetableList= vegetableRepository.findAll();
       if(allVegetableList.isEmpty()){
           throw new VegetableManagmentException("Vegetable not found");
       }
       return allVegetableList;
    }

    @Override
    public List<Vegetables> viewAllVegetableByName(String name) throws VegetableManagmentException {
        return null;
    }
}
