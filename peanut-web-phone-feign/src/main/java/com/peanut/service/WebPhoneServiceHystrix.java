package com.peanut.service;

import com.github.pagehelper.Page;
import com.peanut.entity.*;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class WebPhoneServiceHystrix implements FallbackFactory<WebPhoneService> {

    @Override
    public WebPhoneService create(Throwable throwable) {

        return new WebPhoneService() {
            @Override
            public List<Category> list() {
                Category c = new Category();
                c.setCname("此服务已关闭，请稍后再试！");
                List<Category> list = new ArrayList<>();
                list.add(c);
                return list;
            }

            @Override
            public String index() {
                return "此服务已关闭，请稍后再试！";
            }

            @Override
            public List<Product> listProduct() {
                Product p = new Product();
                p.setPname("此服务已关闭，请稍后再试！");
                List<Product> list = new ArrayList<>();
                list.add(p);
                return list;
            }

            @Override
            public List<Category> navCategory() {
                return null;
            }

            @Override
            public List<Product> navProduct(Integer cid, Integer size) {
                return null;
            }

            @Override
            public List<Category> selectParent() {
                return null;
            }

            @Override
            public List<Product> productByFuCid(Integer cid, Integer size) {
                return null;
            }

            @Override
            public List<Comment> comment(Integer pid, Integer goodorbad, Integer size) {
                return null;
            }

            @Override
            public List<Product> productByLike(String pname, Integer size) {
                return null;
            }

            @Override
            public List<ProductImage> selectImageProduct(String color, Integer cid, Integer pid, Integer size) {
                return null;
            }

            @Override
            public Product selectProductImageEdition(Integer pid) {
                return null;
            }

            @Override
            public int inserLove(Integer pid, Integer uid) {
                return 0;
            }

            @Override
            public int deleteLove(Integer pid, Integer uid) {
                return 0;
            }

            @Override
            public int isLove(Integer pid, Integer uid) {
                return 0;
            }

            @Override
            public List<Comment> commentAndReply(Integer pid, Integer goodorbad, Integer pageNum) {
                return null;
            }

            @Override
            public int insertComment(Comment com) {
                return 0;
            }

            @Override
            public int selectCount(Integer pid, Integer goodorbad) {
                return 0;
            }

            @Override
            public int insertCommentImage(CommentImage ci) {
                return 0;
            }

            @Override
            public Orders ordersByOname(Integer uid, long oid, Integer pid) {
                return null;
            }

            @Override
            public List<Reply> replyByCoid(Integer coId) {
                return null;
            }

            @Override
            public int insertReply(Reply reply) {
                return 0;
            }

            @Override
            public Comment commentByCoId(Integer coId) {
                return null;
            }
        };

    }
}
