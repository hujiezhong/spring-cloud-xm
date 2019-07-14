package com.peanut.dao;

import com.peanut.entity.Address;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressDao {
      List<Address> seabi(int uid);

      int deladd(int aid);
}
