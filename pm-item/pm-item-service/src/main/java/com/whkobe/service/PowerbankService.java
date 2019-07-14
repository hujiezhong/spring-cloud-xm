package com.whkobe.service;

import com.whkobe.api.*;
import com.whkobe.pojo.*;
import com.whkobe.utils.RedisUtil;
import lombok.val;
import org.hibernate.query.criteria.internal.expression.function.AggregationFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.query.PartTreeJpaQuery;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.persistence.OrderBy;
import javax.persistence.criteria.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
/*@Transactional*/
public class PowerbankService {

    @Autowired
    private PowerbankApi powerbankApi;
    @Autowired
    private CategoryApi categoryApi;
    @Autowired
    private OrdersApi ordersApi;
    @Autowired
    private CollectionsApi collectionsApi;
    @Autowired
    private OrderdetailsApi orderdetailsApi;

    public List<Product> queryPowerbank(Integer cid){
        List<Product> list = this.powerbankApi.findByCid(cid);
        return list;
    }

    public Category queryCname(Integer cid){
        Category category=this.categoryApi.findByCid(cid);
        return category;
    }

    public List<Category> queryPingp(Integer parentid){
        List<Category> categoryList=this.categoryApi.findByParentid(parentid);
        return categoryList;
    }

    public List<Product> findBySepc(Product product, String tiaojian) {
        if (tiaojian.equals("jianjia")) {
            List<Product> products = this.powerbankApi.findAll(ProductSpec.toFenLei(product),new Sort(Sort.Direction.ASC, "shopprice"));
            return products;
        } else if (tiaojian.equals("comment")) {
            product.setPostage(1);
            List<Product> products = this.powerbankApi.findAll(ProductSpec.toFenLei2(product));
            int cc=0;
            for (Product product1 : products) {
                if (product1.getComment()!=null){
                     cc=1;
                }
            }
            if (cc==1){
                Collections.sort(products,new ProductSort());
            }
            return  products;

        } else {
            List<Product> products=this.powerbankApi.findAll(ProductSpec.toFenLei(product), new Sort(Sort.Direction.DESC, tiaojian));
            return products;
        }
    }

    public Product findByPid(int pid){
        return powerbankApi.findByPid(pid);
    }

    public List<Product> findByPname(Product product,String tiaojian){
        if (tiaojian.equals("jianjia")) {
            List<Product> products = this.powerbankApi.findAll(ProductSpec.toFenLei3(product), new Sort(Sort.Direction.ASC, "shopprice"));
            return products;
        } else if (tiaojian.equals("comment")) {
            product.setPostage(1);
            List<Product> products = this.powerbankApi.findAll(ProductSpec.toFenLei3(product));
            Collections.sort(products,new ProductSort());
            return  products;

        } else {
            List<Product> products = this.powerbankApi.findAll(ProductSpec.toFenLei3(product), new Sort(Sort.Direction.DESC, tiaojian));
            return products;
        }
    }

    public List<Category> findLikePname(Product product){
        return this.categoryApi.findAll(ProductSpec.toFenLei4(product));
    }

    public void saveOrders(Orders orders){
        this.ordersApi.save(orders);
    }

    public void saveCol(com.whkobe.pojo.Collections collections){
        com.whkobe.pojo.Collections collections1 = this.collectionsApi.findByPidAndUid(collections.getPid(),collections.getUid());
        if (collections1!=null){

        }else {
            this.collectionsApi.save(collections);
        }
    }
    public void delCol(com.whkobe.pojo.Collections collections){
        this.collectionsApi.deleteByPidAndUid(collections.getPid(),collections.getUid());
    }

    @Autowired
    private AddressApi addressApi;

    public void saveAdd(Address address){

        this.addressApi.save(address);
    }

    public List<Address> getAdd(int uid){
        return this.addressApi.findByUid(uid);
    }

    public Address findAddByAid(int aid){
        return this.addressApi.findByAid(aid);
    }

    @Autowired
    RedisUtil redisUtil;

    public com.peanut.entity.User getUser(){
        return  (com.peanut.entity.User)redisUtil.get("user");
    }


    public void saveOdetail(Orderdetails orderdetails){
        this.orderdetailsApi.save(orderdetails);
    }

    public void updateInventory(int inventory,int pid){

        this.powerbankApi.updateInventory(inventory,pid);
    }
//       * 复杂查询测试
//     * @param page
//     * @param size
//     * @return
//             */
//    public Page<Task> findBySepc(int page, int size){
//
//        PageRequest pageReq = this.buildPageRequest(page, size);
//        Page<Task> tasks = this.taskDao.findAll(new MySpec(), pageReq);
//        //传入了new MySpec() 既下面定义的匿名内部类 其中定义了查询条件
//        return tasks;
//
//    }
//
//    /**
//     * 建立分页排序请求
//     * @param page
//     * @param size
//     * @return
//     */
//    private PageRequest buildPageRequest(int page, int size) {
//        Sort sort = new Sort(Direction.DESC,"createTime");
//        return new PageRequest(page,size, sort);
//    }

}
