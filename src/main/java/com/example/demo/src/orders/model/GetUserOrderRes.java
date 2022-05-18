package com.example.demo.src.orders.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetUserOrderRes {
    private int user;
    private int id;
    private float amount;
    private boolean shipped;


    /*
    private int userIdx;
    private String userName;
    private String ID;
    private String email;
    private String password;
    */

}
