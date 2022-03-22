package com.jupiterboy.springboot.mybatisplus.parameter;

import java.io.Serializable;

public interface IMonitorItemParameter extends Serializable {

    MonitorItemParameter toMonitorItemParameter();
}
