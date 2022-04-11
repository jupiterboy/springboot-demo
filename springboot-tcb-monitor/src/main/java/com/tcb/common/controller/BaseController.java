package com.tcb.common.controller;

import com.tcb.common.model.manager.CollectorManager;
import com.tcb.common.model.manager.DeviceManager;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseController<S> {

    @Autowired
    protected CollectorManager collectorManager;

    @Autowired
    protected DeviceManager deviceManager;

    @Autowired
    protected S service;

}
