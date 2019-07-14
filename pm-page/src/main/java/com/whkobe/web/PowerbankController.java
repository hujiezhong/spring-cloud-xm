package com.whkobe.web;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.netflix.discovery.converters.Auto;
import com.sun.org.apache.xml.internal.resolver.helpers.PublicId;
import com.whkobe.pojo.*;
import com.whkobe.service.CartService;
import com.whkobe.service.PowerbankService;
import com.whkobe.service.SearchService;
import com.whkobe.utils.RedisUtil;
import jdk.nashorn.api.scripting.ScriptUtils;
import org.bouncycastle.math.raw.Mod;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sun.util.resources.ca.CalendarData_ca;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Function;

@Controller
public class PowerbankController {
    @Autowired
    PowerbankService powerbankService;

    @Autowired
    CartService cartService;

    @Autowired
    SearchService searchService;

    @Autowired
    private RedisUtil redis;

    @GetMapping("/powerbank/{cid}")
    public String toPowerbank(Model model, @PathVariable("cid") int cid){
        List<Product> list=this.powerbankService.powerbank(cid);
        Page<Product> pages=null;
        model.addAttribute("user",redis.get("user"));
        model.addAttribute("powerbank",list);
        Category cc=this.powerbankService.category(cid);
        model.addAttribute("category",cc);
        List<Category> pp=this.powerbankService.pingpai(cid);
        model.addAttribute("pingpai",pp);
        model.addAttribute("jiage","shopprice");
        model.addAttribute("jiabiao","  ↑  ");
        model.addAttribute("pj",0);
        model.addAttribute("th",0);
        model.addAttribute("yh",0);
        model.addAttribute("tiaojian","flag");
        List<Cart> cartList = cartService.findCartList();
        int dd=0;
        for (Cart cart : cartList) {
            dd+=cart.getNum();
        }
        model.addAttribute("cartnum",dd);
        model.addAttribute("cartlist",cartList);
        return "powerbank";
    }


    @RequestMapping("/powerbank/{cid}/{tiaojian}-{pj}-{th}-{yh}")
    public String toFenlei(Model model,HttpServletRequest request,@PathVariable("cid")int cid,@PathVariable("tiaojian") String tiaojian,@PathVariable("pj") int pj,@PathVariable("th") int th,@PathVariable("yh") int yh){
        if (tiaojian.equals("shopprice")){
            model.addAttribute("jiage","jianjia");
            model.addAttribute("jiabiao","  ↓  ");
        }else if (tiaojian.equals("jianjia")){
            model.addAttribute("jiage","shopprice");
            model.addAttribute("jiabiao","  ↑  ");
        }else {
            model.addAttribute("jiage","shopprice");
            model.addAttribute("jiabiao","  ↓  ");
        }
        Product product=new Product();
        if(th==1){
            product.setRateid(1);
        }
        if(yh==1){
            product.setInventory(1);
        }
        product.setCid(cid);
       List<Product> list=powerbankService.fenlei(product,tiaojian);
       model.addAttribute("zongy",list);
        model.addAttribute("powerbank",list);
        Category cc=this.powerbankService.category(cid);
        model.addAttribute("category",cc);
        List<Category> pp=this.powerbankService.pingpai(cid);
        model.addAttribute("pingpai",pp);
        model.addAttribute("tiaojian",tiaojian);
        model.addAttribute("pj",pj);
        model.addAttribute("th",th);
        model.addAttribute("yh",yh);
        List<Cart> cartList = cartService.findCartList();
        int dd=0;
        for (Cart cart : cartList) {
            dd+=cart.getNum();
        }
        model.addAttribute("cartnum",dd);
        return "powerbank";
    }

    @GetMapping("/pro{pid}/{fff}")
    public String toProduct(Model model, @PathVariable("pid")int pid, @PathVariable("fff")String fff){
        return "p"+pid+fff;
    }

    @GetMapping("/product/search")
    public String toSearch(Model model, HttpServletRequest request){
        String keyword= request.getParameter("keyword");
        Product product=new Product();
        product.setPname(keyword);
        model.addAttribute("user",redis.get("user"));
       /* List<Product> productList = powerbankService.findlikepname(product,"flag");*/
        List<Category> categorylist=powerbankService.catlikepname(keyword);
        List<Product> productList = searchService.searchbykey(keyword);
        model.addAttribute("powerbank",productList);
        model.addAttribute("quanbu",keyword);
        model.addAttribute("category",categorylist);
        model.addAttribute("jiage","shopprice");
        model.addAttribute("jiabiao","  ↑  ");
        model.addAttribute("pj",0);
        model.addAttribute("th",0);
        model.addAttribute("yh",0);
        model.addAttribute("tiaojian","flag");
        List<Cart> cartList = cartService.findCartList();
        int dd=0;
        for (Cart cart : cartList) {
            dd+=cart.getNum();
        }
        model.addAttribute("cartnum",dd);
        return "pro-search";
    }
    @RequestMapping("/powerbank/search/{tiaojian}-{pj}-{th}-{yh}")
    public String toChaxun(Model model,@RequestParam("keyword") String search,@PathVariable("tiaojian") String tiaojian,@PathVariable("pj") int pj,@PathVariable("th") int th,@PathVariable("yh") int yh){

        if (tiaojian.equals("shopprice")){
            model.addAttribute("jiage","jianjia");
            model.addAttribute("jiabiao","  ↓  ");
        }else if (tiaojian.equals("jianjia")){
            model.addAttribute("jiage","shopprice");
            model.addAttribute("jiabiao","  ↑  ");
        }else {
            model.addAttribute("jiage","shopprice");
            model.addAttribute("jiabiao","  ↓  ");
        }
        Product product=new Product();
        if(th==1){
            product.setRateid(1);
        }
        if(yh==1){
            product.setInventory(1);
        }
        product.setPname(search);
        List<Product> list=powerbankService.findlikepname(product,tiaojian);
        model.addAttribute("powerbank",list);
        List<Category> cc=this.powerbankService.catlikepname(search);
        model.addAttribute("category",cc);
        List<Category> pp=this.powerbankService.catlikepname(search);
        model.addAttribute("pingpai",pp);
        model.addAttribute("tiaojian",tiaojian);
        model.addAttribute("pj",pj);
        model.addAttribute("th",th);
        model.addAttribute("yh",yh);
        model.addAttribute("quanbu",search);
        List<Cart> cartList = cartService.findCartList();
        int dd=0;
        for (Cart cart : cartList) {
            dd+=cart.getNum();
        }
        model.addAttribute("cartnum",dd);
        return "pro-search";
    }

    @RequestMapping("/col/{ddd}/{pid}")
    @ResponseBody
    public int xihuan(Model model,@PathVariable("ddd")String ddd,@PathVariable("pid") int pid){
        Collections collections=new Collections();
        collections.setPid(pid);
        com.peanut.entity.User user = (com.peanut.entity.User)redis.get("user");
        if (user==null){
            return 0;
        }else {
            System.out.println(user.getUsername());
            collections.setUid(user.getUid());
            if (ddd.equals("add")){
                powerbankService.saveCol(collections);
            }else {
                powerbankService.delCol(collections);
            }
        }
        List<Cart> cartList = cartService.findCartList();
        int dd=0;
        for (Cart cart : cartList) {
            dd+=cart.getNum();
        }
        model.addAttribute("cartnum",dd);
        return 1;
    }

    @RequestMapping("/saveAdd")
    public String saveAdd(Model model, HttpServletRequest request){
        String aname= request.getParameter("aname");
        String aphone= request.getParameter("aphone");
        String s_province= request.getParameter("s_province");
        String s_city= request.getParameter("s_city");
        String s_county= request.getParameter("s_county");
        String address= request.getParameter("address");
        int postalcode= Integer.valueOf(request.getParameter("postalcode"));
        String tabel= request.getParameter("tabel");
        Address address1=new Address();
        com.peanut.entity.User user = (com.peanut.entity.User)redis.get("user");
        address1.setUid(user.getUid());
        address1.setAname(aname);
        address1.setAddress(s_province+" "+s_city+" "+s_county);
        address1.setDetailed(address);
        address1.setPostalcode(postalcode);
        address1.setTabel(tabel);
        address1.setAphone(aphone);
        powerbankService.saveAdd(address1);
        List<Cart> cartList = cartService.findCartList();
        List<Cart> list=new ArrayList<>();
        int jian=0;
        Double yuan=0.0;
        for (Cart cart : cartList) {
            if (cart.getBiaoji()==0){
                list.add(cart);
                jian+=cart.getNum();
                yuan+=cart.getNum()*cart.getEdition().getEshopprice();
            }
        }
        List<Address> addressList=powerbankService.getAdd(user.getUid());
        model.addAttribute("addlist",addressList);
        model.addAttribute("cartlist",list);
        model.addAttribute("jian",jian);
        model.addAttribute("yuan",yuan);
        return "order";
    }
}
