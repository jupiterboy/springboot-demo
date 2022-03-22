package com.jupiterboy.springboot.jpa.snowflake.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class User {

    @Id
    @GenericGenerator(name = "snowflakeId", strategy = "com.jupiterboy.springboot.jpa.snowflake.GenerateSnowflakeId" )
    @GeneratedValue(generator = "snowflakeId")
    private Long id;

    private String userName;

    private String password;

    private int age;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

}
