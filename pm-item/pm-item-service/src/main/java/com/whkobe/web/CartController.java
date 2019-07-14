package com.whkobe.web;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.netflix.discovery.converters.Auto;
import com.peanut.entity.User;
import com.whkobe.pojo.Cart;
import com.whkobe.service.CartService;
import com.whkobe.utils.CookieUtils;
import com.whkobe.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private  HttpServletRequest request;

    @Autowired
    private  HttpServletResponse response;


    @Autowired
    private RedisUtil redis;


    @RequestMapping("/findCartList")
    public List<Cart> findCartList(){

        User user = (User) redis.get("user");
        String cartListString = CookieUtils.getCookieValue(request, "cartList","UTF-8");
        if(cartListString==null || cartListString.equals("")){
            cartListString="[]";
        }
        //cookie中读取购物车
        List<Cart> cartList_cookie = JSON.parseArray(cartListString, Cart.class);
        if(user==null) { //如果是没有登陆的状态
            System.out.println(cartList_cookie);
            return cartList_cookie;

        }else {

            System.out.println("redis中取数据,并且合并");
            //redis中读取购物车
            List<Cart> cartListcookie = cartService.findCartListFromRedis(user.getUsername());
            if(cartList_cookie.size()>0) { //如果获取到了cookie中的购物车 就合并，
                cartListcookie = cartService.mergeCartList(cartList_cookie, cartListcookie);
                //存入redis购物车
                cartService.saveCartListToRedis(user.getUsername(), cartListcookie);
                //删除之前cookie购物车
                CookieUtils.deleteCookie(request, response, "cartList");

                System.out.println("经过了合并购物车的逻辑");
            }
            System.out.println("返回购物车");
            System.out.println(cartListcookie.size());
            return cartListcookie;
        }

    }

    @RequestMapping("/setCartNum")
    public boolean setCartShul(@RequestParam int eid,@RequestParam int shu){
        User user = (User)redis.get("user");
        cartService.setCartNumToRedis(user.getUsername(),eid,shu);
        return true;
    }

    @RequestMapping("/delshop")
    public void delshop(@RequestParam String name){
        cartService.deleteShop(name);
    }

    @RequestMapping("/setCartBiaoji")
    public boolean setCartBiaoji(@RequestParam int eid,@RequestParam int biaoji,@RequestParam String username){
        cartService.setCartBiaoji(username,eid,biaoji);
        return true;
    }

    @RequestMapping("/setCartBiaojiAll")
    public boolean setCartBiaojiAll(@RequestParam int num,String username){
        cartService.setCartBiaojiAll(username,num);
        return true;
    }
    /**
     * 添加商品到购物车
     * @param num
     * @return
     */
    @RequestMapping("/addGoodsToCartList")
    //springmvc注解解决方式
   /* @CrossOrigin(origins="http://localhost:9030")*/
    public boolean addGoodsToCartList(@RequestParam int eid,@RequestParam Integer num){
        //传统解决方式
        //response.setHeader("Access-Control-Allow-Origin", "http://localhost:9105");
        //cookie跨域请求设置
        response.setHeader("Access-Control-Allow-Credentials", "true");
        User user = (User)redis.get("user");
        try {
            List<Cart> cartList =findCartList();//获取购物车列表
            cartList = cartService.addGoodsToCartList(cartList,eid, num);

            if(user == null) {
                System.out.println("cookie中存数据");
                CookieUtils.setCookie(request, response, "cartList", JSON.toJSONString(cartList),3600*24,"UTF-8");

            }else {
                System.out.println("redis中存数据");
                cartService.saveCartListToRedis(user.getUsername(), cartList);

            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}



