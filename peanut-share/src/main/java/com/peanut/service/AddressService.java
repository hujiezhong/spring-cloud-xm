package com.peanut.service;

import com.peanut.entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> seabi(int uid);

    int deladd(int aid);
}
