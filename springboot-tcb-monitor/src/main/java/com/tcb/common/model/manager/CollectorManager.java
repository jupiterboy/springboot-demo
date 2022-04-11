package com.tcb.common.model.manager;

import com.tcb.common.constant.CacheKeyPrefix;
import com.tcb.common.entity.CollectorEntity;
import com.tcb.common.entity.MeasurePointEntity;
import com.tcb.common.model.Channel;
import com.tcb.common.model.Collector;
import com.tcb.common.model.CollectorModule;
import com.tcb.common.model.MeasurePoint;
import com.tcb.common.utils.JsonUtils;
import com.tcb.modules.config.service.CollectorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class CollectorManager {

    @Autowired
    private CollectorService collectorService;

    @Autowired
    private DeviceManager deviceManager;

    @Autowired
    private StringRedisTemplate template;

    private Set<Collector> collectors = new HashSet<>(0);

    // 从数据库中获取所有采集器数据，加载到内存中
    @PostConstruct
    public void init(){
        flushCache();
        loadCollectors();
        // 初始化通道绑定, 将通道绑定到监测点
        initChannelBind();
    }
//    @Bean
//    @TimeIt
    public List<CollectorEntity> loadCollectors(){
        List<CollectorEntity> collectorEntities = Collections.EMPTY_LIST;
        int size = template.keys(CacheKeyPrefix.COLLECTOR_ENTITY + "*").size();
        long start = System.currentTimeMillis();
        if(size == 0){
            collectorEntities = collectorService.findAllEntities();
            log.info("load collector entities from db.");
            System.out.println(System.currentTimeMillis() - start);
            collectorEntities.forEach(collectorEntity -> {
                updateCollector(collectorEntity);
                updateCache(collectorEntity);
            });
        }else{
            collectorEntities = template.keys(CacheKeyPrefix.COLLECTOR_ENTITY + "*").stream()
                    .map(key -> template.opsForValue().get(key))
                    .filter(value -> value != null)
                    .map(value -> JsonUtils.fromJson(value, CollectorEntity.class))
                    .collect(Collectors.toList());
            log.info("load collector entities from redis.");
            collectorEntities.forEach(collectorEntity -> {
                updateCollector(collectorEntity);
            });
        }
        log.info("load collector data success. cost: {}ms" , (System.currentTimeMillis() - start));
        return collectorEntities;
    }

    public void addCollector(Collector collector){
        collectors.add(collector);
    }

    public void removeCollector(String id){
        collectors.removeIf(collector -> collector.getId().equals(id));
    }

    public Set<Collector> getCollectors() {
        return collectors;
    }

    // get collector
    public Collector getCollector(String id){
        return collectors.stream()
                .filter(collector -> collector.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Collector take(String number){
        return collectors.stream()
                .filter(collector -> collector.getNumber().equals(number))
                .findFirst()
                .orElse(null);
    }

    // get channel
    public Channel getChannel(String id){
        return collectors.stream()
                .flatMap(collector -> collector.getChannels().stream())
                .filter(channel -> channel.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Channel takeChannel(String collectorNumber, String channelNumber){
        return collectors.stream()
                .filter(collector -> collector.getNumber().equals(collectorNumber))
                .flatMap(collector -> collector.getChannels().stream())
                .filter(channel -> channel.getNumber().equals(channelNumber))
                .findFirst()
                .orElse(null);
    }

    // get module
    public CollectorModule getCollectorModule(String id){
        return collectors.stream()
                .flatMap(collector -> collector.getCollectorModules().stream())
                .filter(module -> module.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public void initChannelBind(){
        log.info("init channel bind, collector size: {}", collectors.size());
        collectors.stream().flatMap(collector -> collector.getChannels().stream()).forEach(channel -> {
            MeasurePointEntity measurePointEntity = channel.getEntity().getMeasurePointEntity();
            if(measurePointEntity != null){
                MeasurePoint measurePoint = deviceManager.getMeasurePoint(measurePointEntity.getId());
                if(measurePoint != null){
                    // 模型相互关联
                    channel.setMeasurePoint(measurePoint);
                    measurePoint.setChannel(channel);
                    // 实体类相互关联
                    channel.getEntity().setMeasurePointEntity(measurePoint.getEntity());
                    measurePoint.getEntity().setChannelEntity(channel.getEntity());
                    log.debug("bind channel {} to measure point {}", channel.getId(), measurePoint.getId());
                }else{
                    log.warn("measure point {} not found", measurePointEntity.getId());
                }
            }else{
                log.warn("channel {} has no measure point", channel.getId());
            }
        } );
    }

    public Set<Collector> getCollectors(String ordId){
        return collectors.stream()
                .filter(collector -> collector.getOrgId().equals(ordId))
                .collect(Collectors.toSet());
    }

    public void updateCollector(String collectorId){
        CollectorEntity collectorEntity = collectorService.getEntity(collectorId);
        updateCollector(collectorEntity);
        updateCache(collectorEntity);
//        System.out.println(JsonUtils.toPrettyJson(collectorEntity));
    }

    private void updateCollector(CollectorEntity collectorEntity) {
        if(collectorEntity != null){
            Collector collector = getCollector(collectorEntity.getId());
            if(collector == null){
                collector = new Collector(collectorEntity);
                addCollector(collector);
            }
            collector.update(collectorEntity);
        }
    }

    private void updateCache(CollectorEntity collectorEntity) {
        String key = CacheKeyPrefix.COLLECTOR_ENTITY + ":" + collectorEntity.getId();
        String value = JsonUtils.toJson(collectorEntity);
        template.opsForValue().set(key, value);
    }

    public void flushCache(){
        template.keys(CacheKeyPrefix.COLLECTOR_ENTITY + "*").forEach(key -> {
            template.delete(key);
        });
    }

    public void updateCache(){
        flushCache();
        loadCollectors();
    }

    /**
     * 根据采集器编号检查采集器是否存在
     * @param collectorNumber    采集器编号
     * @return  true:存在，false:不存在
     */
    public boolean contains(String collectorNumber){
        return collectors.stream().anyMatch(collector -> collector.getNumber().equals(collectorNumber));
    }
}
