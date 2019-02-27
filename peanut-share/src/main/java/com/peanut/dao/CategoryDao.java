package com.peanut.dao;

import com.peanut.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryDao {

    public List<Category> list();

    List<Category> listNav();

    List<Category> selectParent();

}
