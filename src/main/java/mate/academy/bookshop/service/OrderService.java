package mate.academy.bookshop.service;

import java.util.List;
import java.util.Set;
import mate.academy.bookshop.dto.order.OrderDto;
import mate.academy.bookshop.dto.order.OrderItemDto;
import mate.academy.bookshop.dto.order.PlaceOrderRequestDto;
import mate.academy.bookshop.dto.order.UpdateStatusRequestDto;

public interface OrderService {
    OrderDto placeOrder(Long userId, PlaceOrderRequestDto requestDto);

    List<OrderDto> getOrdersHistory(Long userId);

    OrderDto updateStatusOrder(Long userId, Long orderId, UpdateStatusRequestDto statusRequestDto);

    Set<OrderItemDto> getAllOrderItems(Long userId, Long orderId);

    OrderItemDto getOrderItemById(Long userId, Long orderItemId);
}
