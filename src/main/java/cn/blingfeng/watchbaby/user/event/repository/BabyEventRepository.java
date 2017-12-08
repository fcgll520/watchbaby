package cn.blingfeng.watchbaby.user.event.repository;

import cn.blingfeng.watchbaby.dataobject.BabyEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author : blingfeng
 * @date : 2017/12/08
 * @description
 **/
public interface BabyEventRepository extends JpaRepository<BabyEvent,String>{
    /**
     * 查找用户的宝宝大事件
     * @param userId
     * @param pageable
     * @return
     */
    Page<BabyEvent> findByUserId(String userId, Pageable pageable);
}
