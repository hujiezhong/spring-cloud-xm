package com.peanut.controller;

import com.peanut.entity.*;
import com.peanut.service.*;
import com.peanut.util.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
public class PhoneController {

    @Autowired
    private CategoryService cs;

    @Autowired
    private ProductService ps;

    @Autowired
    private CommentService commentService;

    @Autowired
    private ProductImageService pis;

    @Autowired
    private CollectionService collectionService;

    @Autowired
    private CommentImageService cis;

    @Autowired
    private OrdersService os;

    @Autowired
    private ReplyService rs;

    @Autowired
    private RedisUtil redis;

    @Value("${server.port}")
    private String po;

    @RequestMapping("/api/a/list2")
    public List<Category> list(){
        List<Category> list = cs.list();   //分类
        System.out.println(po);
        return list;
    }

    @RequestMapping("list2Product")
    public List<Product> listProduct(){
        return ps.select(); // 商品
    }

    @RequestMapping("/api/a/index2")
    public String index(){
        return "index";
    }

    @RequestMapping("navCategory")
    public List<Category> navCategory(){
        return cs.listNav();
    }

    @RequestMapping("navProduct")
    public List<Product> navProduct(@RequestParam Integer cid, @RequestParam Integer size){
        return ps.selectNav(cid, size);
    }

    @RequestMapping("selectParent")
    public List<Category> selectParent(){
        List<Category> list = cs.selectParent();
        return list;
    }

    @RequestMapping("productByFuCid")
    public List<Product> productByFuCid(@RequestParam Integer cid, @RequestParam Integer size){
        return ps.productByFuCid(cid, size);
    }

    @RequestMapping("comment")   //查询评论 pid：商品 goodorbad：好坏评  size：查询多少 0为查询所有 或是 没有条件
    public List<Comment> comment(@RequestParam Integer pid, @RequestParam Integer goodorbad, @RequestParam Integer size){
        return commentService.comment(pid,goodorbad,size);
    }

    @RequestMapping("productByLike")    //模糊查询
    public List<Product> productByLike(@RequestParam String pname,@RequestParam Integer size){
        return ps.productByLike(pname, size);
    }

    @RequestMapping("selectImageProduct")   //查询大图商品
    public List<ProductImage> selectImageProduct(@RequestParam String color,
                                                 @RequestParam Integer cid,
                                                 @RequestParam Integer pid,
                                                 @RequestParam Integer size){
        return pis.selectImageProduct(color,cid, pid, size);
    }

    @RequestMapping("selectProductImageEdition")
    public Product selectProductImageEdition(@RequestParam Integer pid){
        return ps.selectProductImageEdition(pid);
    }

    @RequestMapping("inserLove")
    public int inserLove(@RequestParam Integer pid, @RequestParam Integer uid){
        return collectionService.inserLove(pid, uid);
    }

    @RequestMapping("deleteLove")
    public int deleteLove(@RequestParam Integer pid, @RequestParam Integer uid){
        return collectionService.deleteLove(pid, uid);
    }

    @RequestMapping("isLove")
    public int isLove(@RequestParam Integer pid, @RequestParam Integer uid){
        return collectionService.isLove(pid, uid);
    }

    @RequestMapping("commentAndReply")
    public List<Comment> commentAndReply(@RequestParam Integer pid, @RequestParam Integer goodorbad, @RequestParam Integer pageNum){
        //PageHelper.startPage(pageNum,5);
        System.out.println(pid);
        List<Comment> list = commentService.commentAndReply(pid,goodorbad);
        //Page<Comment> page = (Page<Comment>)list;
        //System.out.println("---"+page.getPageNum());
        //redis.set("pageNum",page.getPageNum());
       // redis.set("pages",page.getPages()); //总页数
        return list;
    }

    @RequestMapping(value = "insertComment")
    public int insertComment(@RequestBody Comment com){
        int i = commentService.insertComment(com);
        return com.getCoId();
    }

    @RequestMapping("selectCount")
    public int selectCount(@RequestParam Integer pid, @RequestParam Integer goodorbad){
        return commentService.selectCount(pid,goodorbad);
    }

    @RequestMapping("insertCommentImage")
    public int insertCommentImage(@RequestBody CommentImage ci){
        return cis.insertCommentImage(ci);
    }

    @RequestMapping("ordersByOname")
    public Orders ordersByOname(@RequestParam Integer uid, @RequestParam long oid, @RequestParam Integer pid){
        return os.ordersByOname(uid, oid, pid);
    }

    @RequestMapping("replyByCoid")
    public List<Reply> replyByCoid(@RequestParam Integer coId){
        return rs.replyByCoid(coId);
    }

    @RequestMapping("insertReply")
    public int insertReply(@RequestBody Reply reply){
        return rs.insertReply(reply);
    }

    @RequestMapping("commentBtCoId")
    public Comment commentByCoId(@RequestParam Integer coId){
        return commentService.commentByCoId(coId);
    }

}
