package com.tcb.common.event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tcb.common.constant.MonitorEventType;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public abstract class SpringApplicationEvent extends ApplicationEvent implements MonitorEvent {

    private final int type;
    private final String name;

    public abstract String getSourceId();
    public abstract String getSourceName();

    public SpringApplicationEvent(Object source, int type) {
        super(source);
        this.type = type;
        this.name = MonitorEventType.getName(type);
    }

    @JsonIgnore
    @Override
    public Object getSource() {
        return super.getSource();
    }
}
