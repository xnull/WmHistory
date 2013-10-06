package ru.wmtool.test.other;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Получение путей к классам
 * 
 * @author null
 *
 */
public class ClazzTest {

	@Test
	public void testPathToCLass(){
		String packageName = ClazzTest.class.getPackage().getName();
		String className = ClazzTest.class.getCanonicalName();
		
		System.out.println(String.format("Имя класса - %s", className));
		System.out.println(String.format("Имя пакета - %s", packageName));
		
		assertEquals(className, "ru.wmtool.test.other.ClazzTest");
		assertEquals(packageName, "ru.wmtool.test.other");
	}
}
