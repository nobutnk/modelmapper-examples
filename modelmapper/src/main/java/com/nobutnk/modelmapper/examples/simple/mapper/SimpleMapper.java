package com.nobutnk.modelmapper.examples.simple.mapper;

import org.modelmapper.ModelMapper;

import com.nobutnk.modelmapper.examples.simple.dest.OrderDTO;
import com.nobutnk.modelmapper.examples.simple.src.Order;

public class SimpleMapper {

    public OrderDTO mapToDto(Order order) {
        ModelMapper modelMapper = new ModelMapper();
        OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
        return orderDTO;
    }
}
