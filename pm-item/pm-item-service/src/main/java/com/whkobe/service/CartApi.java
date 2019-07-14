package com.whkobe.service;

import com.whkobe.pojo.Category;
import com.whkobe.pojo.Edition;
import com.whkobe.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CartApi extends JpaRepository<Edition,Integer>, JpaSpecificationExecutor<Edition> {
}
