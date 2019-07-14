package com.peanut.service;

import com.peanut.entity.Collections;

import java.util.List;

public interface CollectionService {

    int inserLove(Integer pid, Integer uid);

    int deleteLove(Integer pid, Integer uid);

    int isLove(Integer pid, Integer uid);

    List<Collections> secbylike(Integer uid);

    int delbycolid (Integer colid);

    int selikecount(Integer uid);

}
