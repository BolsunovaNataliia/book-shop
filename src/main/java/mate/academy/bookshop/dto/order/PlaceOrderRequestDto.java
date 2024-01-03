package mate.academy.bookshop.dto.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class PlaceOrderRequestDto {
    @NotNull
    @Size(max = 255)
    private String shippingAddress;
}
