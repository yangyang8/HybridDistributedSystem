package com.zhishulian.framework.exception;

import com.google.common.collect.ImmutableMap;
import com.zhishulian.framework.model.response.CommonCode;
import com.zhishulian.framework.model.response.ResponseResult;
import com.zhishulian.framework.model.response.ResultCode;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 异常捕获类
 * 统一异常获取处理类
 */
@ControllerAdvice
public class ExceptionCatch {



    //定义map，配置异常类型所对应的错误代码
    private static ImmutableMap<Class<? extends Throwable>,ResultCode> EXCEPTIONS;
    //定义map的builder对象，去构建ImmutableMap
    protected static ImmutableMap.Builder<Class<? extends Throwable>,ResultCode> builder = ImmutableMap.builder();

    static {
        //定义异常类型所对应的错误代码
        builder.put(HttpMessageNotReadableException.class,CommonCode.INVALID_PARAM);
    }

    /**
     * 可知异常 也就是开发者自己抛出的异常
     * @param customException
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public ResponseResult customException(CustomException customException){
        ResultCode commonCode = customException.getResultCode();
        return new ResponseResult(commonCode);
    }


    /**
     * 不可知的异常，也就是Spring 框架或第三方框架抛出的Exception
     * @param Exception
     * @return
     */
    @ExceptionHandler
    @ResponseBody
    public ResponseResult exception(Exception Exception){
        if(EXCEPTIONS==null){
            EXCEPTIONS=builder.build();
        }
        ResultCode resultCode = EXCEPTIONS.get(Exception.getClass());
        if(resultCode!=null){
            return new ResponseResult(resultCode);
        }else{
            return new ResponseResult(CommonCode.SERVER_ERROR);
        }

    }

}
