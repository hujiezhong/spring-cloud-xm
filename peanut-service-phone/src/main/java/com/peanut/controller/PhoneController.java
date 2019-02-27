package com.peanut.controller;

import com.peanut.entity.Category;
import com.peanut.entity.Product;
import com.peanut.service.CategoryService;
import com.peanut.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
public class PhoneController {

    @Autowired
    private CategoryService cs;

    @Autowired
    private ProductService ps;

    @Value("${server.port}")
    private String po;

    @RequestMapping("/api/a/list2")
    public List<Category> list(){
        List<Category> list = cs.list();   //分类
        System.out.println(po);
        return list;
    }

    @RequestMapping("list2Product")
    public List<Product> listProduct(){
        return ps.select(); // 商品
    }

    @RequestMapping("/api/a/index2")
    public String index(){
        return "index";
    }

    @RequestMapping("navCategory")
    public List<Category> navCategory(){
        return cs.listNav();
    }

    @RequestMapping("navProduct")
    public List<Product> navProduct(@RequestParam Integer cid, @RequestParam Integer size){
        return ps.selectNav(cid, size);
    }

    @RequestMapping("selectParent")
    public List<Category> selectParent(){
        List<Category> list = cs.selectParent();
        return list;
    }

    @RequestMapping("productByFuCid")
    public List<Product> productByFuCid(@RequestParam Integer cid, @RequestParam Integer size){
        return ps.productByFuCid(cid, size);
    }

}
