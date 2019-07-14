package com.peanut.service.impl;

import com.peanut.dao.ProductDao;
import com.peanut.entity.Product;
import com.peanut.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao pd;

    @Override
    public List<Product> select() {
        return pd.select();
    }

    @Override
    public List<Product> selectNav(Integer cid, Integer size) {
        return pd.selectNav(cid, size);
    }

    @Override
    public List<Product> productByFuCid(Integer cid, Integer size) {
        return pd.productByFuCid(cid, size);
    }

    @Override
    public List<Product> productByLike(String pname, Integer size) {
        return pd.productByLike(pname, size);
    }

    @Override
    public Product selectProductImageEdition(Integer pid) {
        return pd.selectProductImageEdition(pid);
    }
}
