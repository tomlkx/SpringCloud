package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
public class OrderController {

    //public static final String PaymentSrv_URL = "http://localhost:8001";
    public static final String PaymentSrv_URL = "http://cloud-payment-service";//服务注册中心上的微服务名称
    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/consumer/pay/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO){
        return restTemplate.postForObject(PaymentSrv_URL+"/pay/add",payDTO,ResultData.class);
    }
    @GetMapping("/consumer/pay/get/{id}")
    public ResultData getOrderById(@PathVariable("id") Integer id){
        return restTemplate.getForObject(PaymentSrv_URL+"/pay/get/"+id,ResultData.class,id);
    }
    @GetMapping("/consumer/pay/delete/{id}")
    public ResultData deleteOrderById(@PathVariable("id") Integer id){
        return  restTemplate.exchange(PaymentSrv_URL + "/pay/del/" + id, HttpMethod.DELETE, null, ResultData.class).getBody();
    }
    @PutMapping("/consumer/pay/update")
    public ResultData updateOrderById(@RequestBody PayDTO payDTO){
        HttpEntity<PayDTO> entity = new HttpEntity<>(payDTO);
        return restTemplate.exchange(PaymentSrv_URL+"/pay/update",HttpMethod.PUT,entity,ResultData.class).getBody();
    }
    @GetMapping("/consumer/pay/getAll")
    public ResultData orderList(){
        return  restTemplate.getForObject(PaymentSrv_URL+"/pay/getAll",ResultData.class);
    }
}
