package Ecom.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Ecom.Exception.ProductException;
import Ecom.Model.Category;
import Ecom.Repository.CategoryRepository;
import Ecom.Service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	private final CategoryRepository categoryRepository;

	@Autowired
	public CategoryServiceImpl(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	@Override
	public Category addCategory(Category category) throws ProductException {
		if (category == null)
			throw new ProductException("Category can be Null");
		return categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Integer categoryId, Category updatedCategory) throws ProductException {

		Category existingCategory = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ProductException("Category with ID " + categoryId + " not found."));

		existingCategory.setName(updatedCategory.getName());

		return categoryRepository.save(existingCategory);
	}

	@Override
	public void removeCategory(Integer categoryId) throws ProductException {

		Category existingCategory = categoryRepository.findById(categoryId)
				.orElseThrow(() -> new ProductException("Category with ID " + categoryId + " not found."));

		categoryRepository.delete(existingCategory);
	}

	@Override
	public List<Category> getAllcategory() throws ProductException {
		
		List<Category> allCategories = categoryRepository.findAll();
		if (allCategories.isEmpty()) {
			throw new ProductException("Category List Empty");
		}
		return allCategories;
	}
}
