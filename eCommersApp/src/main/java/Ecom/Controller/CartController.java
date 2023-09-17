package Ecom.Controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import Ecom.Model.Cart;
import Ecom.Model.Product;
import Ecom.Service.CartService;

@RestController
@RequestMapping("/ecom/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping("/add-product")
    public ResponseEntity<Cart> addProductToCart(@RequestParam Integer userId, @RequestParam Integer productId) {
        Cart cart = cartService.addProductToCart(userId, productId);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);

    }

    @PutMapping("/increase-productQty/{cartId}/{productId}")
    public ResponseEntity<Cart> increaseProductQuantity(
            @PathVariable Integer cartId,
            @PathVariable Integer productId

    ) {
        Cart cart = cartService.increaseProductQuantity(cartId, productId);
        return ResponseEntity.ok(cart);
    }

    @PutMapping("/decrease-productQty/{cartId}/{productId}")
    public ResponseEntity<Cart> decreaseProductQuantity(
            @PathVariable Integer cartId,
            @PathVariable Integer productId

    ) {
        Cart cart = cartService.decreaseProductQuantity(cartId, productId);
        return ResponseEntity.ok(cart);
    }

    @DeleteMapping("/remove-product/{cartId}/{productId}")
    public ResponseEntity<String> removeProductFromCart(@PathVariable Integer cartId, @PathVariable Integer productId) {
        cartService.removeProductFromCart(cartId, productId);
        String msg = "Prodcut is removed from cart";
        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }

    @DeleteMapping("/empty-Cart/{cartId}")
    public ResponseEntity<String> removeAllProductFromCart(@PathVariable Integer cartId) {
        cartService.removeAllProductFromCart(cartId);
        String msg = "All product Remove From cart";
        return new ResponseEntity<String>(msg, HttpStatus.OK);
    }

    @GetMapping("/products/{cartId}")
    public ResponseEntity<Cart> getAllCartProducts(@PathVariable Integer cartId) {
        Cart products = cartService.getAllCartProduct(cartId);
        return ResponseEntity.ok(products);
    }
}
