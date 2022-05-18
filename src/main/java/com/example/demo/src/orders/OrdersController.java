package com.example.demo.src.orders;

import com.example.demo.config.BaseException;
import com.example.demo.config.BaseResponse;
import com.example.demo.config.BaseResponseStatus;
import com.example.demo.src.orders.model.*;
import com.example.demo.utils.JwtService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.demo.config.BaseResponseStatus.*;
import static com.example.demo.utils.ValidationRegex.isRegexEmail;

@RestController
@RequestMapping("/app/orders")
public class OrdersController {
    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private final OrdersProvider ordersProvider;
    @Autowired
    private final OrdersService ordersService;
    @Autowired
    private final JwtService jwtService;




    public OrdersController(OrdersProvider ordersProvider, OrdersService ordersService, JwtService jwtService){
        this.ordersProvider = ordersProvider;
        this.ordersService = ordersService;
        this.jwtService = jwtService;
    }


    //Query String
    @ResponseBody
    @GetMapping("") // (GET) 127.0.0.1:9000/app/users
    public BaseResponse<List<GetOrdersRes>> getOrders(@RequestParam(required = false) String id) {
        try{
            if( id == null){
                List<GetOrdersRes> getOrdersRes = ordersProvider.getOrders();
                return new BaseResponse<>(getOrdersRes);
            }
            // Get Users
            List<GetOrdersRes> getOrdersRes = ordersProvider.getOrdersByid(Integer.parseInt(id));
            return new BaseResponse<>(getOrdersRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }

    /**
     * 회원 1명 조회 API
     * [GET] /users/:userIdx
     * @return BaseResponse<GetUserRes>
     */
    // Path-variable
    @ResponseBody
    @GetMapping("/getorder/id/{id}") // (GET) 127.0.0.1:9000/app/users/:userIdx
    public BaseResponse<GetOrdersRes> getOrder(@PathVariable("id") int id) {
        // Get an order
        try{
            GetOrdersRes getOrdersRes = ordersProvider.getOrder(id);
            return new BaseResponse<>(getOrdersRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }

    }


    /**
     * Placing an order API
     * [POST] /orders
     * @return BaseResponse<PostOrdersRes>
     */
    // Body
    @ResponseBody
    @PostMapping("/createOrder")
    public BaseResponse<PostOrderRes> createOrder(@RequestBody PostOrderReq postOrderReq) {
        /*try{
            if(postOrderReq.getamount()<0)
                return new BaseResponse<>(BaseResponseStatus.POST_ORDERS_INVALID_AMOUNT);
        }*/
        /*try{
            if(postOrderReq.getamount() == null)
                return new BaseResponse<>(BaseResponseStatus.POST_ORDERS_EMPTY_AMOUNT);
        }*/
        try{
            PostOrderRes postOrderRes = ordersService.createOrder(postOrderReq.getUser(),postOrderReq.getAmount());
            return new BaseResponse<>(postOrderRes);
        } catch(BaseException exception){
            return new BaseResponse<>((exception.getStatus()));
        }
    }



}
