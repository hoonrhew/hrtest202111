package com.example.demo.src.orders;


import com.example.demo.config.BaseException;
import com.example.demo.src.orders.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;

//Provider : Read의 비즈니스 로직 처리
@Service
public class OrdersProvider {

    private final com.example.demo.src.orders.OrdersDao OrdersDao;
    private final JwtService jwtService;


    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public OrdersProvider(com.example.demo.src.orders.OrdersDao OrdersDao, JwtService jwtService) {
        this.OrdersDao = OrdersDao;
        this.jwtService = jwtService;
    }

    public List<GetOrdersRes> getOrders() throws BaseException{
        try{
            List<GetOrdersRes> getOrdersRes = OrdersDao.getOrders();
            return getOrdersRes;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }

    public List<GetOrdersRes> getOrdersByid(int id) throws BaseException{
        try{
            List<GetOrdersRes> getOrderByid = OrdersDao.getOrdersByid(id);
            return getOrderByid;
        }
        catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    public GetOrdersRes getOrder(int id) throws BaseException {
        try {
            GetOrdersRes getOrderRes = OrdersDao.getOrder(id);
            return getOrderRes;
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }
/*
    public int checkEmail(String email) throws BaseException{
        try{
            return OrdersDao.checkEmail(email);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR2);
        }
    }

    public int checkUserExits(int userIdx) throws BaseException{
        try{
            return OrdersDao.checkUserExists(userIdx);
        } catch (Exception exception){
            throw new BaseException(DATABASE_ERROR3);
        }
    }

    public PostLoginRes logIn(PostLoginReq postLoginReq) throws BaseException{
        User user = OrdersDao.getPwd(postLoginReq);
        String encryptPwd;
        try {
            encryptPwd=new SHA256().encrypt(postLoginReq.getPassword());
        } catch (Exception ignored) {
            throw new BaseException(PASSWORD_DECRYPTION_ERROR);
        }

        if(user.getPassword().equals(encryptPwd)){
            int userIdx = user.getUserIdx();
            String jwt = jwtService.createJwt(userIdx);
            return new PostLoginRes(userIdx,jwt);
        }
        else{
            throw new BaseException(FAILED_TO_LOGIN);
        }

    }
*/
}
