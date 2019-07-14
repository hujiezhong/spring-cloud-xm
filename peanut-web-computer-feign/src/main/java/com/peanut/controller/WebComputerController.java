package com.peanut.controller;

import com.alibaba.fastjson.JSON;
import com.peanut.entity.*;
import com.peanut.service.WebComputerService;
import com.peanut.util.RedisUtil;
import io.lettuce.core.metrics.CommandLatencyId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class WebComputerController {
    @Autowired
    private WebComputerService wcs;
    @Autowired
    private RedisUtil ru;

    public static String shuju=null;


    @RequestMapping("/api/b/yz")
    @ResponseBody
    public String pc(@RequestParam String smsMob)
    {
        String cun= wcs.yanzhengma(smsMob);
        return cun;
    }
    @RequestMapping("/api/b/deng")
    @ResponseBody
    public String pl(@RequestParam String smsMob,@RequestParam String yanz)
    {
        User tong=wcs.login(smsMob,yanz);
        if(tong!=null) {
            System.out.println("登录成功");
            return "Y";
        }else
        {
            return "N";
        }
    }
    @RequestMapping("/api/b/lu")
    public String dl(@RequestParam Map<String,Object> map)
    {
        System.out.println(map.get("username"));
        User cheng=wcs.lu(map);
        if (cheng!=null)
        {
            ru.set("user",cheng);
            return "redirect:/api/b/in";
        }
        else
        {
            return "redirect:/api/b/login";
        }
    }

    @RequestMapping("/api/b/regist")
    public String reg(@RequestParam String username,@RequestParam String name,@RequestParam String address,@RequestParam String password,@RequestParam String phone, Model model)
    {
        User user=new User();
        user.setUsername(username);
        user.setName(name);
        user.setAddress(address);
        user.setPassword(password);
        user.setPhone(phone);
        user.setLevel(0);
        user.setStatus(0);
        System.out.println(user.getPhone()+"\n"+user.getPassword()+"\n"+user.getAddress());
        String err=wcs.zhuce(user);
        if (err.equals("N"))
        {
            model.addAttribute("er","手机号码已被注册，请重新输入");
            return "register";
        }
        else
        {
            return "denglu";
        }
    }

    @RequestMapping("/api/b/login")
    public String login()
    {
        return "denglu";
    }

    @RequestMapping("/api/b/regi")
    public String regi()
    {
        return "register";
    }

    @RequestMapping("/api/b/in")
    public String in(Model model)
    {
        model.addAttribute("user",(User)ru.get("user"));
        return "redirect:http://localhost:9090/api/a/api/a/index2";
    }
    @RequestMapping("/api/b/like")
    public String col(Model model)
    {
        List<Collections> list=wcs.col();
        model.addAttribute("list",list);
        model.addAttribute("user",ru.get("user"));
        return "collection";
    }
    @RequestMapping("/api/b/del")
    @ResponseBody
    public String dels(@RequestParam Integer colid)
    {
        String de= wcs.dels(colid);
        return de;
    }
    @RequestMapping("/api/b/or")
    public String ord(Model model)
    {
        List<Orders> list=wcs.ord();
        model.addAttribute("list",list);
        model.addAttribute("user",ru.get("user"));
        return "order";
    }
    //退出登录
    @RequestMapping("api/b/tui")
    public String tui()
    {
        ru.remove("user");
        return "redirect:http://localhost:9090/api/a/api/a/index2";
    }
    //根据订单状态来查询信息
    @RequestMapping("/api/b/selbysta")
    public String selbysta(@RequestParam Integer ostate,Model model)
    {
        User user=(User)ru.get("user");
        List<Orders> list=wcs.selbysta(ostate);
        model.addAttribute("list",list);
        ru.set("user",user);
        return "order";
    }

    @ResponseBody
    @RequestMapping("/api/b/selmohu")
    public String selmohu(@RequestParam String value)
    {
        List<Orders> list=wcs.selmohu(value);
        String json = JSON.toJSONString(list);
        return json;
    }
    //跳转个人信息
    @RequestMapping("/api/b/person")
    public String selc(Model model)
    {
        int [] cou=wcs.selc();

        model.addAttribute("cou",cou);
        model.addAttribute("user",ru.get("user"));
        return "person";
    }

    @RequestMapping("/api/b/xiangqing")
    public String selid(@RequestParam String oid,Model model)
    {
        List<Orders> list=wcs.selid(oid);
        System.out.println(list.get(0).getOaddress()+""+list.size());
        model.addAttribute("list",list);
        return "orderdetails";
    }

    @RequestMapping("api/b/payy")
    public String payy(@RequestParam String oid,Model model)
    {
        List<Orders> list=wcs.payy(oid);
        System.out.println(list.size());
        model.addAttribute("list",list);
        return  "pay";
    }

    @RequestMapping("api/b/quxiao")
    @ResponseBody
    public String ups(@RequestParam String oid)
    {
        User user=(User)ru.get("user");
        Integer qu=wcs.ups(oid);
        System.out.println(qu);
        if (qu>0)
        {
            ru.set("user",user);
            return "Y";
        }else
        {
            return "N";
        }
    }

    //确认收货
    @RequestMapping("api/b/wancheng")
    @ResponseBody
    public String wancheng(@RequestParam String oid)
    {
        Integer wan=wcs.wancheng(oid);
        if (wan>0)
        {
            return "Y";
        }
        else
        {
            return "N";
        }
    }
    //跳转修改页面
    @RequestMapping("api/b/update")
    public String update(Model model)
    {
        model.addAttribute("user",ru.get("user"));
        return "update";
    }
    //进行用户信息的修改
    @RequestMapping("api/b/xiugai")
    public String xiugai(@RequestParam String username,@RequestParam String name,@RequestParam String phone,@RequestParam String address,@RequestParam String password,Model model)
    {
        User user=(User)ru.get("user");
        Integer uid=user.getUid();
        System.out.println(uid+"UID");
        user.setUsername(username);
        user.setPhone(phone);
        user.setPassword(password);
        user.setName(name);
        user.setAddress(address);
        user.setUid(uid);
        System.out.println(user.getPhone());
        String x=wcs.xiugai(user);
        System.out.println("x"+x);
        ru.set("user",user);
        if (x.equals("Y")) {
            model.addAttribute("user",user);
            return "update";
        }
        else
        {
            return "update";
        }
    }
    @RequestMapping(value = "api/b/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest req, @RequestParam("file") MultipartFile file, Model m)
    {
        try {
            //2.根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了
            String fileName = System.currentTimeMillis() + file.getOriginalFilename();
            //3.通过req.getServletContext().getRealPath("") 获取当前项目的真实路径，然后拼接前面的文件名
            /* req.getServletContext().getRealPath("") + "uploaded" + File.separator + fileName;*/
            String destFileName =ResourceUtils.getURL("classpath:").getPath()+"static/static/picture/"+fileName;
            //4.第一次运行的时候，这个文件所在的目录往往是不存在的，这里需要创建一下目录（创建到了webapp下uploaded文件夹下）
            System.out.println(destFileName+"\n"+fileName);
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs();
            //5.把浏览器上传的文件复制到希望的位置
            file.transferTo(destFile);
            //6.把文件名放在model里，以便后续显示用
            String  touxiangpath="/static/picture/"+fileName;
            return "redirect:http://localhost:9090/api/b/api/b/updateT?touxiangpath="+touxiangpath;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            return "上传失败," + e.getMessage();
        }
    }

    @RequestMapping("api/b/updateT")
    public String updateT(@RequestParam String touxiangpath)
    {
        String uuu=wcs.updateT(touxiangpath);
        System.out.println(uuu);
        if (uuu.equals("Y"))
        {
            return "redirect:http://localhost:9090/api/b/api/b/update";
        }else
        {
            return "redirect:http://localhost:9090/api/b/api/b/update";
        }
    }
    //跳转到地址页面
    @RequestMapping("api/b/add")
    public String add(Model model)
    {
        List<Address> list=wcs.add();
        model.addAttribute("list",list);
        model.addAttribute("user",ru.get("user"));
        return "add";
    }

    //删除收货地址
    @RequestMapping("api/b/dels")
    @ResponseBody
    public String dels(@RequestParam int aid)
    {
        String ss=wcs.dels(aid);
        return ss;
    }

}