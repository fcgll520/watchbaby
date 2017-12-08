package cn.blingfeng.watchbaby.user.login.repository;

import cn.blingfeng.watchbaby.dataobject.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : blingfeng
 * @date : 2017/12/03
 * @description
 **/
public interface UserRepository extends JpaRepository<User,String>{
    /**
     * 通过手机账号查找用户
     * @param phone
     * @return user
     */
    User findByUserPhone(String phone);


}
