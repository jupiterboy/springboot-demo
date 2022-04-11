package com.tcb.common.listener;

public interface EventListener<E> {

    void onEvent(E event);
}
