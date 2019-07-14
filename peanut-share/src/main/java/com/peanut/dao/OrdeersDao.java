package com.peanut.dao;

import com.peanut.entity.Orders;
import com.peanut.util.RedisCache;
import com.peanut.util.RedisUtil;
import org.apache.ibatis.annotations.CacheNamespace;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//@CacheNamespace(implementation = RedisCache.class)
public interface OrdeersDao {

    Orders ordersByOname(@Param("uid") Integer uid, @Param("oid") long oid, @Param("pid") Integer pid);

    List<Orders> selord(Integer uid);

    List<Orders> selbysta(Integer uid,Integer ostate);

    List<Orders> selmohu(Integer uid,String value);

    int selcount1(Integer uid);

    int selcount3(Integer uid);

    int selcount4(Integer uid);

    List<Orders> selid(Integer uid,String oid);

    int ups(String oname);

    int wangcheng(String oid);

    int fukuan(String oid);

    List<Orders> selpay(Integer uid,String oid);

}
