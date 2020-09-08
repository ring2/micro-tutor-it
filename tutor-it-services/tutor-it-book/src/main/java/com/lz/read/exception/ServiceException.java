package com.lz.read.exception;

import com.lz.read.common.CommonStatus;
import lombok.Getter;

/**
 * @author :     lz
 * @date :       2020/4/1 11:40
 * description:  服务 异常处理类
 **/
@Getter
public class ServiceException extends RuntimeException {
    private CommonStatus commonStatus;
    public ServiceException(){
        super("服务出现未知异常");
    }
    public ServiceException(CommonStatus exceptionStatus){
        super(exceptionStatus.getMessage());
        this.commonStatus = exceptionStatus;
    }
}
