package com.github.andersonfonseka.core;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.security.Permissions;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.github.andersonfonseka.domain.Person;

public class JavaBeanUtils {
	
	private static final String METHOD_ADD_ALL = "addAll";
	private static final String METHOD_CLEAR = "clear";
	private static final String METHOD_VALUE = "value";
	private static final String GET = "get";
	private static final String SET = "set";
	
	private List<Class<? extends Object>> listWrapperClass = configureRegistredWrapperClass();

	public void copyProperties(Object source, Object target) throws Exception {
		
		List<Method> sourceMethods = new ArrayList<Method>();
		getSuperClassDeclaredMethods(source.getClass(), sourceMethods);
		sourceMethods.addAll(Arrays.asList(source.getClass().getDeclaredMethods()));	
		
		for (Method method : sourceMethods) {
			Object result = getAcessorMethodGet(source, method);
			
			if (result != null){
				String setterMethodName = SET + method.getName().substring(3);
				String fieldName = getFieldNameByMethod(method, source.getClass());
				getAcessorMethodSet(target, setterMethodName, result, fieldName);
			}
		}
	}
	
	


	private void getSuperClassDeclaredMethods(Class<?> clazz, List<Method> methods){
		if (!clazz.getSuperclass().getName().equals("java.lang.Object")){
			methods.addAll(Arrays.asList(clazz.getSuperclass().getDeclaredMethods()));
			getSuperClassDeclaredMethods(clazz.getSuperclass(), methods);
		}
	}
	

	private String formatFieldName(Method method) {
		String fieldName = method.getName().substring(3,4).toLowerCase() + method.getName().substring(4);
		return fieldName;
	}

	private Object getAcessorMethodGet(Object source, Method method) throws Exception {

		if (method.getName().startsWith(GET) && !(method.getModifiers() == Modifier.PUBLIC + Modifier.FINAL + Modifier.NATIVE)){
			Method methodGet = method;
			Object result = methodGet.invoke(source);
			   	   return result;
		}
		
		return null;
	}
	
	private void getAcessorMethodSet(Object target, String methodName, Object value, String fieldName) throws Exception {

		List<Method> targetMethods = new ArrayList<Method>();
		getSuperClassDeclaredMethods(target.getClass(), targetMethods);
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
			
		}else if(value.getClass().isEnum()){
			assignValueForEnum(target, value, methodAssign);
			
		} else {
			assignValueForObject(target, value, methodAssign);
		}
	}


	private void assignValueForEnum(Object target, Object value, Method methodAssign)
			throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String enumValue =(String) value.getClass().getDeclaredMethod(METHOD_VALUE).invoke(value);
		methodAssign.invoke(target, Enum.valueOf((Class<Enum>) methodAssign.getParameterTypes()[0], enumValue));
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
		
		//The set method of list not exist
		if(methodAssign == null){
			
			String getMethodName = GET + capitalize(fieldName);
			
			Method methodGet = target.getClass().getMethod(getMethodName);
			List<?> listResult = (List<?>)methodGet.invoke(target);
			
			if(listResult != null){
				
				Method methodClear = listResult.getClass().getMethod(METHOD_CLEAR);
				methodClear.invoke(listResult);
				
				Method methodAddAll = listResult.getClass().getMethod(METHOD_ADD_ALL, Collection.class);
				methodAddAll.invoke(listResult, objecListTarget);
			}
			
	    //The set method of list exist
		}else {
			methodAssign.invoke(target, objecListTarget);
		}
	}
	
	protected String capitalize(String fieldName) {
		return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
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
		return listWrapperClass.contains(value.getClass());
	}
	
	private static List<Class<? extends Object>> configureRegistredWrapperClass() {
		List<Class<?>> arrayList = new ArrayList<Class<?>>();
		arrayList.add(String.class);
		arrayList.add(Integer.class);
		arrayList.add(Long.class);
		arrayList.add(Double.class);
		arrayList.add(Float.class);
		arrayList.add(Byte.class);
		arrayList.add(Short.class);
		arrayList.add(Character.class);
		arrayList.add(Date.class);
		arrayList.add(Boolean.class);
		
		return arrayList;
	}

	private Class<?> getGenericType(Class<?> clazz, String fieldName) throws Exception {
		 
		Field listField = clazz.getDeclaredField(fieldName);
        ParameterizedType listType = (ParameterizedType) listField.getGenericType();
        Class<?> listGenericClass = (Class<?>) listType.getActualTypeArguments()[0];
		
        return listGenericClass;
        
	}
	
	private String getFieldNameByMethod(Method method, Class<? extends Object> clazz) throws Exception {
		String fieldName;
		Field[] declaredFields = clazz.getDeclaredFields();
		
		for (Field field : declaredFields) {
			if(field.getName().equalsIgnoreCase(method.getName().substring(3))){
				fieldName = field.getName();
				return fieldName;
			}
		}
		
		fieldName = getFieldNameByMethod(method,clazz.getSuperclass());
		
		return fieldName;
	}

}
