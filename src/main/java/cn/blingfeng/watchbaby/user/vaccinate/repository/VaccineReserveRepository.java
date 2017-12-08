package cn.blingfeng.watchbaby.user.vaccinate.repository;

import cn.blingfeng.watchbaby.dataobject.VaccineReserve;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : blingfeng
 * @date : 2017/12/06
 * @description
 **/
public interface VaccineReserveRepository extends JpaRepository<VaccineReserve,String>{
    /**
     * 查询用户预约的疫苗信息
     * @param userId
     * @param pageable
     * @return
     */
    Page<VaccineReserve> findByUserId(String userId, Pageable pageable);
}
