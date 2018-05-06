package com.honger.expo.annotation;

import java.lang.annotation.*;

/**
 * Created by chenjian on 2018/5/2.
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface CountAnnotation {
}
