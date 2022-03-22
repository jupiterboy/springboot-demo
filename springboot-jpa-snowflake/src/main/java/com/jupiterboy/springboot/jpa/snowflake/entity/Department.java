package com.jupiterboy.springboot.jpa.snowflake.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
@Getter
@Setter
@ToString
public class Department implements java.io.Serializable {

    public static final int STATUS_DISABLED = 0;

    public static final int STATUS_ENABLED = 1;

    @Id
    @GenericGenerator(name = "snowflakeId", strategy = "com.jupiterboy.springboot.jpa.snowflake.GenerateSnowflakeId" )
    @GeneratedValue(generator = "snowflakeId")
    private Long id;

    private String departmentName;

    private String departmentDesc;

    private Integer status;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private List<Department> children = new ArrayList<Department>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "department")
    private List<User> users = new ArrayList<User>();

}