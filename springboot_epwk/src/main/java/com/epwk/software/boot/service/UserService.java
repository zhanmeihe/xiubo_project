package com.epwk.software.boot.service;

import java.util.List;

import com.epwk.software.boot.bean.User;

public interface UserService {

	int addUser(User user);

	List<User> findAllUser(int pageNum, int pageSize);

}
