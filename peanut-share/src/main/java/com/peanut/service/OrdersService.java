package com.peanut.service;

import com.peanut.entity.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrdersService {

    Orders ordersByOname( Integer uid, long oid, Integer pid);

    List<Orders> selord(Integer uid);

    List<Orders> selbysta(Integer uid,Integer ostate);

    List<Orders> selmohu(Integer uid,String value);

    int selcount1(Integer uid);

    int selcount3(Integer uid);

    int selcount4(Integer uid);

    List<Orders> selid(Integer uid,String oid);

    int ups(String oid);

    int wangcheng(String oid);

    int fukuan(String oid);

    List<Orders> selpay(Integer uid,String oid);

}
