package com.peanut.service.impl;


import com.peanut.dao.CategoryDao;
import com.peanut.entity.Category;
import com.peanut.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao cd;

    @Override
    public List<Category> list() {
        return cd.list();
    }

    @Override
    public List<Category> listNav() {
        return cd.listNav();
    }

    @Override
    public List<Category> selectParent() {
        return cd.selectParent();
    }
}
