package com.nobutnk.modelmapper.examples.nested.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.nobutnk.modelmapper.examples.nested.dest.NestedDestination;
import com.nobutnk.modelmapper.examples.nested.src.Account;
import com.nobutnk.modelmapper.examples.nested.src.Item;
import com.nobutnk.modelmapper.examples.nested.src.NestedSource;
import com.nobutnk.modelmapper.examples.nested.src.Order;

public class NestedMapperTest {

    @Test
    public void testMapToDestination() {
        
        Account account1 = new Account("name1", Integer.valueOf(20));
        Item item1 = new Item("item1", Integer.valueOf(100));
        Item item2 = new Item("item2", Integer.valueOf(100));
        List<Item> items = new ArrayList<>();
        items.add(item1);
        items.add(item2);
        Order order1 = new Order(account1, items);
        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        NestedSource source = new NestedSource(orders);
        
        NestedMapper mapper = new NestedMapper();
        NestedDestination destination = mapper.mapToDto(source);
        
        // assert
        assertThat(destination.getWrapper().getOrders().get(0).getAccount().getName(),
                is(source.getOrders().get(0).getAccount().getName()));
        assertThat(destination.getWrapper().getOrders().get(0).getAccount().getAge(),
                is(source.getOrders().get(0).getAccount().getAge()));
        assertThat(destination.getWrapper().getOrders().get(0).getItems(),
                is(nullValue()));
    }
}
