package com.whkobe.service;

import com.whkobe.pojo.Product;

import java.util.Comparator;

public class ProductSort implements Comparator<Product> {


    @Override
    public int compare(Product o1, Product o2) {
        if(o1.getComment().size()>o2.getComment().size()){
            return -1;
        }
        return 1;
    }
}
