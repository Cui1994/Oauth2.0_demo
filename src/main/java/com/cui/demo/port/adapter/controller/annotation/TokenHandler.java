package com.cui.demo.port.adapter.controller.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 对应 Aspect @ControllerTokenHandlerAspect
 * 参数需包含 @AccountVerifyInfo
 * 方法需抛出 @UserException
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface TokenHandler {
}
