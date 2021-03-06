package com.nobutnk.modelmapper.examples.simple.src;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    private Customer customer;
    private Address billingAddress;
}
