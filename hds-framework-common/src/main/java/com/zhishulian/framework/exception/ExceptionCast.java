package com.zhishulian.framework.exception;

import com.zhishulian.framework.model.response.ResultCode;

public class ExceptionCast {

    public static void cast(ResultCode resultCode){
        throw new CustomException(resultCode);
    }
}
