package com.whkobe.api;

import com.whkobe.pojo.Category;
import com.whkobe.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface CategoryApi extends JpaRepository<Category,Integer>,JpaSpecificationExecutor<Category> {
    //根据cid查分类名
    Category findByCid(int cid);

    //查出分类下所有品牌
    List<Category> findByParentid(int parentid);
}
