package com.jupiterboy.springboot.rabbitmq;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor // !!! 无参构造方法
public class User implements Serializable {

    private String username;
    private String password;

}
