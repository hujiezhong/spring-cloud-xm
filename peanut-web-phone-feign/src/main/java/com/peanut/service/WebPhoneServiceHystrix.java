package com.peanut.service;

import com.peanut.entity.Category;
import com.peanut.entity.Product;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WebPhoneServiceHystrix implements FallbackFactory<WebPhoneService> {

    @Override
    public WebPhoneService create(Throwable throwable) {

        return new WebPhoneService() {
            @Override
            public List<Category> list() {
                Category c = new Category();
                c.setCname("此服务已关闭，请稍后再试！");
                List<Category> list = new ArrayList<>();
                list.add(c);
                return list;
            }

            @Override
            public String index() {
                return "此服务已关闭，请稍后再试！";
            }

            @Override
            public List<Product> listProduct() {
                Product p = new Product();
                p.setPname("此服务已关闭，请稍后再试！");
                List<Product> list = new ArrayList<>();
                list.add(p);
                return list;
            }

            @Override
            public List<Category> navCategory() {
                return null;
            }

            @Override
            public List<Product> navProduct(Integer cid, Integer size) {
                return null;
            }

            @Override
            public List<Category> selectParent() {
                return null;
            }

            @Override
            public List<Product> productByFuCid(Integer cid, Integer size) {
                return null;
            }
        };

    }
}
