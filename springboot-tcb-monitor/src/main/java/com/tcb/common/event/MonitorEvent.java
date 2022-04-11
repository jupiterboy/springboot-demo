package com.tcb.common.event;

public interface MonitorEvent {

    int getType();

    String getName();

    String getSourceId();

    String getSourceName();
}
