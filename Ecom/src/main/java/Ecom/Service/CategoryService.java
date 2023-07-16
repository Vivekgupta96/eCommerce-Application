package Ecom.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import Ecom.Exception.ProductException;
import Ecom.Model.Category;

@Service
public interface CategoryService {
	
	public Category addCategory(Category category) throws ProductException;
	
	public Category updateCategory(Integer categoryId, Category updatedCategory) throws ProductException;
	
	public void removeCategory(Integer categoryId)throws ProductException;
	
	public List<Category> getAllcategory()throws ProductException;
	
	
}
