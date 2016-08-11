package com.framework.wight.filter;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.StopWatch;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

import com.framework.core.utils.BeanUtils;


/**
 * 记录方法的执行时间、方法名、使用的参数
 * @author guojingfu
 */
@SuppressWarnings("deprecation")
public class MethodLogAdvice implements MethodInterceptor {
	protected static Logger logger =null ;  
	protected static String className =null ;
	@SuppressWarnings("unused")
	@Autowired
	private SimpleJdbcTemplate sjdbcTemplate;
	
	public MethodLogAdvice() {
		super();
		className = getClass().getName();
	    logger=LoggerFactory.getLogger(className);
	}
    /**
     * 拦截要执行的目标方法
     */
    @SuppressWarnings("unchecked")
	public Object invoke(MethodInvocation invocation) throws Throwable {
    	//用 commons-lang 提供的 StopWatch 计时，Spring 也提供了一个 StopWatch
    	StopWatch clock = new StopWatch();
        clock.start(); //计时开始
        Object result = invocation.proceed();
        clock.stop();  //计时结束
        
    	//方法参数类型，转换成简单类型
    	Class[] params = invocation.getMethod().getParameterTypes();
    	Object[] object = invocation.getArguments();
    	String[] simpleParams = new String[params.length];
    	for (int i = 0; i < params.length; i++) {
			simpleParams[i] = params[i].getSimpleName()+":::"+BeanUtils.Obj2Map(object[i]);
		}
    	String strLogs = "Takes : " + clock.getTime() + " ms ["
                + invocation.getThis().getClass().getName() + "."
                + invocation.getMethod().getName() + "("
				+ StringUtils.join(simpleParams, ",") + ")] "
				+ "";
    	logger.info(strLogs);
    	//String sql = "insert xxx into table values(" +	strLogs +")";
    	//sjdbcTemplate.getJdbcOperations().execute(sql);
        return result;
    }
}