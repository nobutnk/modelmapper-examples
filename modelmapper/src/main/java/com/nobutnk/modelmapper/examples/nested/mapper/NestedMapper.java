package com.nobutnk.modelmapper.examples.nested.mapper;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;

import com.nobutnk.modelmapper.examples.nested.dest.NestedDestination;
import com.nobutnk.modelmapper.examples.nested.dest.Wrapper;
import com.nobutnk.modelmapper.examples.nested.src.Account;
import com.nobutnk.modelmapper.examples.nested.src.NestedSource;
import com.nobutnk.modelmapper.examples.nested.src.Order;

public class NestedMapper {

    public NestedDestination mapToDto(NestedSource source) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        modelMapper.addConverter(new NestedConverter());
        NestedDestination destination = modelMapper.map(source, NestedDestination.class);
        return destination;
    }
    
    public void maptoDto(NestedSource source, NestedDestination destination) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.addConverter(new NestedConverter());
        
        modelMapper.map(source, destination);
    }
    
    /**
     * Converter
     * @author nobutnk
     *
     */
    protected class NestedConverter implements Converter<NestedSource, NestedDestination>  {
        
        public NestedDestination convert(MappingContext<NestedSource, NestedDestination> context) {
            NestedSource source = context.getSource();
            NestedDestination destination = context.getDestination();
            if (destination == null) {
                destination = new NestedDestination();
                Wrapper wrapper = new Wrapper();
                destination.setWrapper(wrapper);
            }

            List<Order> sourceOrders = source.getOrders();

            if (sourceOrders != null && sourceOrders.size() > 0) {
                Wrapper wrapper = destination.getWrapper();
                List<Order> destOrders = new ArrayList<>();
                Account account = new Account();
                account.setName(sourceOrders.get(0).getAccount().getName());
                account.setAge(sourceOrders.get(0).getAccount().getAge());
                Order order = new Order();
                order.setAccount(account);
                destOrders.add(order);
                wrapper.setOrders(destOrders);
                destination.setWrapper(wrapper);
            }

            return destination;
        }
    }

}
