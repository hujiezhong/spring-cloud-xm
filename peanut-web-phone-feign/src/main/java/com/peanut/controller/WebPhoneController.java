package com.peanut.controller;

import com.alibaba.fastjson.JSON;
import com.netflix.client.http.HttpResponse;
import com.peanut.entity.Category;
import com.peanut.entity.Product;
import com.peanut.service.WebPhoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.xml.ws.spi.http.HttpContext;
import java.util.List;


@Controller
public class WebPhoneController {

    @Autowired
    private WebPhoneService wps;

    @RequestMapping("/api/a/list2")
    public String list(Model mod) {
        List<Category> list = wps.list();
        List<Product> list2 = wps.listProduct();
        mod.addAttribute("list", list);
        mod.addAttribute("list2", list2);
        mod.addAttribute("navCategory", wps.navCategory());
        return "list";
    }

    @RequestMapping("/api/a/index2")
    public String index(HttpSession session) {
        session.setAttribute("navCategory", wps.navCategory()); // 头部nav分类展示
        return wps.index();
    }

    @RequestMapping("/api/a/ajaxNav")
    @ResponseBody
    public String ajaxNav(Integer cid, String callback) {
        //session.setAttribute("navProduct",wps.navProduct(cid)); //头部 分类下的销量最高的商品 五个
        List<Product> products = wps.navProduct(cid);
        String json = callback + "(" + JSON.toJSONString(products) + ")";
        return json;
    }


}
