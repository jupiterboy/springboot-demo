package com.tcb.common.repository;

import com.tcb.common.entity.ChannelValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChannelValueRepository extends JpaRepository<ChannelValueEntity, Long> {

}

