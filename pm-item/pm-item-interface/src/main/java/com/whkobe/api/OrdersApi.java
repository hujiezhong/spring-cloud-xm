package com.whkobe.api;

import com.whkobe.pojo.Orders;
import com.whkobe.pojo.Product;
import org.hibernate.sql.Insert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrdersApi extends JpaRepository<Orders,Integer> {
}
