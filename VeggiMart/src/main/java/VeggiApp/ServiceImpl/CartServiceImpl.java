package VeggiApp.ServiceImpl;

import VeggiApp.Exception.CartException;
import VeggiApp.Model.Vegetables;
import VeggiApp.Repository.CartRepository;
import VeggiApp.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;


    @Override
    public String addVegetableToCart(Vegetables vegetables) throws CartException {
        return null;
    }

    @Override
    public void increseVegetableQuantity(Integer vegetableId, Integer qty) throws CartException {

    }

    @Override
    public void dercreseVegetableQuantity(Integer vegetableId, Integer qty) throws CartException {

    }

    @Override
    public String removeVegetableFromCart(Integer vegetableId) throws CartException {
        return null;
    }

    @Override
    public List<Vegetables> getAllCartUtem() throws CartException {
        return null;
    }
}
