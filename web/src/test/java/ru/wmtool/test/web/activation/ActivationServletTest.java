package ru.wmtool.test.web.activation;

import java.util.Vector;
import java.util.concurrent.atomic.AtomicLong;

import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;

import static org.junit.Assert.*;

public class ActivationServletTest {
	
	@Before(value = "")
	public void init(){
	}
	
	@Test
	public void doGetTest(){
		
		MockHttpServletRequest request = new MockHttpServletRequest();
//		request.setParameter("code", value)
	}
}
