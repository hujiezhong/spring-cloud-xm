package com.whkobe.service;

import com.whkobe.pojo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value ="item-service")
public interface PowerbankService {

    @GetMapping("product")
    public List<Product> powerbank(@RequestParam int cid);

    @GetMapping("category")
    public Category category(@RequestParam int cid);

    @GetMapping("pingpai")
    public List<Category> pingpai(@RequestParam int cid);

    @RequestMapping(value = "fenlei", consumes = "application/json")
    public List<Product> fenlei(@RequestBody Product product, @RequestParam String tiaojian);

    @GetMapping("pidone")
    public Product findbypid(@RequestParam("pid")Integer pid);

    @RequestMapping("prolikepname")
    public  List<Product> findlikepname(@RequestBody Product product,@RequestParam String tiaojian);

    @GetMapping("catlikepname")
    public List<Category> catlikepname(@RequestParam("panme") String pname);

    @RequestMapping("saveorder")
    public void saveorder(@RequestBody Orders orders);

    @RequestMapping("savecol")
    public void saveCol(@RequestBody com.whkobe.pojo.Collections collections);
    @RequestMapping("delcol")
    public void delCol(@RequestBody com.whkobe.pojo.Collections collections);

    @RequestMapping("saveadd")
    public void saveAdd(@RequestBody Address collections);
    @RequestMapping("getadd")
    public List<Address> getAdd(@RequestParam int uid);

    @RequestMapping("getone")
    public Address getOne(@RequestParam int aid);

    @RequestMapping("getUsrt")
    public User getUser();

    @RequestMapping("saveodetail")
    public void saveodetail(@RequestBody Orderdetails orderdetails);

    @RequestMapping("updadeinventory")
    public void updateinventoru(@RequestParam int inventory,@RequestParam int pid);
}
