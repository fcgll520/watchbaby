package cn.blingfeng.watchbaby.user.vaccinate.repository;

import cn.blingfeng.watchbaby.dataobject.VaccinateUser;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;



import static org.junit.Assert.*;

/**
 * @author : blingfeng
 * @date : 2017/12/08
 * @description
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class VaccinateUserRepositoryTest {
    @Autowired
    private VaccinateUserRepository userRepository;
    @Test
    public void findByUserId() {
        Pageable pageable = new PageRequest(0,2);
        Page<VaccinateUser> userPage = userRepository.findByUserId("15124507855192172", pageable);
        Assert.assertNotNull(userPage.getContent());
    }
}