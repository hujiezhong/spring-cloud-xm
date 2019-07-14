package com.whkobe.service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.peanut.entity.User;
import com.whkobe.pojo.Cart;
import com.whkobe.pojo.Category;
import com.whkobe.pojo.Edition;
import com.whkobe.pojo.Product;
import com.whkobe.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;


@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartApi cartApi;

    @Autowired
    private RedisUtil redis;
    @Override
    public List<Cart> addGoodsToCartList(List<Cart> cartList, int  eid, Integer num) {

        Cart cart = searchCartByEid(cartList,eid);

        //4.如果购物车列表中不存在该购物车
        if(cart==null){

            //4.1 新建购物车对象 ，

            //查询出eid的全部信息
             Edition product= cartApi.findAll(ProductSpec.toFenLei5(eid)).get(0);

            cart=new Cart();
            cart.setNum(1);
            cart.setBiaoji(0);
            cart.setEdition(product);
            //4.2将购物车对象添加到购物车列表
            cartList.add(cart);
            System.out.println(product.getEname());

        }else{

                //5.2. 如果有，在原购物车明细上添加数量，更改金额
                cart.setNum(cart.getNum()+num);
               /* orderItem.setTotalFee(new BigDecimal(orderItem.getNum()*orderItem.getPrice().doubleValue())  );*/
                //如果数量操作后小于等于0，则移除
                if(cart.getNum()<=0){
                    cartList.remove(cart);//移除购物车明细
                }
                /*//如果移除后cart的明细数量为0，则将cart移除
                if(cartList.size()==0){

                }*/
        }
        return cartList;
    }

    //判断购物车是否存在该商品
    private Cart searchCartByEid(List<Cart> cartList, int eid){
        for(Cart cart:cartList){
            if(cart.getEdition().getEid()==eid){
                return cart;
            }
        }
        return null;
    }

    /*private TbOrderItem searchOrderItemByItemId(List<TbOrderItem> orderItemList ,Long itemId ){
        for(TbOrderItem orderItem :orderItemList){
            if(orderItem.getItemId().longValue()==itemId.longValue()){
                return orderItem;
            }
        }
        return null;
    }


    private TbOrderItem createOrderItem(TbItem item,Integer num){
        if(num<=0){
            throw new RuntimeException("数量非法");
        }

        TbOrderItem orderItem=new TbOrderItem();
        orderItem.setGoodsId(item.getGoodsId());
        orderItem.setItemId(item.getId());
        orderItem.setNum(num);
        orderItem.setPicPath(item.getImage());
        orderItem.setPrice(item.getPrice());
        orderItem.setSellerId(item.getSellerId());
        orderItem.setTitle(item.getTitle());
        orderItem.setTotalFee(new BigDecimal(item.getPrice().doubleValue()*num));
        return orderItem;
    }*/

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public List<Cart> findCartListFromRedis(String username) {
        System.out.println("从redis中提取购物车数据....."+username);
        List<Cart> cartList = (List<Cart>) redisTemplate.boundHashOps("cartList").get(username);
            if (cartList == null) {
                cartList = new ArrayList();
            }
            return cartList;

    }

    @Override
    public void saveCartListToRedis(String username, List<Cart> cartList) {
        System.out.println("向redis存入购物车数据....."+username);
        redisTemplate.boundHashOps("cartList").put(username, cartList);
        List<Cart> cartLis = (List<Cart>) redisTemplate.boundHashOps("cartList").get(username);
    }

    @Override
    public void setCartNumToRedis(String username,int eid,int num) {
        List<Cart> cartLis = (List<Cart>) redisTemplate.boundHashOps("cartList").get(username);
        if(cartLis.size()==1&&num==0){
            cartLis=new ArrayList<>();
        }else {
            Iterator<Cart> iterator = cartLis.iterator();
            while(iterator.hasNext()){
                Cart xx= iterator.next();
                if (xx.getEdition().getEid()==eid){
                    xx.setNum(num);
                    if(num==0){
                        iterator.remove();
                    }
                }
            }

        }
        redisTemplate.boundHashOps("cartList").put(username, cartLis);
    }
    @Override
    public void setCartBiaoji(String username,int eid,int biaoji){
        User user = (User)redis.get("user");
        List<Cart> cartLis = (List<Cart>) redisTemplate.boundHashOps("cartList").get(user.getUsername());
        Iterator<Cart> iterator = cartLis.iterator();
        while(iterator.hasNext()){
            Cart xx= iterator.next();
            if (xx.getEdition().getEid()==eid){
                xx.setBiaoji(biaoji);
            }
        }
        redisTemplate.boundHashOps("cartList").put(user.getUsername(), cartLis);
    }
    @Override
    public void setCartBiaojiAll(String username,int num){
        User user = (User)redis.get("user");
        List<Cart> cartLis = (List<Cart>) redisTemplate.boundHashOps("cartList").get(user.getUsername());
        Iterator<Cart> iterator = cartLis.iterator();
        while(iterator.hasNext()){
            Cart xx= iterator.next();
            if (num==1){
                xx.setBiaoji(10);
            }else {
                xx.setBiaoji(0);
            }
        }
        redisTemplate.boundHashOps("cartList").put(user.getUsername(), cartLis);
    }

    @Override
    public List<Cart> mergeCartList(List<Cart> cartList1, List<Cart> cartList2) {
        System.out.println("合并购物车");
        for (Cart cart : cartList2) {
            cartList1=addGoodsToCartList(cartList1, cart.getEdition().getEid(),cart.getNum());
        }

        return cartList1;
    }

    @Override
    public void deleteShop(String name){
        System.out.println(name);
        List<Cart> cartLis = (List<Cart>) redisTemplate.boundHashOps("cartList").get(name);
        Iterator<Cart> iterator = cartLis.iterator();
        while(iterator.hasNext()){
            Cart xx= iterator.next();
            if (xx.getBiaoji()==0){
                iterator.remove();
            }
        }
        redisTemplate.boundHashOps("cartList").put(name, cartLis);
    }
}


