package com.whkobe.api;

import com.whkobe.pojo.Address;
import com.whkobe.pojo.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressApi  extends JpaRepository<Address,Integer> {

    List<Address> findByUid(int uid);

    Address findByAid(int aid);
}
