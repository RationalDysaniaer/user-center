package com.dysaniaer.usercenter.common;

/**
 * 返回结果工具类
 *
 * @author RationalDysaniaer
 * @date 2024/9/6 8:54
 */
public class ResultUtils {
    public static <T>  BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "success");
    }

    public static  BaseResponse error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    public static  BaseResponse error(ErrorCode errorCode, String message, String description) {
        return new BaseResponse<>(errorCode.getCode(),null, message, description);
    }

    public static  BaseResponse error(int code, String message, String description) {
        return new BaseResponse<>(code, null,message, description);
    }

    public static  BaseResponse error(ErrorCode errorCode, String description) {
        return new BaseResponse<>(errorCode.getCode(),null, description);
    }
}
