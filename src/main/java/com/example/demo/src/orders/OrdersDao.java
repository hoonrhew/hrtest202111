package com.example.demo.src.orders;


import com.example.demo.src.orders.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class OrdersDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetOrdersRes> getOrders(){
        String getUsersQuery = "select * from orders";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetOrdersRes(

                        rs.getInt("id"),
                        rs.getInt("user"),
                        rs.getFloat("amount"),
                        rs.getBoolean("paid"),
                        rs.getBoolean("shipped"))
                );
    }

    public List<GetOrdersRes> getOrdersByid(int id){
        String getUsersByEmailQuery = "select * from orders where id =?";
        int getUsersByEmailParams = id;
        return this.jdbcTemplate.query(getUsersByEmailQuery,
                (rs, rowNum) -> new GetOrdersRes(
                        rs.getInt("id"),
                        rs.getInt("user"),
                        rs.getFloat("amount"),
                        rs.getBoolean("paid"),
                        rs.getBoolean("shipped")),
                getUsersByEmailParams);
    }
/*
    public GetUserInfoRes selectUserInfo(int userIdx){
        String selectUsersByIdx = "select     userIdx, first_name,last_name from UserInfo where userIdx =?";
        int selectUserForOrdersByIdx = userIdx;
        return this.jdbcTemplate.queryForObject(selectUsersByIdx,
                (rs, rowNum) -> new GetUserInfoRes(
                        rs.getInt("userIdx"),
                        rs.getString("first_name"),
                        rs.getString("last_name")
                        ),
                selectUserForOrdersByIdx);
    }

    public List<GetUserOrderRes> selectUserOrder(int userIdx){
        String selectUserOrder = "select user as userId, id, amount, shipped   from orders where user =?";
        int selectUsersByIdx = userIdx;
        return this.jdbcTemplate.query(selectUserOrder ,
                (rs, rowNum) -> new GetUserOrderRes(
                        rs.getInt("user"),
                        rs.getInt("id"),
                        rs.getFloat("amount"),
                        rs.getBoolean("shipped")
                        ),
                selectUsersByIdx);
    }
    */
    public GetOrdersRes getOrder(int id){
        String getUserQuery = "select * from UserInfo where id= ?";
        int getUserParams = id;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetOrdersRes(
                        rs.getInt("id"),
                        rs.getInt("user"),
                        rs.getFloat("amount"),
                        rs.getBoolean("paid"),
                        rs.getBoolean("shipped")),
                getUserParams);
    }




    public int insertOrder(int user, float amount){
        String insertOrderQuery = "insert into orders (user, amount) VALUES (?,?)";
        Object[] insertOrderParams = new Object[]{user, amount};
        this.jdbcTemplate.update(insertOrderQuery, insertOrderParams);

        String lastInsertidQuery = "select last_insert_id() ";
        return this.jdbcTemplate.queryForObject(lastInsertidQuery, int.class);

    }

/*
    public int checkEmail(String email){
        String checkEmailQuery = "select exists(select email from UserInfo where email = ?)";
        String checkEmailParams = email;
        return this.jdbcTemplate.queryForObject(checkEmailQuery,
                int.class,
                checkEmailParams);

    }

    public int checkUserExists(int userIdx){
        String checkUserExitsQuery = "select exists(select userIdx from UserInfo where userIdx = ?)";
        int checkUserExitsParams = userIdx;
        return this.jdbcTemplate.queryForObject(checkUserExitsQuery,
                int.class,
                checkUserExitsParams);

    }
    */

    /*
    public int modifyUserName(PatchUserReq patchUserReq){
        String modifyUserNameQuery = "update UserInfo set userName = ? where userIdx = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getUserName(), patchUserReq.getUserIdx()};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }
    */
    /*
    public User getPwd(PostLoginReq postLoginReq){

        String getPwdQuery = "select userIdx, id, password,email,userName from UserInfo where id = ?";
        String getPwdParams = String.valueOf(postLoginReq.getId());

        return this.jdbcTemplate.queryForObject(getPwdQuery,
                (rs,rowNum)-> new User(
                        rs.getInt("userIdx"),
                        rs.getString("userName"),
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password")
                ),
                getPwdParams
                );

    }
    */

}
