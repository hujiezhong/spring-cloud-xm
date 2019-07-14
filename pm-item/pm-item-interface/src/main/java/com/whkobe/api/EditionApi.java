package com.whkobe.api;

import com.whkobe.pojo.Edition;
import com.whkobe.pojo.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EditionApi extends JpaRepository<Edition,Integer>, JpaSpecificationExecutor<Edition> {
}
