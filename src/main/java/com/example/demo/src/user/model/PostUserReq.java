package com.example.demo.src.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostUserReq {
    // private String UserName;
    // private String id;
    // private String email;
    // private String password;


    private int id;
    private int userIdx;
    private String UserName;
    private String first_name;
    private String last_name;

    private String address;

    private java.util.Date is_deleted;
    private java.util.Date created_at;
    private java.util.Date updated_at;

    private String email;
    private String password;

}