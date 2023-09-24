package Ecom.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Ecom.Model.Product;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
	@Query("SELECT p FROM Product p  WHERE p.name like %:prduct%")
	public List<Product> findByName(@Param("prduct") String name);
	
	@Query("SELECT p FROM Product p  WHERE p.category like %:cat%")
	public List<Product> getProductCategoryName(@Param("cat") String category);

    List<Product> findAllByNameContainingIgnoreCase(String keyword, Sort sort);

//	@Query(value = "SELECT p FROM Product p JOIN Category c ON p.category_id = c.category_id WHERE c.name = :cat", nativeQuery = true)
//	public List<Product> getProductCategoryName(@Param("cat") String category);

	

}
