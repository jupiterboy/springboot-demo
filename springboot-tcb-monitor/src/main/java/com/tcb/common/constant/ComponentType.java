package com.tcb.common.constant;

/**
 * @author yuhai
 *
 * 监控设备组件类型定义
 */
public class ComponentType {

    /**
     * 设备组
     */
    public static final int DEVICE = 0;

    /**
     * 组件
     */
    public static final int DEVICE_MODULE = 1;

    /**
     * 部件
     */
    public static final int COMPONENT = 2;

    public static String getName(int type) {
        switch (type) {
            case DEVICE:
                return "设备组";
            case DEVICE_MODULE:
                return "组件";
            case COMPONENT:
                return "部件";
            default:
                return "未知";
        }
    }
}
