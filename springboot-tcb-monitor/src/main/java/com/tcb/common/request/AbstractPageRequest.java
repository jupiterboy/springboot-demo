package com.tcb.common.request;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public abstract class AbstractPageRequest extends AbstractRequest {

    @NotNull(message = "页大小不能为空")
    @Range(min = 1, max = 100)
    private Integer pageSize;

    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码不能小于1")
    private Integer pageNum;

    public Pageable getPageable() {
        return getPageable("createTime");
    }

    public Pageable getPageable(String... properties) {
        return PageRequest.of(pageNum, pageSize, getSort(), properties);
    }

}
