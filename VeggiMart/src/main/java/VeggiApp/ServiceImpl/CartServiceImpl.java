package VeggiApp.ServiceImpl;

import VeggiApp.Exception.CartException;
import VeggiApp.Model.Cart;
import VeggiApp.Model.Customer;
import VeggiApp.Model.Vegetables;
import VeggiApp.Repository.CartRepository;
import VeggiApp.Repository.CustomerRepository;
import VeggiApp.Repository.VegetableManagmentRepository;
import VeggiApp.Service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private VegetableManagmentRepository vegetableRepository;



    @Override
    public String addVegetableToCart(Integer userId,Vegetables vegetables) throws CartException {
        Cart cart = cartRepository.findByUserId(userId);
        if (cart == null) {
            Optional<Customer> existingCustomer=customerRepository.findById(userId);
            if(existingCustomer.isEmpty()){
                throw  new CartException("Costomer Not found");
            }
            Customer findCustomet=existingCustomer.get();
            // If the cart doesn't exist, create a new one for the user
           cart= new Cart();
            cart.setCustomer(findCustomet);
        }

        // Check if the vegetable already exists in the cart

      Cart existingVegInCart=cartRepository.findByVegId(vegetables.getVegetableId());

        if (existingVegInCart==null) {
            existingVegInCart.getVegetables().add(vegetables);

        }

        // Save the updated cart to the database
        cartRepository.save(existingVegInCart);

        return "Added Successfully";
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
