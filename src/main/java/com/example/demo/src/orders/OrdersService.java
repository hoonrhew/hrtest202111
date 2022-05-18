package com.example.demo.src.orders;



import com.example.demo.config.BaseException;
import com.example.demo.src.orders.model.PostOrderRes;
import com.example.demo.utils.JwtService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.example.demo.config.BaseResponseStatus.DATABASE_ERROR;

// Service Create, Update, Delete 의 로직 처리
@Service
@AllArgsConstructor
public class OrdersService {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final OrdersDao ordersDao;
    private final OrdersProvider ordersProvider;
    private final JwtService jwtService;




    //POST

    public PostOrderRes createOrder(int user, Float amount) throws BaseException {

        try{
            int id = ordersDao.insertOrder(user, amount);
            return new PostOrderRes(id);
        } catch (Exception exception) {
            throw new BaseException(DATABASE_ERROR);
        }
    }


    /*
    public void modifyUserName(PatchUserReq patchUserReq) throws BaseException {
        try{
            int result = ordersDao.modifyUserName(patchUserReq);
            if(result == 0){
                throw new BaseException(MODIFY_FAIL_USERNAME);
            }
        } catch(Exception exception){
            throw new BaseException(DATABASE_ERROR);
        }
    }
    */
}
