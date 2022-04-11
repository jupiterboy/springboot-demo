package com.tcb.common.repository;

import com.tcb.common.entity.CollectorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CollectorRepository extends JpaRepository<CollectorEntity, String> {

    List<CollectorEntity> findByOrgId(String orgId);
}

