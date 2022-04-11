package com.tcb.common.repository;

import com.tcb.common.entity.ChannelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelRepository extends JpaRepository<ChannelEntity, String> {

    @Query(value = "select * from channel where collector_id = ?1", nativeQuery = true)
    List<ChannelEntity> findByCollectorId(String collectorId);

}

