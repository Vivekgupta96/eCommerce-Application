package VeggiApp.Service;

import VeggiApp.Exception.CartException;
import VeggiApp.Model.Vegetables;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CartService {
    public String addVegetableToCart(Vegetables vegetables) throws CartException;

    public void increseVegetableQuantity(Integer vegetableId,Integer qty)throws CartException;

    public void dercreseVegetableQuantity(Integer vegetableId,Integer qty)throws CartException;

    public String removeVegetableFromCart(Integer vegetableId)throws CartException;

    public List<Vegetables> getAllCartUtem()throws CartException;

}
