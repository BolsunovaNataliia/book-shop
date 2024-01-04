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
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Order management",
        description = "Endpoints for user's order management")
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
    @Operation(summary = "Get user's order history",
            description = "Get user's order history")
    public List<OrderDto> getOrdersHistory(Authentication authentication,
                                           Pageable pageable) {
        User user = (User) authentication.getPrincipal();
        return orderService.getAll(user.getId(), pageable);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    @Operation(summary = "Update order status",
            description = "Update order status by order id")
    OrderDto updateOrderStatus(@PathVariable(name = "id") Long orderId,
                               @RequestBody @Valid UpdateStatusRequestDto statusRequestDto) {
        return orderService.updateStatusOrder(orderId, statusRequestDto);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/{orderId}/items")
    @Operation(summary = "Get all user's order items",
            description = "Get all user's order items for specific order")
    public Set<OrderItemDto> getAllOrderItems(Authentication authentication,
                                              @PathVariable Long orderId,
                                              Pageable pageable) {
        User user = (User) authentication.getPrincipal();
        return orderService.getAllOrderItems(user.getId(), orderId, pageable);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping("/{orderId}/items/{itemId}")
    @Operation(summary = "Get specific order item from order",
            description = "Get specific order item from order")
    OrderItemDto getItemById(Authentication authentication,
                             @PathVariable Long orderId,
                             @PathVariable Long itemId) {
        User user = (User) authentication.getPrincipal();
        return orderService.getItemById(user.getId(), orderId, itemId);
    }
}
