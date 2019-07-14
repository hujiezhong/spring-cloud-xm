package com.peanut.service.impl;

import com.peanut.dao.CollectionDao;
import com.peanut.entity.Collections;
import com.peanut.service.CollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionDao cd;

    @Override
    public int inserLove(Integer pid, Integer uid) {
        return cd.inserLove(pid, uid);
    }

    @Override
    public int deleteLove(Integer pid, Integer uid) {
        return cd.deleteLove(pid, uid);
    }

    @Override
    public int isLove(Integer pid, Integer uid) {
        return cd.isLove(pid,uid);
    }

    @Override
    public List<Collections> secbylike(Integer uid) {
        return cd.secbylike(uid);
    }

    @Override
    public int delbycolid(Integer colid) {
        return cd.delbycolid(colid);
    }

    @Override
    public int selikecount(Integer uid) {
        return cd.selikecount(uid);
    }
}
