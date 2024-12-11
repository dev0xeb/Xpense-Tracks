package org.XpenseTracks.services;

import org.XpenseTracks.data.model.Category;
import org.XpenseTracks.data.repository.CategoryRepo;
import org.XpenseTracks.dtos.request.CategoryRequest;
import org.XpenseTracks.dtos.response.CategoryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServicesImpl implements CategoryServices {

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public CategoryResponse createCategory(CategoryRequest categoryRequest) {
        if(categoryRequest.getBudgetId() == null || categoryRequest.getBudgetId().isEmpty()){
            throw new IllegalArgumentException("Category budget id cannot be empty");
        }
        if(categoryRequest.getCategoryType() == null){
            throw new IllegalArgumentException("Invalid category type");
        }
        Category category = new Category();
        category.setCategoryId(categoryRequest.getBudgetId());
        category.setCategoryType(categoryRequest.getCategoryType());
        Category savedCategory = categoryRepo.save(category);

        CategoryResponse categoryResponse = new CategoryResponse();
        categoryResponse.setCategoryId(savedCategory.getCategoryId());
        categoryResponse.setBudgetId(savedCategory.getBudgetId());
        categoryResponse.setCategoryType(savedCategory.getCategoryType());
        categoryResponse.setMessage("Created successfully");
        return categoryResponse;
    }

    @Override
    public CategoryResponse getCatgoryById(String CategoryId) {
        Optional<Category> getCategory = categoryRepo.findById(CategoryId);
        if (getCategory.isPresent()) {
            Category category = getCategory.get();
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setCategoryId(category.getCategoryId());
            categoryResponse.setBudgetId(category.getBudgetId());
            categoryResponse.setCategoryType(category.getCategoryType());
            categoryResponse.setMessage("Category found");
            return categoryResponse;
        } else {
            throw new IllegalArgumentException("Category not found");
        }
    }

    @Override
    public CategoryResponse updateCategory(String categoryId, CategoryRequest categoryRequest) {
        Optional<Category> getCategoryId = categoryRepo.findById(categoryId);
        if (getCategoryId.isPresent()) {
            Category category = getCategoryId.get();
            category.setBudgetId(categoryRequest.getBudgetId());
            category.setCategoryType(categoryRequest.getCategoryType());
            Category savedCategory = categoryRepo.save(category);

            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setCategoryId(savedCategory.getCategoryId());
            categoryResponse.setBudgetId(savedCategory.getBudgetId());
            categoryResponse.setCategoryType(savedCategory.getCategoryType());
            categoryResponse.setMessage("Updated successfully");
            return categoryResponse;
        } else {
            throw new IllegalArgumentException("Category not found");
        }
    }

    @Override
    public void deleteCategory(String categoryId) {
        if(categoryRepo.existsById(categoryId)){
            categoryRepo.deleteById(categoryId);
        } else{
            throw new IllegalArgumentException("Category not found");
        }
    }

    @Override
    public List<CategoryResponse> getAllCategories() {
        List<Category> findCategories = categoryRepo.findAll();
        return findCategories.stream().map(category -> {
            CategoryResponse categoryResponse = new CategoryResponse();
            categoryResponse.setCategoryId(category.getCategoryId());
            categoryResponse.setBudgetId(category.getBudgetId());
            categoryResponse.setCategoryType(category.getCategoryType());
            categoryResponse.setMessage("Category found");
            return categoryResponse;
        }).collect(Collectors.toList());
    }
}
