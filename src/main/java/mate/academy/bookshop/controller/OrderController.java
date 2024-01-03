package mate.academy.bookshop.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.bookshop.dto.order.OrderDto;
import mate.academy.bookshop.dto.order.OrderItemDto;
import mate.academy.bookshop.dto.order.PlaceOrderRequestDto;
import mate.academy.bookshop.dto.order.UpdateStatusRequestDto;
import mate.academy.bookshop.model.User;
import mate.academy.bookshop.service.OrderService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order management",
        description = "Endpoints for order management")
@RequiredArgsConstructor
@RestController
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @PostMapping
    @Operation(summary = "Place user's order by shipping address",
            description = "Place user's order by shipping address")
    public OrderDto placeOrder(Authentication authentication,
                               @RequestBody @Valid PlaceOrderRequestDto requestDto) {
        User user = (User) authentication.getPrincipal();
        return orderService.placeOrder(user.getId(), requestDto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    @Operation(summary = "Get an order history",
            description = "Retrieve a user's order history")
    public List<OrderDto> getOrdersHistory(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return orderService.getOrdersHistory(user.getId());
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Update order's status by order id",
            description = "Update order's status by order id")
    OrderDto updateStatusOrder(Authentication authentication,
                               Long orderId,
                               UpdateStatusRequestDto statusRequestDto) {
        User user = (User) authentication.getPrincipal();
        return orderService.updateStatusOrder(user.getId(), orderId, statusRequestDto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/{orderId}/items")
    @Operation(summary = "Get all order items",
            description = "Get all order items")
    public Set<OrderItemDto> getAllOrderItems(Authentication authentication, Long orderId) {
        User user = (User) authentication.getPrincipal();
        return orderService.getAllOrderItems(user.getId(), orderId);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/{orderId}/items/{itemId}")
    @Operation(summary = "Get order item by id",
            description = "Get order item by id")
    OrderItemDto getOrderItemById(Authentication authentication, Long orderItemId) {
        User user = (User) authentication.getPrincipal();
        return orderService.getOrderItemById(user.getId(), orderItemId);
    }
}
