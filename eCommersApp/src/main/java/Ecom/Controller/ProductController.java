package Ecom.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Ecom.Exception.ProductException;
import Ecom.Model.Product;
import Ecom.ModelDTO.ProductDTO;
import Ecom.Service.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ecom/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        try {
            Product newProduct = productService.addProduct(product);
            return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
        } catch (ProductException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<Product> updateProduct(@Valid @PathVariable Integer productId, @RequestBody ProductDTO updatedProduct) {
        try {
            Product updatedProductResult = productService.updateProduct(productId, updatedProduct);
            return new ResponseEntity<>(updatedProductResult, HttpStatus.OK);
        } catch (ProductException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/product-By-name/{name}")
    public ResponseEntity<List<Product>> getProductByName(@PathVariable String name) {
        try {
            List<Product> products = productService.getProductByName(name);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (ProductException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = productService.getAllProduct();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (ProductException e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String category) {
        try {
            List<Product> products = productService.getProductByCategory(category);
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (ProductException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> removeProduct(@PathVariable Integer productId) {
        try {
            productService.removeProduct(productId);
            return new ResponseEntity<>("Product removed successfully.", HttpStatus.OK);
        } catch (ProductException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
