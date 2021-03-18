package com.jysh.tacos.data;

import com.jysh.tacos.Order;

public interface OrderRepository {
    Order save(Order order);
}
