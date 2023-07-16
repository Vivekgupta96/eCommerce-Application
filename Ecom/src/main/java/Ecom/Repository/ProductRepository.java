package Ecom.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Ecom.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query("SELECT p FROM Product p  WHERE p.name like %:prduct%")
	public Optional<List<Product>> findByName(@Param("prduct") String name);
	
	@Query("SELECT p FROM Product p  WHERE p.category like %:cat%")
	public List<Product> getProductCategoryName(@Param("cat") String category);
	
//	@Query(value = "SELECT p FROM Product p JOIN Category c ON p.category_id = c.category_id WHERE c.name = :cat", nativeQuery = true)
//	public List<Product> getProductCategoryName(@Param("cat") String category);

	

}
