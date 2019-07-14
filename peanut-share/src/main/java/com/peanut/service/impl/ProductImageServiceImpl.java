package com.peanut.service.impl;

import com.peanut.dao.ProductImagesDao;
import com.peanut.entity.ProductImage;
import com.peanut.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImagesDao pd;

    @Override
    public List<ProductImage> selectImageProduct(String color, Integer cid, Integer pid, Integer size) {
        return pd.selectImageProduct(color,cid,pid,size);
    }
}
