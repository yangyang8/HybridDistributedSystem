package com.zhishulian.auth.controlller;

import com.alibaba.fastjson.JSONObject;
import com.zhishulian.api.auth.AuthControllerApi;
import com.zhishulian.auth.service.AuthService;
import com.zhishulian.framework.domain.ucenter.ext.AuthToken;
import com.zhishulian.framework.domain.ucenter.request.LoginRequest;
import com.zhishulian.framework.domain.ucenter.response.AuthCode;
import com.zhishulian.framework.domain.ucenter.response.JwtResult;
import com.zhishulian.framework.domain.ucenter.response.LoginResult;
import com.zhishulian.framework.exception.ExceptionCast;
import com.zhishulian.framework.model.response.CommonCode;
import com.zhishulian.framework.model.response.ResponseResult;
import com.zhishulian.framework.utils.CookieUtil;
import com.zhishulian.framework.web.BaseController;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author Administrator
 * @version 1.0
 **/
@RestController
@RequestMapping("/")
public class AuthController extends BaseController implements AuthControllerApi {

    @Value("${auth.clientId}")
    String clientId;
    @Value("${auth.clientSecret}")
    String clientSecret;
    @Value("${auth.cookieDomain}")
    String cookieDomain;
    @Value("${auth.cookieMaxAge}")
    int cookieMaxAge;

    @Autowired
    AuthService authService;

    @Override
    @PostMapping("/userloginV2")
    public LoginResult login(LoginRequest loginRequest) {
        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getUsername())){
            ExceptionCast.cast(AuthCode.AUTH_USERNAME_NONE);
        }
        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getPassword())){
            ExceptionCast.cast(AuthCode.AUTH_PASSWORD_NONE);
        }
        //账号
        String username = loginRequest.getUsername();
        //密码
        String password = loginRequest.getPassword();

        //申请令牌
        AuthToken authToken =  authService.login(username,password,clientId,clientSecret);

        //用户身份令牌
        String access_token = authToken.getAccess_token();
        //将令牌存储到cookie
        this.saveCookie(access_token);

        return new LoginResult(CommonCode.SUCCESS,access_token);
    }



    @PostMapping("/userlogin")
    public LoginResult login(@RequestParam("username") String username, @RequestParam("password") String password) {
        //申请令牌
        AuthToken authToken =  authService.login(username,password,clientId,clientSecret);

        //用户身份令牌
        String access_token = authToken.getAccess_token();
        //将令牌存储到cookie
        this.saveCookie(access_token);

        return new LoginResult(CommonCode.SUCCESS,access_token);
    }

    //将令牌存储到cookie
    private void saveCookie(String token){

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response,cookieDomain,"/","uid",token,cookieMaxAge,false);
    }


    /**
     * 根据Cookie来获取jwt令牌令牌
     * @return
     */
    @Override
    @GetMapping("/getJwt")
    public JwtResult getJwt() {
        String cookieToken = getCookieToken();
        if(cookieToken==null){
            return new JwtResult(CommonCode.FAIL,null);
        }
        //进行去请求redis的相关令牌
        AuthToken redisToken = getRedisToken(cookieToken);

        if(redisToken==null){
            return new JwtResult(CommonCode.FAIL,null);
        }

        return new JwtResult(CommonCode.SUCCESS,redisToken.getAccess_token());
    }


    @GetMapping("/userjwt")
    public JwtResult userjwt() {
        //取出cookie中的用户身份令牌
        String uid = getCookieToken();
        if(uid == null){
            return new JwtResult(CommonCode.FAIL,null);
        }

        //拿身份令牌从redis中查询jwt令牌
        AuthToken userToken = authService.getUserToken(uid);
        if(userToken!=null){
            //将jwt令牌返回给用户
            String jwt_token = userToken.getJwt_token();
            return new JwtResult(CommonCode.SUCCESS,jwt_token);
        }
        return null;
    }

    //退出
    @Override
    @PostMapping("/userlogout")
    public ResponseResult logout() {
        //取出cookie中的用户身份令牌
        String uid = getCookieToken();
        //删除redis中的token
        boolean result = authService.delToken(uid);
        //清除cookie
        this.clearCookie(uid);
        return new ResponseResult(CommonCode.SUCCESS);
    }

    private void clearCookie(String uid) {
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response,cookieDomain,"/","uid",uid,0,false);

    }


    @Autowired
    private StringRedisTemplate redisTemplate;

    private AuthToken getRedisToken(String assesToken){
        String token="user_token:"+assesToken;
        String jwtToken = redisTemplate.boundValueOps(token).get();
        AuthToken authToken = JSONObject.parseObject(jwtToken, AuthToken.class);

        return  authToken;
    }


    private String getCookieToken(){
        //获取Cookie
        Map<String, String> cookieMap = CookieUtil.readCookie(this.request, "uid");
        String accessToken = cookieMap.get("uid");
        return accessToken;
    }
}
