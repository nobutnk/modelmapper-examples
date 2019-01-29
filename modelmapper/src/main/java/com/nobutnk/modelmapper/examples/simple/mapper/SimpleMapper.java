package com.nobutnk.modelmapper.examples.simple.mapper;

import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;

import com.nobutnk.modelmapper.examples.simple.dest.OrderDTO;
import com.nobutnk.modelmapper.examples.simple.src.Order;

public class SimpleMapper {

    public OrderDTO mapToDto(Order order) {
        ModelMapper modelMapper = new ModelMapper();
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        return orderDTO;
    }

    public Order mapToOrder(OrderDTO orderDto) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        Condition<String, String> conditionStringIsNotTom = new Condition<String, String>() {
            public boolean applies(MappingContext<String, String> context) {
                return !"Tom".equals(context.getSource());
            }
        };
        
        PropertyMap<OrderDTO, Order> orderMap = new PropertyMap<OrderDTO, Order>() {
            protected void configure() {
                map().getBillingAddress().setStreet(source.getBillingStreet());
                map().getBillingAddress().setCity(source.getBillingCity());
                map().getCustomer().getName().setFirstName(source.getCustomerFirstName());
                when(conditionStringIsNotTom).map().getCustomer().getName().setLastName(source.getCustomerLastName());
            }
        };

        modelMapper.addMappings(orderMap);
        Order order = modelMapper.map(orderDto, Order.class);
        return order;
    }
}
