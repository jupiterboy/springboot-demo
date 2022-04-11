package com.tcb.common.model.manager;

import com.tcb.common.entity.ComponentEntity;
import com.tcb.common.model.ConfigModel;
import com.tcb.common.model.MeasurePoint;
import com.tcb.common.model.parameter.ComponentParameter;

import java.util.HashSet;
import java.util.Set;

public class MeasurePointManager extends ConfigModel<ComponentEntity, ComponentParameter> {

    private Set<MeasurePoint> measurePoints = new HashSet<>(0);

    public MeasurePointManager(ComponentEntity entity) {
        super(entity);
    }

    public void addMeasurePoint(MeasurePoint measurePoint) {
        this.measurePoints.add(measurePoint);
    }

    public void removeMeasurePoint(MeasurePoint measurePoint) {
        this.measurePoints.remove(measurePoint);
    }

    public Set<MeasurePoint> getMeasurePoints() {
        return this.measurePoints;
    }

    public MeasurePoint getMeasurePoint(String id) {
        return this.measurePoints.stream()
                .filter(measurePoint -> measurePoint.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

}
