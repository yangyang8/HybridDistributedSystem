package com.zhishulian.framework.exception;

import com.zhishulian.framework.model.response.CommonCode;
import com.zhishulian.framework.model.response.ResultCode;

/**
 * 自定义异常类
 * 主要是处理可知的异常
 */
public class CustomException extends RuntimeException {
    private ResultCode resultCode;
    public CustomException(ResultCode resultCode){
        this.resultCode=resultCode;
    }


    public ResultCode getResultCode(){
        return this.resultCode;
    }
}
