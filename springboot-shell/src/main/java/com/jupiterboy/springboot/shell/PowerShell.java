package com.jupiterboy.springboot.shell;

import com.tcb.modules.config.vo.CollectorVO;
import com.tcb.modules.config.vo.DeviceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.web.client.RestTemplate;

@ShellComponent
public class PowerShell {

    @Autowired
    private RestTemplate restTemplate;

    @ShellMethod("power on collector.")
    public void powerOn(String collectorNumber) {
        restTemplate.postForLocation("http://localhost:8080/shell/virtual/powerOn/" + collectorNumber , null);
    }

    @ShellMethod(value = "power on collector and active channels", key = "power")
    public void powerOn(String collectorNumber, String active) {
        restTemplate.postForLocation("http://localhost:8080/shell/virtual/powerOn/" + collectorNumber + "/" + active, null);
    }

    @ShellMethod("power off collector.")
    public void powerOff(String collectorNumber) {
        restTemplate.postForLocation("http://localhost:8080/shell/virtual/powerOff/" + collectorNumber , null);
    }

    @ShellMethod("list collectors.")
    public String listCollectors() {
        CollectorVO[] vos = restTemplate.getForObject("http://localhost:8080/shell/collector/list",CollectorVO[].class);
        return JsonUtils.toPrettyJson(vos);
    }

    @ShellMethod("list device.")
    public String listDevices() {
        DeviceVO[] vos = restTemplate.getForObject("http://localhost:8080/shell/device/list",DeviceVO[].class);
        return JsonUtils.toPrettyJson(vos);
    }
}
