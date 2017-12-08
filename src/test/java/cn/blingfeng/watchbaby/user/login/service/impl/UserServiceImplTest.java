package cn.blingfeng.watchbaby.user.login.service.impl;

import cn.blingfeng.watchbaby.common.utils.KeyUtil;
import cn.blingfeng.watchbaby.common.utils.form.UserRegistryForm;
import cn.blingfeng.watchbaby.common.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

/**
 * @author : blingfeng
 * @date : 2017/12/04
 * @description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class UserServiceImplTest {
    @Autowired
    private UserServiceImpl userService;
    @Test
    public void login() throws Exception {

        String phone = "13343618459";
        String password = "1234";
        ResultVo result = userService.login(phone,password);
        Assert.assertEquals(new Integer(0),result.getCode());
    }

    @Test
    public void getUserKey(){
        log.info(KeyUtil.getKey());
    }

    @Test
    public void registry(){
        UserRegistryForm userRegistryForm = new UserRegistryForm();
        userRegistryForm.setPhoneCheckCode("1111");
        userRegistryForm.setPicCheckCode("sas");
        userRegistryForm.setUserPhone("13353672709");
        userRegistryForm.setUserBirth(new Date());
        userRegistryForm.setUserPassword("gyc670894");
        userRegistryForm.setUserSex(0);
        userRegistryForm.setUserName("blingfeng");
        ResultVo registry = userService.registry(userRegistryForm);

    }
    @Test
    public void data(){
        log.info(String.valueOf(new Date()));
    }
}