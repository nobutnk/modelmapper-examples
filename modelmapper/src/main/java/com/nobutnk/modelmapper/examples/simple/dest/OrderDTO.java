package com.nobutnk.modelmapper.examples.simple.dest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String customerFirstName;
    private String customerLastName;
    private String billingStreet;
    private String billingCity;
}
