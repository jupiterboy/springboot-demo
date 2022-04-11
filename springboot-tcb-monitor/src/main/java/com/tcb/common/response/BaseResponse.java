package com.tcb.common.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseResponse {

    public static final int SUCCESS = 0;
    public static final int FAIL = 1;

    private int code;

    private Object data;

    private String message;

    public static BaseResponse success(Object data) {
        return new BaseResponse(SUCCESS, data, null);
    }

    public static BaseResponse fail(String message) {
        return new BaseResponse(FAIL, null, message);
    }
}
