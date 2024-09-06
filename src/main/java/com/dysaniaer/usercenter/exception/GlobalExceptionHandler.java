package com.dysaniaer.usercenter.exception;

import com.dysaniaer.usercenter.common.BaseResponse;
import com.dysaniaer.usercenter.common.ErrorCode;
import com.dysaniaer.usercenter.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 *
 * @author RationalDysaniaer
 * @date 2024/9/6 8:55
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)//只捕获BusinessException异常
    public BaseResponse businessExceptionHandler(BusinessException e) {
        log.error("businessException: "+e.getMessage(),e);
        return ResultUtils.error(e.getCode(),e.getMessage(),e.getDescription());
    }

    @ExceptionHandler(RuntimeException.class)
    public BaseResponse runtimeExceptionHandler(RuntimeException e) {
        log.info("runtimeException: ",e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR,e.getMessage(),"");
    }
}
