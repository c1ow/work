package com.work.test.annotation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import com.work.annotation.MeFirstAnnotation;

public class AnnotationTest {
	
	public  AnnotationTest() {
		System.err.println("xxx");
		meAnno(MeFirstAnnotation.class);
	}
	
	@MeFirstAnnotation(id = 1,description="zzzzzzzzzzzzzzz")
	public static boolean vp(String description) {
		System.err.println(description);
		return (description.matches("\\w*\\d\\w*"));
	}
	@MeFirstAnnotation(id = 2,description="yyyyyyyyyyyyyyy")
	public String ep(String pw) {
		return new StringBuffer(pw).reverse().toString();
	}
	@MeFirstAnnotation(id = 3,description="xxxxxxxxxxxxxxxx")
	public boolean cp(List<String> pws ,String pw) {
		return !pws.contains(pw);
	}
	/**
	 * 解析
	 * @param meAnno
	 * @param clazz
	 */
	public static void meAnno(Class<?> clazz) {
		for(Method method:clazz.getDeclaredMethods()) {
			MeFirstAnnotation annotation = method.getAnnotation(MeFirstAnnotation.class);
			if (annotation!=null) {
				System.err.println(annotation.description()+"==="+annotation.id());
//				meAnno.remove(new Integer(annotation.id()));
			}
		}
//		for(int i:meAnno) {
//			System.err.println("xxxx:"+i);
//		}
	}
	
	public static void print() {
		System.err.println("输出");
	}
	
	public static void main(String[] args) {
//		AnnotationTest annotationTest = new AnnotationTest();
		System.err.println(vp(""));
//		System.err.println(annotationTest.ep("2222"));
//		List<String> pws = new ArrayList<String>();
//		pws.add("111");
//		pws.add("222");
//		pws.add("333");
// 		System.err.println(annotationTest.cp(pws,"2222"));
// 		
// 		List<Integer> meAnno =new ArrayList<Integer>();
// 		Collections.addAll(meAnno,1,2,3,4);
// 		meAnno(AnnotationTest.class);
 		
	}
}
