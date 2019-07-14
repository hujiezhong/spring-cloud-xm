package com.peanut.controller;

import com.peanut.dao.CollectionDao;
import com.peanut.entity.*;
import com.peanut.entity.Collections;
import com.peanut.service.*;
import com.peanut.util.HttpClientUtil;
import com.peanut.util.RedisUtil;
import freemarker.template.utility.Collections12;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class ServiceComputerController {
    @Autowired
    private  RedisUtil ru;
    //用户名
    private static String Uid = "xiamou";

    //接口安全秘钥
    private static String Key = "d41d8cd98f00b204e980";

    //手机号码，多个号码如13800000000,13800000001,13800000002

    //private static String smsMob = "17388963072";
    //随机生成验证码
    private static  int  yan=(int)(Math.random()*100000);
    //短信内容
    private static String smsText = "欢迎注册花生米账号，您的验证码为"+yan;
    @Autowired
    private UserService us;
    @Autowired
    private CollectionService cs;
    @Autowired
    private OrdersService os;
    @Autowired
    private AddressService as;

    //获取我的收藏
    @RequestMapping("/api/b/like")
    public List<Collections> col()
    {
        User user=(User)ru.get("user");
        Integer uid=user.getUid();
        List<Collections> list=cs.secbylike(uid);
        ru.set("user",user);
        return list;
    }
    //获取订单
    @RequestMapping("/api/b/or")
    public List<Orders> ord()
    {
        User user=(User)ru.get("user");
        Integer uid=user.getUid();
        List<Orders> list=os.selord(uid);
        ru.set("user",user);
        return list;
    }

    //根据订单编号 ，商品信息来模糊查询
    @RequestMapping("/api/b/selmohu")
    public List<Orders> selmohu(@RequestParam String value)
    {
        User user=(User)ru.get("user");
        Integer uid=user.getUid();
        List<Orders> list=os.selmohu(uid,value);
        ru.set("user",user);
        return list;
    }
    //根据订单状态来查询信息
    @RequestMapping("/api/b/selbysta")
    public List<Orders> selbysta(@RequestParam Integer ostate)
    {
        User user=(User)ru.get("user");
        Integer uid=user.getUid();
        List<Orders> list=os.selbysta(uid,ostate);
        ru.set("user",user);
        return list;
    }

    //获取手机验证码
    @RequestMapping("/api/b/yz")
    public String yanzhengma(@RequestParam String smsMob)
    {
        int count=us.secbyphone(smsMob);
        if(count>0) {
            HttpClientUtil client = HttpClientUtil.getInstance();
            int result = client.sendMsgUtf8(Uid, Key, smsText, smsMob);
            return "Y";
        }
        return "N";
    }

    @RequestMapping("/api/b/deng")
    public User login(@RequestParam String smsMob,@RequestParam String yanz) {
        System.out.println("客户验证码"+yanz+"生成验证码"+yan+"电话号码"+smsMob);
        if (yanz.equals(String.valueOf(yan))) {
            System.out.println("123123客户验证码"+yanz+"生成验证码"+yan+"电话号码"+smsMob);
            User user = us.seubyphone(smsMob);
            ru.set("user",user);
            return user;
        }
        return null;
    }
    @RequestMapping("/api/b/lu")
    public User lu(@RequestParam Map<String,Object> map)
    {
        User uu=us.sebyname(map);
        if (uu!=null)
        {
            ru.set("user",uu);
            return uu;
        }else {
            return null;
        }

    }
    //进行注册
    @RequestMapping("/api/b/regist")
    public String zhuce(@RequestBody User user)
    {
        int pcun=us.secbyphone(user.getPhone());
        if (pcun>0)
        {
            return "N";
        }else
        {
            int ru=us.intuser(user);
            return "Y";
        }
    }

    @RequestMapping("/api/b/del")
    public String dels(@RequestParam Integer colid)
    {
        int del=cs.delbycolid(colid);
        if (del>0)
        {
            return "Y";
        }
        else
        {
            return "N";
        }
    }

    /*个人信息页*/
    @RequestMapping("/api/b/person")
    public int[] selc()
    {
        User user=(User)ru.get("user");
        Integer uid=user.getUid();
        int daizf=os.selcount1(uid);
        int daijs=os.selcount3(uid);
        int like=cs.selikecount(uid);
        int ping=os.selcount4(uid);
        int [] cout={daizf,daijs,like,ping};
        ru.set("user",user);
        return cout;
    }
    //订单详情
    @RequestMapping("api/b/xiangqing")
    public List<Orders> selid(@RequestParam String oid)
    {
        User user=(User)ru.get("user");
        Integer uid=user.getUid();
        List<Orders> list=os.selid(uid,oid);
        ru.set("user",user);
        return list;
    }

    //跳转支付页面
    @RequestMapping("api/b/payy")
    public List<Orders> payy(@RequestParam String oid)
    {
        User user=(User)ru.get("user");
        Integer uid=user.getUid();
        List<Orders> list=os.selpay(uid,oid);
        Integer ff=os.fukuan(oid);
        System.out.println(ff);
        ru.set("user",user);
        return list;
    }

    //跳转到地址页面
    @RequestMapping("api/b/add")
    public List<Address> add()
    {
        User user=(User)ru.get("user");
        Integer uid=user.getUid();
        List<Address> list=as.seabi(uid);
        ru.set("user",user);
        return list;
    }
    //删除收货地址
    @RequestMapping("api/b/dels")
    public String dels(@RequestParam int aid)
    {
        int res=as.deladd(aid);
        if (res>0)
        {
            return "Y";
        }else
        {
            return "N";
        }
    }

    //取消订单
    @RequestMapping("api/b/quxiao")
    public int ups(@RequestParam String oid)
    {

        Integer qu=os.ups(oid);
        return qu;
    }

    //确认收货
    @RequestMapping("api/b/wancheng")
    public int wancheng(@RequestParam String oid)
    {
        Integer fin=os.wangcheng(oid);
        return fin;
    }

    //进行用户信息的修改
    @RequestMapping("api/b/xiugai")
    public String xiugai(@RequestBody User user)
    {
        int pcun=us.secbyphone(user.getPhone());
        if (pcun>1)
        {
            return "N";
        }else {
            int xx=us.updateU(user);
            if (xx>0)
            {
                ru.set("user",user);
                return "Y";
            }
            else
            {
                return "N";
            }
        }
    }
    @RequestMapping("api/b/updateT")
    public String updateT(@RequestParam String touxiangpath)
    {
        User user=(User)ru.get("user");
        user.setTouXiangPath(touxiangpath);
        int ut=us.updateT(user);
        ru.set("user",user);
        if (ut>0)
        {
            return "Y";
        }else
        {
            return "N";
        }
    }

}
