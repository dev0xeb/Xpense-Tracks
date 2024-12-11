package org.XpenseTracks.services;

import org.XpenseTracks.data.model.Category;
import org.XpenseTracks.dtos.request.CategoryRequest;
import org.XpenseTracks.dtos.response.CategoryResponse;

import java.util.List;

public interface CategoryServices {
    CategoryResponse createCategory(CategoryRequest categoryRequest);
    CategoryResponse getCatgoryById(String CategoryId);
    CategoryResponse updateCategory(String categoryId, CategoryRequest categoryRequest);
    void deleteCategory(String categoryId);
    List<CategoryResponse> getAllCategories();
}
