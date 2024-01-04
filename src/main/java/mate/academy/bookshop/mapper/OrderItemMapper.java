package mate.academy.bookshop.mapper;

import java.util.Set;
import mate.academy.bookshop.config.MapperConfig;
import mate.academy.bookshop.dto.order.OrderItemDto;
import mate.academy.bookshop.model.OrderItem;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(config = MapperConfig.class)
public interface OrderItemMapper {
    @Mapping(target = "bookId", source = "book.id")
    @Named("toDto")
    OrderItemDto toDto(OrderItem orderItem);

    @IterableMapping(qualifiedByName = "toDto")
    Set<OrderItemDto> toDtoSet(Set<OrderItem> orderItems);
}
