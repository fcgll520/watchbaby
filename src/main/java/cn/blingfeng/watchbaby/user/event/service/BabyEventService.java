package cn.blingfeng.watchbaby.user.event.service;

import cn.blingfeng.watchbaby.common.utils.dto.BabyEventDTO;
import cn.blingfeng.watchbaby.dataobject.BabyEvent;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author : blingfeng
 * @date : 2017/12/08
 * @description
 **/
public interface BabyEventService {
    /**
     * 宝宝事件列表
     * @param userId
     * @param pageable
     * @return
     */
    Page<BabyEventDTO> babyEventList(String userId, Pageable pageable);

    /**
     * 增加宝宝大事件
     * @param babyEvent
     * @return
     */
    BabyEvent addBabyEvent(BabyEvent babyEvent);

    /**
     * 删除宝宝大事件
     * @param userId
     * @param eventId
     */
    void deleteBabyEvent(String userId,String eventId);

    /**
     * 更新宝宝大事件
     * @param babyEvent
     * @return
     */
    BabyEvent updateBabyEvent(BabyEvent babyEvent);
}
