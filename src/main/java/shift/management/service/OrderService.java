package shift.management.service;

import java.util.List;

import shift.management.entity.Order;

public interface OrderService {
	List<Order> getAll();
	
	Order getOrderById(Long id);
	
	void createOrder(Order order);
	
	void editOrder(Long id, Order order);
	
	void deleteOrder(Long id);
}
