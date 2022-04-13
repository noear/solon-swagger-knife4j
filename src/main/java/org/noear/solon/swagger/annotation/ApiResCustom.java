package org.noear.solon.swagger.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义返回值(2.9.1实验性用法,后期可能有变动)
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiResCustom {
    Class<?> value() default Void.class;
}
