package com.tcb.common.constant;

/**
 * @author yuhai
 *
 * 采集器模块类型定义
 *
 */
public class ModuleType {

    /**
     * 未知模块
     */
    public static final int UNKNOWN = 0;

    /**
     * 振动模块
     */
    public static final int VIBRATION = 1;

    /**
     * 温度模块
     */
    public static final int TEMPERATURE = 2;

    // TODO: 定义具体的采集器模块类型

    public static String getName(int type) {
        switch (type) {
            case UNKNOWN:
                return "未知模块";
            case VIBRATION:
                return "振动模块";
            case TEMPERATURE:
                return "温度模块";
            default:
                return "未知模块";
        }
    }
}
