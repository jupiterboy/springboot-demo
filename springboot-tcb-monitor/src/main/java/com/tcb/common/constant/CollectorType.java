package com.tcb.common.constant;

/**
 * @author yuhai
 *
 * 采集器类型定义
 */
public class CollectorType {

    public static final int MODULE = 1;

    // TODO: 定义具体采集器类型

    public static String getName(int type) {
        switch (type) {
        case MODULE:
            return "模块";
        default:
            return "未知";
        }
    }
}
