package com.peanut.service;

        import com.github.pagehelper.Page;
        import com.peanut.entity.*;
        import org.springframework.cloud.openfeign.FeignClient;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.RequestBody;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;
        import org.springframework.web.bind.annotation.RequestParam;

        import javax.ws.rs.POST;
        import java.util.List;
        import java.util.Map;

@FeignClient(value = "peanut-service-phone",fallbackFactory = WebPhoneServiceHystrix.class)
public interface WebPhoneService {

    @RequestMapping("/api/a/list2")
    public List<Category> list();

    @RequestMapping("/api/a/index2")
    public String index();

    @RequestMapping("list2Product")
    public List<Product> listProduct();

    @RequestMapping("navCategory")
    public List<Category> navCategory();

    @RequestMapping("navProduct")
    public List<Product> navProduct(@RequestParam Integer cid, @RequestParam Integer size);

    @RequestMapping("selectParent")
    public List<Category> selectParent();

    @RequestMapping("productByFuCid")
    public List<Product> productByFuCid(@RequestParam Integer cid, @RequestParam Integer size);

    @RequestMapping("comment")   //查询评论 pid：商品 goodorbad：好坏评  size：查询多少 0为查询所有 或是 没有条件
    public List<Comment> comment(@RequestParam Integer pid, @RequestParam Integer goodorbad, @RequestParam Integer size);

    @RequestMapping("productByLike")    //模糊查询
    public List<Product> productByLike(@RequestParam String pname, @RequestParam Integer size);

    @RequestMapping("selectImageProduct")   //查询大图商品
    public List<ProductImage> selectImageProduct(@RequestParam String color,
                                                 @RequestParam Integer cid,
                                                 @RequestParam Integer pid,
                                                 @RequestParam Integer size);

    @RequestMapping("selectProductImageEdition")
    public Product selectProductImageEdition(@RequestParam Integer pid);

    @RequestMapping("inserLove")
    public int inserLove(@RequestParam Integer pid, @RequestParam Integer uid);

    @RequestMapping("deleteLove")
    public int deleteLove(@RequestParam Integer pid, @RequestParam Integer uid);

    @RequestMapping("isLove")   //判断是否存在这个喜欢
    public int isLove(@RequestParam Integer pid, @RequestParam Integer uid);

    @RequestMapping("commentAndReply")
    public List<Comment> commentAndReply(@RequestParam Integer pid, @RequestParam Integer goodorbad, @RequestParam Integer pageNum);

    @RequestMapping(value = "insertComment")
    public int insertComment(@RequestBody Comment com);

    @RequestMapping("selectCount")
    public int selectCount(@RequestParam Integer pid,@RequestParam Integer goodorbad);

    @RequestMapping("insertCommentImage")
    public int insertCommentImage(@RequestBody CommentImage ci);

    @RequestMapping("ordersByOname")
    public Orders ordersByOname(@RequestParam Integer uid, @RequestParam long oid, @RequestParam Integer pid);

    @RequestMapping("replyByCoid")
    public List<Reply> replyByCoid(@RequestParam Integer coId);

    @RequestMapping("insertReply")
    public int insertReply(@RequestBody Reply reply);

    @RequestMapping("commentBtCoId")
    public Comment commentByCoId(@RequestParam Integer coId);

}
