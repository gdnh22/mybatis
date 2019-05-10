package com.joy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.joy.entity.UserEntity;
import com.joy.service.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	@RequestMapping("/userlist")
	public List<UserEntity> queryList(int page, int number){
		//PageHelper.startPage(1, 20); //分页，固定
        PageHelper.startPage(page, number);//需要在body里面传参。
		return userService.queryList();
	}

    @RequestMapping("/queryUser")
    public UserEntity queryUserEntity(long userId){
        UserEntity userEntity=userService.findById(userId);
        return userEntity;
    }

    @RequestMapping("/insert")
	public int insertEntity() {
        return userService.insertEntity();
	}

    @RequestMapping("/insertParam")
    public int insertParam() {
        return userService.insertParam();
    }

    @RequestMapping("/insertByMap")
    public int insertByMap() {
        return userService.insertByMap();
    }

    @RequestMapping("/updateEntity")
    public int updateEntity() {
        return userService.updateEntity();
    }

    @RequestMapping("/deleteEntity")
    public int deleteEntity() {
        return userService.deleteEntity();
    }
}
