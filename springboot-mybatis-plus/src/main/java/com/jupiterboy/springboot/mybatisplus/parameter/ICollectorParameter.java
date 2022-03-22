package com.jupiterboy.springboot.mybatisplus.parameter;

import java.io.Serializable;

public interface ICollectorParameter extends Serializable {

    CollectorParameter toCollectorParameter();
}
