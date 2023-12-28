package mate.academy.bookshop.controller;

import mate.academy.bookshop.dto.category.CategoryDto;
import mate.academy.bookshop.model.Category;

import java.util.Collections;
import java.util.List;

public class CategoryController {
    public CategoryDto createCategory(CategoryDto categoryDto) {
        return null;
    }

    public List<Category> getAll() {
        return Collections.emptyList();
    }

    public CategoryDto getCategoryById(Long id) {
        return null;
    }

    public CategoryDto updateCategory(Long id, CategoryDto categoryDto) {
        return null;
    }

    public void deleteCategory(Long id) {

    }

    // (endpoint: "/{id}/books")
    public List getBooksByCategoryId(Long id) {
        return Collections.emptyList();
    }
}
