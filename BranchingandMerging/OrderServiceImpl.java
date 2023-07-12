package com.homework.supplychainmgmt.service;

import com.homework.supplychainmgmt.dao.OrderRepository;
import com.homework.supplychainmgmt.model.Order;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order findOrderById(int id) throws DataAccessException {
        Order order = orderRepository.findOrderById(id);
        if (order == null){
            return null;
        }

        return order;
    }

    @Override
    public Collection<Order> findAllOrders() throws DataAccessException {
        Collection<Order>orders = new ArrayList<>();
        orders.addAll(orderRepository.findAll());
        if (orders.isEmpty()){
            return null;
        }
        return orders;
    }

    @Override
    public Collection<Order> findAllMyOrders(int userId) throws DataAccessException {
        return orderRepository.findOrdersByClientId(userId);
    }

    @Override
    public Collection<Order> findAllOrdersForMe(int userId) throws DataAccessException {
        return orderRepository.findOrdersByManufacturerId(userId);
    }

    @Override
    public Order addOrder(Order order) throws DataAccessException {
        return orderRepository.save(order);
    }

    @Override
    public void cancelOrder(int orderId) throws DataAccessException {
            orderRepository.deleteOrderById(orderId);
    }

    @Override
    public Order setOrderStatus(int id, String status) throws DataAccessException {
        Order order = orderRepository.findOrderById(id);
//            switch (order.getStatus()){
//                case "NEW":
//                    if (status.equals("ANALYSIS")){
//                        order.setStatus(status);
//                    }
//                    break;
//
//                case "ANALYSIS":
//                    if (status.equals("NEW")){
//                        order.setStatus(status);
//                    }
//                    else if (status.equals("IN PROGRESS")){
//                        order.setStatus(status);
//                    }
//                    break;
//
//                case "IN PROGRESS":
//                    if (status.equals("READY FOR DELIVERY")){
//                        order.setStatus(status);
//                    }
//                    break;
//            }
        if (order.getStatus().equals("NEW")){
            order.setStatus(status);
        }
            return order;
    }
}
