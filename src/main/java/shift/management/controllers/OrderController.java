package shift.management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import shift.management.entity.Order;
import shift.management.service.OrderService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@GetMapping("")
	public ResponseEntity<List<Order>> getAllOrder() {
		var response = orderService.getAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Order> getOrderById(@PathVariable("id") long id) {
		return new ResponseEntity<>(orderService.getOrderById(id), HttpStatus.OK);
	}

	@PostMapping("")
	public ResponseEntity<Void> createOrder(@RequestBody Order order) {
		orderService.createOrder(order);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Void> updateOrder(@PathVariable("id") long id, @RequestBody Order order) {
		orderService.editOrder(id, order);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> updateOrder(@PathVariable("id") long id) {
		orderService.deleteOrder(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
