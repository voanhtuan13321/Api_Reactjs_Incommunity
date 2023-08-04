package fa.edu.api.controllers;

import fa.edu.api.entities.Order;
import fa.edu.api.services.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Order controller class.
 *
 * @author AnhTuan
 * @version 1.0
 * @since 04/08/2023
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/api/orders")
@Slf4j
@RequiredArgsConstructor
public class OrderController {
  private final OrderService orderService;

  /**
   * Get all the orders Is confirmed.
   *
   * @return list of orders
   */
  @GetMapping()
  public ResponseEntity<List<Order>> getAllOrdersIsConfirmed() {
    log.info("get All Orders Is Confirmed");
    List<Order> orders = orderService.findAllIsConfirmed(true);
    return ResponseEntity.ok().body(orders);
  }

  /**
   * Get all the orders Is not confirmed.
   *
   * @return list of orders
   */
  @GetMapping()
  public ResponseEntity<List<Order>> getAllOrdersIsNotConfirmed() {
    log.info("get All Orders Is Not Confirmed");
    List<Order> orders = orderService.findAllIsConfirmed(false);
    return ResponseEntity.ok().body(orders);
  }

  /**
   * Get all the orders by user id and Is confirmed.
   *
   * @return list of orders
   */
  @GetMapping(path = "/users/{userId}")
  public ResponseEntity<List<Order>> getAllOrdersByUserIdAndIsConfirmed(@PathVariable(name = "userId") Long userId) {
    log.info("get All Orders By UserId");
    List<Order> orders = orderService.findAllByUserIdAndIsConfirmed(userId, true);
    return ResponseEntity.ok().body(orders);
  }

  /**
   * Get all the orders by user id and Is not confirmed.
   *
   * @return list of orders
   */
  @GetMapping(path = "/users/{userId}")
  public ResponseEntity<List<Order>> getAllOrdersByUserIdAndIsNotConfirmed(@PathVariable(name = "userId") Long userId) {
    log.info("get All Orders By UserId");
    List<Order> orders = orderService.findAllByUserIdAndIsConfirmed(userId, false);
    return ResponseEntity.ok().body(orders);
  }
}