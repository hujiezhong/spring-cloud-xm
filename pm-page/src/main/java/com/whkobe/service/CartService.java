package com.whkobe.service;

import com.whkobe.pojo.Cart;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.swing.*;
import java.util.List;

@FeignClient(value ="item-service")
public interface CartService {

    @RequestMapping("/findCartList")
    public List<Cart> findCartList();

    @RequestMapping("/addGoodsToCartList")
    public boolean addGoodsToCartList(@RequestParam int eid,@RequestParam Integer num);

    @RequestMapping("/setCartNum")
    public boolean setCartShul(@RequestParam int eid,@RequestParam int shu);

    @RequestMapping("/setCartBiaoji")
    public boolean setCartBiaoji(@RequestParam int eid,@RequestParam int biaoji,@RequestParam String username);

    @RequestMapping("/delshop")
    public void delshop(@RequestParam String name);

    @RequestMapping("/setCartBiaojiAll")
    public boolean setCartBiaojiAll(@RequestParam int num,String username);

}
