package com.github.andersonfonseka.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class JavaBeanUtils {
	
	public JavaBeanUtils() {
		super();
	}

	public void copyProperties(Object source, Object target) throws Exception {
		
		List<Method> sourceMethods = new ArrayList<Method>();
					 sourceMethods.addAll(Arrays.asList(source.getClass().getSuperclass().getDeclaredMethods()));
					 sourceMethods.addAll(Arrays.asList(source.getClass().getDeclaredMethods()));
		
		for (Method method : sourceMethods) {
			Object result = getAcessorMethodGet(source, method);
			
			if (result != null){
				String setterMethodName = "set" + method.getName().substring(3);
				String fieldName = formatFieldName(method);
				getAcessorMethodSet(target, setterMethodName, result, fieldName);
			}
		}
	}

	private String formatFieldName(Method method) {
		String fieldName = method.getName().substring(3,4).toLowerCase() + method.getName().substring(4);
		return fieldName;
	}

	private Object getAcessorMethodGet(Object source, Method method) throws Exception {

		if (method.getName().startsWith("get") && !(method.getModifiers() == Modifier.PUBLIC+Modifier.FINAL+Modifier.NATIVE)){
			Method methodGet = method;
			Object result = methodGet.invoke(source);
			   	   return result;
		}
		
		return null;
	}
	
	private void getAcessorMethodSet(Object target, String methodName, Object value, String fieldName) throws Exception {

		List<Method> targetMethods = new ArrayList<Method>();
					 targetMethods.addAll(Arrays.asList(target.getClass().getSuperclass().getDeclaredMethods()));
					 targetMethods.addAll(Arrays.asList(target.getClass().getDeclaredMethods()));
		
		Method methodAssign = null;
		
		for (Method method : targetMethods) {
			if (method.getName().equals(methodName)){
				methodAssign = method;
				break;
			}
		}
		
		if (checkInstance(value)){
			methodAssign.invoke(target, value);	
		} else if (value instanceof List) {
			assignValueForList(target, value, methodAssign, fieldName);
		} else if (value instanceof Collection) {
			assignValueForCollection(target, value, methodAssign, fieldName);			
		} else {
			assignValueForObject(target, value, methodAssign);
		}
	}

	private void assignValueForObject(Object target, Object value, Method methodAssign)
			throws ClassNotFoundException, Exception, IllegalAccessException, InvocationTargetException {
		
		Class<?> clazz = methodAssign.getParameterTypes()[0];
		
		Object objSource = value;
		Object newObject = Class.forName(clazz.getName()).newInstance();
		copyProperties(objSource, newObject);
		methodAssign.invoke(target, newObject);
	}

	private void assignValueForList(Object target, Object value, Method methodAssign, String fieldName) throws Exception {
		List<?> objListSource = (List<?>) value;
		List objecListTarget = (List<?>) Class.forName(value.getClass().getName()).newInstance();
		
		Class<?> clazz = getGenericType(target.getClass(), fieldName);
		
		for (Object object : objListSource) {
			Object newObject = Class.forName(clazz.getName()).newInstance();
			copyProperties(object, newObject);
			objecListTarget.add(newObject);
		}
		
		methodAssign.invoke(target, objecListTarget);
	}
	
	private void assignValueForCollection(Object target, Object value, Method methodAssign, String fieldName) throws Exception {
		Collection<?> objListSource = (Collection<?>) value;
		Collection objecListTarget = (Collection<?>) Class.forName(value.getClass().getName()).newInstance();
		
		Class<?> clazz = getGenericType(target.getClass(), fieldName);
		
		for (Object object : objListSource) {
			Object newObject = Class.forName(clazz.getName()).newInstance();
			copyProperties(object, newObject);
			objecListTarget.add(newObject);
		}
		
		methodAssign.invoke(target, objecListTarget);
	}
	
	private boolean checkInstance(Object value){
		
		if (value instanceof String){
			return true;
		} else if (value instanceof Integer){
			return true;
		} else if (value instanceof Long){
			return true;
		} else if (value instanceof Double){
			return true;
		} else if (value instanceof Float){
			return true;
		}
		
		return false;
		
	}

	private Class<?> getGenericType(Class<?> clazz, String fieldName) throws Exception {
		 
		Field listField = clazz.getDeclaredField(fieldName);
        ParameterizedType listType = (ParameterizedType) listField.getGenericType();
        Class<?> listGenericClass = (Class<?>) listType.getActualTypeArguments()[0];
		
        return listGenericClass;
        
	}
	

}
