package com.whkobe.web;

import com.whkobe.api.OrdersApi;
import com.whkobe.pojo.*;
import com.whkobe.service.CartService;
import com.whkobe.service.PowerbankService;
import com.whkobe.utils.RedisUtil;
import com.whkobe.utils.SnowflakeIdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private PowerbankService powerbankService;

    @Autowired
    private RedisUtil redis;

    @RequestMapping("/cart")
    public String tocart(Model model){
        List<Cart> cartList = cartService.findCartList();
        model.addAttribute("cartlist",cartList);
        model.addAttribute("user",redis.get("user"));
        double cc=0;
        int zz=0;
        int ss=0;
        for (Cart cart : cartList) {
            cc+=cart.getNum()*cart.getEdition().getEshopprice();
            zz+=cart.getNum();
            if (cart.getBiaoji()==0){
                ss+=cart.getNum();
            }
        }
        model.addAttribute("total",cc);
        model.addAttribute("zong",zz);
        model.addAttribute("xuan",ss);
        return "cart";
    }

    @RequestMapping("/cart/{eid}/{num}")
    @ResponseBody
    public List<Double> jiacart(@PathVariable("eid")int eid,@PathVariable("num")int num){
        cartService.addGoodsToCartList(eid, num);
        List<Cart> cartList=cartService.findCartList();
        List<Double> strings=new ArrayList<>();
        double cc=0;
        double vv=0;
        double ss=0;
        for (Cart cart : cartList) {
            vv+=cart.getNum();
            if (cart.getBiaoji()==0){
                ss+=cart.getNum();
            }
            if (cart.getEdition().getEid()==eid){
                strings.add(cart.getEdition().getEshopprice()*cart.getNum());
            }
            cc+=cart.getNum()*cart.getEdition().getEshopprice();
        }
        strings.add(cc);
        strings.add(vv);
        strings.add(ss);
        return strings;
    }

    @RequestMapping("/cartjjall/{num}")
    @ResponseBody
    public List<Double> cartjjall(@PathVariable("num")int num){
        cartService.setCartBiaojiAll(num,"kobe");
        List<Cart> cartList=cartService.findCartList();
        List<Double> strings=new ArrayList<>();
        double cc=0;
        double zz=0;
        double vv= 0;
        for (Cart cart : cartList) {
            vv+=cart.getNum();
            if (cart.getBiaoji()==0) {
                zz += cart.getNum();
                cc += cart.getNum() * cart.getEdition().getEshopprice();
            }
        }
        strings.add(zz);
        strings.add(cc);
        strings.add(vv);
        return strings;
    }

    @RequestMapping("/cartjj/{eid}/{num}")
    @ResponseBody
    public List<Double> cartjj(@PathVariable("eid")int eid,@PathVariable("num")int num){
        List<Cart> cartList=cartService.findCartList();
        List<Double> strings=new ArrayList<>();
        double cc=0;
        double zz=0;
        double vv=0;
        for (Cart cart : cartList) {
            vv+=cart.getNum();
            if (cart.getEdition().getEid()==eid){
                if (num==1){
                    cartService.setCartBiaoji(eid,0,"kobe");
                    zz+=cart.getNum();
                    cc+=cart.getNum()*cart.getEdition().getEshopprice();
                }else {
                    cartService.setCartBiaoji(eid,10,"kobe");
                }
            }else {
                if (cart.getBiaoji()==0){
                    zz+=cart.getNum();
                    cc+=cart.getNum()*cart.getEdition().getEshopprice();
                }
            }
        }
        strings.add(zz);
        strings.add(cc);
        strings.add(vv);
        return strings;
    }

    @ResponseBody
    @RequestMapping("/setcart/{eid}/{shu}")
    public List<Double> setcartshu(@PathVariable("eid")int eid,@PathVariable("shu")int shu){
        boolean b = cartService.setCartShul(eid, shu);
        List<Double> strings=new ArrayList<>();
        List<Cart> chars=cartService.findCartList();
        if(chars.size()==0){
            strings.add(0.0);
            strings.add(0.0);
            strings.add(0.0);
            return strings;
        }
        double cc=0;
        double ss=0;
        double vv=0;
        for (Cart cart : chars) {
            ss+=cart.getNum();
            if (cart.getBiaoji()==0){
                vv+=cart.getNum();
            }
            cc+=cart.getNum()*cart.getEdition().getEshopprice();
        }
        strings.add(ss);
        strings.add(cc);
        strings.add(vv);
        return strings;
    }

    @RequestMapping("/cart/add/{eid}")
    @ResponseBody
    public int addCart(@PathVariable("eid") int eid) {
        boolean b = cartService.addGoodsToCartList(eid, 1);
        if (b==true){
            List<Cart> cartList = cartService.findCartList();
            int nn=0;
            for (Cart cart : cartList) {
                nn=nn+cart.getNum();
            }
            return nn;
        }
        return 1;
    }

    @RequestMapping("/order")
    public String toOrder(Model model){
        //判断是否登陆
        com.peanut.entity.User user=(com.peanut.entity.User) redis.get("user");
        if (user==null){
            return "redirect:http://localhost:9090/api/b/api/b/login";
        }else {
            model.addAttribute("user",redis.get("user"));
            List<Cart> cartList = cartService.findCartList();
            List<Cart> list=new ArrayList<>();
            int jian=0;
            Double yuan=0.0;
            for (Cart cart : cartList) {
                if (cart.getBiaoji()==0){
                    list.add(cart);
                    jian+=cart.getNum();
                    yuan+=cart.getNum()*cart.getEdition().getEshopprice();
                }
            }
            List<Address> addressList=powerbankService.getAdd(1);
            model.addAttribute("addlist",addressList);
            model.addAttribute("cartlist",list);
            model.addAttribute("jian",jian);
            model.addAttribute("yuan",yuan);

            return "order";
        }
    }

    private OrdersApi ordersApi;

    @RequestMapping("/jiesuan/{dizhi}/{total}")
    @ResponseBody
    public  boolean jiesuan(@PathVariable("dizhi")int aid,@PathVariable("total")double total){
        User user=powerbankService.getUser();
        System.out.println(user.getUsername());
        Orders orders=new Orders();
        long ll=SnowflakeIdWorker.generateId();
        orders.setOid(ll);
        Address address=powerbankService.getOne(aid);
        orders.setOname(address.getAname());
        orders.setOstate(1);
        orders.setOordertime(new Date());
        orders.setOaddress(address.getAddress()+address.getDetailed());
        orders.setUid(1);
        orders.setOtelphone(address.getAphone());
        orders.setOtotal(total);
        powerbankService.saveorder(orders);
        List<Cart> cartList = cartService.findCartList();
        for (Cart cart : cartList) {
            System.out.println(cart.getBiaoji());
            if (cart.getBiaoji()==0){
                Orderdetails orderdetails=new Orderdetails();
                orderdetails.setOdcount(cart.getNum());
                orderdetails.setOdsubtotal(cart.getNum()*cart.getEdition().getEshopprice());
                orderdetails.setEid(cart.getEdition().getEid());
                orderdetails.setPid(cart.getEdition().getPid());
                orderdetails.setOid(ll);
                powerbankService.saveodetail(orderdetails);
                Product product=powerbankService.findbypid(cart.getEdition().getPid());
                System.out.println(product.getInventory()-cart.getNum()+"\t"+product.getPid());
                powerbankService.updateinventoru(product.getInventory()-cart.getNum(),product.getPid());
            }
        }
        return true;
    }

}
