package mate.academy.bookshop.mapper;

import mate.academy.bookshop.config.MapperConfig;
import mate.academy.bookshop.dto.book.BookDto;
import mate.academy.bookshop.dto.book.CreateBookRequestDto;
import mate.academy.bookshop.dto.category.BookDtoWithoutCategoryIds;
import mate.academy.bookshop.model.Book;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.web.bind.annotation.GetMapping;

@Mapper(config = MapperConfig.class)
public interface BookMapper {
    BookDto toDto(Book book);

    Book toModel(CreateBookRequestDto requestDto);
    // Book toEntity(CreateBookRequestDto bookDto);

    @GetMapping("/{id}/books")
    BookDtoWithoutCategoryIds toDtoWithoutCategories(Book book);
//    (HINT: BookDtoWithoutCategoryIds class could be used
//            as a response class for @GetMapping("/{id}/books") endpoint)

//    @AfterMapping
//    default void setCategoryIds(@MappingTarget BookDto bookDto, Book book);
}
