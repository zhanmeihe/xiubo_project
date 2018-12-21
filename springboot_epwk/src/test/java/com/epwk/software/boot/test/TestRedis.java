package com.epwk.software.boot.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.epwk.software.boot.service.RedisService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {

	
	@Autowired
	private RedisService dis;

	@SuppressWarnings("unchecked")
	@Test
	public void setValue() {
		dis.setValue("12", "正则");

	}

	@Test
	public void findAllUsers2() {
		System.out.println("get key value:" + dis.getValue("12"));
	}

}
