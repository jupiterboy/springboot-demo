package com.jupiterboy.springboot.jpa.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "department")
@Getter
@Setter
@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class Department implements java.io.Serializable {

    public static final int STATUS_DISABLED = 0;

    public static final int STATUS_ENABLED = 1;

    @Column(nullable = false)
    @Id
    @GeneratedValue(generator = "system-uuid")
    private String id;

    private String departmentName;

    private String departmentDesc;

    private Integer status;

    private Long userId;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private List<Department> children = new ArrayList<Department>();

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", departmentDesc='" + departmentDesc + '\'' +
                ", status=" + status +
                ", userId=" + userId +
                ", children=" + children +
                '}';
    }
}