package com.tcb.common.repository;

import com.tcb.common.entity.ComponentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComponentRepository extends JpaRepository<ComponentEntity, String> {

    List<ComponentEntity> findByType(int type);

    List<ComponentEntity> findByOrgId(String orgId);
}

