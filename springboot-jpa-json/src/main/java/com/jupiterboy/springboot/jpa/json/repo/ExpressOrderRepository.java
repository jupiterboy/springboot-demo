package com.jupiterboy.springboot.jpa.json.repo;

import com.jupiterboy.springboot.jpa.json.entity.ExpressOrder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public interface ExpressOrderRepository extends JpaRepository<ExpressOrder, Long>, JpaSpecificationExecutor<ExpressOrder> {

    default List<ExpressOrder> selectByCargoName(String cargoName){
        Specification<ExpressOrder> query = new Specification<ExpressOrder>() {

            @Override
            public Predicate toPredicate(Root<ExpressOrder> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(criteriaBuilder.equal(
                                criteriaBuilder.function(
                                        "JSON_EXTRACT",
                                        String.class,
                                        root.get("cargoModel"),
                                        criteriaBuilder.literal("$.name")
                                ), cargoName));

                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };

        return findAll(query);
    };

    @Query("select s from ExpressOrder s where JSON_EXTRACT(cargoModel, '$.name')=?1")
    List<ExpressOrder> findByCargoName(String cargoName);
}