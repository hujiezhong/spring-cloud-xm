package com.whkobe.vo;

import com.whkobe.enums.ExceptionEnum;
import lombok.Data;

@Data
public class ExceptionResult {
    private  int status;
    private String message;
    private long timestamp;

    public ExceptionResult(ExceptionEnum exceptionEnum){
        this.status=exceptionEnum.getCode();
        this.message= exceptionEnum.getMsg();
        this.timestamp=System.currentTimeMillis();
    }
}
