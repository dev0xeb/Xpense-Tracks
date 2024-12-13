package org.XpenseTracks.controllers;

import org.XpenseTracks.dtos.request.CategoryRequest;
import org.XpenseTracks.dtos.response.CategoryResponse;
import org.XpenseTracks.services.CategoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryServices categoryServices;

    @PostMapping
    public ResponseEntity<CategoryResponse> createCategory(@RequestBody CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryServices.createCategory(categoryRequest);
        return ResponseEntity.ok(categoryResponse);
    }
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> getCategoryById(@RequestParam String id) {
        CategoryResponse categoryResponse = categoryServices.getCategoryById(id);
        if (categoryResponse == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(categoryResponse);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> updateCategory(@PathVariable String categoryId, @RequestBody CategoryRequest categoryRequest) {
        CategoryResponse categoryResponse = categoryServices.updateCategory(categoryId, categoryRequest);
        if (categoryResponse == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(categoryResponse);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryResponse> deleteCategory(@PathVariable String categoryId) {
        categoryServices.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getAllCategories() {
        List<CategoryResponse> categoryResponses = categoryServices.getAllCategories();
        return ResponseEntity.ok(categoryResponses);
    }
}
