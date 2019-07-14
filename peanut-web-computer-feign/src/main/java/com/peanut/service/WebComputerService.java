package com.peanut.service;

import com.peanut.entity.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@FeignClient(value = "peanut-service-computer")
public interface WebComputerService {
    @RequestMapping("/api/b/yz")
    public String yanzhengma(@RequestParam String smsMob);

    @RequestMapping("/api/b/deng")
    public User login(@RequestParam String smsMob,@RequestParam String yanz);

    @RequestMapping("/api/b/lu")
    public User lu(@RequestParam Map<String,Object> map);

    @RequestMapping("/api/b/like")
    public List<Collections> col();

    @RequestMapping("/api/b/regist")
    public String zhuce(@RequestBody User user);

    @RequestMapping("/api/b/upd")
    public String xiugai(Model model);

    @RequestMapping("/api/b/del")
    public String dels(@RequestParam Integer colid);

    @RequestMapping("/api/b/or")
    public List<Orders> ord();

    //根据订单状态来查询信息
    @RequestMapping("/api/b/selbysta")
    public List<Orders> selbysta(@RequestParam Integer ostate);

    @RequestMapping("/api/b/selmohu")
    public List<Orders> selmohu(@RequestParam String value);
    //跳转个人信息
    @RequestMapping("/api/b/person")
    public int[] selc();

    //跳转订单详情
    @RequestMapping("api/b/xiangqing")
    public List<Orders> selid(@RequestParam String oid);

    //取消订单
    @RequestMapping("api/b/quxiao")
    public int ups(@RequestParam String oid);

    //跳转支付页面
    @RequestMapping("api/b/payy")
    public List<Orders> payy(@RequestParam String oid);

    //确认收货
    @RequestMapping("api/b/wancheng")
    public int wancheng(@RequestParam String oid);


    //进行用户的修改
    @RequestMapping("api/b/xiugai")
    public String xiugai(@RequestBody User user);

    //修改用户的头像
    @RequestMapping("api/b/updateT")
    public String updateT(@RequestParam String touxiangpath);

    //跳转到地址页面
    @RequestMapping("api/b/add")
    public List<Address> add();

    //删除收货地址
    @RequestMapping("api/b/dels")
    public String dels(@RequestParam int aid);
}
