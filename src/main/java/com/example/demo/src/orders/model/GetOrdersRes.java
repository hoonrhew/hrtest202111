package com.example.demo.src.orders.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetOrdersRes {
    private int id;
    private int user;
    private float amount;
    private boolean paid;
    private boolean shipped;
}
