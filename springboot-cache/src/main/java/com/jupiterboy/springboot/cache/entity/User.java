package com.jupiterboy.springboot.cache.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@GenericGenerator(name = "system-uuid", strategy = "org.hibernate.id.UUIDGenerator")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
@Table(name = "user")
@Data
@ToString
public class User implements Serializable {
    @Id
    @GeneratedValue(generator = "system-uuid")
    private String id;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Integer age;

}
