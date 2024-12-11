package org.XpenseTracks.services;

import org.XpenseTracks.data.model.CategoryType;
import org.XpenseTracks.data.repository.CategoryRepo;
import org.XpenseTracks.dtos.request.CategoryRequest;
import org.XpenseTracks.dtos.response.CategoryResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class CategoryServicesImplTest {
    @Autowired
    private CategoryServicesImpl categoryServices;
    @Autowired
    private CategoryRepo categoryRepo;

    @BeforeEach
    public void setUp() {
        categoryRepo.deleteAll();
    }

    @Test
    public void testCreateCategory() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setBudgetId("1");
        categoryRequest.setCategoryType(CategoryType.EDUCATION);
        CategoryResponse categoryResponse = categoryServices.createCategory(categoryRequest);

        assertNotNull(categoryResponse);
//        assertEquals("1", categoryResponse.getBudgetId());
        assertEquals(CategoryType.EDUCATION, categoryResponse.getCategoryType());
    }

    @Test
    public void testGetCategory() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setBudgetId("1");
        categoryRequest.setCategoryType(CategoryType.EDUCATION);

        CategoryResponse createdCategory = categoryServices.createCategory(categoryRequest);
        CategoryResponse getCategory = categoryServices.getCatgoryById(createdCategory.getCategoryId());

        assertEquals(createdCategory.getCategoryId(), getCategory.getCategoryId());
        assertEquals(createdCategory.getCategoryType(), getCategory.getCategoryType());
    }

    @Test
    public void testUpdateCategory() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setBudgetId("1");
        categoryRequest.setCategoryType(CategoryType.EDUCATION);
        CategoryResponse categoryResponse = categoryServices.createCategory(categoryRequest);

        CategoryRequest updateCategory = new CategoryRequest();
        updateCategory.setBudgetId("2");
        updateCategory.setCategoryType(CategoryType.GROCERIES);
        CategoryResponse updatedCategoryResponse = categoryServices.updateCategory(categoryResponse.getCategoryId(), updateCategory);
        assertEquals(categoryResponse.getCategoryId(), updatedCategoryResponse.getCategoryId());
        assertEquals(CategoryType.GROCERIES, updatedCategoryResponse.getCategoryType());
//        assertEquals("2", updatedCategoryResponse.getCategoryId());
    }

    @Test
    public void testDeleteCategory(){
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setBudgetId("1");
        categoryRequest.setCategoryType(CategoryType.EDUCATION);

        CategoryResponse categoryResponse = categoryServices.createCategory(categoryRequest);
        categoryServices.deleteCategory(categoryResponse.getCategoryId());
        assertFalse(categoryRepo.existsById(categoryResponse.getCategoryId()));
    }

    @Test
    public void testGetAllCategories() {
        CategoryRequest categoryRequest = new CategoryRequest();
        categoryRequest.setBudgetId("1");
        categoryRequest.setCategoryType(CategoryType.EDUCATION);
        CategoryResponse categoryResponse = categoryServices.createCategory(categoryRequest);

        CategoryRequest categoryRequest2 = new CategoryRequest();
        categoryRequest2.setBudgetId("2");
        categoryRequest2.setCategoryType(CategoryType.GROCERIES);
        CategoryResponse categoryResponse2 = categoryServices.createCategory(categoryRequest2);

        List<CategoryResponse> getCategories = categoryServices.getAllCategories();
        assertEquals(2, getCategories.size());
    }
}