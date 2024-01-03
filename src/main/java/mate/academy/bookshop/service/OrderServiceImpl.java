package mate.academy.bookshop.service;

import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import mate.academy.bookshop.dto.order.OrderDto;
import mate.academy.bookshop.dto.order.OrderItemDto;
import mate.academy.bookshop.dto.order.PlaceOrderRequestDto;
import mate.academy.bookshop.dto.order.UpdateStatusRequestDto;
import mate.academy.bookshop.exception.EntityNotFoundException;
import mate.academy.bookshop.mapper.OrderItemMapper;
import mate.academy.bookshop.mapper.OrderMapper;
import mate.academy.bookshop.model.Book;
import mate.academy.bookshop.model.CartItem;
import mate.academy.bookshop.model.Order;
import mate.academy.bookshop.model.OrderItem;
import mate.academy.bookshop.model.ShoppingCart;
import mate.academy.bookshop.model.User;
import mate.academy.bookshop.repository.order.OrderRepository;
import mate.academy.bookshop.repository.orderitem.OrderItemRepository;
import mate.academy.bookshop.repository.shoppingcart.ShoppingCartRepository;
import mate.academy.bookshop.repository.user.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderItemRepository orderItemRepository;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;

    @Override
    @Transactional
    public OrderDto placeOrder(Long userId, PlaceOrderRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "There is not found user with id " + userId));
        ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                "There is not found shopping cart for user with id " + userId));
        Order order = new Order();
        order.setUser(user);
        order.setStatus(Order.Status.PENDING);
        Set<OrderItem> orderItems = new HashSet<>();
        Set<CartItem> cartItems = shoppingCart.getCartItems();
        if (cartItems.isEmpty()) {
            throw new EntityNotFoundException(
                    "There is not found any cart items in the shopping cart of user with id "
                            + userId);
        }
        BigDecimal total = new BigDecimal(0);
        for (CartItem cartItem: cartItems) {
            Book book = cartItem.getBook();
            int quantity = cartItem.getQuantity();
            BigDecimal price = cartItem.getBook().getPrice();
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setBook(book);
            orderItem.setPrice(price);
            orderItem.setQuantity(quantity);
            orderItems.add(orderItem);
            total.add(price.multiply(BigDecimal.valueOf(quantity)));
            cartItem.setDeleted(true);
        }
        order.setTotal(total);
        order.setOrderDate(LocalDateTime.now());
        order.setShippingAddress(requestDto.getShippingAddress());
        order.setOrderItems(orderItems);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDto(savedOrder);
    }

    @Override
    public List<OrderDto> getOrdersHistory(Long userId) {
        return orderRepository.findAllByUserId(userId).stream()
                .map(orderMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public OrderDto updateStatusOrder(Long userId, Long orderId,
                                          UpdateStatusRequestDto statusRequestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "There is not found user with id " + userId));
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException(
                        "There is not found order with id " + orderId));
        if (!order.getUser().equals(user)) {
            throw new RuntimeException("For this user absent order with id " + orderId);
        }
        order.setStatus(statusRequestDto.getStatus());
        return orderMapper.toDto(order);
    }

    @Override
    public Set<OrderItemDto> getAllOrderItems(Long userId, Long orderId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "There is not found user with id " + userId));
        Order order = orderRepository.findById(orderId).orElseThrow(
                () -> new EntityNotFoundException(
                        "There is not found order with id " + orderId));
        if (!order.getUser().equals(user)) {
            throw new RuntimeException("For this user absent order with id " + orderId);
        }
        Set<OrderItem> orderItems = order.getOrderItems();
        Set<OrderItemDto> orderItemDtos = new HashSet<>();
        for (OrderItem item: orderItems) {
            orderItemDtos.add(orderItemMapper.toDto(item));
        }
        return orderItemDtos;
    }

    @Override
    public OrderItemDto getOrderItemById(Long userId, Long orderItemId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException(
                        "There is not found user with id " + userId));
        OrderItem orderItem = orderItemRepository.findById(orderItemId).orElseThrow(
                () -> new EntityNotFoundException(
                        "There is not found order item with id " + orderItemId));
        if (orderItem.getOrder().getUser().equals(user)) {
            throw new RuntimeException("For this user absent order item with id " + orderItemId);
        }
        return orderItemMapper.toDto(orderItem);
    }
}
