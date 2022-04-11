package com.tcb.common.constant;

public class ConnectionStatus {

    /**
     * 下线状态
     */
    public static final int OFFLINE = 0;

    /**
     * 上线状态
     */
    public static final int ONLINE = 1;

    public static String getName(int status) {
        switch (status) {
            case OFFLINE:
                return "下线";
            case ONLINE:
                return "上线";
            default:
                return "未知";
        }
    }
}
