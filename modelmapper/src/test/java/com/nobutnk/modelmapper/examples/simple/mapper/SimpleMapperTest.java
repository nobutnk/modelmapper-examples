package com.nobutnk.modelmapper.examples.simple.mapper;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import com.nobutnk.modelmapper.examples.simple.dest.OrderDTO;
import com.nobutnk.modelmapper.examples.simple.src.Address;
import com.nobutnk.modelmapper.examples.simple.src.Customer;
import com.nobutnk.modelmapper.examples.simple.src.Name;
import com.nobutnk.modelmapper.examples.simple.src.Order;

public class SimpleMapperTest {

    @Test
    public void testMapToDto() {
        Address address = new Address("street", "city");
        Name name = new Name("first", "last");
        Customer customer = new Customer(name);
        Order order = new Order(customer, address);
        
        SimpleMapper mapper = new SimpleMapper();
        OrderDTO orderDTO = mapper.mapToDto(order);
        
        assertThat(orderDTO.getCustomerFirstName(),
                is(order.getCustomer().getName().getFirstName()));
        assertThat(orderDTO.getCustomerLastName(),
                is(order.getCustomer().getName().getLastName()));
        assertThat(orderDTO.getBillingStreet(),
                is(order.getBillingAddress().getStreet()));
        assertThat(orderDTO.getBillingCity(),
                is(order.getBillingAddress().getCity()));
    }
    
    @Test
    public void testMapToOrder() {
        OrderDTO orderDTO = new OrderDTO(
                "customerFirst", "customerLast",
                "billingStreet", "billingCity");
        
        SimpleMapper mapper = new SimpleMapper();
        Order order = mapper.mapToOrder(orderDTO);
        
        assertThat(order.getCustomer().getName().getFirstName(),
                is(orderDTO.getCustomerFirstName()));
        assertThat(order.getCustomer().getName().getLastName(),
                is(orderDTO.getCustomerLastName()));
        assertThat(order.getBillingAddress().getStreet(),
                is(orderDTO.getBillingStreet()));
        assertThat(order.getBillingAddress().getCity(),
                is(orderDTO.getBillingCity()));
    }
    
    @Test
    public void testMapToOrderWhenLastNameIsTomCase() {
        OrderDTO orderDTO = new OrderDTO(
                "customerFirst", "Tom",
                "billingStreet", "billingCity");
        
        SimpleMapper mapper = new SimpleMapper();
        Order order = mapper.mapToOrder(orderDTO);
        
        assertThat(order.getCustomer().getName().getFirstName(),
                is(orderDTO.getCustomerFirstName()));
        assertThat(order.getCustomer().getName().getLastName(),
                is(nullValue()));
        assertThat(order.getBillingAddress().getStreet(),
                is(orderDTO.getBillingStreet()));
        assertThat(order.getBillingAddress().getCity(),
                is(orderDTO.getBillingCity()));
    }
}
