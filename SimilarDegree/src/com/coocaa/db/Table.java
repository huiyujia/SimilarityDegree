package com.coocaa.db;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented 
@Inherited
public @interface Table {
	String name() default "";
}
