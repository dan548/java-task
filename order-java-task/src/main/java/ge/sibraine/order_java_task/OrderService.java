package ge.sibraine.order_java_task;

import java.util.List;
import java.util.Map;

public interface OrderService {

    List<Order> getAllOrders();
    Order getOrderDetails(long orderId);
    Order placeOrder(OrderRequest orderRequest);
    Order updateOrder(long orderId, OrderRequest orderDetails);
    Map<String, Boolean> removeOrder(long orderId);


}
