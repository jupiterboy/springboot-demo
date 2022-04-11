package com.tcb.common.repository;

import com.tcb.common.entity.SnapshotEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SnapshotRepository extends JpaRepository<SnapshotEntity, Long> {

}

