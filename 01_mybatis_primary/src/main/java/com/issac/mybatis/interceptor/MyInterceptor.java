package com.issac.mybatis.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**
 * @author: ywy
 * @date: 2019-10-24
 * @desc:
 */
@Intercepts(
        {@Signature(method = "query", type = Executor.class, args = {
                MappedStatement.class,Object.class, RowBounds.class, ResultHandler.class
        })}
)
public class MyInterceptor implements Interceptor {

    /**
     * @param invocation {代理对象，被监控的方法对象，当前被监控的方法运行时所需要的实参}
     * @return
     * @throws Throwable
     */
    public Object intercept(Invocation invocation) throws Throwable {
        System.out.println("被拦截方法执行之前");
        Object proceed = invocation.proceed();
        System.out.println("被拦截方法执行之后");
        return proceed;
    }

    /**
     * @param target 被拦截对象 Executor
     *               被拦截对象所在的类有实现接口
     *               就为当前拦截对象生成一个代理对象【$Proxy】
     *               <p>
     *               被拦截对象所在的类没有指定的接口
     *               这个对象之后行为就不会被代理操作
     * @return
     */
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    public void setProperties(Properties properties) {

    }
}
