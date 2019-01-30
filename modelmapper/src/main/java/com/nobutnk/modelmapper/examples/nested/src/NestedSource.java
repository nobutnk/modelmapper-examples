package com.nobutnk.modelmapper.examples.nested.src;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class NestedSource {

    private List<Order> orders;
}
