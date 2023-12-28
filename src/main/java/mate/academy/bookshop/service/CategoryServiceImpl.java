package mate.academy.bookshop.service;

import java.util.Collections;
import java.util.List;
import mate.academy.bookshop.dto.category.CategoryDto;
import mate.academy.bookshop.model.Category;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public CategoryDto toDto(Category category) {
        return null;
    }

    @Override
    public Category toEntity(CategoryDto categoryDTO) {
        return null;
    }

    List findAll() {
        return Collections.emptyList();
    }

    CategoryDto getById(Long id) {
        return null;
    }

    CategoryDto save(CategoryDto categoryDto) {
        return null;
    }

    CategoryDto update(Long id, CategoryDto categoryDto) {
        return null;
    }

    void deleteById(Long id) {

    }
}
