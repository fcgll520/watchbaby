package cn.blingfeng.watchbaby.user.login.repository;

import cn.blingfeng.watchbaby.dataobject.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : blingfeng
 * @date : 2017/12/03
 * @description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    @Test
    public void findByUserPhone() throws Exception {
        User byUserPhone = userRepository.findByUserPhone("13353672707");
        Assert.assertNotNull(byUserPhone);
    }

}