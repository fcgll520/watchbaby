package cn.blingfeng.watchbaby.user.login.service.impl;

import cn.blingfeng.watchbaby.common.utils.JsonUtil;
import cn.blingfeng.watchbaby.common.utils.KeyUtil;
import cn.blingfeng.watchbaby.common.utils.form.UserRegistryForm;
import cn.blingfeng.watchbaby.common.utils.redis.JedisClientPool;
import cn.blingfeng.watchbaby.common.vo.ResultVo;
import cn.blingfeng.watchbaby.dataobject.User;
import cn.blingfeng.watchbaby.user.login.repository.UserRepository;
import cn.blingfeng.watchbaby.user.login.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : blingfeng
 * @date : 2017/12/03
 * @description
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JedisClientPool jedisClientPool;

    @Value("${redis.expire.time}")
    private Integer EXPIRE_TIME;

    private final String TOKEN_KEY = "token";

    @Override
    public ResultVo login(String phone, String password) {
        User resultUser = userRepository.findByUserPhone(phone);
        if (resultUser == null) {
            return ResultVo.error(1, "手机号或密码错误");
        }
        String md5Password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!resultUser.getUserPassword().equalsIgnoreCase(md5Password)) {
            return ResultVo.error(1, "手机号或密码错误");
        }
        /**
         * 检查redis中该用户是否登录 若有，则清除记录
         */
        jedisClientPool.batchDel(resultUser.getUserId());
        String token = KeyUtil.getRedisKey(resultUser.getUserId());
        resultUser.setUserPassword(null);
        jedisClientPool.set(token, JsonUtil.objectToJson(resultUser), EXPIRE_TIME);
        /** 登录相关功能 */
        Map<String, String> map = new HashMap<>(1);
        map.put(TOKEN_KEY, token);
        return ResultVo.ok(map);
    }

    @Override
    public ResultVo logout(String token) {
        jedisClientPool.del(token);
        return ResultVo.ok();
    }

    @Override
    public ResultVo registry(UserRegistryForm userRegistryForm) {
        User user = new User();
        BeanUtils.copyProperties(userRegistryForm, user);
        String userKey = KeyUtil.getKey();
        user.setUserId(userKey);
        user.setUserPassword(DigestUtils.md5DigestAsHex(user.getUserPassword().getBytes()));
        try {
            userRepository.save(user);
        } catch (Exception e) {
            log.error("【用户注册】手机号重复");
        }
        return ResultVo.ok();
    }

    @Override
    public ResultVo<User> getUserInfo(String token) {
        String userInfoJson = jedisClientPool.get(token);
        User user = JsonUtil.jsonToPojo(userInfoJson, User.class);
        return ResultVo.ok(user);
    }
}
