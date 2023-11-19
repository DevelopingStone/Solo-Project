package personal.core.service;

import personal.core.order.Order;

public interface OrderService {

    Order createOrder(Long memberId, String itemName, int itemPrice);

}
