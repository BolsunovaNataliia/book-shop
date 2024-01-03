package mate.academy.bookshop.dto.order;

import lombok.Data;
import mate.academy.bookshop.model.Order;

@Data
public class UpdateStatusRequestDto {
    private Order.Status status;
}
