package com.coocaa.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) 
@Documented 
@Inherited
public @interface Column {

	public String name() default "";
	public boolean insertable() default true;
	public boolean updatatble() default true;
	
}
