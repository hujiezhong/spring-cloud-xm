package com.peanut.service;


import com.peanut.entity.ProductImage;

import java.util.List;

public interface ProductImageService {

    List<ProductImage> selectImageProduct(String color, Integer cid, Integer pid, Integer size);

}
