package com.whkobe.api;

import com.whkobe.pojo.Orderdetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderdetailsApi extends JpaRepository<Orderdetails,Integer> {
}
