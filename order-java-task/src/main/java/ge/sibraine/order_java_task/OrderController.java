package ge.sibraine.order_java_task;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order getOrderDetails(@PathVariable Long id) {
        return orderService.getOrderDetails(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long placeOrder(@RequestBody @Valid OrderRequest orderDetails) {
        Order placedOrder = orderService.placeOrder(orderDetails);
        return placedOrder.getId();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Order updateOrder(@PathVariable Long id, @RequestBody @Valid OrderRequest orderDetails) {
        return orderService.updateOrder(id, orderDetails);
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> removeOrder(@PathVariable Long id) {
        return orderService.removeOrder(id);
    }

}
