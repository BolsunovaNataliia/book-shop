package mate.academy.bookshop.service;

import mate.academy.bookshop.dto.category.CategoryDto;
import mate.academy.bookshop.model.Category;

public interface CategoryService {
    CategoryDto toDto(Category category);

    Category toEntity(CategoryDto categoryDTO);
}
