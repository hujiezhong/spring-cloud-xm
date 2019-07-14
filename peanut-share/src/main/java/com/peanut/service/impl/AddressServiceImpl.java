package com.peanut.service.impl;

import com.peanut.dao.AddressDao;
import com.peanut.entity.Address;
import com.peanut.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao ad;

    @Override
    public List<Address> seabi(int uid) {
        return ad.seabi(uid);
    }

    @Override
    public int deladd(int aid) {
        return ad.deladd(aid);
    }
}
