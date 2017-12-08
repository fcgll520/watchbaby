package cn.blingfeng.watchbaby.user.login.controller;

import cn.blingfeng.watchbaby.common.enums.ResultEnum;
import cn.blingfeng.watchbaby.common.exception.BabyException;
import cn.blingfeng.watchbaby.common.utils.form.UserRegistryForm;
import cn.blingfeng.watchbaby.common.vo.ResultVo;
import cn.blingfeng.watchbaby.dataobject.User;
import cn.blingfeng.watchbaby.user.login.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

/**
 * @author : blingfeng
 * @date : 2017/12/04
 * @description
 **/
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping()
    public ResultVo registry(@Valid UserRegistryForm userRegistryForm,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.error("【用户注册】表单参数错误 userRegistryForm={}", bindingResult.getFieldErrors().get(0));
            throw new BabyException(ResultEnum.USER_REGISTRY_FORM_PARAM_ERROR);
        }
        /**
         * 验证码参数验证等等
         */

        ResultVo result = userService.registry(userRegistryForm);
        return result;
    }

    @PostMapping("/token")
    public ResultVo login(@RequestParam("phone") String phone,
                          @RequestParam("password") String password) {
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(password)) {
            log.error("【用户登录】登录参数错误 phone={},password={}", phone, password);
            throw new BabyException(ResultEnum.USER_LOGIN_PARAM_ERROR);
        }
        ResultVo result = userService.login(phone, password);
        return result;
    }

    @DeleteMapping("/token/{token}")
    public ResultVo logout(@PathParam("token") String token) {
        ResultVo result = userService.logout(token);
        return result;
    }

    @GetMapping()
    public ResultVo userInfo(@RequestParam("token") String token){
        ResultVo<User> userInfo = userService.getUserInfo(token);
        return userInfo;
    }

}
