package shift.management.service.impl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shift.management.entity.Order;
import shift.management.repository.OrderRepository;
import shift.management.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public List<Order> getAll() {
		return orderRepository.findAll();
	}

	@Override
	public Order getOrderById(Long id) {
		return orderRepository.findById(id).get();
	}

	@Override
	public void createOrder(Order order) {
		order.setCreatedDate(LocalDate.now());
		orderRepository.save(order);
	}

	@Override
	public void editOrder(Long id, Order orderReq) {
		Order order = orderRepository.findById(id).get();
		order.setOrderName(orderReq.getOrderName());
		order.setStartDate(orderReq.getStartDate());
		order.setEndDate(orderReq.getEndDate());
		order.setDescription(orderReq.getDescription());
		orderRepository.save(order);
	}

	@Override
	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}

}
