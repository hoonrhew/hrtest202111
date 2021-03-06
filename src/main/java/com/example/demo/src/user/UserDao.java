package com.example.demo.src.user;


import com.example.demo.src.user.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.List;

@Repository
public class UserDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource){
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public List<GetUserRes> getUsers(){
        String getUsersQuery = "select * from UserInfo";
        return this.jdbcTemplate.query(getUsersQuery,
                (rs,rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("userName"),
                        rs.getInt("ID"),
                        rs.getString("Email"),
                        rs.getString("password"))
                );
    }

    public List<GetUserRes> getUsersByEmail(String email){
        String getUsersByEmailQuery = "select * from UserInfo where email =?";
        String getUsersByEmailParams = email;
        return this.jdbcTemplate.query(getUsersByEmailQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("userName"),
                        rs.getInt("ID"),
                        rs.getString("Email"),
                        rs.getString("password")),
                getUsersByEmailParams);
    }

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
    public GetUserRes getUser(int userIdx){
        String getUserQuery = "select userIdx, userName, id, email, password from UserInfo where userIdx = ?";
        int getUserParams = userIdx;
        return this.jdbcTemplate.queryForObject(getUserQuery,
                (rs, rowNum) -> new GetUserRes(
                        rs.getInt("userIdx"),
                        rs.getString("userName"),
                        rs.getInt("ID"),
                        rs.getString("Email"),
                        rs.getString("password")),
                getUserParams);
    }




    public int createUser(PostUserReq postUserReq){
        String createUserQuery = "insert into UserInfo (id, userIdx, UserName, first_name, last_name, address, is_delted, created_at, updated_at, email, password) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        Object[] createUserParams = new Object[]{postUserReq.getUserName(), postUserReq.getId(), postUserReq.getPassword(), postUserReq.getEmail()};
        this.jdbcTemplate.update(createUserQuery, createUserParams);

        String lastInserIdQuery = "select last_insert_id()";
        return this.jdbcTemplate.queryForObject(lastInserIdQuery,int.class);
    }

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
    /*
    public int modifyUserName(PatchUserReq patchUserReq){
        String modifyUserNameQuery = "update UserInfo set userName = ? where userIdx = ? ";
        Object[] modifyUserNameParams = new Object[]{patchUserReq.getUserName(), patchUserReq.getUserIdx()};

        return this.jdbcTemplate.update(modifyUserNameQuery,modifyUserNameParams);
    }
    */
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


}
