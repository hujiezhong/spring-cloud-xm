package com.whkobe.api;

import com.sun.xml.internal.bind.v2.model.core.ID;
import com.whkobe.pojo.Category;
import com.whkobe.pojo.Product;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.criteria.Root;
import java.awt.print.Pageable;
import java.util.List;

public interface PowerbankApi extends JpaRepository<Product,Integer>,JpaSpecificationExecutor<Product> {
    //查分类商品
    List<Product> findByCid(int cid);

    Product findByPid(int pid);

    @Query(value = "update product set inventory=?1 where pid=?2",nativeQuery = true)
    @Modifying
    @Transactional
    public void updateInventory(int inventory,int pid);
}