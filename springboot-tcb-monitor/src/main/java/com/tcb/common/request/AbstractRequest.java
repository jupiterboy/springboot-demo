package com.tcb.common.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.Past;
import java.util.Date;

@Data
public abstract class AbstractRequest {

    @Past(message = "起始时间必须为过去时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date startTime;

    @Past(message = "结束时间必须为过去时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date endTime;

    private String sort = "desc";

    public Sort.Direction getSort() {
        return sort.toLowerCase().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
    }
}
