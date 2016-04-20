package org.dream.web.log;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.dream.common.exception.EvcharException;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ControllerAspj {
	private static int count = 0;

	@Pointcut(value = "execution(* org.dream.web.controller.*..*.*(..)) && args(..)")
	public void init() {
	}

	@Around(value = "init()")
	public Object methodAop(ProceedingJoinPoint jp) {

		System.out.println("@Around start excute : " + ++count);

		Object obj = new Object();

		String methodName = jp.getSignature().getName();
		Object[] args = jp.getArgs();

		HttpServletRequest requset = null;

		for (int i = 0; i < args.length; i++) {
			if (args[i] instanceof HttpServletRequest) {
				requset = (HttpServletRequest) args[i];
			}
		}

		//System.out.println("RemoteAddr : " + requset.getRemoteAddr());

		try {
			obj = jp.proceed();
		} catch (Throwable e) {
			if (e.getClass() == EvcharException.class) {
				throw new EvcharException(((EvcharException) e).getCode(), e.getMessage());
			}
			if (e.getClass() == IllegalStateException.class) {
				throw new IllegalStateException(e.getMessage(), e);
			}
		} finally {
			count = 0;
		}

		System.out.println("@Around start end : " + ++count);

		System.out.println("methodName : " + methodName);

		return obj;
	}

	@AfterThrowing(value = "init()", throwing = "ex")
	public void throwException(Exception ex) {
		System.out.println("@AfterThrowing excute");
		count = 0;
	}

	@AfterReturning(value = "init()")
	public void afterReturn(JoinPoint joinPoint) {
		System.out.println("@AfterReturning excute : " + ++count);
	}

	@After(value = "init()")
	public void after(JoinPoint joinPoint) {
		System.out.println("@After excute : " + ++count);
	}

	@Before(value = "init()")
	public void before(JoinPoint joinPoint) {
		System.out.println("@Before excute : " + ++count);
	}
}
