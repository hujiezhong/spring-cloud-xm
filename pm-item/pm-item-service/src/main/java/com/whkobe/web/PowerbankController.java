package com.whkobe.web;


import com.whkobe.pojo.*;
import com.whkobe.service.PowerbankService;
import org.apache.commons.math3.analysis.function.Add;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.jws.soap.SOAPBinding;
import java.util.*;

@RestController
public class PowerbankController {
    @Autowired
    private PowerbankService powerbankService;

    @GetMapping("product")
    public List<Product> powerbank(@RequestParam int cid){
        List<Product> list=powerbankService.queryPowerbank(cid);
        return list;
    }

    @GetMapping("category")
    public Category category(@RequestParam int cid){
        Category list1=powerbankService.queryCname(cid);
        return list1;
    }

    @GetMapping("pingpai")
    public List<Category> pingpai(@RequestParam int cid){
        List<Category> ll=powerbankService.queryPingp(cid);
        return ll;
    }


    public Session session;
    @RequestMapping("fenlei")
    public List<Product> fenlei(@RequestBody Product product, @RequestParam("tiaojian") String tiaojian){
        List<Product> list=powerbankService.findBySepc(product,tiaojian);
      /*  List order_by_eid = session.createFilter(list.get(1).getEdition(), "order by eid").setFirstResult(10).setMaxResults(50).list();*/
        return list;
    }

    @GetMapping("pidone")
    public Product findbypid(@RequestParam("pid")Integer pid){
        return powerbankService.findByPid(pid);
    }

    @GetMapping("catlikepname")
    public List<Category> catlikepname(@RequestParam("panme") String pname){
        Product product=new Product();
        product.setPname(pname);
        return powerbankService.findLikePname(product);
    }

    @RequestMapping("saveorder")
    public void saveorder(@RequestBody Orders orders){
        powerbankService.saveOrders(orders);
    }

    @RequestMapping("savecol")
    public void saveCol(@RequestBody com.whkobe.pojo.Collections collections){
        powerbankService.saveCol(collections);
    }
    @RequestMapping("delcol")
    public void delCol(@RequestBody com.whkobe.pojo.Collections collections){
        powerbankService.delCol(collections);
    }

    @RequestMapping("saveadd")
    public void saveAdd(@RequestBody Address collections){
        powerbankService.saveAdd(collections);
    }
    @RequestMapping("getadd")
    public List<Address> getAdd(@RequestParam int uid){
        return powerbankService.getAdd(uid);
    }

    @RequestMapping("getone")
    public Address getOne(@RequestParam int aid){
        return powerbankService.findAddByAid(aid);
    }

    @RequestMapping("getUsrt")
    public com.peanut.entity.User getUser(){
        return powerbankService.getUser();
    }

    @RequestMapping("saveodetail")
    public void saveodetail(@RequestBody Orderdetails orderdetails){
        powerbankService.saveOdetail(orderdetails);
    }

    @RequestMapping("updadeinventory")
    public void updateinventoru(@RequestParam int inventory,@RequestParam int pid){
        powerbankService.updateInventory(inventory,pid);
    }
}
