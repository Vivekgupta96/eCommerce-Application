package Ecom.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Ecom.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
