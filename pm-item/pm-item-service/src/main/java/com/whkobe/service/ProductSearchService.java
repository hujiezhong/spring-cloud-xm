package com.whkobe.service;

import com.whkobe.pojo.Product;
import com.whkobe.pojo.Product1;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

public interface ProductSearchService {

    public List<Product1> search(String keywords);

    public String deleteAll();

    public List<Product1> tiaojianc(Product product,String tiaojian);
}
