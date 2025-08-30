package com.order.orderservice.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerResponse {

    private Customer customer;
    private Boolean isError;
    private String errorMessage;

}
