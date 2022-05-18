package com.example.demo.src.orders.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class GetUserInfoRes {
    private int userIdx;
    private String first_name;
    private String last_name;


    /*
    private int userIdx;
    private String userName;
    private String ID;
    private String email;
    private String password;
    */

}
