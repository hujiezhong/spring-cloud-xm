package com.whkobe.service;

import com.whkobe.pojo.Cart;

import java.util.List;

public interface CartService {
    public List<Cart> findCartListFromRedis(String username);

    public void saveCartListToRedis(String username, List<Cart> cartList);

    public List<Cart> mergeCartList(List<Cart> cartList1, List<Cart> cartList2);

    public List<Cart> addGoodsToCartList(List<Cart> cartList, int  eid, Integer num);

    public void setCartNumToRedis(String username,int eid,int num);

    public void setCartBiaoji(String username,int eid,int biaoji);

    public void setCartBiaojiAll(String username,int num);

    public void deleteShop(String name);
}
