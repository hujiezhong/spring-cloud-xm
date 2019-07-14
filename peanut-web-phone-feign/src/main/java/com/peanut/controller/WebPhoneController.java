package com.peanut.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.Page;
import com.peanut.entity.*;
import com.peanut.service.WebPhoneService;
import com.peanut.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Path;
import java.io.File;
import java.io.IOException;
import java.util.*;


@Controller
public class WebPhoneController {

    @Autowired
    private WebPhoneService wps;

    @Autowired
    private RedisUtil redis;

    @Autowired
    private MongoTemplate mongodb;

    @RequestMapping("/api/a/list2")
    public String list(Model mod) {
        List<Category> list = wps.list();
        List<Product> list2 = wps.listProduct();
        mod.addAttribute("user",redis.get("user"));
        mod.addAttribute("list", list);
        mod.addAttribute("list2", list2);
        mod.addAttribute("navCategory", wps.navCategory());
        mod.addAttribute("tuiJian", wps.navProduct(0, 5));            //查询推荐，销量最高
        return "list";
    }

    @RequestMapping("/api/a/index2")
    public String index(Model mod) {
        mod.addAttribute("user",redis.get("user"));
        mod.addAttribute("navCategory", wps.navCategory());                // 头部nav分类展示
        mod.addAttribute("partentList", wps.selectParent());                    //列表夫分类
        mod.addAttribute("phone", wps.productByFuCid(1, 8));          //查询8个手机
        mod.addAttribute("computer", wps.productByFuCid(2, 7));       //查询电脑
        mod.addAttribute("parts", wps.productByFuCid(3, 7));          //查询配件
        mod.addAttribute("tuiJian", wps.navProduct(0, 5));            //查询推荐，销量最高
        return wps.index();
    }

    @RequestMapping("/api/a/gengduo")   //查询大图为多种色彩的商品
    public String gengDuo(Model mod, Integer cid) {
        mod.addAttribute("user",redis.get("user"));
        mod.addAttribute("navCategory", wps.navCategory());
        mod.addAttribute("duoProduct", wps.selectImageProduct("n", cid, 0, 18));
        return "gengduo";
    }

    @RequestMapping("/api/a/goumai/{pid}")
    public String gouMai(Model mod, @PathVariable(value = "pid",required = false) Integer pid){
        mod.addAttribute("navCategory", wps.navCategory());
        mod.addAttribute("pie",wps.selectProductImageEdition(pid));
        mod.addAttribute("color", wps.selectImageProduct("y", 0, pid, 1000));  //懒得写sql 加个判断 查询商品图片的颜色
        mod.addAttribute("user",redis.get("user"));
        return "goumai";
    }

    @RequestMapping("/api/a/ping/{oid}/{pid}")  //评论
    public String ping(@PathVariable(value = "oid") long oid,
                       @PathVariable(value = "pid") Integer pid, Model mod){
        User user = (User)redis.get("user");
        mod.addAttribute("orders",wps.ordersByOname(user.getUid(),oid,pid));
        mod.addAttribute("user",redis.get("user"));
        redis.set("user",user);
        return "comment";
    }

    @RequestMapping("/api/a/insertComment")
    public String insertComment(Integer pid, String coContent, Integer goodOrBad){      //添加评论
        System.out.println(coContent);
        Comment com = new Comment();
        com.setGoodorbad(goodOrBad);
        Product p = new Product();
        p.setPid(pid);
        com.setProduct(p);
        com.setCoContent(coContent);
        User user = (User)redis.get("user");
        user.setUid(user.getUid());
        com.setUser(user);
        int coId = wps.insertComment(com);
        for(String s : images){
            CommentImage ci = new CommentImage();
            ci.setCiFileName("/static/picture/"+s);
            ci.setCoId(coId);
            wps.insertCommentImage(ci);
        }
        redis.set("user",user);
        images.clear();
        return "redirect:http://localhost:9090/api/a/api/a/commentAndReply/"+pid+"/0";
    }

    private static List<String> images = new ArrayList<>();

    @RequestMapping("/api/a/text")
    public String iii(@RequestParam("file") MultipartFile file,@RequestParam("coId") Integer coId)throws IllegalStateException, IOException{
        System.out.println(coId);
        String fileName = file.getOriginalFilename();  //文件名
        String type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;  //后缀
        if(type != null){
            if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                //判断后缀是否为图片
                String realPath = ResourceUtils.getURL("classpath:").getPath()+"static/static/picture/";
                String uuid = UUID.randomUUID().toString().replaceAll("-","");
                String path = realPath+uuid+"."+type;
                System.out.println(path);
                file.transferTo(new File(path));
                images.add(uuid+"."+type);
            }else{
                //后缀错误，传的不是图片

            }
        }else{
            //没有后缀
        }
        return "text";
    }

    @RequestMapping("/api/a/commentAndReply/{pid}/{goodorbad}")   //查询所有评论回复
    public String commentAndReply(@PathVariable(value = "pid") Integer pid,
                                  @PathVariable(value = "goodorbad") Integer goodorbad,
                                  Model mod){
        List<Comment> list = wps.commentAndReply(pid, goodorbad==null?0:goodorbad, 1);  //查全部  开始查第一叶   pagenum 没用了
        mod.addAttribute("commentAndReply",list);
        mod.addAttribute("navCategory", wps.navCategory());
        /*mod.addAttribute("pageNum",redis.get("pageNum"));       //当前页码
        mod.addAttribute("pages",redis.get("pages"));           //总页数*/
        mod.addAttribute("count",wps.selectCount(pid,0));   //所有评论数量
        mod.addAttribute("good",wps.selectCount(pid,1));   //好评数量
        mod.addAttribute("bad",wps.selectCount(pid,2));   //坏评数量
        mod.addAttribute("pie",wps.selectProductImageEdition(pid));
        mod.addAttribute("user",redis.get("user"));
        return "pinglun";
    }

    @RequestMapping("/api/a/canShu/{pid}")
    public String canShu(Integer pid){

        return "xiangqing";
    }

    @RequestMapping("/api/a/commentXiang/{coId}")
    public String commentXiang(@PathVariable(value = "coId") Integer coId, Model mod){
        mod.addAttribute("comment",wps.commentByCoId(coId));
        mod.addAttribute("user",redis.get("user"));
        return "commentXiang";
    }

    @RequestMapping("/api/a/ajaxInsertReply")
    @ResponseBody
    public String ajaxInsertReply(Integer coId, String rcontent){
        Reply reply = new Reply();
        reply.setRcontent(rcontent);
        Comment comment = new Comment();
        comment.setCoId(coId);
        reply.setComment(comment);
        User user = (User)redis.get("user");
        if(user != null){
            reply.setUser(user);
            int yong = wps.insertReply(reply);
            if(yong >0){
                List<Reply> list = wps.replyByCoid(coId);
                String json = JSON.toJSONString(list);
                return json;
            }
        }
        redis.set("user",user);
        return JSON.toJSONString(new User());
    }

    @RequestMapping("/api/a/ajaxCommentGengDuo/{pid}/{pageNum}/{goodorbad}")
    @ResponseBody           //弃用了
    public String ajaxCommentGengDuo(@PathVariable(value = "pid") Integer pid,
                                     @PathVariable(value = "pageNum") Integer pageNum,
                                     @PathVariable(value = "goodorbad") Integer goodorbad,
                                     Model mod){
        List<Comment> list = wps.commentAndReply(pid,goodorbad==null?0:goodorbad,pageNum);
       // mod.addAttribute("pageNum",redis.get("pageNum"));       //当前页码   需要更新当前页码
        String json = JSON.toJSONString(list);
        System.out.println(json);
        return json;
    }

    @RequestMapping("/api/a/ajaxNav")
    @ResponseBody
    public String ajaxNav(Integer cid, String callback) {
        //session.setAttribute("navProduct",wps.navProduct(cid)); //头部 分类下的销量最高的商品 五个
        List<Product> products = wps.navProduct(cid, 6);
        String json = callback + "(" + JSON.toJSONString(products) + ")";
        return json;
    }

    @RequestMapping("/api/a/productByCid")      //菜单查询夫分类下的24个商品
    @ResponseBody
    public String ajaxProductByFuCid(Integer cid, String callback) {
        return callback + "(" + JSON.toJSONString(wps.productByFuCid(cid, 24)) + ")";
    }

    @RequestMapping("/api/a/ajaxCommentByPidOne")
    @ResponseBody
    public String ajaxCommentByPidOne(@RequestParam Integer pid, String callback) {
        String json = JSON.toJSONString(wps.comment(pid, 1, 1));
        return callback + "(" + json + ")";  //查询该商品下的 一条好评
    }

    @RequestMapping("/api/a/ajaxProductByLike")
    @ResponseBody
    public String ajaxProductByLike(@RequestParam String pname, String callback) {
        return callback + "(" + JSON.toJSONString(wps.productByLike(pname, 5)) + ")";
    }

    @RequestMapping("/api/a/ajaxInsertLove")
    @ResponseBody
    public int ajaxInsertLove(Integer pid){
        User user = (User)redis.get("user");
        if(user != null){
            int yong = wps.inserLove(pid, user.getUid());
            redis.set("user",user);
            return yong;
            //已经登陆
        }
        return -1;  //没有登陆 不能加入喜欢
    }

    @RequestMapping("/api/a/ajaxDeleteLove")
    @ResponseBody
    public int ajaxDeleteLove(Integer pid){
        User user = (User)redis.get("user");
        int yong = wps.deleteLove(pid, user.getUid());
        redis.set("user",user);
        return yong;
        //删除不需要判断是否登陆
    }

    @RequestMapping("/api/a/ajaxIsLove")
    @ResponseBody
    public int ajaxIsLove(Integer pid){
        User user = (User)redis.get("user");
        if(user != null){
            int yong = wps.isLove(pid, user.getUid());
            redis.set("user",user);
            return yong;
        }
        redis.set("user",user);
        return -1;
        //删除不需要判断是否登陆
    }

    /*@RequestMapping("/api/a/picture")
    @ResponseBody
    public String picture(@RequestParam("file") List<MultipartFile> list)throws IllegalStateException, IOException {
        if(list != null){
            Map<String,List<String>> tus = new HashMap<>();
            List<String> ls = new ArrayList<>();
            for(MultipartFile m : list){
                String fileName = m.getOriginalFilename();  //文件名
                String type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;  //后缀
                if(type != null){
                    if ("GIF".equals(type.toUpperCase())||"PNG".equals(type.toUpperCase())||"JPG".equals(type.toUpperCase())) {
                        //判断后缀是否为图片
                        String realPath = ResourceUtils.getURL("classpath:").getPath()+"static/static/picture/";
                        String uuid = UUID.randomUUID().toString().replaceAll("-","");
                        System.out.println(realPath);
                        String path = realPath+uuid+"."+type;
                        m.transferTo(new File(path));
                        ls.add("/static/picture/"+uuid+"."+type);

                    }else{
                        //后缀错误，传的不是图片

                    }
                }else{
                    //没有后缀
                }
            }
            tus.put("url",ls);
            String json = JSON.toJSONString(tus);
            System.out.println(json);
            return json;
        }else{
            return "";
        }

    }*/


}
