package com.whkobe.api;

import com.whkobe.pojo.Collections;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.transaction.annotation.Transactional;

public interface CollectionsApi extends JpaRepository<Collections,Integer> {
    @Modifying
    @Transactional
    void deleteByPidAndUid(int pid,int uid);

    Collections findByPidAndUid(int pid,int uid);
}
