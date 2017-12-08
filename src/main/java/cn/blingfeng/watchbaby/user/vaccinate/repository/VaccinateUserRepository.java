package cn.blingfeng.watchbaby.user.vaccinate.repository;

import cn.blingfeng.watchbaby.dataobject.VaccinateUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : blingfeng
 * @date : 2017/12/06
 * @description
 **/
public interface VaccinateUserRepository extends JpaRepository<VaccinateUser,String>{
    /**
     * 通过userId查询接种记录
     * @param userId
     * @param pageable
     * @return
     */
    Page<VaccinateUser> findByUserId(String userId, Pageable pageable);

}
