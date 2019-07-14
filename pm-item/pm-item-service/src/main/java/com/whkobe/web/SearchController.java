package com.whkobe.web;

import com.netflix.discovery.converters.Auto;
import com.whkobe.pojo.Product;
import com.whkobe.pojo.Product1;
import com.whkobe.service.PowerbankService;
import com.whkobe.service.ProductSearchService;
import com.whkobe.service.ProductSort;
import com.whkobe.service.ProductSpec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class SearchController {

    @Autowired
    private ProductSearchService searchService;

    @Autowired
    private PowerbankService powerbankService;

    @RequestMapping("/search")
    private List<Product> searchbykey(@RequestParam String keywords){
        searchService.deleteAll();
        List<Product1> productList=searchService.search(keywords);
        System.out.println(keywords);
        List<Product> list=new ArrayList<>();
        for (Product1 product : productList) {
           /* Product product1=new Product();*/
            System.out.println(product.getPid());
            Product product1=powerbankService.findByPid(product.getPid());
            product1.setPname(product.getPname());
            product1.setDescription(product.getDescription());
            list.add(product1);
        }
        return list;
    }

    @RequestMapping("prolikepname")
    public  List<Product> findlikepname(@RequestBody Product product, @RequestParam String tiaojian){
        searchService.deleteAll();
        List<Product1> productList=searchService.tiaojianc(product,tiaojian);
        List<Product> list=new ArrayList<>();
        for (Product1 product2 : productList) {
            Product product1=powerbankService.findByPid(product2.getPid());
            product1.setPname(product2.getPname());
            product.setDescription(product2.getDescription());
            list.add(product1);
        }
        if(tiaojian.equals("comment")){
            Collections.sort(list,new ProductSort());
        }
        return list;
    }
}
