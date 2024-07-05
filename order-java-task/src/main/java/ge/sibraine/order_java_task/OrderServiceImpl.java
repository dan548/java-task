package ge.sibraine.order_java_task;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order getOrderDetails(long orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));
    }

    @Override
    public Order placeOrder(OrderRequest orderRequest) {
        Order order = Order.builder()
                .userId(orderRequest.getUserId())
                .product(orderRequest.getProduct())
                .quantity(orderRequest.getQuantity())
                .price(orderRequest.getPrice())
                .status("Created")
                .build();
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(long orderId, OrderRequest orderDetails) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));

        order.setProduct(orderDetails.getProduct());
        order.setQuantity(orderDetails.getQuantity());
        order.setPrice(orderDetails.getPrice());
        order.setStatus("Updated");
        return orderRepository.save(order);
    }

    @Override
    public Map<String, Boolean> removeOrder(long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found for this id :: " + orderId));

        orderRepository.delete(order);
        return Map.of("deleted", Boolean.TRUE);
    }
}
