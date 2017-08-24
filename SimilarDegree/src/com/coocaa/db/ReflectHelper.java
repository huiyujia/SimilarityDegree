package com.coocaa.db;

import com.coocaa.util.DateUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Siber.xu
 *	反射工具
 */
public class ReflectHelper {
	
	/**
	 * 根据FieldName 获取get方法
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	public static Method getMethodByFieldName(Class<?> clazz, String fieldName){
		try {
			if(clazz.getName().equals("java.lang.Object")){
				return null;
			}
			String methodname="get"  + Character.toUpperCase(fieldName.charAt(0)) + fieldName.substring(1);

			Method method = clazz.getDeclaredMethod(methodname);

			return method;
		} catch (NoSuchMethodException e) {
			return getMethodByFieldName(clazz.getSuperclass(), fieldName);
		}
	}

	public static void main(String[]args){
		Class clazz = String.class.getClass();
		if(clazz.getName().equals(Object.class.getName())){
			System.out.println("==========");
		}else{
			System.out.println("xxxxxxx");
		}

		if(clazz.equals(Object.class.getClass())){
			System.out.println("==========");
		}else{
			System.out.println("xxxxxxx");
		}
	}
	/**
	 * 获取class中的Field
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	public static Field getFieldByFieldName(Class<?> clazz, String fieldName) {
		try {
			return clazz.getDeclaredField(fieldName);
		} catch (Exception e) {
			return null;
		}
	}
	/**
	 * 获取obj对象fieldName的Field
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Field getFieldByFieldName(Object obj, String fieldName) {
		if (obj == null || fieldName == null) {
			return null;
		}
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				return superClass.getDeclaredField(fieldName);
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * 获取obj对象fieldName的属性值
	 * @param obj
	 * @param fieldName
	 * @return
	 */
	public static Object getValueByFieldName(Object obj, String fieldName) {
		Object value = null;
		try {
			Field field = getFieldByFieldName(obj, fieldName);
			if (field != null) {
				if (field.isAccessible()) {
					value = field.get(obj);
				} else {
					field.setAccessible(true);
					value = field.get(obj);
					field.setAccessible(false);
				}
			}
		} catch (Exception e) {
		}
		return value;
	}

	/**
	 * 获取obj对象fieldName的属性值
	 * @param obj
	 * @param fieldType
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T getValueByFieldType(Object obj, Class<T> fieldType) {
		Object value = null;
		for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass
				.getSuperclass()) {
			try {
				Field[] fields = superClass.getDeclaredFields();
				for (Field f : fields) {
					if (f.getType() == fieldType) {
						if (f.isAccessible()) {
							value = f.get(obj);
							break;
						} else {
							f.setAccessible(true);
							value = f.get(obj);
							f.setAccessible(false);
							break;
						}
					}
				}
				if (value != null) {
					break;
				}
			} catch (Exception e) {
			}
		}
		return (T) value;
	}

	/**
	 * 设置obj对象fieldName的属性值
	 * @param obj
	 * @param fieldName
	 * @param value
	 * @throws SecurityException
	 * @throws NoSuchFieldException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static boolean setValueByFieldName(Object obj, String fieldName,Object value) {
		try {
			//java.lang.Class.getDeclaredField()方法用法实例教程 - 方法返回一个Field对象，它反映此Class对象所表示的类或接口的指定已声明字段。
			//此方法返回这个类中的指定字段的Field对象
			Field field = obj.getClass().getDeclaredField(fieldName);
		  /**
			* public void setAccessible(boolean flag)
            *       throws SecurityException将此对象的 accessible 标志设置为指示的布尔值。值为 true 则指示反射的对象在使用时应该取消 Java 语言访问检查。值为 false 则指示反射的对象应该实施 Java 语言访问检查。 
			* 	首先，如果存在安全管理器，则在 ReflectPermission("suppressAccessChecks") 权限下调用 checkPermission 方法。 
			* 	如果 flag 为 true，并且不能更改此对象的可访问性（例如，如果此元素对象是 Class 类的 Constructor 对象），则会引发 SecurityException。 
			* 	如果此对象是 java.lang.Class 类的 Constructor 对象，并且 flag 为 true，则会引发 SecurityException。 
			* 	参数：
			* 	flag - accessible 标志的新值 
 			* 	抛出： 
			* 	SecurityException - 如果请求被拒绝。
			*/
			if (field.isAccessible()) {//获取此对象的 accessible 标志的值。 
				field.set(obj, value);//将指定对象变量上此 Field 对象表示的字段设置为指定的新值
			} else {
				field.setAccessible(true);
				field.set(obj, value);
				field.setAccessible(false);
			}
			return true;
		} catch (Exception e) {
		}
		return false;
	}
	
	
	/**   
	 * 通过反射,获得定义Class时声明的父类的范型参数的类型.   
	 * 如public BookManager extends GenricManager<Book>   
	 *   
	 * @param clazz clazz The class to introspect   
	 * @param index the Index of the generic ddeclaration,start from 0.   
	 */  
	@SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(Class clazz, int index) throws IndexOutOfBoundsException {
		Type genType = clazz.getGenericSuperclass();
		//Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (!(genType instanceof ParameterizedType)) {
			return Object.class;
		}

		Type[] params = ((ParameterizedType) genType).getActualTypeArguments();

		if (index >= params.length || index < 0) {
			return Object.class;
		}
		if (!(params[index] instanceof Class)) {
			return Object.class;
		}
		return (Class) params[index];
	}
	
	/**
	 * 通过反射,获得定义Class时声明的父类的范型参数的类型
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public static Class getSuperClassGenricType(Class clazz){
	
		return getSuperClassGenricType(clazz, 0);
	}
	
	/**
	 * 通过反射，获取实体所有非null的属性，并增加到map中
	 * @param entity
	 * @return
	 */
	public static <T> Map<String, Object> getClassFieldsValues(T entity){
		Map<String, Object> map = new HashMap<String, Object>();
		Field[]  fields = entity.getClass().getDeclaredFields();
		 for (Field field : fields) {
			Object obj = ReflectHelper.getValueByFieldName(entity, field.getName());
			if(obj != null && !obj.equals("")){
			    if(obj instanceof Date){
			        map.put(field.getName(), DateUtils.formatDate((Date) obj, "yyyy/MM/dd"));
			    }else{
			        map.put(field.getName(), obj);
			    }
			}
		}
		return map;
	}
}
