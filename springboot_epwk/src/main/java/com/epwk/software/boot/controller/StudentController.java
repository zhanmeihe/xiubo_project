package com.epwk.software.boot.controller;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.epwk.software.boot.bean.User;
import com.epwk.software.boot.service.RedisService;
import com.epwk.software.boot.service.UserService;
import com.epwk.software.boot.utils.CommonResponse;
import com.epwk.software.boot.utils.CommonSuccessResponse;
import com.epwk.software.boot.utils.SystemErrorResponse;

import redis.clients.jedis.Jedis;

/**
 * {all 所有querySQL语句均使用redis缓存策略,并且捕获异常写入日志}
 * 
 * @author zhanmeihe
 *
 */
@Controller
@RequestMapping(value = "/user")
public class StudentController {

	@Autowired
	private UserService userService;

	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisService redis;
	Jedis rediss = null;  
	private static final Logger logger = LogManager.getLogger(StudentController.class);

	@SuppressWarnings("unchecked")
	@ResponseBody
	@RequestMapping(value = "/all/{pageNum}/{pageSize}", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	public CommonResponse findAllUser(@PathVariable("pageNum") int pageNum, @PathVariable("pageSize") int pageSize) {
		try {
			if (redis.exists("user")) {
				System.out.println("已存在redis");
				return new CommonSuccessResponse(redis.getValue("user"));

			} else {
				List<User> s = userService.findAllUser(pageNum, pageSize);
				System.out.println("不存在redis");
				redis.setValue("user", s);
				//rediss.ttl(key)
				redis.expire("user", 1800, TimeUnit.SECONDS);
				return new CommonSuccessResponse(userService.findAllUser(pageNum, pageSize));

			}

		} catch (Exception e) {
			logger.error("异常是：", e);
		}
		return new SystemErrorResponse();
	}

	@RequestMapping(value = "/springboot")
	public String Test(Model mo) {
		mo.addAttribute("date", new Date());
		return "hello";
	}

}
