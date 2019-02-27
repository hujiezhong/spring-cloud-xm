package com.peanut.dao;

import com.peanut.entity.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao {

    List<Product> select();

    List<Product> selectNav(Integer cid, Integer size);

    List<Product> productByFuCid(Integer cid, Integer size);
}
