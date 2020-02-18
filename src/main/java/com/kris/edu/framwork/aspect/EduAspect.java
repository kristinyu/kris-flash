package com.kris.edu.framwork.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.kris.edu.framwork.BaseResult.BaseResult;
import com.kris.edu.utils.IpUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Aspect
@Component
@Configuration
@Slf4j
public class EduAspect {

    @Pointcut("execution(public * com.kris.edu.app.user.controller.UserContoller.*(..)))")
    public void KrisCut(){
    }

    @Around("KrisCut()")
    public Object aroundAspect(ProceedingJoinPoint pjp){
        //记录请求开始执行时间：
        long beginTime = System.currentTimeMillis();
        //获取请求信息
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = sra.getRequest();
        //获取ip地址、请求地址、请求类名、方法名
        String remoteAddress = IpUtils.getIpAddress(request);
        //获取请求地址
        String requestURI = request.getRequestURI();
        //获取方法名
        String methodName = pjp.getSignature().getName();
        //获取类名
        String clazzName = pjp.getTarget().getClass().getSimpleName();
        //获取请求参数：
        MethodSignature ms = (MethodSignature) pjp.getSignature();
        //获取请求参数类型
        String[] parameterNames = ms.getParameterNames();
        //获取请求参数值
        Object[] parameterValues = pjp.getArgs();
        StringBuffer sb = new StringBuffer();
        //组合请求参数，进行日志打印
        if (parameterNames != null && parameterNames.length > 0) {
            for (int i = 0; i < parameterNames.length; i++) {
                if (parameterNames[i].equals("bindingResult")) {
                    break;
                }
                if ((parameterValues[i] instanceof HttpServletRequest) || (parameterValues[i] instanceof HttpServletResponse)) {
                    sb.
                            append("[").
                            append(parameterNames[i]).append("=").append(parameterValues[i])
                            .append("]");
                } else {
                    sb.
                            append("[").
                            append(parameterNames[i]).append("=")
                            .append(JSON.toJSONString(parameterValues[i], SerializerFeature.WriteDateUseDateFormat))
                            .append("]");
                }
            }
        }
        Object result = null;
        try {
            result = pjp.proceed();
        } catch (Throwable throwable) {
            //请求操纵失败
            //记录错误日志
            log.error("(ง•̀_•́)ง (っ•̀ω•́)っ          切面处理请求错误！ IP信息(ง•̀_•́)ง->： 【{}}】 " +
                            "URI信息(ง•̀_•́)ง->：【{}】 请求映射控制类(ง•̀_•́)ง->：【{}】 " +
                            "请求方法(ง•̀_•́)ง->：【{}】 请求参数列表(ง•̀_•́)ง->：【{}】", remoteAddress, requestURI, clazzName, methodName,
                    sb.toString());
        }
        //请求操作成功
        String resultJosnString = "";
        if (result != null) {
            if (result instanceof BaseResult) {
                resultJosnString = JSON.toJSONString(result, SerializerFeature.WriteDateUseDateFormat);
            } else {
                resultJosnString = String.valueOf(result);
            }
        }
        //记录请求完成执行时间：
        long endTime = System.currentTimeMillis();
        long usedTime = endTime - beginTime;
        //记录日志
        log.info("请求操作成功！ 请求耗时：【{}】 " +
                        "IP信息(◍'౪`◍)ﾉﾞ->： 【{}}】  URI信息(◍'౪`◍)ﾉﾞ->：【{}】 " +
                        "请求映射控制类(◍'౪`◍)ﾉﾞ->：【{}】 请求方法(◍'౪`◍)ﾉﾞ->：【{}】 " +
                        "请求参数列表(◍'౪`◍)ﾉﾞ->：【{}】 返回值(◍'౪`◍)ﾉﾞ->：【{}】", usedTime, remoteAddress, requestURI, clazzName,
                methodName, sb.toString(), resultJosnString);

        return result;

    }

}
