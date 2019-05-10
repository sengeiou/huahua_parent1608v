package com.huahua.friend.filter;

import huahua.common.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName: JwtFilter
 * @Author: zhangyuyang
 * @Time : 2019/4/26 -上午11:30
 * @Version:1.0
 */
@Component
public class JwtFilter extends HandlerInterceptorAdapter {
    @Autowired
    JwtUtil jwtUtil;

    /**
     * 请求接口之前的调用（AOP） admin/user
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("--------------------我进入拦截器了-----------");
        // 获取请求头中的数据
        String header = request.getHeader("Authorization");// Authorization 参数名称
        if (StringUtils.isEmpty(header)){
            throw  new RuntimeException("权限不足");
        }
        //Bearer 也为null    header.startsWith 查询字符串中以某个name开头的数据是否存在的判断
        if (!header.startsWith("Bearer ")){
            throw  new RuntimeException("权限不足");
        }
        String token = header.substring(7);
        //token解析后的明文数据Claims
        Claims claims = null;
        try {
            claims = jwtUtil.parseJWT(token);
        }catch (Exception e){
            e.printStackTrace();
        }
        if (null ==claims){
            throw  new RuntimeException("权限不足");
        }
        //用户或者管理员的信息 放入session
        if (StringUtils.equals("admin", (String) claims.get("roles"))){
            request.setAttribute("admin_claims",claims);
        }
        if (StringUtils.equals("user", (String) claims.get("roles"))){
            request.setAttribute("user_claims",claims);
        }
        return  true ;
    }
}
