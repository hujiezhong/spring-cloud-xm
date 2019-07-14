package com.peanut.service.impl;

import com.peanut.dao.OrdeersDao;
import com.peanut.entity.Orders;
import com.peanut.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdeersDao od;

    @Override
    public Orders ordersByOname(Integer uid, long oid, Integer pid) {
        return od.ordersByOname(uid, oid, pid);
    }

    @Override
    public List<Orders> selord(Integer uid)
    {
        return od.selord(uid);
    }

    @Override
    public int fukuan(String oid) {
        return od.fukuan(oid);
    }

    @Override
    public List<Orders> selpay(Integer uid, String oid) {
        return od.selpay(uid,oid);
    }

    @Override
    public int ups(String oid) {
        return od.ups(oid);
    }

    @Override
    public List<Orders> selid(Integer uid, String oid) {
        return od.selid(uid,oid);
    }

    @Override
    public int wangcheng(String oid) {
        return od.wangcheng(oid);
    }

    @Override
    public int selcount4(Integer uid) {
        return od.selcount4(uid);
    }

    @Override
    public int selcount1(Integer uid) {
        return od.selcount1(uid);
    }

    @Override
    public int selcount3(Integer uid) {
        return od.selcount3(uid);
    }

    @Override
    public List<Orders> selmohu(Integer uid, String value) {
        return od.selmohu(uid,value);
    }

    @Override
    public List<Orders> selbysta(Integer uid, Integer ostate) {
        return od.selbysta(uid,ostate);
    }
}
