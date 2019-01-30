package com.nobutnk.modelmapper.examples.nested.dest;

import java.util.List;

import com.nobutnk.modelmapper.examples.nested.src.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Wrapper {
    private List<Order> orders;
}
