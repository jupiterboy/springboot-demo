package com.tcb.common.constant;

/**
 * @author yuhai
 *
 * 监测项项类型定义
 */
public class MonitorItemType {

    /**
     * 未知类型
     */
    public static final int DEFAULT = 0;

    // TODO:定义具体的监测项类型

    /**
     * 类型1
     */
    public static final int TYPE_1 = 1;

    /**
     * 类型2
     */
    public static final int TYPE_2 = 2;

    public static String getName(int type) {
        switch (type) {
            case TYPE_1:
                return "类型1";
            case TYPE_2:
                return "类型2";
            default:
                return "未知类型";
        }
    }

}
