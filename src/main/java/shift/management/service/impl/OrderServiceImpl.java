package shift.management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import shift.management.entity.Order;
import shift.management.repository.OrderRepository;
import shift.management.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	@Autowired
	private OrderRepository orderRepository;
	
	@Override
	public List<Order> getAll() {
		return orderRepository.findAll();
	}

}
