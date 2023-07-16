package Ecom.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import Ecom.Model.Category;
import Ecom.Model.Product;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
 
	public Optional<Category> findByName(String name);
}
