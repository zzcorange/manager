package com.chengzi.zuul.filter;

import com.chengzi.zuul.service.RedisOperator;
import com.chengzi.zuul.service.UserServiceForDataBase;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;

@Component
@PropertySource("classpath:properties/login.properties")
public class LoginFilter extends ZuulFilter implements InitializingBean {
    private static Logger log= LoggerFactory.getLogger(LoginFilter.class);
    @Autowired
    private RedisOperator redisOperator;
    @Value("${login.nottoken}")
    private  String notNeedToken;

    private static ArrayList<String> notNeedTokenList ;

    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return -10;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        boolean isLogin = true;
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        HttpSession session = request.getSession();
        log.info(String.format("%s >>> %s",request.getMethod(),request.getRequestURL().toString())+">>>"+request.getRequestURI());
        Object acceptToken = request.getParameter("token");
        Object token = session.getAttribute("token");
        log.info("sesssionToken="+token);
        if(!isNotNeedToken(request.getRequestURI())//需要鉴权
                &&((acceptToken==null&&token==null)//参数，session中都没有token
                    ||(token==null&&acceptToken!=null&&!redisOperator.isLogin(acceptToken.toString())))//参数中的token无效
                ){
            isLogin = false;
            log.warn("token is empty");
            ctx.setSendZuulResponse(false);
            ctx.setResponseStatusCode(401);
            try{
                if(request.getRequestURI().endsWith("html"))
                    ctx.getResponse().sendRedirect("http://localhost:8080/web/login.html");
                else
                    ctx.getResponse().getWriter().write("token is empty");

            }catch (Exception e){

            }
        }
        if(isLogin&&token==null){
            session.setAttribute("token","acceptToken");
        }
        System.out.println("通过验证");
        return null;
    }

    /**
     * 判断此url是否不需要token
     * @param url
     * @return
     */
    public boolean isNotNeedToken(String url){
        if(notNeedTokenList.size()==0) return false;
        for(String temp : notNeedTokenList){
            if(url.matches(temp)){
                return  true;
            }
        }
        return false;
    }
    @Override
    public void afterPropertiesSet() throws Exception {
        if(notNeedToken!=null&&!"".equals(notNeedToken)){
            notNeedTokenList = new ArrayList<String>(Arrays.asList(notNeedToken.split(";")));
        }else
            notNeedTokenList = new ArrayList<>();
        System.out.println("notNeedTokenList----》"+notNeedTokenList.toString());
    }
}
