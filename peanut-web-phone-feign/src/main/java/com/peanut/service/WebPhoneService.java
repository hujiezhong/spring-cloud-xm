package com.peanut.service;

        import com.peanut.entity.Category;
        import com.peanut.entity.Comment;
        import com.peanut.entity.Product;
        import org.springframework.cloud.openfeign.FeignClient;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestParam;

        import java.util.List;

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

}
