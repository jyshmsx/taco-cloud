package com.jysh.tacocs.data;

import com.jysh.tacocs.Order;

public interface OrderRepository {
    Order save(Order order);
}
