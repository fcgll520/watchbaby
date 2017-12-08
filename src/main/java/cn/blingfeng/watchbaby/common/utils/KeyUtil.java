package cn.blingfeng.watchbaby.common.utils;

import lombok.Synchronized;
import org.hibernate.annotations.Synchronize;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @author : blingfeng
 * @date : 2017/12/04
 * @description 主键生成工具类
 **/
@Component
public class KeyUtil {

    private static String userPre;

    @Synchronized
    public static String getKey() {
        /**
         * 当前时间戳 + 4位随机数
         */
        Random random = new Random();
        int randomValue = random.nextInt(8999) + 1000;
        return System.currentTimeMillis() + String.valueOf(randomValue);
    }

    public static String getRedisKey(String userKey) {
        return userPre + userKey + System.currentTimeMillis();

    }
    @Value("${redis.key.user.pre}")
    public  void setUserPre(String userPre) {
        KeyUtil.userPre = userPre;
    }
}
