package VeggiApp.Service;

import VeggiApp.Exception.VegetableManagmentException;
import VeggiApp.Model.Vegetables;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VegetableManagmentService  {

    public Vegetables addVegetable(Vegetables vegetables) throws VegetableManagmentException;

    public  Vegetables updateVegetableDetails(Integer vegetableId,Vegetables vegetables)throws VegetableManagmentException;

    public String deleteVegetables(Integer vegetableId )throws VegetableManagmentException;

    public List<Vegetables> viewAllVegetableList()throws VegetableManagmentException;

    public List<Vegetables> viewAllVegetableByName(String name)throws VegetableManagmentException;

}
