package Ecom.Controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import Ecom.Model.Product;
import Ecom.ModelDTO.ProductDTO;
import Ecom.Service.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/ecom/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) {
        Product newProduct = productService.addProduct(product);
        return new ResponseEntity<>(newProduct, HttpStatus.CREATED);
    }

    @PutMapping("/update/{productId}")
    public ResponseEntity<Product> updateProduct( @PathVariable Integer productId, @Valid @RequestBody ProductDTO updatedProduct) {
        Product updatedProductResult = productService.updateProduct(productId, updatedProduct);
        return new ResponseEntity<>(updatedProductResult, HttpStatus.OK);
    }

    @GetMapping("/product-By-name/{name}")
    public ResponseEntity<List<Product>> getProductByName(@PathVariable String name) {
        List<Product> products = productService.getProductByName(name);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Product>> search(
       @RequestParam (required = false)  String keyword,
       @RequestParam(required = false,defaultValue = "asc") String sort,
       @RequestParam(required = false,defaultValue = "price") String sortBy

    ) {
        List<Product> products = productService.getAllProduct(keyword, sort, sortBy);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }



    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> getProductByCategory(@PathVariable String category) {
        List<Product> products = productService.getProductByCategory(category);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }


    @GetMapping("/{productId}")
    public ResponseEntity<Product> getSingleProduct(@PathVariable Integer productId) {
        Product singleProsuct = productService.getSingleProduct(productId);
        return new ResponseEntity<>(singleProsuct, HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> removeProduct(@PathVariable Integer productId) {
        productService.removeProduct(productId);
        return new ResponseEntity<>("Product removed successfully.", HttpStatus.OK);
    }
}
