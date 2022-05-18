package com.example.demo.src.orders.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetOrderByUserRes {
    private boolean isMyOrder;
    private GetUserInfoRes getUserInfoRes;
    private List<GetUserOrderRes> getUserOrderRes;

    /*
    private int userIdx;
    private String userName;
    private String ID;
    private String email;
    private String password;
    */

}
