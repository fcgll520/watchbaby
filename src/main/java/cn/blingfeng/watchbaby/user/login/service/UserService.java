package cn.blingfeng.watchbaby.user.login.service;


import cn.blingfeng.watchbaby.common.utils.form.UserRegistryForm;
import cn.blingfeng.watchbaby.common.vo.ResultVo;
import cn.blingfeng.watchbaby.dataobject.User;

import java.util.Map;

/**
 * @author : blingfeng
 * @date : 2017/12/03
 * @description
 **/
public interface UserService {
    /**
     *
     * @param phone
     * @param password
     * @return
     */
    ResultVo<Map<String,String>> login(String phone,String password);

    /**
     * 退出登录
     * @param token
     * @return 退出结果
     */
    ResultVo logout(String token);

    ResultVo registry(UserRegistryForm userRegistryForm);

    /**
     * 获取用户信息
     * @param token
     * @return
     */
    ResultVo<User> getUserInfo(String token);
}
