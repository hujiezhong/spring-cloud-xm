package com.whkobe.service;


import com.whkobe.pojo.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value ="item-service")
public interface SearchService {

    @RequestMapping("/search")
    public List<Product> searchbykey(@RequestParam String keywords);
}
